package T1;

import java.util.List;

class Node {

    public List<Node> children;
    public String content;
    public int priority;

    public Node(String content) {
        this.content = content;
        this.children = new List<Node>();
    }
}