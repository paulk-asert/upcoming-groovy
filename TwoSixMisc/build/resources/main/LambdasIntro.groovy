import static java.util.stream.Collectors.toList

(1..10).forEach((it) -> { println it })
assert (1..10).stream()
        .filter((it) -> it % 2 == 0)
        .map((it) -> it * 2)
        .collect(toList()) == [4, 8, 12, 16, 20]
