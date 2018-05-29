class StringMacroMethodsTest extends GroovyTestCase {
    void testUpper() {
        assertScript '''
            def foo = upper('Foo')
            assert foo == 'FOO'
        '''
        def msg = shouldFail '''
            def foo = upper(42)
        '''
        assert msg.contains("Can't use upper with non-String")
    }
}
