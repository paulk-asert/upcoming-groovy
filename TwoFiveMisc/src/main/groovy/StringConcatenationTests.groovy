import net.jqwik.api.*

class StringConcatenationTests {
    @Property
    void 'size of concatenated string is sum of size of each'(
            @ForAll String s1, @ForAll String s2
    ) {
        String conc = s1 + s2
        assert conc.size() == s1.size() + s2.size()
    }
}
