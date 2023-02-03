package programmers.level3;


import java.util.Arrays;

/**
 * [문제명] 야근 지수
 * [풀이시간] (30분)
 * [한줄평]
 * v1. 단순 반복문(실패 - 정확성 9, 13 만 성공, 효율성 모두 실패)
 * - while 문 내에서 Arrays.sort() 로 매번 정렬을 하다보니 시간초과가 난 것 같다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/">문제</a>
 */
class Solution12927 {
    public static void main(String[] args) {
        //
        int[] works1 = {4, 3, 3};
        System.out.println(solution(4, works1));

        // 6
        int[] works2 = {2, 1, 2};
        System.out.println(solution(1, works2));
    }

    public static long solution(int n, int[] works) {
        long answer = 0;
        int t = 0;  // 일한 시간
        while(t < n) {
            int max = works[0];
            if(max == 0)
                break;
            Arrays.sort(works);
            for(int i = works.length - 1; i >= 0; i--) {
                if(t < n && max == works[i]) {
                    works[i]--;
                    t++;
                } else {
                    break;
                }
            }
        }
        for(int w : works) {
            answer += Math.pow(w, 2);
        }
        return answer;
    }
}