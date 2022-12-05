package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] 2개 이하로 다른 비트
 * [한줄평]
 * v1. (실패-10, 11 시간초과)
 */
class Solution77885 {

    public static void main(String[] args) {
        // [3, 11]
        long[] numbers = {2, 7};
        System.out.println(solution(numbers));
    }

    /**
     * @param numbers 정수들이 담긴 배열(1 ≤ 길이 ≤ 100,000)
     * @return numbers의 모든 수들에 대하여 각 수의 f 값을 배열에 차례대로 담아 return
     */
    public static List<Long> solution(long[] numbers) {
        List<Long> answer = new ArrayList<>();
        for(long x : numbers) {
            answer.add(f(x));
        }
        return answer;
    }

    /**
     * @param x 0 ≤ x ≤ 10^15
     * @return x보다 크고 x와 비트가 1~2개 다른 수들 중에서 제일 작은 수
     */
    public static long f(long x) {
        // 1. x -> 2진수 변환
        String s1 = Long.toBinaryString(x);
        System.out.println("s1 = " + s1);
        // 2. x값을 1씩 증가하며 비트값 차이가 1~2개 이내인 수 찾기
        // 모두 1으로 이루어져 있으면 앞에 0 붙이기
        if(!s1.contains("0"))
            s1 = "0" + s1;
        long n = x;
        boolean isFind = false;
        while(!isFind) {
            String s2 = Long.toBinaryString(++n);
            System.out.println("s2 = " + s2);
            int diff = 0;   // 비트가 다른 것의 개수
            for(int i = 0; i < s1.length(); i++) {
                if(s1.charAt(i) != s2.charAt(i)) diff++;
                if(diff >= 3) break;
            }
            if(diff <= 2) isFind = true;
        }
        return n;
    }
}