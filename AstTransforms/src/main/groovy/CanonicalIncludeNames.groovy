import groovy.transform.*



@Canonical(includeNames=true)
class Golfer {
    String first, last
}

def gn = new Golfer('Greg', 'Norman')
assert gn.toString() ==
        'Golfer(first:Greg, last:Norman)'
