import org.codehaus.groovy.control.CompilationFailedException

class CustomCanonicalTest extends GroovyTestCase {
    void testFirstLastConstructorButNoFirstInToString() {
        def captain = new Person('James', 'Kirk')
        assert captain.toString() == 'Person(last:Kirk)'
    }

    void testNullPropsNotInToString() {
        def james = new Person('James')
        assert james.toString() == 'Person()'
    }

    void testInitialInToString() {
        def captain = new Person('James', 'Kirk')
        captain.initial = 'T'
        assert captain.toString() == 'Person(last:Kirk, initial:T)'
    }

    void testSortable() {
        [new Person('Audrey', 'Hepburn'),
         new Person('Harrison', 'Ford')].with {
            assert toSorted(Person.comparatorByFirst())*.first == ['Audrey', 'Harrison']
            assert toSorted(Person.comparatorByLast())*.last == ['Ford', 'Hepburn']
        }
    }

    /*
     * uncomment for bonus extension 1
     * an advanced extension looking at builders
     */
//    void testHasBuilder() {
//        def captain = Person.builder().first('James').initial('T').last('Kirk').build()
//        assert captain.toString() == 'Person(last:Kirk, initial:T)'
//    }

    /*
     * uncomment for bonus extension 2
     * an advanced extension looking at additional constructors
     */
//    void testFirstInitialLastTupleConstructor() {
//        def captain = new Person('James', 'T', 'Kirk')
//        assert captain.toString() == 'Person(last:Kirk, initial:T)'
//    }

    /*
     * uncomment for bonus extension 3
     * an advanced extension looking at final parameters
     */
//    void testAutomaticFinalParams() {
//        def message = shouldNotCompile '''
//            @CustomCanonical
//            class PersonWithFullname {
//                String id, first, initial, last
//                def fullname(sep) { sep = null }
//            }
//        '''
//        assert message.contains("Cannot assign a value to final variable 'sep'")
//    }

    private shouldNotCompile(String script) {
        try {
            GroovyClassLoader gcl = new GroovyClassLoader()
            gcl.parseClass(script, getTestClassName())
        } catch (CompilationFailedException cfe) {
            return cfe.message
        }
        fail("the compilation succeeded but should have failed")
    }

}

@CustomCanonical
class Person {
    UUID id = UUID.randomUUID()
    String first, last
    String initial
}
