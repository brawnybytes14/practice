import java.util.*;

class LCS {
    public static void main(String[] args) throws Exception {
        // BufferReader br = new BufferReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();

            String str1 = sc.next();
            String str2 = sc.next();

            System.out.println(getMaxLength(n1, n2, str1, str2));
        }
        sc.close();
    }

    static int getMaxLength(int n1, int n2, String str1, String str2) {
        int[][] result = new int[n1 + 1][n2 + 1];

        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                if (i == 0 || j == 0) {
                    result[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    result[i][j] = 1 + result[i - 1][j - 1];
                } else {
                    result[i][j] = Math.max(result[i][j - 1], result[i - 1][j]);
                }
            }
        }

        return result[n1][n2];
    }
}