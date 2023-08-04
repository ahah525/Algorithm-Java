package programmers.level1;


/**
 * [문제명] [1차] 비밀지도
 * [풀이 시간] 35분 / 11분 / 8분
 * [한줄평] 비트 연산을 활용해야겠다는 것은 빨리 떠올렸는데, 진수 변환/앞에 0 채우기 등 문법 공부를 더 해야겠다고 느꼈다. 참고로 2번 방식이 약 7~8초 이상 느리다.
 * / 비트 연산만 떠올리면 쉽게 풀 수 있는 문제였다.
 * / 비트 연산으로 쉽게 풀 수 있는 문제로 더 이상 안풀어봐도 될 문제다.
 * 1_v1. 문자열, 비트(성공) -> 빠름
 * [풀이] 비트 OR 연산을 한 값을 2진수로 변환하고 StringBuilder로 자릿수를 채우고 변환한다.
 * 1_v2. 문자열, 비트(성공)
 * [풀이] String.format(), replaceAll() 으로 0 채우기와 변환(0->" ", 1->"#") 수행
 * 2_v1. 문자열, 비트(성공)
 * [풀이] 1_v2 동일
 * 3_v1. 문자열, 비트(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17681">문제</a>
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12938/solution_groups?language=java">다른 풀이</a>
 */
class Solution17681 {
    public static void main(String[] args) {
        // ["#####","# # #", "### #", "# ##", "#####"]
        int n1 = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};
        System.out.println(solution(n1, arr1, arr2));

        // ["#####","# # #", "### #", "# ##", "#####"]
        int n2 = 6;
        int[] arr3 = {46, 33, 33 ,22, 31, 50};
        int[] arr4 = {27 ,56, 19, 14, 14, 10};
        System.out.println(solution(n2, arr3, arr4));
    }

    // 1_v1, 3_v1
    /**
     * @param n 지도의 한 변 크기
     * @param arr1 지도1
     * @param arr2 지도2
     * @return 원래의 비밀지도를 해독하여 '#', 공백으로 구성된 문자열 배열
     */
    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i = 0; i < n; i++) {
            // 1. OR 연산값 -> 2진수 변환
            String s = Integer.toBinaryString(arr1[i] | arr2[i]);
            StringBuilder sb = new StringBuilder();
            // 2. 앞에 0(' ') 채우기
            for(int j = 0; j < n - s.length(); j++) {
                sb.append(' ');
            }
            // 3. 변환(0 -> ' ', 1 -> '#')
            for(char c : s.toCharArray()) {
                if(c == '0') sb.append(' ');
                else sb.append('#');
            }
            answer[i] = sb.toString();
        }
        return answer;
    }

    // 1_v2, 2_v1
    public static String[] solution2(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i = 0; i < n; i++) {
            // 1. OR 연산값 -> 2진수 변환 -> 앞에 0 채우기
            String format = "%" + n + "s"; // n 자리
            String s = String.format(format, Integer.toBinaryString(arr1[i] | arr2[i]));
            // 2. 변환 후 저장
            answer[i] = s.replaceAll("0", " ").replaceAll("1", "#");
        }
        return answer;
    }
}