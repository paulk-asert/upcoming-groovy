@Info3
class Info3Test extends GroovyTestCase {
    void testHasGetInfoMethod() {
        println info
        assert info.contains('Info3Test')
    }
}
