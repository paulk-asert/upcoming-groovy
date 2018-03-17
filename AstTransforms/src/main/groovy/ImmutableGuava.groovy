import groovy.transform.Immutable
import paulk.transform.construction.GuavaImmutablePropertyHandler
@Immutable(propertyHandler=GuavaImmutablePropertyHandler)
class Person {
    List names = ['John', 'Smith']
    List books = ['GinA', 'ReGinA']
}

['names', 'books'].each {
    println new Person()."$it".dump()
}
//<com.google.common.collect.RegularImmutableList@90b9bd9 array=[John, Smith]>
//<com.google.common.collect.RegularImmutableList@95b86f34 array=[GinA, ReGinA]>
