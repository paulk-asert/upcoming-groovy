import groovy.transform.AnnotationCollector
import groovy.transform.AutoFinal
import groovy.transform.EqualsAndHashCode
import groovy.transform.Sortable
import groovy.transform.ToString
import groovy.transform.TupleConstructor
import groovy.transform.builder.Builder

@AnnotationCollector
@ToString(excludes = 'id,first', includeNames = true, ignoreNulls = true)
@EqualsAndHashCode
@TupleConstructor(excludes = 'id,initial')
@TupleConstructor(includes = 'first,initial,last', defaults = false, force = true)
@Sortable(includes = 'first,last')
@AutoFinal
@Builder(excludes = 'id')
@interface CustomCanonical {}
