import groovy.contracts.*

import static groovy.test.GroovyAssert.shouldFail

abstract class Material {
    abstract String getColor()

    String toString() {
        "${getClass().name} with color ${color.toLowerCase()}"
    }
}

class Straw extends Material { String color = "Yellow" }
class Wood extends Material { String color = "Brown" }
class Brick extends Material { String color = "Red" }

def materials = [new Straw(), new Wood(), new Brick()]
assert materials*.toString() == ['Straw with color yellow',
        'Wood with color brown', 'Brick with color red']

class Glass extends Material {
    @Ensures({ result -> result != null })
    String getColor() { color }
    String color = null // transparent
}

def ex = shouldFail(AssertionError) {
    new Glass().toString() // PostconditionViolation
}
println ex.message
