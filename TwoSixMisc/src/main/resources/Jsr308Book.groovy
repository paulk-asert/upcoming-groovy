import java.lang.annotation.Retention
import java.lang.annotation.Target
import java.lang.reflect.AnnotatedType
import java.lang.reflect.Type

import static java.lang.annotation.ElementType.*
import static java.lang.annotation.RetentionPolicy.RUNTIME

@Target([PARAMETER, FIELD, METHOD, ANNOTATION_TYPE, TYPE_USE, LOCAL_VARIABLE])
@Retention(RUNTIME)
@interface Large{}

class Book extends @Large ArrayList<@Large String> {}

AnnotatedType annotatedType = Book.annotatedSuperclass
Type type = annotatedType.type
println type.typeName
if (annotatedType.annotations)
    println annotatedType.annotations[0]
