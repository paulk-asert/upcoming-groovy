def r = Random::new
println r().nextInt(10)

def arr2d = String[][]::new
arr2d(2, 2) == [[null, null], [null, null]]

import groovy.transform.Canonical
@Canonical
class Animal {
    String kind
}
def a = Animal::new
assert a('lion').kind == 'lion'
def c = Animal
assert c::new('cat').kind == 'cat'
