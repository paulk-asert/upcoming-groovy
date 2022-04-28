import pojo.Point;
import pojo.PointList;

import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.counting;

public class PointExample {
    public static void main(String[] args) {

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

    }
}
