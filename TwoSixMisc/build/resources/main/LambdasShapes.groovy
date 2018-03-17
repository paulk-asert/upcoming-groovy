// all the shapes
(x, y) -> x + y
(x, y) -> { x + y }
(int x, int y) -> x + y

def c = (int x, int y = 0) -> x + y
assert c(1) == 1
