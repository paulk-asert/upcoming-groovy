import groovy.transform.*

class Animal {
    String type
    String name
}

@ToString(includeNames=true)
class Color {
    Integer r, g, b
}

@NamedVariant
String foo(String s1, @NamedParam String s2,
           @NamedDelegate Color shade,
           @NamedDelegate Animal pet) {
    "$s1 $s2 ${pet.type?.toUpperCase()}:$pet.name $shade"
}

def result = foo(s2: 'S2', g: 12, b: 42, r: 12,
        type: 'Dog', name: 'Rover', 'S1')
assert result == 'S1 S2 DOG:Rover Color(r:12, g:12, b:42)'

/*
String foo(@NamedParam(value = 's2', type = String)
           @NamedParam(value = 'r', type = Integer)
           @NamedParam(value = 'g', type = Integer)
           @NamedParam(value = 'b', type = Integer)
           @NamedParam(value = 'type', type = String)
           @NamedParam(value = 'name', type = String)
           Map __namedArgs, String s1) {
    // some key validation code ...
    return this.foo(s1, __namedArgs.s2,
            ['r': __namedArgs.r, 'g': __namedArgs.g, 'b': __namedArgs.b] as Color,
            ['type': __namedArgs.type, 'name': __namedArgs.name] as Animal)
}
*/
