@Info1
class Info1Test extends GroovyTestCase {
    void testHasGetInfoMethod() {
        assert getInfo() == 'Info1Test'
    }
}