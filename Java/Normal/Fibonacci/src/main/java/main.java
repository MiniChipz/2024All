import java.math.BigInteger;

public class main {

    public static void main(String[] args) {
        BigInteger fib1 = BigInteger.ZERO;
        BigInteger fib2 = BigInteger.ONE;
        BigInteger fib3;

        int total = 0;

        while (true) {
            fib3 = fib1.add(fib2);
            fib1 = fib2;
            fib2 = fib3;

            total += 1;

            System.out.println("Fibo: " + fib3 + " Total: " + total);

            //if (fib3.compareTo(new BigInteger("1000000000000000000000000")) > 0) {
              //  System.out.println("Fibonacci sequence reached the set limit.");
                //break;
            //}
            if (total > 10000) {
                System.out.println("It's over 10000!!");
                break;
            }
        }
    }
}
