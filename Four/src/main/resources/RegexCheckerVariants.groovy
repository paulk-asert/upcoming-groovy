import groovy.transform.TypeChecked
import org.codehaus.groovy.control.CompilationFailedException
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.builder.CompilerCustomizationBuilder
import java.util.regex.Matcher
import java.util.regex.Pattern
import static groovy.test.GroovyAssert.shouldFail

def cases = $/
~/\w{3/                   // missing closing repetition quantifier brace
~"(.)o(.*"                // missing closing group bracket
Pattern.compile(/?/)      // dangling meta character '?' (Java longhand)

'foobar'  =~ /f[o]{2/          // missing closing repetition quantifier brace
'foobar' ==~ /(foo/            // missing closing group bracket
Pattern.matches(/?/, 'foo')    // dangling meta character '?' (Java longhand)
/$

def config = new CompilerConfiguration()
CompilerCustomizationBuilder.withConfig(config) {
    ast(TypeChecked, extensions: 'groovy.typecheckers.RegexChecker')
    imports(Pattern, Matcher)
}
def shell = new GroovyShell(config)
cases.readLines().grep().each { line ->
    println shouldFail(CompilationFailedException) {
        shell.evaluate(line)
    }.message
}

println shouldFail(CompilationFailedException) {
    shell.evaluate($/
def m = 'foobar' =~ /(...)(...)/
assert m[0][1] == 'foo'             // okay
assert m[0][3]                      // type error: only two groups in regex
/$)
}.message

println shouldFail(CompilationFailedException) {
    shell.evaluate($/
Pattern p = Pattern.compile('(...)(...)')
Matcher m = p.matcher('foobar')
assert m.find()
assert m.group(1) == 'foo'          // okay
assert m.group(3)                   // type error: only two groups in regex
/$)
}.message
