package programmers.level1;

import java.util.Arrays;
import java.util.Collections;

/**
 * 정수의 각 자릿수 정렬하기
 * v1. 정수->문자열->String[]->Arrays.sort(?,?)로 내림차순 정렬
 * 1) String[] strings = String.valueOf(n).split("");
 * 2) Arrays.sort(strings, Collections.reverseOrder()) 로 Array 내림차순 정렬
 * >> char[] chars = String.valueOf(n).toCharArray(); 를 사용하지 못한 이유
 * - Arrays.sort(chars) 는 가능하지만, Arrays.sort(chars, Collections.reverseOrder()) 는 불가하기 때문!!
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12933/solution_groups?language=java">풀이 참고</a>
 * @See <a href="https://codechacha.com/ko/java-sorting-array/">Array(배열) 정렬하기</a>
 */
class Solution12933 {
    public static void main(String[] args) {
        // 873211
        long n1 = 118372;
        long answer1 = solution(n1);
        System.out.println(answer1);
    }

    /**
     * @param n 1이상 8000000000 이하인 자연수
     * @return n의 각 자릿수를 큰것부터 작은 순으로 정렬한 새로운 정수를 리턴
     */
    public static long solution(long n) {
        String[] strings = String.valueOf(n).split("");   // 1. long->String->String[] 변환
        Arrays.sort(strings, Collections.reverseOrder());       // 2. string[] 내림차순 정렬

        // 3. StringBuilder 로 이어붙이기
        StringBuilder sb = new StringBuilder();
        for(String x : strings) {
            sb.append(x);
        }
        // 4. StringBuilder->String->Long 변환
        return Long.parseLong(sb.toString());
    }
}