import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.macro.transform.MacroClass

import static org.codehaus.groovy.ast.tools.GeneralUtils.constX

ClassNode buildTemplateClass(ClassNode reference) {
    def methodCount = constX(reference.methods.size())
    def fieldCount = constX(reference.fields.size())

    return new MacroClass() {
        class Statistics {
            java.lang.Integer getMethodCount() {
                return $v { methodCount }
            }

            java.lang.Integer getFieldCount() {
                return $v { fieldCount }
            }
        }
    }
}

@interface Statistics{}

@Statistics
class Person {
    Integer age
    String name
}

def person = new Person(age: 12, name: 'john')

assert person.methodCount == 0
assert person.fieldCount  == 2
