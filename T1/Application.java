
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.lang.System;

public class Application { 

    private static HashMap<Node,List<Node>> tree; 
    private static List<Processor> procs;
    private static Node root;
    private static short count = 0;
    public static void main(String args[])
    {   
        System.out.print("Bem-vindo professor");
        System.out.println("Projeto desenvolvido por Matheus Hrymalak & Pércio Reinert");
        System.out.println("Começaremos do menor caso até o maior, conforme os arquivos passados no Moodle");
       
        try {
        executeCase("T1\\testCases\\caso5.txt");
        // executeCase("T1\\testCases\\caso6.txt");
        // executeCase("T1\\testCases\\caso7.txt");
        // executeCase("T1\\testCases\\caso10.txt");
        // executeCase("T1\\testCases\\caso100.txt");
        // executeCase("T1\\testCases\\caso200.txt");
        // executeCase("T1\\testCases\\caso500.txt");
        // executeCase("T1\\testCases\\caso1000.txt");
        // executeCase("T1\\testCases\\caso2000.txt");
        // executeCase("T1\\testCases\\caso5000.txt");
        // executeCase("T1\\testCases\\caso10000.txt");
            
        } catch (Exception e) {
            System.err.println("CAIU A CASA");
        }
    }

    private static void executeCase(String filePath) throws Exception
    {
        tree = new HashMap<>(); 
        root = null;
        count = 0;
        readFile(filePath);
        process(root, true); // ascending order
        process(root, false); // descending order
        
        
    }

    private static void createProcs(byte procsNumber) {
        Processor core;
        for(byte i = 0; i < procsNumber; i++){
            core = new Processor(null);
            procs.add(i, core);
            core.start();
        }  
    }

    private static void readFile(String fileName) {
        try {
            Scanner myReader = new Scanner(new File(fileName));
            String firstLine = myReader.nextLine();
            // byte procsNumber = Byte.parseByte(firstLine.split("# Proc ")[1]);
            byte procsNumber = 3;
            procs = new ArrayList<>(procsNumber);
            List<Node> auxiliar;
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
            
                String[] parts = data.split(" -> ");
                
                // pensar no uso de Map para resolver a situação
                String[] first  = parts[0].split("_");
                String[] second = parts[1].split("_");
                
                Node father = new Node(first[0], Short.parseShort(first[1]));
                Node child  = new Node(second[0], Short.parseShort(second[1]));
                // adicionar na estrutura de dados
                validateRoot(father,child);
                auxiliar = whichListIChoose(father,child);
                auxiliar.add(child);
                tree.put(father,auxiliar);
            }
            myReader.close();
            System.out.println("I AM THE MOTHER FUCKER ROOT: "+root.name);
            createProcs(procsNumber);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    private static List<Node> whichListIChoose(Node father, Node child) {
        List<Node> list;
        if (tree.containsKey(father)) {
            list = tree.get(father);
            count++;
        } else {
            list = new ArrayList<>();
            count+=2;
        }
        return list;
    }

    private static void validateRoot(Node father, Node child) {
        if (root == null || root.equals(child)) {
            root = father;
        }
    }
    private static void process(Node father, boolean typeOfSort) throws Exception {
        if(count == 0) 
            return;
        List<Node> nodeList =  tree.get(father);
        if(nodeList != null){
            nodeList.add(father);
            // if(nodeList.size() == procs.size()){
                if(typeOfSort)
                nodeList.sort((x,y) ->  {return x.time - y.time;});
            else 
                nodeList.sort((x,y) ->  {return y.time - x.time;});
        // }
        // else{
        //     if(nodeList.size() > procs.size()){
        //     }
        //     else{
                
        //     }
        // }
            for(Node node : nodeList){
                if(!node.processed){
                    manageProcessors(node);
                    process(node, typeOfSort);
                }
            }
        }
    }
    

    private static void manageProcessors(Node node) {
        Processor theCore = null;
        byte position = 0;
        // while(theCore == null){
        //     for(Processor core = procs.get(position); position < procs.size(); position++){
        //         if(!core.isAlive()){
        //             theCore = core;
        //             break;
        //         }
        //     }
        // }
        // theCore = new Processor(node);
        // procs.set(position, theCore);
        node.processed = true;
        count--;
        // theCore.start();
    }
}
    
