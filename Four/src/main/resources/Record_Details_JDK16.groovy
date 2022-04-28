import groovy.transform.*

@RecordOptions(mode=RecordTypeMode.AUTO) // default, not needed
record Person(String name, int age) {}

@RecordOptions(mode=RecordTypeMode.NATIVE)
record Point(int x, int y, String color) { }

@RecordOptions(mode=RecordTypeMode.EMULATE)
@RecordType
class Cyclist {
    String firstName
    String lastName
}

assert new Person('Daniel', 35).toString() == 'Person[name=Daniel, age=35]'
assert new Point(0, 0, 'Blue').toString() == 'Point[x=0, y=0, color=Blue]'
assert new Cyclist('Richie', 'Porte').toString() == 'Cyclist[firstName=Richie, lastName=Porte]'
