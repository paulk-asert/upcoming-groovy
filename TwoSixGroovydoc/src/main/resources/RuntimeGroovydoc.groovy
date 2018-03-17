class Foo {
    /** @Groovydoc fo fum */
    def bar() { }
    @Groovydoc('Hard-coded')
    def baz() { }
}

def getDoc(String name) {
    Foo.methods.find{ it.name == name }.getAnnotation(Groovydoc)
}
assert getDoc('bar').value().contains('@Groovydoc fo fum')
assert getDoc('baz').value().contains('Hard-coded')
