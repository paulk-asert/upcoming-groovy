package sealed.details1

assert [new Diamond(), new Circle()]*.class.simpleName == ['Diamond', 'Circle']
assert [new Equilateral(), new Isosceles()]*.class.simpleName == ['Equilateral', 'Isosceles']
assert [new Square(), new Rectangle()]*.class.simpleName == ['Square', 'Rectangle']
