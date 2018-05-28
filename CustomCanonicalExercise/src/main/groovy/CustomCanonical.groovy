import groovy.transform.AnnotationCollector
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor

@AnnotationCollector
@ToString
@EqualsAndHashCode
@TupleConstructor
@interface CustomCanonical {}
