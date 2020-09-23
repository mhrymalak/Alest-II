
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.lang.System;

public class Application { 

    private static final byte SMALLEST = 0;
    private static final byte BIGGEST = Byte.MAX_VALUE;
    private static HashMap<Node,List<Node>> tree;
    private static List<Processor> procs;
    private static Node root;
    private static short count = 0;
    public static void main(String args[])
    {   
        System.out.print("Bem-vindo professor");
        System.out.println("Projeto desenvolvido por Matheus Hrymalak & Pércio Reinert");
        System.out.println("Começaremos do menor caso até o maior, conforme os arquivos passados no Moodle");
       
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
    }

    private static void executeCase(String filePath) 
    {
        tree = new HashMap<>(); 
        root = null;
        count = 0;
        readFile(filePath);
        process(root);
        
    }

	private static void readFile(String fileName) {
        try {
            Scanner myReader = new Scanner(new File(fileName));
            String firstLine = myReader.nextLine();
            procs = new ArrayList<>(Integer.parseInt(firstLine.split("# Proc ")[1]));
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
    private static void process(Node father) {
        if(count == 0) 
            return;
        var childrenList = tree.get(father);
        short biggest  = 0;
        short smallest = 0;
        List<Short> timeList;
        if(childrenList.size()+1 == procs.size()){
            timeList = new ArrayList<Short>(childrenList.size()+1);
            for(Node child : childrenList){
                timeList.add(child.time);
                
            }
            timeList.add(father.time);
            biggest  = Collections.max(timeList,( Short h1, Short h2) -> h1.compareTo(h2));
            smallest = Collections.min(timeList,( Short h1, Short h2) -> h1.compareTo(h2));
            timeList.remove(biggest);
            timeList.remove(smallest);
            System.out.println(biggest);
            System.out.println(smallest);
        }
    }
    



    private static void process(Node node, byte type) {
        Processor core = null;
        byte position = 0;
        String priority;
        switch(type){
            case SMALLEST:
                core = procs.get(SMALLEST);
                position = SMALLEST;
                priority = "Smaller";
                try {
					core.join();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
            break;    
                
            case BIGGEST:
                position = (byte)(procs.size()-1);
                core = procs.get(position);
                priority = "Bigger";
                try {
					core.join();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
            break;
                
            default:
                Processor testingCore;
                while (core == null) {
                    for(byte i = 1 ; i < procs.size() - 2; i++){
                        testingCore = procs.get(i);
                        if(!testingCore.isAlive()){
                            core = testingCore;
                            position = i;
                            break;
                        }
                    }               
                }
                priority = "Normal";
            break;
        }
        core = new Processor(node,priority);
        procs.set(position, core);
        core.start();
        count--;
    }
}
    
