public class Assignment {
    public static void main(String[] args) {

        String[] pName = {"Linda","Sam","Roger","Alfred","Roberto","Melissa","Brian","Thomas","Leslie","Maria"};
        DOB[] pDOB = new DOB[10];
        for (int i = 0; i <10; i++) {
            pDOB[i] = DOB.randomDOB();
        }

        int cool = rearrangeParticipants(pName, pDOB, pName.length);
        displayResult(pName, pDOB);
        System.out.println(cool);
        
    }
    public static int rearrangeParticipants(String[] pName, DOB[] pDOB, int n){
        if(n==0) return 0; //base case
        displayResult(pName, pDOB);
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
        if(n==nonSeniors) return;
        displayNonSeniorsIncreasingOrder(pName, pDOB, nonSeniors, n-1);
        System.out.println(pName[n-1]+"\t"+pDOB[n-1]);
    }

    public static void displayIncreasingOrder(String[] pName, DOB[] pDOB, int seniors, int n){

    }
    public static void displayResult(String[] pName, DOB[] pDOB) {
        for(int i=0; i<pName.length; i++) {
            System.out.println(i+"\t"+pName[i]+"\t"+pDOB[i]);
        }
        System.out.println();
    }
    public static String[] nameGenerator(int n){
        String[] names = new String[n];
        for(int i=0; i<n; i++){
            names[i]=Integer.toString(i);
        }
        return names;
    }
}
