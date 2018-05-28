import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.macro.runtime.Macro
import org.codehaus.groovy.macro.runtime.MacroContext
import org.codehaus.groovy.syntax.SyntaxException

class StringMacroMethods {

    @Macro
    static Expression upper(MacroContext macroContext, ConstantExpression constX) {
        if (constX.value instanceof String) {
            return new ConstantExpression(constX.value.toUpperCase())
        }
        macroContext.sourceUnit.addError(new SyntaxException("Can't use upper with non-String", constX))
    }
}
