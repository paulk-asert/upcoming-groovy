import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        AnnotatedType annotatedType = Book.class.getAnnotatedSuperclass();
        Type type = annotatedType.getType();
        System.out.println(type.getTypeName());
        System.out.println(annotatedType.getAnnotations()[0]);
//        System.out.println(Stream.of(1, 2).map((Integer x) -> "42").collect(Collectors.joining(",")));
    }
}
