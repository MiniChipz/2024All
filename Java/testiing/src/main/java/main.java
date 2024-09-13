public class main {
    private void testerr(Boolean boo) {
        String stringen; {
            if(boo)
            {
                stringen = "nig";
            }
            else
            {
                stringen = "white";
            }
        }
    }
    public static void hydra() {
        long start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            System.out.println(i);
        }

        long done = System.nanoTime();
        long elapsedTime = (done - start) / 1000;

        System.out.println("Time: " + elapsedTime + " Micro Seconds");
        System.out.println("Start: " + start / 1000 + " Micro Seconds");
        System.out.println("Done: " + done / 1000 + " Micro Seconds");
    }

    public static void main(String[] args) {
        hydra();
    }
}