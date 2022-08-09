/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20190635_cosinesimiliarity;

/**
 *
 * @author a
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 *
 * @author a
 */

public class Main {
 public void cosine_simliartity(String[] files)
    {
        for(int i=0;i<files.length;i++)
        {
             ///////////// doc[i]///////////////////////
                String[] words_doc=null;
                List<String> Doc_i = new ArrayList<String>();
                try ( BufferedReader file = new BufferedReader(new FileReader(files[i]))) {
                String ln;           
                while ((ln = file.readLine()) != null) {
                    words_doc = ln.split("\\W+");  
                    for (String word : words_doc) {
                        word = word.toLowerCase();
                        Doc_i.add(word);
                    }  
                }
            } catch (IOException e) {
                System.out.println("File " + files[i] + " not found. Skip it");
            }
                Set<String> set = new HashSet<>(Doc_i);
                ////////////////////////////////////////////////////////
            
            for(int j=i+1;j<=files.length-1;j++)
            {
               
                ////////////////doc[j]////////////////////////////////////
                String[] words_d=null;
                List<String> Doc_j = new ArrayList<String>();
                 try ( BufferedReader file = new BufferedReader(new FileReader(files[j]))) {
                String ln;
                while ((ln = file.readLine()) != null) {
                    words_d = ln.split("\\W+");
                    for (String word : words_d) {
                        word = word.toLowerCase(); 
                        Doc_j.add(word);
                    }
                }
            } catch (IOException e) {
                System.out.println("File " + files[j] + " not found. Skip it");
            }
                  
                 
                 Set<String> set_two = new HashSet<>(Doc_j);
                 //System.out.println(set_two);
                 Set<String> final_set = new HashSet<>();
                final_set.addAll(set);
                final_set.addAll(set_two);
                /*System.out.println(set);
                System.out.println(set_two);*/
                //System.out.println(final_set);  
                
                ////////////////////////////////////lists of 0 or 1//////////
                ArrayList<Integer> d_one = new ArrayList<Integer>();
                ArrayList<Integer> d_two = new ArrayList<Integer>();
                d_one = zero_or_one(final_set,set);
                d_two = zero_or_one(final_set,set_two);
               /* System.out.println(d_one);
                System.out.println(d_two);*/
                
                /////////////////////////dot_product-/////////////////////////////
                int dot_product = dot_product(d_one,d_two);
                //System.out.println(dot_product);
                /////////////////////// calculate magnitude of vectors//////////////
                float magintude_1=magnitude_of_vectors(d_one);
                float magintude_2=magnitude_of_vectors(d_two);
               // System.out.println(magintude_1);
                /////////////////////////calcute cosine similarity//////////////////
                float cosine_similarity = dot_product / (magintude_1*magintude_2);
                //System.out.println(cosine_similarity);
                //D1 and D2 cosine similarity = 0.9
                System.out.println(files[i] + "   and   " + files[j] + "  cosine similarity =  " + cosine_similarity);
                
            }
        }
    }
    public float magnitude_of_vectors(ArrayList<Integer> a)
    {
        float s =0;
        for(int i=0;i<a.size();i++)
        {
            
            s = s +  (float) Math.pow(a.get(i), 2);
        }
        return (float) Math.sqrt(s);
    }
    public int dot_product(ArrayList<Integer> a1,ArrayList<Integer> a2)
    {
        int sumition = 0;
        for(int i=0;i<a1.size();i++)
        {
            sumition = sumition + a1.get(i)*a2.get(i);
        }
        return sumition;
    }
       public ArrayList<Integer> zero_or_one(Set<String> h1,Set<String> h2)
       {
           ArrayList<Integer> f = new ArrayList<Integer>();
           for (String i : h1) 
           {
               if(h2.contains(i)){
                f.add(1);
               }else{
                   f.add(0);
               }
           } 
           return f;
       }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Main m = new Main();
           m.cosine_simliartity(new String[]{
            "E:\\docs (1)\\100.txt", 
            "E:\\docs (1)\\101.txt",
            "E:\\docs (1)\\102.txt",
            "E:\\docs (1)\\103.txt",            
        });
    }
    
}
