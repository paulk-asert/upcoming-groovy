import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.runtime.InvokerHelper

@interface Macro{}
interface MacroContext{}

@Macro
static Expression nullSafe(MacroContext macroContext, Expression expression) {
    if (expression instanceof PropertyExpression) {
        // exp.prop -> exp?.prop
        PropertyExpression propertyExpression = (PropertyExpression) expression;
        InvokerHelper.setProperty(propertyExpression, "safe", true);
        nullSafe(macroContext, propertyExpression.getObjectExpression());
    } else if (expression instanceof MethodCallExpression) {
        // exp.method() -> exp?.method()
        MethodCallExpression methodCallExpression = (MethodCallExpression) expression;
        methodCallExpression.setSafe(true);
        ArgumentListExpression arguments = (ArgumentListExpression) methodCallExpression.getArguments();
        // method's arguments
        for (Expression argument : arguments.getExpressions()) {
            nullSafe(macroContext, argument);
        }
        nullSafe(macroContext, methodCallExpression.getObjectExpression());
    }
    return expression;
}
