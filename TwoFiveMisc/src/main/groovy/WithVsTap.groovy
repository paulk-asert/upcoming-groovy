class Person {
    String first, last, honorific
    boolean friend
}

def p = new Person(last: 'Gaga', honorific: 'Lady', friend: false)
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
