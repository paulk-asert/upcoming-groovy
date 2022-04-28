import groovy.transform.*

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