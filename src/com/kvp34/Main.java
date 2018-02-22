package com.kvp34;


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Set<String>> listOfTransactiions=fileReader("src/com/kvp34/DataFile.txt");
        Set<String> uniqueListOfProducts= getUniqueListOfProducts(listOfTransactiions);
        float minSupport=10.00f;
        Map<Set<String>,Float> frequentItemList=generateFrequentItemList(listOfTransactiions,uniqueListOfProducts,minSupport);
        System.out.println(frequentItemList);
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

    public static float findSupport(List<Set<String>> transactionList,Set<String> itemSet){
        List<Set<String>> transactionSubset=new ArrayList<Set<String>>();
        for (Set<String> s : transactionList) {
            if(s.containsAll(itemSet)) {
                transactionSubset.add(s);
            }
        }
       return 100.00f*(((float)transactionSubset.size())/((float)transactionList.size()));
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

    public static Map<Set<String>,Float> generateFrequentItemList(List<Set<String>> transactionList, Set<String> uniqueItemList, float minSupport){
        Map<Set<String>,Float> frequentItemList=new HashMap<Set<String>,Float>();
        for(String s:uniqueItemList){
            Set<String> singleItemSet=new HashSet<String>();
            singleItemSet.add(s);
            float support=findSupport(transactionList,singleItemSet);
            if(support>=minSupport) {
                frequentItemList.put(singleItemSet, support);
            }
        }
        return frequentItemList;
    }
}