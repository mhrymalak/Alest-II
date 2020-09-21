package T1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.HashMap;
import java.lang.System;

public class Application {
    public static void main(String args[])
    {   
        System.out.print("Bem-vindo professor");
        System.out.println("Projeto desenvolvido por Matheus Hrymalak & Pércio Reinert");
        System.out.println("Começaremos do menor caso até o maior, conforme os arquivos passados no Moodle");
       
        executeCase((byte)0);
        executeCase((byte)1);
        // executeCase((byte)2);
        // executeCase((byte)3);
        // executeCase((byte)4);
        // executeCase((byte)5);
        // executeCase((byte)6);
        // executeCase((byte)7);
        // executeCase((byte)8);
        // executeCase((byte)9);
        // executeCase((byte)10);

    }

    private static void executeCase(byte caseOption) 
    {   var filePath = "";
        var mapping = new HashMap<String,String>();
        // choose the file option
        switch(caseOption)
        {   case 0:
                filePath = "T1\\testCases\\caso5.txt";
                break;
            case 1:
                filePath = "T1\\testCases\\caso6.txt";
                break;
            case 2:
                filePath = "T1\\testCases\\caso7.txt";
                break;
            case 3:
                filePath = "T1\\testCases\\caso10.txt";
                break;
            case 4:
                filePath = "T1\\testCases\\caso100.txt"; 
                break;
            case 5:
                filePath = "T1\\testCases\\caso200.txt";
                break;
            case 6:
                filePath = "T1\\testCases\\caso500.txt";
                break;
            case 7:
                filePath = "T1\\testCases\\caso1000.txt";
                break;
            case 8:
                filePath = "T1\\testCases\\caso2000.txt";
                break;
            case 9:
                filePath = "T1\\testCases\\caso5000.txt";
                break;
            case 10:
                filePath = "T1\\testCases\\caso10000.txt";
                break;
            default:
                break;
        }

        var fileContent = readFile(filePath);
        if(fileContent.isBlank()){
            System.out.println("pulando este teste");
            return;
        }

        // creating structure
        var contentAsList = dataFormating(fileContent.split(" -> ")); 
        
        System.out.println("Arquivo: "+filePath);

        
    }

    private static List<String> dataFormating(String[] split) {
        
        
    }

    private static String readFile(String filePath)
    {   String content = "";
        //read the file   
        try {
            content = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Erro na Leitura:\n" +e);
        }   
        return content;
    }
}