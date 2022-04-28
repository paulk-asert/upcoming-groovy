record Person(String name, int age) {}

def people = [new Person('Daniel', 35),
    new Person('Linda', 25),
    new Person('Peter', 45)]


assert [['Linda', 25], ['Daniel', 35]] == people
    .findAll { p -> p.age < 40 }
    .sort{ p -> p.age }
    .collect { p -> [p.name, p.age] }

assert [['Linda', 25], ['Daniel', 35]] == people.stream()
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
