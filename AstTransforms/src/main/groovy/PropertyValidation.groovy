import static groovy.test.GroovyAssert.shouldFail

def error = shouldFail {
    new GroovyShell().parse '''
    import groovy.transform.ToString

    @ToString(excludes = 'first')
    class Cyclist {
        String firstName, lastName
    }
    println new Cyclist(firstName: 'Cadel', lastName: 'Evans')
    '''
}
println error.message
/*
import groovy.transform.ToString

@ToString(excludes = 'first')
class Cyclist {
    String firstName, lastName
}
println new Cyclist(firstName: 'Cadel', lastName: 'Evans')
/* */
