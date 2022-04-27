def range = 1..5
assert range == [1, 2, 3, 4, 5]

range = 1..<5
assert range == [1, 2, 3, 4]

range = 1<..5
assert range == [2, 3, 4, 5]

range = 1<..<5
assert range == [2, 3, 4]
