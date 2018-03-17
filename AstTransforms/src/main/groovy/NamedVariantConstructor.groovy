import groovy.transform.*
import static groovy.transform.options.Visibility.*

class Color {
    private int r, g, b

    @VisibilityOptions(PUBLIC)
    @NamedVariant
    private Color(@NamedParam int r, @NamedParam int g, @NamedParam int b) {
        this.r = r
        this.g = g
        this.b = b
    }
}

def pubCons = Color.constructors
assert pubCons.size() == 1
assert pubCons[0].parameterTypes[0] == Map

/*
public Color(@NamedParam(value = 'r', type = int)
             @NamedParam(value = 'g', type = int)
             @NamedParam(value = 'b', type = int)
             Map __namedArgs) {
    this ( __namedArgs .r, __namedArgs .g, __namedArgs .b)
    // plus some key value checking
}
*/
