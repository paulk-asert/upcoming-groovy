import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotatedNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import static org.codehaus.groovy.ast.ClassHelper.STRING_TYPE
import static org.codehaus.groovy.ast.Parameter.EMPTY_ARRAY

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
class Info3Transform extends AbstractASTTransformation {

    void visit(ASTNode[] nodes, SourceUnit source) {
        init(nodes, source)
        AnnotatedNode parent = (AnnotatedNode) nodes[1]

        if (parent instanceof ClassNode) {
            ClassNode classNode = (ClassNode) parent
            def date = new ConstantExpression(new Date().toString())
            def version = new ConstantExpression(GroovySystem.version)
            def body = macro(true) {
                """\
                Name: ${getClass().name}
                Compiled: ${$v{ date }}
                Using Groovy version: ${$v{ version }}""".stripIndent()
            }
            classNode.addMethod('getInfo', ACC_PUBLIC, STRING_TYPE, EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, body)
        }
    }
}
