public class Node {

    public String name;
    public short time;
    public String originalName;

    public Node(String name, short time) {
        this.name = name;
        this.time = time;
        this.originalName = name+"_"+time;
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
    @Override
    public String toString(){
        return "Name: "+this.name+"\nTime: "+this.time;
    }
}