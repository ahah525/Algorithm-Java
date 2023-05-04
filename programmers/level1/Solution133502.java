package programmers.level1;


import java.util.Stack;

/**
 * [문제명] 햄버거 만들기
 * [풀이시간] 40분(10분 + 10분 + 20분) / 17분
 * [한줄평] 어떻게 하면 효율적으로 풀 수 있을지 고민을 해야했던 문제였다. 다른 풀이를 보고 배열로도 풀수 있구나라고 깨달았다..
 * / Stack으로 풀면 쉽게 풀 수 있는 문제였다.
 * 1_v1. String.replace()(실패 - 3~12, 18 실패)
 * 1_v2. StringBuilder.indexOf() + delete() (실패 - 4, 5, 12 시간초과)
 * 1_v3. Stack(성공)
 * 1_v4. int[](성공) -> 빠름
 * 2_v1. Stack(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/133502">문제</a>
 * @See <a href="https://leejams.github.io/%ED%96%84%EB%B2%84%EA%B1%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0/">반례</a>
 */
class Solution133502 {
    public static void main(String[] args) {
        // 3
        int[] ingredient3 = {1, 2, 1, 2, 3, 1, 3, 1, 2, 3, 1, 2, 3, 1};
        System.out.println(solution(ingredient3));
    }

    // 1_v3, 2_v1
    /**
     * @param ingredient 상수에게 전해지는 재료의 정보를 나타내는 정수 배열
     * @return 상수가 포장하는 햄버거의 개수
     */
    public static int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for(int n : ingredient) {
            stack.push(n);
            if(n == 1 && stack.size() >= 4) {
                int a = stack.pop();
                int b = stack.pop();
                int c = stack.pop();
                int d = stack.pop();
                if(b == 3 && c == 2 && d == 1) {
                    answer++;
                } else {
                    stack.push(d);
                    stack.push(c);
                    stack.push(b);
                    stack.push(a);
                }
            }
        }
        return answer;
    }

    // 1_v4
    public static int solution2(int[] ingredient) {
        int answer = 0;
        int[] stack = new int[ingredient.length];
        int i = 0;
        for(int n : ingredient) {
            stack[i] = n;
            if(i >= 3 && stack[i] == 1 && stack[i - 1] == 3 && stack[i - 2] == 2 && stack[i - 3] == 1) {
                i -= 3;
                answer++;
            } else {
                i++;
            }
        }
        return answer;
    }
}