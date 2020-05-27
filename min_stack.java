import java.util.*;

class GfG {
    int minEle = -1;
    Stack<Integer> s = new Stack<Integer>();

    int getMin() {
        return minEle;
    }

    int pop() {
        if (!s.empty()) {
            int top = s.pop();
            if (s.empty()) {
                minEle = -1;
                return top;
            }

            if (top < minEle) {
                int previous = minEle;
                minEle = minEle - top;
                return previous;

            }
            return top;
        }

        return -1;
    }

    void push(int x) {
        if (s.empty()) {
            s.push(x);
            minEle = x;
        } else {
            if (x < minEle) {
                s.push(x - minEle);
                minEle = x;

            } else {
                s.push(x);
            }
        }
    }
}