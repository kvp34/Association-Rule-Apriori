package com.kvp34;


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Set<String>> tran=fileReader("src/com/kvp34/DataFile.txt");

        Set<String> itemset=new HashSet<String>();
        itemset.add("Sugar");

        float support=findSupport(tran,itemset);
        System.out.println(support);

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

    public static float findSupport(List<Set<String>> transactionList,Set<String> itemSet){
        List<Set<String>> transactionSubset=new ArrayList<Set<String>>();
        for (Set<String> s : transactionList) {
            if(s.containsAll(itemSet)) {
                transactionSubset.add(s);
            }
        }
       return ((float)transactionSubset.size())/((float)transactionList.size());
    }
}