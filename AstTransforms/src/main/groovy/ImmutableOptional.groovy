import groovy.transform.Immutable

import java.awt.Point

@Immutable
class Entertainer {
    String first
    Optional<String> last
}

println new Entertainer('Sonny', Optional.of('Bono'))
println new Entertainer('Cher', Optional.empty())

@Immutable
class Template {
//    Optional<Point> origin
}
