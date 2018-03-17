import groovy.transform.*
import static groovy.transform.options.Visibility.PRIVATE

@TupleConstructor
@VisibilityOptions(PRIVATE)
class Person {
    String name
    static makePerson(String first, String last) {
        new Person("$first $last")
    }
}

assert 'John Smith' == Person.makePerson('John', 'Smith').name
def publicCons = Person.constructors
assert publicCons.size() == 0
