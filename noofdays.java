import java.util.*;

class GeeksForGeeks {
    private static int minOperations(int src, int target) {
        LinkedList<Integer> queue = new LinkedList<Integer>();

        queue.offer(src);

        int level = 0;
        while (!queue.isEmpty()) {
           
            int count = queue.size();
           
            while(count-- > 0){
                int result = queue.poll();
               
                if (result == target) {
                    return level;
                }  

                int mul = result * 2;
                int sub = result - 1;

                if (mul > 0 && mul < 1000) {
                    queue.offer(mul);
                }
                if (sub > 0 && sub < 1000) {
                    queue.offer(sub);
                }
            }
            level++;
        }
        return -1;
    }
    public static void main(String[] args) {
        int x = 4, y = 7;
        System.out.println(minOperations(x, y));
    }
}