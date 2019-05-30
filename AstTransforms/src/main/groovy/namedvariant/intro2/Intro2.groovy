package namedvariant.intro2

import static java.awt.Color.*

def chooseBox(Map map) {
    boolean special = RED.equals(map.color)
    boolean large = (Integer) map.size > 12
    String desc = "${(large ? 'large ' : '')}" +
            "${special ? 'special' : 'standard'}"
    println "You chose a $desc box"
}

chooseBox(color: RED, size: 20)
chooseBox(size: 10, color: BLUE)
