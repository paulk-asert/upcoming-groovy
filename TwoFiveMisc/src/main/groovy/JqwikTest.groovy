//@Grab('net.jqwik:jqwik:1.2.0')
import net.jqwik.api.*

class PropertyBasedTests {
    @Property
    void 'size of concatenated string is greater than or equal to size of each'(
        @ForAll String s1, @ForAll String s2
    ) {
        String conc = s1 + s2
        assert conc.size() >= s1.size()
        assert conc.size() >= s2.size()
    }

    @Property
    void 'size of concatenated string is sum of size of each'(
            @ForAll String s1, @ForAll String s2
    ) {
        String conc = s1 + s2
        assert conc.size() == s1.size() + s2.size()
    }
}

~/\w{3/                   // missing closing repetition quantifier brace
~"(.)o(.*"                // missing closing group bracket
Pattern.compile(/?/)      // dangling meta character '?' (Java longhand)

'foobar'  =~ /f[o]{2/          // missing closing repetition quantifier brace
'foobar' ==~ /(foo/            // missing closing group bracket
Pattern.matches(/?/, 'foo')    // dangling meta character '?' (Java longhand)

def m = 'foobar' =~ /(...)(...)/
assert m[0][1] == 'foo'             // okay
assert m[0][3]                      // type error: only two groups in regex

Pattern p = Pattern.compile('(...)(...)')
Matcher m = p.matcher('foobar')
assert m.find()
assert m.group(1) == 'foo'          // okay
assert m.group(3)                   // type error: only two groups in regex

import groovy.transform.TypeChecked

@TypeChecked(extensions = 'groovy.typecheckers.RegexChecker')
def whenIs2020Over() {
    def newYearsEve = '2020-12-31'
    def matcher = newYearsEve =~ /(\d{4})-(\d{1,2})-(\d{1,2}/
}

def newYearsEve = '2020-12-31'
def matcher = newYearsEve =~ /(\d{4})-(\d{1,2})-(\d{1,2}/    // PatternSyntaxException

def num = 42
def list = [1 ,2, 3]
def range = 0..5
def string = 'foo'

println NV(num, list, range, string)

println NVI(range)

println NVD(range)

import org.apache.groovy.util.JavaShell
def opts = ['--enable-preview', '--release', '14']
def src = 'record Coord(int x, int y) {}'
Class coordClass = new JavaShell().compile('Coord', opts, src)
assert coordClass.newInstance(5, 10).toString() == 'Coord[x=5, y=10]'
import groovy.transform.*
@interface RecordBase{}
@interface POJO{}
class ImmutablePropertyHandler{}

@RecordBase
@ToString(cache = true, includeNames = true)
@EqualsAndHashCode(cache = true, useCanEqual = false)
@ImmutableOptions
@PropertyOptions(propertyHandler = ImmutablePropertyHandler)
@TupleConstructor(defaults = false)
@MapConstructor
@KnownImmutable
@POJO
class Point {
    int x, y
}

@ToString(includeSuperProperties = true, cache = true)
@EqualsAndHashCode(cache = true)
@ImmutableBase
@ImmutableOptions
@PropertyOptions(propertyHandler = ImmutablePropertyHandler)
@TupleConstructor(defaults = false)
@MapConstructor(noArg = true, includeSuperProperties = true, includeFields = true)
@KnownImmutable
class Point {
    int x, y
}

@CompileStatic
@POJO
@Canonical(includeNames = true)
class Point {
    Integer x, y
}

@CompileStatic
@POJO
class PointList {
    @Delegate List<Point> points
}

Predicate<Point> xNeqY = p -> p.getX() != p.getY();

Point p13 = new Point(1, 3);
List<Point> pts = List.of(p13, new Point(2, 2), new Point(3, 1));
PointList list = new PointList();
list.setPoints(pts);

System.out.println(list.size());
System.out.println(list.contains(p13));

list.forEach(System.out::println);

long count = list.stream().filter(xNeqY).collect(counting());
System.out.println(count);

@RecordOptions(mode=RecordTypeMode.EMULATE)
@RecordType
class Cyclist {
    String firstName
    String lastName
}

def richie = new Cyclist('Richie', 'Porte')

@RecordOptions(mode=RecordTypeMode.AUTO) // default, not needed
record Cyclist(String firstName, String lastName) { }

@RecordOptions(mode=RecordTypeMode.NATIVE)
record Point(int x, int y, String color) { }

enum Day { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }

import static Day.*

def isWeekend(Day d) {
    switch(d) {
        case Monday..Friday -> false
        case [Sunday, Saturday] -> true
    }
}

assert [Sunday, Monday, Friday].collect{ isWeekend(it)} == [true, false, false]

def isWeekend2(Day d) {
    return switch(d) {
        case Monday..Friday: yield false
        case [Sunday, Saturday]: yield true
    }
}

assert [Sunday, Wednesday, Saturday].collect{ isWeekend2(it)} == [true, false, true]

@Sealed interface Tree<T> {}
@Singleton final class Empty implements Tree {
    String toString() { 'Empty' }
}
@Canonical final class Node<T> implements Tree<T> {
    T value
    Tree<T> left, right
}

Tree<Integer> tree = new Node<>(42, new Node<>(0, Empty.instance, Empty.instance), Empty.instance)
assert tree.toString() == 'Node(42, Node(0, Empty, Empty), Empty)'

import groovy.transform.Sealed

@SealedOptions(mode=SealedMode.AUTO) // default, not needed
@Sealed(permittedSubclasses=[Diamond,Circle]) class Shape { }
final class Diamond extends Shape { }
final class Circle extends Shape { }

@SealedOptions(mode=SealedMode.NATIVE)
sealed trait Triangle permits Equilateral, Isosceles { }
final class Equilateral implements Triangle { }
final class Isosceles implements Triangle { }

@SealedOptions(mode=SealedMode.EMULATE)
sealed interface Polygon { }
final class Square implements Polygon { }
final class Rectangle implements Polygon { }

assert [new Diamond(), new Circle()]*.class.name == ['Diamond', 'Circle']
assert [new Equilateral(), new Isosceles()]*.class.name == ['Equilateral', 'Isosceles']
assert [new Square(), new Rectangle()]*.class.name == ['Square', 'Rectangle']

sealed class Shape permits Circle, Polygon, Rectangle { }
final class Circle extends Shape { }
non-sealed class Polygon extends Shape { }
final class Pentagon extends Polygon { }
sealed class Rectangle extends Shape permits Square { }
final class Square extends Rectangle { }

def range = 1..5
assert range == [1, 2, 3, 4, 5]

range = 1..<5
assert range == [1, 2, 3, 4]

range = 1<..5
assert range == [2, 3, 4, 5]

range = 1<..<5
assert range == [2, 3, 4]


record Person(String name, int age) {}

def people = [new Person('Daniel', 35),
              new Person('Linda', 25),
              new Person('Peter', 45)]


assert [['Linda', 25], ['Daniel', 35]] ==
        people
        .findAll { p -> p.age < 40 }
        .sort{ p -> p.age }
        .collect { p -> [p.name, p.age] }

assert [['Linda', 25], ['Daniel', 35]] ==
        people.stream()
        .filter(p -> p.age < 40)
        .sorted((p1, p2) -> p1.age <=> p2.age)
        .map(p -> [p.name, p.age])
        .toList()

assert [['Linda', 25], ['Daniel', 35]] == GQ {
    from p in people
    where p.age < 40
    orderby p.age
    select p.name, p.age
}.toList()

/*
sealed abstract class Material permits Straw, Wood, Brick {
    abstract String getColor()
    String toString() {
        "${getClass().name} with color ${color.toLowerCase()}"
    }
}

class Straw extends Material { String color = "Yellow" }
class Wood extends Material { String color = "Brown" }
class Brick extends Material { String color = "Red" }

def materials = [new Straw(), new Wood(), new Brick()]
assert materials*.toString() == [
        'Straw with color yellow',
        'Wood with color brown',
        'Brick with color red'
]


class Glass extends Material {
    String color = null // transparent
}
new Glass().toString() // NullPointerException
/* */
sealed abstract class Material permits Straw, Wood, Brick {
    String warranty() {
        switch(this) {
            case Straw -> 'Use only in light winds'
            case Wood -> 'Okay for light breezes'
            case Brick -> 'Withstands huffing and puffing'
        }
    }
}

class Straw extends Material { }
class Wood extends Material { }
class Brick extends Material { }

def materials = [new Straw(), new Wood(), new Brick()]
assert materials*.warranty() == [
        'Use only in light winds',
        'Okay for light breezes',
        'Withstands huffing and puffing'
]

class Glass extends Material { }
new Glass().warranty() // nothing or error
/* */

import groovy.contracts.*

abstract class Material {
    abstract String getColor()

    @Requires({ color != null })
    String toString() {
        "${getClass().name} with color ${color.toLowerCase()}"
    }

}

class Straw extends Material { String color = "Yellow" }
class Wood extends Material { String color = "Brown" }
class Brick extends Material { String color = "Red" }

def materials = [new Straw(), new Wood(), new Brick()]
assert materials*.toString() == [
        'Straw with color yellow',
        'Wood with color brown',
        'Brick with color red'
]


class Glass extends Material {
    @Ensures({ result -> result != null })
    String getColor() { color }
    String color = null // transparent
}

new Glass().toString() // PreconditionViolation

