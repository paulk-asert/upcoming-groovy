import groovy.transform.Immutable

@Immutable
class Point2D {
    int x, y
}

assert new Point2D(10, 20).toString() == 'Point2D(10, 20)'
