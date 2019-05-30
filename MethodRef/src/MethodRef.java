import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.junit.platform.commons.function.Try;
import org.junit.platform.engine.support.hierarchical.SingleTestExecutor;
import org.junit.platform.engine.support.hierarchical.ThrowableCollector;

import java.nio.file.DirectoryStream;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class MethodRef {
    public static void main(String[] args) throws Exception {
        Function<String, String> low1 = String::toLowerCase;
        BiFunction<String, Locale, String> low2 = String::toLowerCase;
        Try.Transformer<String, String> low3 = String::toLowerCase;

        Supplier<String> low4 = "Low4"::toLowerCase;
        System.out.println(low4.get());
        Try.Transformer<Locale, String> low5 = ""::toLowerCase;
        Callable<String> low6 = "Low6"::toLowerCase;
        System.out.println(low6.call());
        Function<Locale, String> low7 = "Low7"::toLowerCase;
        System.out.println(low7.apply(Locale.FRENCH));
        ThrowingSupplier<String> low8 = ""::toLowerCase;
        Runnable low9 = "Low9"::toLowerCase;
        low9.run();
        Executable low10 = "Low10"::toLowerCase;
        ThrowableCollector.Executable low11 = "Low11"::toLowerCase;
        SingleTestExecutor.Executable low12 = "Low12"::toLowerCase;

        // JDK12
        Function<String, String> upper = String::toUpperCase;
        System.out.println("Low13".transform(low1.andThen(upper)));
        System.out.println("Low14".transform(low1.compose(upper)));
        System.out.println((String)"Low15".transform(String::toLowerCase));
        System.out.println((String)"Low16".transform(s -> s.toLowerCase()));

        Predicate<String> empty1 = String::isEmpty;
        DirectoryStream.Filter<String> empty2 = String::isEmpty;
        Try.Transformer<String, Boolean> empty3 = String::isEmpty;
        Function<String, Boolean> empty4 = String::isEmpty;
        Runnable empty5 = ""::isEmpty;
    }
}
