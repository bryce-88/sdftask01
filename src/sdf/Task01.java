package sdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task01 {

    public static void main(String[] args) throws IOException {
        
        HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
        LinkedHashMap<String, Integer> linkedWordMap = new LinkedHashMap<String, Integer>();
        Double totalCount = 0.0;
        

        Path path = Paths.get(args[0]);
        File file = path.toFile();

        if (file.exists()) {
            System.out.println("Reading file now..");
        } else {
            System.out.println("File does not exist.");
        }

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine().replaceAll("\\p{Punct}", "").toLowerCase();
        

        while (null != line) {

            Scanner scan = new Scanner(line);
   
            while (scan.hasNext()) {
                String word = scan.next();
                totalCount++;
                // System.out.print(word);
                
                wordMap.merge(word, 1, Integer::sum);
            }
            

            try {
                line = br.readLine().replaceAll("\\p{Punct}", "").toLowerCase();
            } catch (NullPointerException e) {
                break;
            }

            scan.close();
        }

        br.close();
        fr.close();
        

        System.out.println("\n\n");
        System.out.printf("Total word count is: %.0f\n\n", totalCount);

        wordMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> linkedWordMap.put(x.getKey(), x.getValue()));
        Map<String, Integer> topTenWords = linkedWordMap.entrySet().stream().limit(10).collect(Collectors.toMap(Entry::getKey, Entry::getValue));

        System.out.println(topTenWords);
        // for (Map.Entry<String,Integer> entry : linkedWordMap.entrySet()) 
        //     System.out.println("Word = " + entry.getKey() +
        //                      ", Count = " + entry.getValue() +
        //                      "\n");
    }
}