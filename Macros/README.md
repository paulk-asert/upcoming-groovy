== Macro support

Groovy is well known for its extensible compilation process.
The compiler supports a compile-time metaprogramming extension mechanism
which allows the compilation process to be enhanced with new functionality.
The mechanism allows the definition and execution of AST transformations at
various stages of the compilation process.

Groovy comes with a number of built-in AST transformations and, importantly,
you can write your own. To write such transformations until now, you have
had to become familiar with the compiler's internal representation of
Groovy syntactic structures. But that has now changed.

Groovy 2.5 supports macros. Macros let you use Groovy syntax directly
rather than using the internal compiler representations when creating
compile-time metaprogramming extensions. This puts the creation of transformations
in the hands of all Groovy programmers not just Groovy compiler gurus.

=== Expressions and statements

Suppose we want to create an `@Info` transform which when placed on a class
creates a `getInfo()` method. Calling the `getInfo()` method will display some
information about the class. The traditional way to write the transformation
code might be something like:
[source,groovy]
----
...
def clazz = new MethodCallExpression(new VariableExpression("this"), "getClass", EMPTY_ARGUMENTS)
def body = new ExpressionStatement(new MethodCallExpression(clazz, "getName", EMPTY_ARGUMENTS))
classNode.addMethod('getInfo', ACC_PUBLIC, STRING_TYPE, EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, body)
...
----

This will print out the class name when the `getInfo()` method is called.

With macros, the first two lines become:

[source,groovy]
----
def body = macro(true) { getClass().name }
----

Creation of both expressions and statements are supported. The `true` parameter
to the `macro` method ensures we get a statement here which is what `addMethod` needs.

Frequently you will want to receive parameters or resolve surrounding variables.
This is supported using a special `$v` notation. Suppose we want to augment the `getInfo`
method to additionally output the time the class was compiled and the Groovy version used.
Our code might look like this:

[source,groovy]
----
def date = new ConstantExpression(new Date().toString())
def version = new ConstantExpression(GroovySystem.version)
def body = macro(true) {
    """\
    Name: ${getClass().name}
    Compiled: ${$v{ date }}
    Using Groovy version: ${$v{ version }}""".stripIndent()
}
----

What the macro does is convert any Groovy code inside the closure
argument into its internal representation. When it gets to the
`$v` placeholders it does no conversion and just inserts
those sections directly. This is exactly what we want here since
we wrote those parts old-school style using the internal
AST data structures.

=== Macro classes

In addition to the ability to create statements and expressions, as supported by `macro`,
you might want to create whole classes. The `MacroClass` capability allows you to do that.
You supply the class you are wanting to create in a similar fashion to creating an anonymous
inner class in Java. The following example shows the approach for a scenario where you
might want to extract the information collected in previous examples in a class of its own:

[source,groovy]
----
ClassNode buildInfoClass(ClassNode reference) {
    def date = new ConstantExpression(new Date().toString())
    def vers = new ConstantExpression(GroovySystem.version)
    def name = new ClassExpression(reference)
    ClassNode infoClass = new MacroClass() {
        class DummyName {
            java.lang.String getName() { $v{ name }.name }
            java.lang.String getVersion() { $v{ vers } }
            java.lang.String getCompiled() { $v{ date } }
        }
    }
    infoClass.name = reference.name + 'Info'
    return infoClass
}
----

NOTE: Types inside the `MacroClass` implementation should be resolved inside, that's why we had to write
`java.lang.String` instead of simply writing `String`. See the documentation for further details.

The result of all this is that if we annotate a `Foo` class with `@Info`,
then it is as if we also typed the following source code in as well:

[source,groovy]
----
class FooInfo {
    String getName() { 'Foo' }
    String getVersion() { '2.5.0' }
    String getCompiled() { 'Mon May 28 02:09:35 AEST 2018' }
}
----

=== AST matching

AST transforms typically add additional ASTNodes into the AST tree
or change some existing subtrees of nodes with a transformed subtree.
Macros are great for allowing us to succinctly describe the added or
transformed subtree of nodes but don't offer us anything for identifying
the subtree which might be the candidate to be replaced.
This is where `ASTMatcher` comes to the rescue. `ASTMatcher` compares
two expressions and each expression can be created with or without macros.

Here's a trivial example of a transformer that looks for binary expressions
of the form `1 + 1` in our code and if it finds it, replaces it with the constant `2`:

[source,groovy]
----
Expression transform(Expression exp) {
    Expression pattern = macro { 1 + 1 }
    if (ASTMatcher.matches(exp, pattern)) {
        return macro { 2 }
    }
    return super.transform(exp)
}
----

This happens at compile time, so speeds up our code at runtime.

We can generalise this a bit more since `ASTMatcher` supports constraints:

[source,groovy]
----
Expression pattern = macro { a + b }.withConstraints {
    placeholder a
    placeholder b
    anyToken()
}
----

The `placeholder` constraint allows any term to appear
and the `anyToken` allows `'-'`, `'*'`, `'/'`, `'<'` and other tokens to replace the `'+'`.

We can make our transform a bit smarter too as follows:

[source,groovy]
----
Expression transform(Expression exp) {
    if (ASTMatcher.matches(exp, pattern)) {
        BinaryExpression be = exp
        Expression lhs = be.leftExpression
        Expression rhs = be.rightExpression
        if (lhs instanceof ConstantExpression && rhs instanceof ConstantExpression) {
            def left = lhs.value
            def right = rhs.value
            if ((left instanceof String && right instanceof String)||
                    (left instanceof Integer && right instanceof Integer)) {
                def op = be.operation.text
                left = left instanceof String ? "'" + left + "'" : left
                right = right instanceof String ? "'" + right + "'" : right
                def result = new GroovyShell().evaluate "$left $op $right"
                return constX(result)
            }
        }
    }
    return exp.transformExpression(this)
}
----

Now we can use our transform, e.g.:

[source,groovy]
----
@SmartOps
class Foo {
    int theAnswer = 40 + 2
    int eight = 4 * 2
    def foobar = 'foo' + 'bar'
    def test() {
        assert 3 < 4
        assert foobar.size() == 6
        assert theAnswer + eight == 25 + 25
    }
}
----

If we look at this class at the end of the SEMANTIC_ANALYSIS phase in the AST
browser, we'll indeed see that the initial value expression for `theAnswer`, `eight` and `foobar`
are binary expressions as is the expression for the first assert and the right-hand side of the `==`
expression for the third assert`. If we move forward to the end of the CANONICALIZATION phase
we'll see that all 5 of those binary expressions are now constant expressions. It is as if we had typed
our source code in as:

[source,groovy]
----
class Foo {
    int theAnswer = 42
    int eight = 8
    def foobar = 'foobar'
    def test() {
        assert true
        assert foobar.size() == 6
        assert theAnswer + eight == 50
    }
}
----

=== Macro methods

The compiler capability to _expand_ macros with their replacements can also be enhanced by your own methods.
Consider the following definition:

[source,groovy]
----
class StringMacroMethods {
    @Macro
    static Expression upper(MacroContext macroContext, ConstantExpression constX) {
        if (constX.value instanceof String) {
            return new ConstantExpression(constX.value.toUpperCase())
        }
        macroContext.sourceUnit.addError(new SyntaxException("Can't use upper with non-String", constX))
    }
}
----

If you register the method in the same way as you would with extension methods (by creating
a reference to the class in a `META-INF/groovy/org.codehaus.groovy.runtime.ExtensionMethods` file).

Now, assuming the META-INF file and class are on your classpath, you can use the `upper` method in
your code such as shown in the following test code:

[source,groovy]
----
assertScript '''
    def foo = upper('Foo')
    assert foo == 'FOO'
'''
def msg = shouldFail '''
    def foo = upper(42)
'''
assert msg.contains("Can't use upper with non-String")
----

It's important to realise that use of `upper` doesn't cause a call to `toUpperCase`
 to be embedded in the bytecode but rather causes `toUpperCase` to be called
at compile time.
