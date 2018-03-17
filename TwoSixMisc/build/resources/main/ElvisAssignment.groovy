import groovy.transform.ToString

@ToString
class Person {
    String name
    int age
}
def p = new Person(name: 'Érine')
p.with {
    name = name ?: 'unknown'
    age = age ?: 4
}
assert p.toString() == 'Person(Érine, 4)'
