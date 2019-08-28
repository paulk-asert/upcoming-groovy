assert ('a'..'h').chop(2, 4) == [['a', 'b'], ['c', 'd', 'e', 'f']]

assert 'xyzzy'.replace(xy:'he', z:'l', y: 'o') == 'hello'

def map = [ant:1, bee:2, cat:3]
map.removeAll { k,v -> k == 'bee' }
assert map == [ant:1, cat:3]

assert [1, 2, 3, 4].tails() == [[1, 2, 3, 4], [2, 3, 4], [3, 4], [4], []]

assert 'abcd'.startsWithAny('ab', 'AB')

assert 'abc123'.md5() == 'e99a18c428cb38d5f260853678922e03'
