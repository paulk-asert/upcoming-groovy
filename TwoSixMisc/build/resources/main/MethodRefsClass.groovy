import static java.util.stream.Collectors.toList
// class::staticMethod
assert ['1', '2', '3'] ==
        [1, 2, 3].stream()
                .map(String::valueOf)
                .collect(toList())
// class::instanceMethod
assert ['A', 'B', 'C'] ==
        ['a', 'b', 'c'].stream()
                .map(String::toUpperCase)
                .collect(toList())
