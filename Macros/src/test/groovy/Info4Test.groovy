@Info4
class Info4Test extends GroovyTestCase {
    void testHasGetInfoMethod() {
        assertScript '''
        def info = new Info4TestInfo()
        assert info.name == 'Info4Test'
        assert info.version.startsWith('2.5')
        assert info.compiled.contains('2018')
        println info
        '''
    }
}
