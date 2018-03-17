import java.lang.annotation.Retention
import java.lang.annotation.Target
import java.lang.reflect.TypeVariable

import static java.lang.annotation.ElementType.ANNOTATION_TYPE
import static java.lang.annotation.ElementType.FIELD
import static java.lang.annotation.ElementType.LOCAL_VARIABLE
import static java.lang.annotation.ElementType.METHOD
import static java.lang.annotation.ElementType.PARAMETER
import static java.lang.annotation.ElementType.TYPE_USE
import static java.lang.annotation.RetentionPolicy.RUNTIME

@Target([PARAMETER, FIELD, METHOD, ANNOTATION_TYPE, TYPE_USE, LOCAL_VARIABLE])
@Retention(RUNTIME)
@interface NonNull{}

@Target([PARAMETER, FIELD, METHOD, ANNOTATION_TYPE, TYPE_USE, LOCAL_VARIABLE])
@Retention(RUNTIME)
@interface NonEmpty{}

@Target([PARAMETER, FIELD, METHOD, ANNOTATION_TYPE, TYPE_USE, LOCAL_VARIABLE])
@Retention(RUNTIME)
@interface ReadOnly{}

@Target([PARAMETER, FIELD, METHOD, ANNOTATION_TYPE, TYPE_USE, LOCAL_VARIABLE])
@Retention(RUNTIME)
@interface Existing{}

@Target([PARAMETER, FIELD, METHOD, ANNOTATION_TYPE, TYPE_USE, LOCAL_VARIABLE])
@Retention(RUNTIME)
@interface NonNegative{}

class MyList extends @NonEmpty ArrayList<@NonNegative Integer> implements @NonNull Closeable {
    void close() throws IOException {}
}
assert 'java.util.ArrayList<java.lang.Integer>' == MyList.annotatedSuperclass.type.typeName
//assert ['NonEmpty'] == MyList.annotatedSuperclass.annotations*.name
assert ['java.io.Closeable', 'groovy.lang.GroovyObject'] == MyList.annotatedInterfaces*.type*.typeName

@NonEmpty List<@NonNull @ReadOnly String> myList

@ReadOnly Object x
if (x instanceof Date) { /*...*/ }            // error: incompatible annotations
if (x instanceof @ReadOnly Date) { /*...*/ }  // OK

Object y
if (y instanceof Date) { /*...*/ }                // OK
if (y instanceof @NonNull Date) { /*...*/ }   // error: incompatible annotations

@NonNull Object z = new @NonNull Date()
@NonNull Date d = (@NonNull Date) z

$/ /$
class Outer extends @ReadOnly HashMap<@NonNull String, @NonEmpty List<@ReadOnly File>> {
    class Middle {
        class Inner {
//            void innerMethod(@NonEmpty Outer.@NonNull Middle.@ReadOnly Inner this) { ... }
            void innerMethod(@NonEmpty Outer.Middle.Inner this) { /*...*/ }
        }
    }
    static class Fee {
        static class Fi {
            static class Fo {}
        }
    }
}
class Folder<F extends @Existing File> { /*...*/ }
Collection<? super @Existing File> files

@Existing String @NonNull [] @NonEmpty [] @ReadOnly [] items
//@Existing Outer.@NonEmpty Fee.@NonNull Fi.@ReadOnly Fo outer
TypeVariable tv = Folder.typeParameters[0]
assert [File] == tv.annotatedBounds.collect(e -> e.type)
