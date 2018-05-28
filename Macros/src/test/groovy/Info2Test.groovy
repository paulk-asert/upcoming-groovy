@Info2
class Info2Test extends GroovyTestCase {
    void testHasGetInfoMethod() {
        assert getInfo() == 'Info2Test'
    }
}
