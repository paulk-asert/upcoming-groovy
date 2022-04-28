import groovy.transform.*
import groovy.transform.stc.POJO
import static groovy.transform.DefaultsMode.AUTO

@RecordBase
// RecordBase makes implicit changes equivalent to native
// toString(), equals() and hashCode() record implements or:
// @ToString(cache = true, includeNames = true, includePackage = false,
//           leftDelimiter = '[', rightDelimiter = ']',
//           nameValueSeparator = '=', fieldSeparator = ", ")
// @EqualsAndHashCode(useCanEqual = false)
@RecordOptions
@TupleConstructor(namedVariant = true, force = true, defaultsMode = AUTO)
@PropertyOptions
@KnownImmutable
@POJO
@CompileStatic
class Point2D {
    int x, y
}

assert new Point2D(10, 20).toString() == 'Point2D[x=10, y=20]'
