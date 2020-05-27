//import for Scanner and other utility classes
import java.util.*;

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class TestClass {
    public static void main(String args[] ) throws Exception {

        //Scanner
        Scanner s = new Scanner(System.in);
        String strT = s.nextLine(); 
        int t = Integer.parseInt(strT); 

        while(t-->0){
                String[] nk = s.nextLine().split(" ");
                int n = Integer.parseInt(nk[0]);
                int k = Integer.parseInt(nk[1]);

                String strOne = s.nextLine();  
                String strTwo= s.nextLine(); 

                char[] arrOne = strOne.toCharArray();
                char[] arrTwo = strTwo.toCharArray();

                for(int i = 0; i < k; i++){
                    String[] lr = s.nextLine().split(" ");
                    int l = Integer.parseInt(lr[0]);
                    int r = Integer.parseInt(lr[1]);

                    int compare1 = Character.compare(arrTwo[r], arrTwo[l]);
                    int compare2 = Character.compare(arrTwo[r], arrOne[l]);  
                    if(compare1 < 0 && compare2 < 0){
                        char temp = arrTwo[l];
                        arrTwo[l] = arrTwo[r];
                        arrTwo[r] = temp;
                    }
                }

                //write your code here

                for(int i = 0; i < n; i++){
                     int compare3 = Character.compare(arrTwo[i], arrOne[i]);
                     if(compare3 < 0){
                        arrOne[i] = arrTwo[i];
                    }
                }

                String output = String.valueOf(arrOne);
                System.out.print(output+"\n"); 
        }
    }
}

