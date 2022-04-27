import groovy.transform.*

@RecordOptions(copyWith = true, components = true)
record Material(String name, int strength, String warranty, String color) { }

def straw = new Material('Straw', 4, 'light winds only', 'yellow')
assert straw[1] == 4
assert straw.size() == 4

def sticks = new Material(name: 'Sticks', strength: 42, warranty: 'light winds', color: 'brown')
assert sticks.toList() == ['Sticks', 42, 'light winds', 'brown']
assert sticks.toMap() == [name: 'Sticks', strength: 42, warranty: 'light winds', color: 'brown']

def bricks = sticks.copyWith(name: 'Bricks', color: 'red', warranty: 'heavy winds')
assert bricks.components() instanceof Tuple4
assert bricks.components().toList() == ['Bricks', 42, 'heavy winds', 'red']

@CompileStatic
def upperName(Material m) {
    m.components().getV1().toUpperCase()
}
assert upperName(bricks) == 'BRICKS'
