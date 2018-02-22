package com.kvp34;


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Set<String>> tran=fileReader("src/com/kvp34/DataFile.txt");
        System.out.println(tran);
    }

    public static List<Set<String>> fileReader(String filePath) throws IOException {
        File f = new File(filePath);
        BufferedReader in = new BufferedReader(new FileReader(f));
        String st;
        List<Set<String>> transactions=new ArrayList<Set<String>>();

        while ((st=in.readLine())!=null){
            Set<String> items=new HashSet<String>(Arrays.asList(st.split(", ")));
            transactions.add(items);
        }
        in.close();
        return  transactions;
        }
}