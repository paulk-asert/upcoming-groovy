class Foo {
    /** @Groovydoc fo fum */
    def bar() { }
    @Groovydoc('Hard-coded')
    def baz() { }
}

def docForMethod(String name) {
//    Foo.methods.find{ it.name == name }.getAnnotation(Groovydoc).value()
    Foo.methods.find{ it.name == name }.groovydoc.content
}
assert docForMethod('bar').contains('@Groovydoc fo fum')
assert docForMethod('baz').contains('Hard-coded')
