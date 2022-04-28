package pojo

import groovy.transform.CompileStatic
import groovy.transform.stc.POJO

@CompileStatic
@POJO
class PointList {
    @Delegate List<Point> points
}
