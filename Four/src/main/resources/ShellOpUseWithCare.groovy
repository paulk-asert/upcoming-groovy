abstract class ShellOp {
    abstract String getOp()
    def calc(int one, int two) {
        // powerful approach - use with care
        new GroovyShell().evaluate("$one $op $two")
    }
}

class Multiply extends ShellOp { String op = "*" }

class Add extends ShellOp { String op = "+" }

def ops = [new Add(), new Multiply()]
assert ops*.calc(40, 2) == [42, 80]
