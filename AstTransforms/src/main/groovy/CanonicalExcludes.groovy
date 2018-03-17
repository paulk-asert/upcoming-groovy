import groovy.transform.*



@Canonical(excludes='last')
class Golfer {
    String first, last
}

def greg = new Golfer('Greg')
assert greg.toString() == 'Golfer(Greg)'

/*
@interface ToString {
    String[] excludes() default {};
    boolean includeNames() default false;
    ...
}

@interface EqualsAndHashcode {
    String[] excludes() default {};
    ...
}

@interface TupleConstructor {
    String[] excludes() default {};
    ...
}
*/
