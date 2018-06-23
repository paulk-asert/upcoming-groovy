import org.junit.jupiter.api.*
// other imports not shown ...

class MyTest {
    @Test
    void streamSum() {
        assert Stream.of(1, 2, 3).mapToInt{ i -> i }.sum() > 5
    }

    @RepeatedTest(value=2, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    void streamSumRepeated() {
        assert Stream.of(1, 2, 3).mapToInt{i -> i}.sum() == 6
    }

    private boolean isPalindrome(s) { s == s.reverse()  }

    @ParameterizedTest // requires org.junit.jupiter:junit-jupiter-params
    @ValueSource(strings = [ "racecar", "radar", "able was I ere I saw elba" ])
    void palindromes(String candidate) {
        assert isPalindrome(candidate)
    }

    @TestFactory
    def dynamicTestCollection() {[
            dynamicTest("Add test") { -> assert 1 + 1 == 2 },
            dynamicTest("Multiply Test") { -> assert 2 * 3 == 6 }
    ]}
}