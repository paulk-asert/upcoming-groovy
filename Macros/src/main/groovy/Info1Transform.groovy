import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotatedNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.Parameter
import org.codehaus.groovy.ast.expr.ArgumentListExpression
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
class Info1Transform extends AbstractASTTransformation {

    void visit(ASTNode[] nodes, SourceUnit source) {
        init(nodes, source)
        AnnotatedNode parent = (AnnotatedNode) nodes[1]

        if (parent instanceof ClassNode) {
            ClassNode classNode = (ClassNode) parent
            def clazz = new MethodCallExpression(new VariableExpression("this"), "getClass", EMPTY_ARGUMENTS)
            def body = new ExpressionStatement(new MethodCallExpression(clazz, "getName", EMPTY_ARGUMENTS))
            classNode.addMethod('getInfo', ACC_PUBLIC, STRING_TYPE, EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, body)
        }
    }
}
