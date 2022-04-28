def num = 42
def list = [1 ,2, 3]
def range = 0..5
def string = 'foo'

println SV(num, list, range, string)
println SVI(range)
println SVD(range)
println NV(range)
println NVL(num, list, range, string)

assert SV(num, list, range, string).toString() == '''
num=42, list=[1, 2, 3], range=[0, 1, 2, 3, 4, 5], string=foo
'''.trim()

assert SVI(range).toString() == '''
range=0..5
'''.trim()

def svd = SVD(range).toString()
assert svd.contains('range=<groovy.lang.IntRange@')
assert svd.contains('from=0 to=5 reverse=false inclusiveRight=true inclusiveLeft=true')

def nv = NV(range)
assert nv instanceof NamedValue
assert nv.name == 'range' && nv.val.toString() == '0..5'

def nvl = NVL(num, list, range, string)
assert nvl.toString() == '''
[num=42, list=[1, 2, 3], range=0..5, string='foo']
'''.trim()
assert nvl.collect(NamedValue::getName) == ['num', 'list', 'range', 'string']
assert nvl*.class == [NamedValue] * 4
