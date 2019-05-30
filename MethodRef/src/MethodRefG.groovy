import java.util.function.Function

/* */
// instance.&instance
def tenPlus = 10G::add
assert tenPlus(1G) == 11G
assert tenPlus(2G) == 12G
/*
// Class.&instance
def file = File::new
def isHidden = File::isHidden
assert isHidden(file('.git'))
*/
// Class.&static
def asHex = Integer::toHexString
assert asHex(10) == 'a'

// instance.&static
// generally discouraged
def asOctal = 42::toOctalString
assert asOctal(10) == '12'

@groovy.transform.CompileStatic
def main() {
    Function<String, String> lower = String::toLowerCase
    println 'lower = ' + lower // => Foo$$Lambda$14/0x0000000801205040@7bd4937b
}

main()
// lower = MethodRefG$$Lambda$135/0x0000000801376440@5411dd90

def upper = String::toUpperCase
println 'upper = ' + upper
// upper = org.codehaus.groovy.runtime.MethodClosure@7aa9e414

def blank = String::isBlank
println 'blank = ' + blank
println "blank = $blank"

//def gs = "upper = $upper" // => Foo$$Lambda$14/0x0000000801205040@7bd4937b
//println gs.values // => Foo$$Lambda$14/0x0000000801205040@7bd4937b
//println gs.strings // => Foo$$Lambda$14/0x0000000801205040@7bd4937b

def plus = BigInteger::add
def fivePlus = plus.curry(5G).memoizeAtLeast(10)
assert fivePlus(3G) == 8G
assert fivePlus << 4G == 9G
