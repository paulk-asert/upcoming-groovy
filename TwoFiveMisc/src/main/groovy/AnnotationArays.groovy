import java.lang.annotation.*

class MyClass {
    private static final expected = '@MyAnnotationArray(value=[@MyAnnotation(value=val1), @MyAnnotation(value=val2)])'

    // control
    @MyAnnotationArray([@MyAnnotation("val1"), @MyAnnotation("val2")])
    String method1() { 'method1' }

    // duplicate candidate for auto collection
    @MyAnnotation(value = "val1")
    @MyAnnotation(value = "val2")
    String method2() { 'method2' }

    static void main(String... args) {
        MyClass myc = new MyClass()
        assert 'method1' == myc.method1()
        assert 'method2' == myc.method2()
        assert checkAnnos(myc, "method1") == expected
        assert checkAnnos(myc, "method2") == expected
    }

    private static String checkAnnos(MyClass myc, String name) {
        def m = myc.getClass().getMethod(name)
        List annos = m.getAnnotations()
        assert annos.size() == 1
        annos[0].toString()
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyAnnotationArray)
@interface MyAnnotation {
    String value() default "val0"
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotationArray {
    MyAnnotation[] value()
}
