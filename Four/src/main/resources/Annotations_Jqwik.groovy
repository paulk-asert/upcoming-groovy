//@Grab('net.jqwik:jqwik:1.6.5')
import net.jqwik.api.*

class Annotations_Jqwik {
    @Property
    void 'size of concatenated string is greater than or equal to size of each'(
            @ForAll String s1, @ForAll String s2
    ) {
        String conc = s1 + s2
        assert conc.size() >= s1.size()
        assert conc.size() >= s2.size()
    }

    @Property
    void 'size of concatenated string is sum of size of each'(
            @ForAll String s1, @ForAll String s2
    ) {
        String conc = s1 + s2
        assert conc.size() == s1.size() + s2.size()
    }
}
