import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.expr.VariableExpression
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import static org.codehaus.groovy.ast.ClassHelper.STRING_TYPE
import static org.codehaus.groovy.ast.Parameter.EMPTY_ARRAY
import static org.codehaus.groovy.ast.expr.ArgumentListExpression.EMPTY_ARGUMENTS

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
class Info2Transform extends AbstractASTTransformation {

    void visit(ASTNode[] nodes, SourceUnit source) {
        init(nodes, source)
        AnnotatedNode parent = (AnnotatedNode) nodes[1]

        if (parent instanceof ClassNode) {
            ClassNode classNode = (ClassNode) parent
            def body = macro(true) { getClass().name }
            classNode.addMethod('getInfo', ACC_PUBLIC, STRING_TYPE, EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, body)
        }
    }
}
