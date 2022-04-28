import groovy.transform.*
import groovy.transform.options.*
import static groovy.test.GroovyAssert.shouldFail

@ToString
@PropertyOptions(propertyHandler = ImmutablePropertyHandler)
record Agenda(List topics) { }

def a = new Agenda(topics: ['Sealed', 'Records'])
assert a.topics().size() == 2
shouldFail(UnsupportedOperationException) {
    a.topics().clear()
}
assert a.toString() == 'Agenda([Sealed, Records])'
