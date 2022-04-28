import groovy.transform.*

@RecordType
class Point2D {
    int x, y
}

assert new Point2D(10, 20).toString() == 'Point2D[x=10, y=20]'
