class Name {
    String name
    char firstLetter() { name[0] }
    static cloneOf(Name n) { new Name(name: n.name + '2') }
}
// instance::instanceMethod
def fl = new Name(name: 'Guillaume')::firstLetter
assert fl() == 'G'
// instance::staticMethod
def n = new Name(name: 'Marion')
def clone = n::cloneOf
assert clone(n).name == 'Marion2'