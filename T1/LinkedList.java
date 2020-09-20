package T1;

class Node {

    public Node father;
    public Node children;
    public String content;

    public Node(String content) {
        this.content = content;
    }

    public Node(String content, Node father) {
        this.content = content;
        this.father = father;
    }
}

public class LinkedList {
    private Node root;

    public LinkedList() {
    }

    private void createRoot(String content) {
        if (this.root != null) {
            System.out.print("You have already a root");
            return;
        }
        this.root = new Node(content);
    }

    private void addANode(String father, String content) {
        if (content.isBlank()){
            System.out.print("The content is not valid");
            return;
        }
        var actual = this.root;

        while (!actual.content.equals(father)) {
            var newNode = new Node(content);
            
        }


            
    }
}
