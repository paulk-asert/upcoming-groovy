import groovy.transform.*
import groovy.transform.options.*

@ToString(includeSuperProperties = true, cache = true)
@EqualsAndHashCode(cache = true)
@ImmutableBase
@Final
@ImmutableOptions
@PropertyOptions(propertyHandler = ImmutablePropertyHandler)
@TupleConstructor(defaults = false)
@MapConstructor(noArg = true, includeSuperProperties = true, includeFields = true)
@KnownImmutable
class Point2D {
    int x, y
}

assert new Point2D(10, 20).toString() == 'Point2D(10, 20)'
