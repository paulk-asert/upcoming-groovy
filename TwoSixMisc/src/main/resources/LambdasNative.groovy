import groovy.transform.CompileStatic
import static java.util.stream.Collectors.toList
import java.util.stream.Stream

class Main1 {
    @CompileStatic
    static void doit() {
        assert [2] == Stream.of(1).map( e -> e.plus 1 ).collect(toList())
    }
}

class Main2 {
    static void doit() {
        assert [2] == Stream.of(1).map( e -> e.plus 1 ).collect(toList())
    }
}

Main1.doit()
Main2.doit()
