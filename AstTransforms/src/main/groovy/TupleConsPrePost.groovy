import groovy.transform.ToString
import groovy.transform.TupleConstructor

import static groovy.test.GroovyAssert.shouldFail

@ToString
@TupleConstructor(
        pre = { first = first?.toLowerCase(); assert last },
        post = { this.last = first?.toUpperCase() }
)
class Actor {
    String first, last
}

assert new Actor('Johnny', 'Depp').toString() == 'Actor(johnny, JOHNNY)'
shouldFail(AssertionError) {
    println new Actor('Johnny')
}
