package programmers.level2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [문제명] 가장 큰 수
 * [풀이시간] / 22분
 * [한줄평] 정렬을 해서 풀어야겠다고 빨리 떠올렸으나 반례 힌트를 보고서 해결할 수 있었던 문제였다. 다시 풀었을 때도 또 똑같은 케이스에서 막혔던 문제다.
 * 1_v1. 입력이 모두 0인 경우를 고려하지 않음(실패-11)
 * 1_v2. 입력이 모두 0인 경우 "0"으로 반환(성공)
 * 2_v1. 입력이 모두 0인 경우를 고려하지 않음(실패-11)
 * 2_v2. 입력이 모두 0인 경우 "0"으로 반환(성공)
 * <a href="https://school.programmers.co.kr/questions/34081">반례</a>
 */
class Solution42746 {
    public static void main(String[] args) {
        // "6210"
        int[] numbers1 = {6, 10, 2};
        System.out.println(solution(numbers1));

        // "9534330"
        int[] numbers2 = {3, 30, 34, 5, 9};
        System.out.println(solution(numbers2));

        // "0"
        int[] numbers5 = {0, 0, 0, 0};
        System.out.println(solution(numbers5));

        // "100010001000"
        int[] numbers6 = {1000, 1000, 1000};
        System.out.println(solution(numbers6));
    }

    /**
     *
     * @param numbers 0 또는 양의 정수가 담긴 배열
     * - 길이는 1 이상 100,000 이하
     * - 원소는 0 이상 1,000 이하
     * @return 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return
     */
    public static String solution(int[] numbers) {
        int zero = 0;   // 0의 개수
       // int -> String 변환(String 값 으로 정렬하기 위함)
        List<String> list = new ArrayList<>();
        for(int n : numbers) {
            list.add(Integer.toString(n));
            if(n == 0) zero++;
        }
        // 모두 0인 경우 "0" 바로 리턴
        if(zero == numbers.length) return "0";

        /**
         * 가정) o1 = 3, o2 = 30
         * n1 = 330, n2 = 303
         * n2 - n1 = -27 -> o1, o2 의 자리를 바꾸지 않는다
         */
        // 1. 정렬 수행
        Collections.sort(list, (o1, o2) -> {
            // 이어 붙인 결과값으로 비교
            int n1 = Integer.parseInt(o1 + o2);
            int n2 = Integer.parseInt(o2 + o1);
            // n2 - n1 < 0, n2 < n1 바꾸지 않는다
            return n2 - n1;
        });
        // 2. 정렬된 결과를 이어 붙여 String 으로 반환
        StringBuilder sb = new StringBuilder();
        for(String s : list) {
//            System.out.println(s);
            sb.append(s);
        }
        return sb.toString();
    }
}