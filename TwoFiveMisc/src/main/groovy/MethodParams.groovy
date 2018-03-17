import org.codehaus.groovy.control.CompilerConfiguration

def fooClass = new GroovyClassLoader().parseClass('''
class Foo {
  def foo(String one, Integer two, Date three) {}
}
''')
println fooClass.methods.find{ it.name == 'foo' }.parameters

def cc = new CompilerConfiguration(parameters: true)
def barClass = new GroovyClassLoader(getClass().classLoader, cc).parseClass('''
class Bar {
  def bar(String one, Integer two, Date three) {}
}
''')
println barClass.methods.find{ it.name == 'bar' }.parameters
//[java.lang.String arg0, java.lang.Integer arg1, java.util.Date arg2]
//[java.lang.String one, java.lang.Integer two, java.util.Date three]
