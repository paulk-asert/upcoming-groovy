package namedvariant.intro4

import groovy.transform.NamedVariant
import groovy.transform.TypeChecked
import groovy.transform.VisibilityOptions
import groovy.transform.options.Visibility

import java.awt.*

import static java.awt.Color.*

@TypeChecked
@NamedVariant
@VisibilityOptions(Visibility.PUBLIC)
def chooseBox(Color color, Integer size) {
    boolean special = RED.equals(color)
    boolean large = size > 12
    String desc = "${(large ? 'large ' : '')}" +
            "${special ? 'special' : 'standard'}"
    println "You chose a $desc box"
}

@TypeChecked
def method() {
    chooseBox(color: "red", size: 20)
    chooseBox(colour: BLUE, size: '10')
}

println new Date()
method()

/*
def chooseBox(@NamedParams([
        @NamedParam(value = 'color', type = Color),
        @NamedParam(value = 'size', type = Integer)]) Map __namedArgs) {
    for (String namedArgKey : __namedArgs.keySet()) {
        assert ['color', 'size'].contains( namedArgKey ) : 'Unrecognized namedArgKey: ' + namedArgKey }
    chooseBox( __namedArgs.color, __namedArgs.size)
}
*/

// [Static type checking] - parameter for named arg 'size' has type 'java.lang.String' but expected 'java.lang.Integer'. @ line 28, column 15.
// [Static type checking] - parameter for named arg 'color' has type 'java.lang.String' but expected 'java.awt.Color'. @ line 27, column 15.
