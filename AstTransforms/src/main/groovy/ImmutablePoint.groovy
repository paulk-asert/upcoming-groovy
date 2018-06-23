import groovy.transform.Immutable

@Immutable
class Point {
    int x, y
}

assert new Point(x:0, y:0)
