package programmers.pccp;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] [PCCP 모의고사 #1] 유전법칙
 * [풀이시간] 1시간
 * [한줄평] 전에 풀었던 12899(124 나라의 숫자) 문제와 접근 방식이 굉장히 유사했던 문제였는데, 접근 방식을 떠올리는게 쉽지 않아 어려웠던 문제였다.
 * v1. 수학적 접근(성공)
 * 이 문제의 핵심은 n세대 p번째 값이 부모를 기준으로 몇번째 자식인지를 반복문을 통해 알아내는 것이다. 그리고 경우의 수에 따라 값을 구할 수 있다.
 * [경우의 수]
 * RR 의 자식 = 모두 RR
 * Rr 의 자식 = RR Rr Rr rr
 * rr 의 자식 = 모두 rr
 * @See <a href="https://school.programmers.co.kr/learn/courses/15008/lessons/121685">문제</a>
 * @See <a href="https://postimg.cc/mhN1phqq">그림 풀이</a>
 */
class Solution121685 {
    public static void main(String[] args) {
        // ["RR"]
        int[][] queries1 = {{3, 5}};
        System.out.println(solution(queries1));

        // ["rr", "Rr"]
        int[][] queries2 =  {{3, 8}, {2, 2}};
        System.out.println(solution(queries2));

        // ["RR", "Rr", "RR"]
        int[][] queries3 =  {{3, 1}, {2, 3}, {3, 9}};
        System.out.println(solution(queries3));

        // ["Rr"]
        int[][] queries4 = {{4, 26}};
        System.out.println(solution(queries4));
    }

    /**
     * @param queries (형질을 알고 싶은 완두콩의 세대, 해당 완두콩이 세대 내에서 몇 번째 개체인지를 나타내는 정수)
     * @return queries에 담긴 순서대로 n 세대의 p 번째 개체의 형질을 문자열 배열에 담아서 return
     */
    public static List<String> solution(int[][] queries) {
        List<String> answer = new ArrayList<>();
        for(int[] query : queries) {
            int n = query[0];
            int p = query[1];
            // 1. n 세대 p번째 수가 1세대 기준으로 몇번째 수(cnt)인지 구하기
            // 1세대인 경우 무조건 0
            int cnt = 0;
            if(n != 1) {
                for(int i = 1; i <= n - 2; i++) {
                    cnt += Math.pow(4, i);
                }
                cnt += p;
            }
            // 2. cnt 번째 수의 값 구하기
            answer.add(find(cnt));
        }
        return answer;
    }

    public static String find(int n) {
        // 1. n 번째 수가 1세대를 기준으로 몇 번째 자식의 자식인지 구하기
        StringBuilder sb = new StringBuilder();
        while(n != 0) {
            int r = n % 4;
            sb.append(r);
            n /= 4;
            if(r == 0)
                n--;
        }
        // 2. 1세대(Rr)부터 몇 번째 자식인지에 따라 값 구하기
        for(int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if(c == '1') {
                // 1. 부모가 Rr일 때, 1번째 자식은 RR -> RR의 자식은 무조건 RR 이므로 바로 리턴
                return "RR";
            } else if(c == '0') {
                // 2. 부모가 Rr일 때, 4번째 자식은 rr -> rr의 자식은 무조건 rr 이므로 바로 리턴
                return "rr";
            }
        }
        // 3. 부모가 Rr일 때, 2,3번째 자식은 Rr
        return "Rr";
    }
}