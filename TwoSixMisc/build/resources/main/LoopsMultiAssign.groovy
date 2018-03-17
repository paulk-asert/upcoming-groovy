// multi-assignment
def (String x, int y) = ['foo', 42]
assert "$x $y" == 'foo 42'

// multi-assignment goes loopy
def barNums = []
for (def (String u, int v) = ['bar', 42]; v < 45; u++, v++) {
    barNums << "$u $v"
}
assert barNums == ['bar 42', 'bas 43', 'bat 44']
