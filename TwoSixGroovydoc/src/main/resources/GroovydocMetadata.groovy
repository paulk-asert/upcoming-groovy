import org.codehaus.groovy.control.*

//import static groovy.lang.groovydoc.GroovydocHolder.DOC_COMMENT

// set these first
//System.setProperty('groovy.attach.groovydoc', 'true')
//System.setProperty('groovy.attach.runtime.groovydoc', 'true')

def ast = new CompilationUnit().tap {
    addSource 'myScript.groovy', '''
        /** class doco */
        class MyClass {
            /** method doco */
            def myMethod() {}
        }
    '''
    compile Phases.SEMANTIC_ANALYSIS
}.ast

def classDoc = ast.classes[0].groovydoc
assert classDoc.content.contains('class doco')
def methodDoc = ast.classes[0].methods[0].groovydoc
assert methodDoc.content.contains('method doco')
