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
        if(n==0) return 0;
        displayResult(pName, pDOB);
        for(int i=0; i<n; i++) {
            if(pDOB[n-1].isYounger(pDOB[i]) && !pDOB[i].isSenior()) {
                DOB temp = new DOB(pDOB[i]);
                String tmp = pName[i];
                pDOB[i]=pDOB[n-1];
                pName[i]=pName[n-1];
                pDOB[n-1]=temp;
                pName[n-1]=tmp;
            }
            else if(pDOB[i].isYounger(pDOB[n-1]) && pDOB[n-1].isSenior()){
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
    public static void displayResult(String[] pName, DOB[] pDOB) {
        for(int i=0; i<pName.length; i++) {
            System.out.println(i+"\t"+pName[i]+"\t"+pDOB[i]);
        }
        System.out.println();
    }
}
