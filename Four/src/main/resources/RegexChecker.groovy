import groovy.transform.TypeChecked
import org.codehaus.groovy.control.CompilationFailedException
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer

import java.util.regex.PatternSyntaxException
import static groovy.test.GroovyAssert.shouldFail

shouldFail(PatternSyntaxException) {

def newYearsEve = '2020-12-31'
def matcher = newYearsEve =~ /(\d{4})-(\d{1,2})-(\d{1,2}/    // PatternSyntaxException

}

shouldFail(CompilationFailedException) {

new GroovyShell().evaluate($/
import groovy.transform.TypeChecked

@TypeChecked(extensions = 'groovy.typecheckers.RegexChecker')
def whenIs2020Over() {
    def newYearsEve = '2020-12-31'
    def matcher = newYearsEve =~ /(\d{4})-(\d{1,2})-(\d{1,2}/
}
/$)

}

def config = new CompilerConfiguration()
def tc = new ASTTransformationCustomizer(TypeChecked, extensions: 'groovy.typecheckers.RegexChecker')
config.addCompilationCustomizers(tc)
def shell = new GroovyShell(config)
shouldFail(CompilationFailedException) {

shell.evaluate($/
def newYearsEve = '2020-12-31'
def matcher = newYearsEve =~ /(\d{4})-(\d{1,2})-(\d{1,2}/
/$)

}
