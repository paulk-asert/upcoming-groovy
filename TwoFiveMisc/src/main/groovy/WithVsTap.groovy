import groovy.transform.TupleConstructor

class Person2 {
    String first, last, honorific
    boolean friend
}

def p = new Person2(last: 'Gaga', honorific: 'Lady', friend: false)
def greeting = 'Dear ' + p.with{ friend ? first : "$honorific $last" }
assert greeting == 'Dear Lady Gaga'

new Person().tap {
    friend = true
    first = 'Bob'
}.tap {
    assert friend && first || !friend && last
}.tap {
    if (friend) {
        println "Dear $first"
    } else {
        println "Dear $honorific $last"
    }
}

@TupleConstructor(includes='first, last')
class Person {
    String first, last
    boolean known = false
}

String greet(String name) {
    "Hello $name"
}

assert 'Hello John Smith' ==
        greet(new Person('John', 'Smith').with{ "$first $last" })

String welcome(Person p) {
    "Hello ${p.known ? 'Friend' : 'Stranger'}"
}

assert 'Hello Stranger' == welcome(new Person('John', 'Smith'))
assert 'Hello Friend' == welcome(new Person('Betty', 'Boo').tap{ known = true })
