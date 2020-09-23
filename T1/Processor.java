package T1;

public class Processor extends java.lang.Thread {

    private Node node;

    public Processor(Node node) {
        this.node = node;
    }
    @Override
    public void run(){
      if(this.node != null) {
          try {
                  Thread.sleep((long) (node.time));
              } catch (InterruptedException e) {
                  e.printStackTrace(System.err);
              }
              System.out.println(node.toString());
      }
    }
}