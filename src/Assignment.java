// ----------------------------------------------------------------
// Assignment 1
// Written by: Adamo Orsini (40174716) and Koosha Gholipour (40176826)
// ----------------------------------------------------------------

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Assignment {
    
    public static void main(String[] args) {

        PrintStream pw = null;

        int[] test = {10,20,30,40,50,60,70,80,90,100,200,1000,2000,10000,100000,1000000};
        for(int size:test){

            try{
                pw = new PrintStream(new FileOutputStream("out"+size+".txt"));
            }
            catch(IOException e){
                e.printStackTrace();
            }
            System.setOut(pw);
    
            String[] pName = nameGenerator(size); //make the names of all the participants as numbers
            DOB[] pDOB = new DOB[size];
            for (int i = 0; i <size; i++) {
                pDOB[i] = DOB.randomDOB(); //makes a random DOB per person
            }
    
            System.out.println("\ntesting rearrangeParticipants..."); //tests
            long start = System.nanoTime();
            int seniors = rearrangeParticipants(pName, pDOB, size, 0);
            long end = System.nanoTime();
            System.out.println("rearrangeParticipants took "+(end-start)/1000000.0+" ms to complete with "+size+" people");
    
            int nonSeniors = size-seniors;
            System.out.println("There are "+seniors+" seniors");
    
            System.out.println("\ntesting displaySeniorsIncreasingOrder...");
            start = System.nanoTime();
            displaySeniorsIncreasingOrder(pName, pDOB, seniors);
            end = System.nanoTime();
            System.out.println("displaySeniorsIncreasingOrder took "+(end-start)/1000000.0+" ms to complete with "+size+" people");
    
    
            System.out.println("\ntesting displayNonSeniorsIncreasingOrder...");
            start = System.nanoTime();
            displayNonSeniorsIncreasingOrder(pName, pDOB, nonSeniors, size);
            end = System.nanoTime();
            System.out.println("displayNonSeniorsIncreasingOrder took "+(end-start)/1000000.0+" ms to complete with "+size+" people");
    
            
            System.out.println("\ntesting displayIncreasingOrder...");
            start = System.nanoTime();
            displayIncreasingOrder(pName, pDOB, seniors, size);
            end = System.nanoTime();
            System.out.println("displayIncreasingOrder took "+(end-start)/1000000.0+" ms to complete with "+size+" people");
        }
    }
    public static int rearrangeParticipants(String[] pName, DOB[] pDOB, int n, int i){
        if(n==0) return 0; //base case
        if(i<n) {
            if(pDOB[n-1].isYounger(pDOB[i]) && !pDOB[i].isSenior()) { //if we find a non senior in the loop and the person at n is younger, 
                DOB temp = new DOB(pDOB[i]);                          //swap because we want oldest non senior at the bottom
                String tmp = pName[i];
                pDOB[i]=pDOB[n-1];
                pName[i]=pName[n-1];
                pDOB[n-1]=temp;
                pName[n-1]=tmp;
            }
            else if(pDOB[i].isYounger(pDOB[n-1]) && pDOB[n-1].isSenior()){ //we want the exact opposite if the person at n is a senior
                DOB temp = new DOB(pDOB[i]);
                String tmp = pName[i];
                pDOB[i]=pDOB[n-1];
                pName[i]=pName[n-1];
                pDOB[n-1]=temp;
                pName[n-1]=tmp;
            }
            return(rearrangeParticipants(pName, pDOB, n,i+1));
        }
        if(pDOB[n-1].isSenior()) {
            return(1+rearrangeParticipants(pName, pDOB, n-1,0));
        }
        return (rearrangeParticipants(pName, pDOB, n-1,0));
    }

    public static void displaySeniorsIncreasingOrder(String[] pName, DOB[] pDOB, int seniors){
        if(seniors==0) return;
        System.out.println(pName[seniors-1]+"\t"+pDOB[seniors-1]); //since seniors is in decreasing order, we print from bottom to top
        displaySeniorsIncreasingOrder(pName, pDOB,seniors-1);
    }

    public static void displayNonSeniorsIncreasingOrder(String[] pName, DOB[] pDOB, int nonSeniors, int n){
        if(nonSeniors==0) return;
        displayNonSeniorsIncreasingOrder(pName, pDOB, nonSeniors-1, n-1); //non-seniors are in increasing order and we count up from the 
        System.out.println(pName[n-1]+"\t"+pDOB[n-1]);                    //bottom so we call the function before writing to output in order because the recursion counts from n to non-seniors
    }

    public static void displayIncreasingOrder(String[] pName, DOB[] pDOB, int seniors, int n){
        displayNonSeniorsIncreasingOrder(pName, pDOB, n-seniors, n);
        displaySeniorsIncreasingOrder(pName, pDOB, seniors);
    }
    public static String[] nameGenerator(int n){
        String[] names = new String[n];
        for(int i=0; i<n; i++){
            names[i]=Integer.toString(i);
        }
        return names;
    }

}
