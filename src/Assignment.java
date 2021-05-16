public class Assignment {
    public static void main(String[] args) {

        String[] pName = {"Linda","Sam","Roger","Alfred","Roberto","Melissa","Brian","Thomas","Leslie","Maria"};
        DOB d0,d1,d2,d3,d4,d5,d6,d7,d8,d9;
        d0 = new DOB(1,1,2003);
        d1 = new DOB(24,2,1940);
        d2 = new DOB(11,12,1995);
        d3 = new DOB(31,3,1980);
        d4 = new DOB(29,6,1950);
        d5 = new DOB(25,7,1945);
        d6 = new DOB(15,7,2002);
        d7 = new DOB(20,7,2004);
        d8 = new DOB(27,4,1990);
        d9 = new DOB(9,5,1941);
        DOB[] pDOB = {d0,d1,d2,d3,d4,d5,d6,d7,d8,d9};

        int cool = rearrangeParticipants(pName, pDOB, pName.length);
        displayResult(pName, pDOB);
        System.out.println(cool);
        
    }
    public static int rearrangeParticipants(String[] pName, DOB[] pDOB, int participants){
        if(participants<=0) return 0;
        displayResult(pName, pDOB);
        if(pDOB[participants-1].isSenior()){
            for(int i=0; i<participants; i++){
                if(!pDOB[participants-1].isYounger(pDOB[i])){
                    DOB temp = new DOB(pDOB[i]);
                    String tmp = pName[i];
                    pDOB[i]=pDOB[participants-1];
                    pName[i]=pName[participants-1];
                    pDOB[participants-1]=temp;
                    pName[participants-1]=tmp;
                }
            }
            return(1+rearrangeParticipants(pName, pDOB, participants-1));
        }
        else{
            for(int i=0; i<participants; i++){
                if(pDOB[participants-1].isYounger(pDOB[i])){
                    DOB temp = new DOB(pDOB[i]);
                    String tmp = pName[i];
                    pDOB[i]=pDOB[participants-1];
                    pName[i]=pName[participants-1];
                    pDOB[participants-1]=temp;
                    pName[participants-1]=tmp;
                }
            }
            return(rearrangeParticipants(pName, pDOB, participants-1));
        }
    }
    public static void displayResult(String[] pName, DOB[] pDOB){
        for(int i=0; i<pName.length; i++){
            System.out.println(i+"\t"+pName[i]+"\t"+pDOB[i]);
        }
        System.out.println();
    }
}
