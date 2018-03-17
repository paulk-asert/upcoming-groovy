class Person {
    String first, last
    @Delegate
    String getFullName() {
        "$first $last"
    }
}

def p = new Person(first: 'John', last: 'Smith')
assert p.equalsIgnoreCase('JOHN smith')

