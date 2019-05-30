package namedvariant.intro3

import groovy.transform.TypeChecked

import static java.awt.Color.*

@TypeChecked
def chooseBox(Map map) {
    boolean special = RED.equals(map.colour) // Silent fail!
    boolean large = (Integer) map.size > 12
    String desc = "${(large ? 'large ' : '')}" +
            "${special ? 'special' : 'standard'}"
    println "You chose a $desc box"
}

@TypeChecked
def method() {
    chooseBox(color: "red", size: 20)
    chooseBox(colour: BLUE, size: '10') // GroovyCastException at runtime!
}

method()

