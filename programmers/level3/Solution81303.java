package programmers.level3;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [문제명] 표 편집
 * [풀이시간] (50분)
 * [한줄평]
 * 1_v1. (실패 - 정확성 테스트 5,9,11~22,24,27,29~30 실패, 효율성 테스트 모두 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/">문제</a>
 */
class Solution81303 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    public String solution(int n, int k, String[] cmd) {
        boolean[] deleted = new boolean[n];
        Stack<int[]> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int p = k;
        // 1.
        for(int i = 0; i < n; i++) {
            list.add(i);
        }
        // 2.
        for(String s : cmd) {
            StringTokenizer st = new StringTokenizer(s);
            String c = st.nextToken();
            switch(c) {
                case "U":
                    p = Math.max(0, p - Integer.parseInt(st.nextToken()));
                    break;
                case "D":
                    p = Math.min(n - 1, p + Integer.parseInt(st.nextToken()));
                    break;
                case "C":
                    stack.push(new int[] {p, list.get(p)});
                    deleted[list.get(p)] = true;
                    list.remove(p);
                    if(p == list.size()) p--;
                    break;
                case "Z":
                    int[] arr = stack.pop();
                    if(p >= arr[1]) p++;
                    list.add(arr[0], arr[1]);
                    deleted[list.get(arr[0])] = false;
                    break;
            }
            // System.out.println(p);
        }
        // System.out.println(list);

        for(boolean status : deleted) {
            sb.append((status) ? "X" : "O");
        }
        return sb.toString();
    }
}