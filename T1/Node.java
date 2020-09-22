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
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    @Override
    public boolean equals(Object other){
       if(other != null){
           Node otherNode = (Node)other;
           if(this.name.equals(otherNode.name)){
               return this.time == otherNode.time;
           }
       }
        return false;
    }
}