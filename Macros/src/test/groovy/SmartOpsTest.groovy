class SmartOpsTest extends GroovyTestCase {
    void testSmartOpsInFoo() {
        assertScript '''
        @SmartOps
        class Foo {
            int theAnswer = 40 + 2
            int eight = 4 * 2
            def foobar = 'foo' + 'bar'
            def test() {
                assert foobar.size() == 6
                assert theAnswer + eight == 50
            }            
        }
        new Foo().test()
        '''
    }
}
