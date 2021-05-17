public class Assignment {
    public static void main(String[] args) {

        int test = 6580;

        String[] pName = nameGenerator(test);
        DOB[] pDOB = new DOB[test];
        for (int i = 0; i <test; i++) {
            pDOB[i] = DOB.randomDOB();
        }

        long start = System.nanoTime();
        int seniors = rearrangeParticipants(pName, pDOB, pName.length);
        long end = System.nanoTime();
        System.out.println("rearrangeParticipants took "+(end-start)+" ms to complete with "+test+" people");
        int nonSeniors = test-seniors;
        System.out.println("There are "+seniors+" seniors");

        System.out.println("\nTesting displaySeniorsIncreasingOrder...");
        displaySeniorsIncreasingOrder(pName, pDOB, seniors);
        System.out.println("\nTesting displayNonSeniorsIncreasingOrder...");
        displayNonSeniorsIncreasingOrder(pName, pDOB, nonSeniors, test);
        System.out.println("\nTesting displayIncreasingOrder...");
        displayIncreasingOrder(pName, pDOB, seniors, test);
        

        System.out.println("rearrangeParticipants took "+(end-start)/(double)1000000+" ms to complete with "+test+" people");

    }
    public static int rearrangeParticipants(String[] pName, DOB[] pDOB, int n){
        if(n==0) return 0; //base case
        for(int i=0; i<n; i++) {
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
        }
        if(pDOB[n-1].isSenior()) {
            return(1+rearrangeParticipants(pName, pDOB, n-1));
        }
        return (rearrangeParticipants(pName, pDOB, n-1));
    }

    public static void displaySeniorsIncreasingOrder(String[] pName, DOB[] pDOB, int seniors){
        if(seniors==0) return;
        System.out.println(pName[seniors-1]+"\t"+pDOB[seniors-1]);
        displaySeniorsIncreasingOrder(pName, pDOB,seniors-1);
    }

    public static void displayNonSeniorsIncreasingOrder(String[] pName, DOB[] pDOB, int nonSeniors, int n){
        if(nonSeniors==0) return;
        displayNonSeniorsIncreasingOrder(pName, pDOB, nonSeniors-1, n-1);
        System.out.println(pName[n-1]+"\t"+pDOB[n-1]);
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
