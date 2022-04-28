// run on JDK14 with --enable-preview

import org.apache.groovy.util.JavaShell
def opts = ['--enable-preview', '--release', '14']
def src = 'record Coord(int x, int y) {}'
Class coordClass = new JavaShell().compile('Coord', opts, src)
assert coordClass.newInstance(5, 10).toString() == 'Coord[x=5, y=10]'
