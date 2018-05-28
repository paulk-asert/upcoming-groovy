import groovy.transform.ToString
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotatedNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.expr.ClassExpression
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.macro.transform.MacroClass
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.codehaus.groovy.transform.ToStringASTTransformation

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
class Info4Transform extends AbstractASTTransformation {

    void visit(ASTNode[] nodes, SourceUnit source) {
        init(nodes, source)
        AnnotatedNode parent = (AnnotatedNode) nodes[1]

        if (parent instanceof ClassNode) {
            ClassNode classNode = (ClassNode) parent
            ClassNode infoClass = buildInfoClass(classNode)
            classNode.getModule().addClass(infoClass)
        }
    }

    ClassNode buildInfoClass(ClassNode reference) {
        def date = new ConstantExpression(new Date().toString())
        def vers = new ConstantExpression(GroovySystem.version)
        def name = new ClassExpression(reference)
        ClassNode infoClass = new MacroClass() {
            class DummyName {
                java.lang.String getName() { $v{ name }.name }
                java.lang.String getVersion() { $v{ vers } }
                java.lang.String getCompiled() { $v{ date } }
            }
        }
        infoClass.name = reference.name + 'Info'
        return infoClass
    }
}
