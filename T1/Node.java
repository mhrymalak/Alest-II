package T1;

import java.util.ArrayList;
import java.util.List;

class Node {

    public List<Node> children;
    public String name;
    public int time;

    public Node(String name, int time) {
        this.name = name;
        this.time = time;
        this.children = new ArrayList<Node>();
    }
}