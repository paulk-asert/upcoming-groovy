sealed class Shape permits Circle, Polygon, Rectangle { }
final class Circle extends Shape { }
non-sealed class Polygon extends Shape { }
final class Pentagon extends Polygon { }
sealed class Rectangle extends Shape permits Square { }
final class Square extends Rectangle { }

assert [new Polygon(), new Pentagon(), new Circle()]*.class.name == ['Polygon', 'Pentagon', 'Circle']
assert [new Square(), new Rectangle()]*.class.name == ['Square', 'Rectangle']
