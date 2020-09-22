package T1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.lang.System;

public class Application { 

    private static HashMap<Node,List<Node>> tree; 
    private static Integer procs;
    private static Node root;
    public static void main(String args[])
    {   
        System.out.print("Bem-vindo professor");
        System.out.println("Projeto desenvolvido por Matheus Hrymalak & Pércio Reinert");
        System.out.println("Começaremos do menor caso até o maior, conforme os arquivos passados no Moodle");
       
        executeCase("T1\\testCases\\caso5.txt");
        executeCase("T1\\testCases\\caso6.txt");
        executeCase("T1\\testCases\\caso7.txt");
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
        readFile(filePath);
        process();
        
    }

    
    private static void readFile(String fileName) {
        tree = new HashMap<>(); 
        root = null;
        try {
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            String firstLine = myReader.nextLine();
            procs = Integer.parseInt(firstLine.split("# Proc ")[1]);
            
            while (myReader.hasNextLine()) {
                
                //private static Map<Node, List<Node>> tree = new HashMap<>();
                String data = myReader.nextLine();
                
                String[] parts = data.split(" -> ");
                
                // pensar no uso de Map para resolver a situação
                String[] first  = parts[0].split("_");
                String[] second = parts[1].split("_");
                
                Node father = new Node(first[0], Integer.parseInt(first[1]));
                Node child  = new Node(second[0], Integer.parseInt(second[1]));
                
                // adicionar na estrutura de dados
                validateRoot(father,child);
                if (tree.containsKey(father)) {
                    List<Node> auxiliar = tree.get(father);
                    auxiliar.add(child);
                    tree.put(father, auxiliar);
                } else {
                    List<Node> maisUm = new ArrayList<>();
                    maisUm.add(child);
                    tree.put(father,maisUm);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("I AM THE MOTHER FUCKER ROOT: "+root.name);
        List<Node> temQueFazer = new ArrayList<>();
        temQueFazer.add(root);
        tree.put(null,temQueFazer);
    }
    
    private static void validateRoot(Node father, Node child) {
        if (root == null || root.equals(child)) {
            root = father;
        }
    }
    private static void process() {
        

    }
}