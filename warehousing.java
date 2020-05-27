
//import for Scanner and other utility classes
import java.util.*;

class TestClass {
    public static void main(String args[]) throws Exception {

        // Scanner
        Scanner s = new Scanner(System.in);
        Hashtable<String, Integer> h = new Hashtable<String, Integer>();
        String[] units = s.nextLine().split(",");
        int n = units.length;
        String[][] arr = new String[units.length - 1][4];

        for (int i = 0; i < n - 1; i++) {
            String[] values = s.nextLine().split(" ");
            arr[i][0] = "1";
            arr[i][1] = values[0]; // unit
            arr[i][2] = values[2]; // coeff
            arr[i][3] = values[3]; // unit
        }

        // initialize with LCM
        for (int j = 0; j < n; j++) {
            int ans = 1;
            for (int i = 0; i < n - 1; i++) {
                int coeff1 = Integer.parseInt(arr[i][0]);
                int coeff2 = Integer.parseInt(arr[i][2]);
                if (units[j].equals(arr[i][1])) {
                    ans = lcm(ans, coeff1);
                } else if (units[j].equals(arr[i][3])) {
                    ans = lcm(ans, coeff2);
                }
            }
            h.put(units[j], ans);
        }

        boolean flag = true;
        while (flag) {

            flag = false;
            for (int j = 0; j < n - 1; j++) {
                int lcm1 = h.get(arr[j][1]);
                int lcm2 = h.get(arr[j][3]);

                int value1 = Integer.parseInt(arr[j][0]);
                int value2 = Integer.parseInt(arr[j][2]);

                int multiplier1 = lcm1 / value1;
                int multiplier2 = lcm2 / value2;

                if (multiplier1 * multiplier2 > 1)
                    flag = true;

                value1 = value1 * multiplier1 * multiplier2;
                value2 = value2 * multiplier1 * multiplier2;

                arr[j][0] = Integer.toString(value1);
                arr[j][2] = Integer.toString(value2);

                h.put(arr[j][1], value1);
                h.put(arr[j][3], value2);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(h.entrySet());

        // sort the entries based on the value by custom Comparator
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry1.getValue().compareTo(entry2.getValue());
            }
        });

        Map<String, Integer> mapSortedByValues = new LinkedHashMap<String, Integer>();

        String[] result = new String[n];
        int k = 0;
        // put all sorted entries in LinkedHashMap
        for (Map.Entry<String, Integer> entry : list) {
            // mapSortedByValues.put(entry.getKey(), entry.getValue());
            result[k++] = entry.getValue() + entry.getKey();
        }

        System.out.println(String.join(" = ", result));
    }

    static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // method to return LCM of two numbers
    static int lcm(int a, int b) {
        if (a < b)
            return (a * b) / gcd(a, b);
        return (a * b) / gcd(b, a);
    }
}
