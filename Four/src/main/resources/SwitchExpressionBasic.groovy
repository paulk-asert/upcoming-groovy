def a = 9
def result = switch(a) {
    case 6, 8 -> 'b'
    case 9 -> 'c'
    default -> 'z'
}
assert 'c' == result
