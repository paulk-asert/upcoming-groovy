import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotatedNode
import org.codehaus.groovy.ast.ClassCodeExpressionTransformer
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.expr.BinaryExpression
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.macro.matcher.ASTMatcher
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import static org.codehaus.groovy.ast.tools.GeneralUtils.constX

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
class SmartopsTransform extends AbstractASTTransformation {

    void visit(ASTNode[] nodes, SourceUnit source) {
        init(nodes, source)
        AnnotatedNode parent = (AnnotatedNode) nodes[1]

        if (parent instanceof ClassNode) {
            ClassNode classNode = (ClassNode) parent
            def transformer = makeSmartPlusTransformer()
            transformer.visitClass(classNode)
        }
    }

    Expression pattern = macro { a + b }.withConstraints {
        placeholder a
        placeholder b
        anyToken()
    }

    private ClassCodeExpressionTransformer makeSmartPlusTransformer() {
        return new ClassCodeExpressionTransformer() {
            @Override
            Expression transform(Expression exp) {
                if (ASTMatcher.matches(exp, pattern)) {
                    BinaryExpression be = exp
                    Expression lhs = be.leftExpression
                    Expression rhs = be.rightExpression
                    if (lhs instanceof ConstantExpression && rhs instanceof ConstantExpression) {
                        def left = lhs.value
                        def right = rhs.value
                        if ((left instanceof String && right instanceof String)||
                                (left instanceof Integer && right instanceof Integer)) {
                            def op = be.operation.text
                            left = left instanceof String ? "'" + left + "'" : left
                            right = right instanceof String ? "'" + right + "'" : right
                            def result = new GroovyShell().evaluate "$left $op $right"
                            return constX(result)
                        }
                    }
                }
                return exp.transformExpression(this)
            }

            @Override
            protected SourceUnit getSourceUnit() {
                return null
            }
        }
    }
}
