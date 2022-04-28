package pojo

import groovy.transform.*
import groovy.transform.stc.POJO

@CompileStatic
@POJO
@Canonical(includeNames = true, includePackage = false)
class Point {
    Integer x, y
}
