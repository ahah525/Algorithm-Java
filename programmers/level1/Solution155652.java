package programmers.level1;


import java.util.Arrays;

/**
 * [문제명] 둘만의 암호
 * [풀이시간] (35분)
 * [한줄평]
 * 1_v1. (실패-3~8, 10~14, 16~19 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/155652">문제</a>
 */
class Solution155652 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param s 문자열(5 ≤ s의 길이 ≤ 50)
     * @param skip 문자열(1 ≤ skip의 길이 ≤ 10)
     * @param index 자연수(1 ≤ index ≤ 20)
     * @return 위 규칙대로 s를 변환한 결과
     */
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        // 오름차순 정렬
        char[] arr = skip.toCharArray();
        Arrays.sort(arr);
        skip = new String(arr);
        //
        for(char c : s.toCharArray()) {
            // (c + 1) ~ end 사이에 skip 해야하는 문자가 몇개인지
            char end = (char) (c + index);
            if(end > 'z') end = (char) ('a' + (end - 'z' - 1) % 26);
            int n = 0;
            //
            if(c + 1 <= end) {
                for(char a : arr) {
                    // ()
                    if(a <= c) continue;
                    if(a > end) break;
                    n++;
                }
            } else {
                for(char a : arr) {
                    if(a <= end || c + 1 <= a)
                        n++;
                }
            }
            end = (char) (end + n);
            if(end > 'z') end = (char) ('a' + (end - 'z' - 1) % 26);
            sb.append(end);
        }
        return sb.toString();
    }
}