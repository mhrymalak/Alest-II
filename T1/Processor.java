package T1;

public class Processor extends java.lang.Thread {

    private String priority;
    private Node node;

    public Processor(Node node, String priority) {
        this.node = node;
        this.priority = priority;
    }

    @Override
    public void run(){
        try {
            Thread.sleep((long) (node.time));
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
        System.out.println(node.toString()+"\nPriority was "+this.priority);
    }
}