package com.kvp34;


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Set<String>> listOfTransactiions=fileReader("src/com/kvp34/DataFile.txt");
        Set<String> uniqueListOfProducts= getUniqueListOfProducts(listOfTransactiions);
        int minSupport=1;
        Map<Set<String>,Integer> frequentItemList=generateFrequentItemList(listOfTransactiions,uniqueListOfProducts,minSupport);

        System.out.println(frequentItemList);

        Set<String> br=new HashSet<String>();
        br.add("Salt");
        Set<String> bt=new HashSet<String>();
        bt.add("Sugar");
        float conf=getConfidence(listOfTransactiions,br,bt);

        System.out.println(conf);
    }

    public static List<Set<String>> fileReader(String filePath) throws IOException {
        File f = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String eachLine;
        List<Set<String>> transactions=new ArrayList<Set<String>>();

        while ((eachLine=br.readLine())!=null){
            Set<String> items=new HashSet<String>(Arrays.asList(eachLine.split(", ")));
            transactions.add(items);
        }
        br.close();
        return  transactions;
        }

    public static int findSupport(List<Set<String>> transactionList,Set<String> itemSet){
        List<Set<String>> transactionSubset=new ArrayList<Set<String>>();
        for (Set<String> s : transactionList) {
            if(s.containsAll(itemSet)) {
                transactionSubset.add(s);
            }
        }
       return transactionSubset.size();//(int)(100.00f*((float)transactionSubset.size()/(float) transactionList.size()));
    }
    public static float getConfidence(List<Set<String>> transactionList,Set<String> LHS, Set<String> RHS){
        float confidence;
        Set<String> mergedSet=new HashSet<String>();
        mergedSet.addAll(LHS);
        mergedSet.addAll(RHS);
        confidence=((float)findSupport(transactionList,mergedSet))/(float)(findSupport(transactionList,LHS));
        return confidence;
    }

    public static Set<String> getUniqueListOfProducts(List<Set<String>> transactionList){
        Set<String> uniqueItemList=new HashSet<String>();
        for(Set<String> eachTransaction:transactionList)
        {
            for(String eachItem:eachTransaction)
            {
                uniqueItemList.add(eachItem);
            }
        }
        return uniqueItemList;
    }

    public static Map<Set<String>,Integer> generateFrequentItemList(List<Set<String>> transactionList, Set<String> uniqueItemList, int minSupport){
        Map<Set<String>,Integer> frequentItemList=new HashMap<Set<String>,Integer>();
        for(String s:uniqueItemList){
            Set<String> singleItemSet=new HashSet<String>();
            singleItemSet.add(s);
            int support=findSupport(transactionList,singleItemSet);
            if(support>=minSupport) {
                frequentItemList.put(singleItemSet, support);
            }
        }
        return frequentItemList;
    }


}