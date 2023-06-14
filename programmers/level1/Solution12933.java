package programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * [문제명] 정수의 각 자릿수 정렬하기
 * [풀이시간] / 4분
 * [한줄평] / 비슷한 문제를 풀었던 경험이 있어서 쉽게 풀었다.
 * 1_v1. (성공)
 * [풀이] 정수를 문자열로 변환하고 split("")으로 각 자리수를 구한다
 * 1) String[] strings = String.valueOf(n).split("");
 * 2) Arrays.sort(strings, Collections.reverseOrder()) 로 Array 내림차순 정렬
 * >> char[] chars = String.valueOf(n).toCharArray(); 를 사용하지 못한 이유
 * - Arrays.sort(chars) 는 가능하지만, Arrays.sort(chars, Collections.reverseOrder()) 는 불가하기 때문!!
 * 2_v1. (성공)
 * [풀이] 10으로 나눈 나머지를 구하는 것을 반복해서 각 자리수를 구한다
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

    // 1_v1
    /**
     * @param n 1이상 8000000000 이하인 자연수
     * @return n의 각 자릿수를 큰것부터 작은 순으로 정렬한 새로운 정수를 리턴
     */
    public static long solution(long n) {
        // 1. 정수->문자열 변환, 각 자리수 구하기
        String[] arr = Long.toString(n).split("");
        // 2. 내림차순 정렬
        Arrays.sort(arr, Collections.reverseOrder());
        // 3. 배열->문자열 변환
        StringBuilder sb = new StringBuilder();
        for(String s : arr) {
            sb.append(s);
        }
        // 4. 문자열->정수 변환
        return Long.parseLong(sb.toString());
    }

    // 2_v1
    public long solution2(long n) {
        // 1. 각 자리수 구하기
        List<Integer> list = new ArrayList<>();
        while(n != 0) {
            list.add((int) (n % 10));
            n /= 10;
        }
        // 2. 내림차순 정렬
        Collections.sort(list, Collections.reverseOrder());
        // 3. 리스트->문자열 변환
        StringBuilder sb = new StringBuilder();
        for(int num : list) {
            sb.append(num);
        }
        // 4. 문자열->정수 변환
        return Long.parseLong(sb.toString());
    }
}