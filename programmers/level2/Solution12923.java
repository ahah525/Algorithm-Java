package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] 숫자 블록
 * [풀이시간] 1시간 10분(17분 + 53분) / (43분)
 * [한줄평] 반례를 찾기가 너무 어려웠고 결국 풀이를 보고 해결했던 문제다.
 * 1_v1. (실패 - 정확성 1~14 실패, 효율성 1~6 실패)
 * 1_v2. (성공)
 * 2_v1. 구현(실패 - 정확성 13 실패, 효율성 1~6 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12923">문제</a>
 * @See <a href="https://ondol-diary.tistory.com/43">풀이 참고</a>
 */
class Solution12923 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    int MAX = 10000000; // 블록에 적힌 숫자의 최댓값

    // 1_v1
    /**
     * @param begin 시작점
     * @param end 끝점
     * @return 그 구간에 깔려 있는 블록의 숫자 배열
     */
    public int[] solution(long begin, long end) {
        int s = (int) begin;
        int e = (int) end;
        int[] answer = new int[e - s + 1];
        for(int i = s; i <= e; i++) {
            answer[i - s] = getCnt(i);
        }
        return answer;
    }

    public int getCnt(int n) {
        if(n == 1) return 0;
        // Math.sqrt(n)이하의 약수 모음
        List<Integer> list = new ArrayList<>();
        // (i), (n / i): 블록에 적힌 숫자
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                // 위 조건을 만족하는 i 중에서 i가 최솟값일 때 (n / i)가 최댓값이 된다.
                if(n / i <= MAX) return n / i;
                list.add(i);
            }
        }
        return list.size() == 0 ? 1 : list.get(list.size() - 1);
    }

    // 2_v1
    public int[] solution2(long begin, long end) {
        int b = (int) begin;
        int e = (int) end;
        int n = e - b + 1;
        int[] answer = new int[n];
        for(int i = 1; i <= e / 2; i++) {
            int s = Math.max(2, (int) Math.ceil((double) b / i));
            // System.out.println(s);
            while(true) {
                if(s * i > e) break;
                answer[s * i - b] = i;
                s++;
            }
            // System.out.println(Arrays.toString(answer));
        }
        return answer;
    }
}