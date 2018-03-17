import groovy.transform.AutoImplement

import java.lang.reflect.Modifier

@AutoImplement
class MyClass extends AbstractList<String> implements Closeable, Iterator<String> {
/* */
    String get(int param0) {
        return null
    }

    String next() {
        return null
    }

    boolean hasNext() {
        return false
    }

    void close() throws Exception {
    }

    int size() {
        return 0
    }
    /* */
}

println System.getProperty('java.specification.version')


def testEmptyIterator(Iterator it) {
    assert it.toList() == []
}

def emptyIterator = [hasNext: {false}] as Iterator
testEmptyIterator(emptyIterator)
assert emptyIterator.class.name.contains('Proxy')
//println emptyIterator.dump()
//println emptyIterator.next()

def myClass = new MyClass()
testEmptyIterator(myClass)
assert myClass instanceof MyClass

assert Modifier.isAbstract(Iterator.getDeclaredMethod('hasNext').modifiers)
assert !Modifier.isAbstract(MyClass.getDeclaredMethod('hasNext').modifiers)
//println myClass.dump()

@AutoImplement(exception = UncheckedIOException)
class MyWriter extends Writer { }

@AutoImplement(exception = UnsupportedOperationException,
        message = 'Not supported by MyIterator')
class MyIterator implements Iterator<String> { }

@AutoImplement(code = { throw new UnsupportedOperationException(
        'Should never be called but was called on ' + new Date()) })
class EmptyIterator implements Iterator<String> {
    boolean hasNext() { false }
}

println new MyWriter()
println new MyIterator()
println new EmptyIterator()//.next()

//@AutoImplement(code = { 42 })
//class DummyIterator implements Comparator { }

//assert 42 == new DummyIterator().compare('foo', 'bar')
//assert 42 == new DummyIterator().compare('bar', 'foo')

//@AutoImplement(code = { 42 })
//class DummyIterator2 implements Iterator { }
