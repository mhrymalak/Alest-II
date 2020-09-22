public class Processors extends java.lang.Thread {

    private int count; 
    private int limit;

    public Processors( int limit ) {
        this.count = 0;
        this.limit = limit; 
    }

    public void run() {
        while (count <= limit) {
            System.out.println( super.getName() + "\t" + count );
            count++;
            // trecho de código para demonstração somente
            try {
                Thread.sleep(1000); // coloca a "thread" para "dormir" 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace( System.err );
            }
        }
    }
}