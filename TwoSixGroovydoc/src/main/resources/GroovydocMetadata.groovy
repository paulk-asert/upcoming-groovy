import org.codehaus.groovy.control.*

import static groovy.lang.groovydoc.GroovydocHolder.DOC_COMMENT

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
def getDoc(node) { node.nodeMetaData[DOC_COMMENT].content }
def myClass = ast.classes[0]
def myClassDoc = getDoc(myClass)
def myMethodDoc = getDoc(myClass.methods[0])
assert myClassDoc.contains('class doco')
assert myMethodDoc.contains('method doco')
