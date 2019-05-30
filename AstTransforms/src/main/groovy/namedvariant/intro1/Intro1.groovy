package namedvariant.intro1

import java.awt.Color
import static java.awt.Color.*

def chooseBox(Color c, Integer size) {
    boolean special = RED.equals(c)
    boolean large = size > 12
    String desc = "${(large ? 'large ' : '')}" +
            "${special ? 'special' : 'standard'}"
    println "You chose a $desc box"
}

chooseBox(RED, 20) //You chose a large special box
chooseBox(BLUE, 10) //You chose a standard box
