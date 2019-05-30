import java.util.function.Function;

class MethodRefJ {
    public static void main(String[] args) {
        Function<String, String> lower = String::toLowerCase;
        System.out.println("lower = " + lower);
    }
}
// lower = MethodRefJ$$Lambda$14/0x0000000801204840@4e515669