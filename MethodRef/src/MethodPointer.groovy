// instance.&instance
def tenPlus = 10G.&add
assert tenPlus(1) == 11G
assert tenPlus(2) == 12G

// Class.&instance
def file = File.&new
def isHidden = File.&isHidden
assert isHidden(file('.git'))

// Class.&static
def asHex = Integer.&toHexString
assert asHex(10) == 'a'

// instance.&static
// generally discouraged
def asOctal = 42.&toOctalString
assert asOctal(10) == '12'
