import static java.util.stream.Collectors.toList

(1..10).forEach(e -> { println e })

assert (1..10).stream()
        .filter(e -> e % 2 == 0)
        .map(e -> e * 2)
        .collect(toList()) == [4, 8, 12, 16, 20]
