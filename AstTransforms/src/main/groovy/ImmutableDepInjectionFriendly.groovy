import groovy.transform.*
import groovy.transform.options.*

@ImmutableBase
@PropertyOptions(propertyHandler = ImmutablePropertyHandler)
@Canonical(defaults=false)
class Shopper {
    String first, last
    Date born
    List items
}

println new Shopper('John', 'Smith', new Date(), [])
