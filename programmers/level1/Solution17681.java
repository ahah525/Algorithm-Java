package programmers.level1;


/**
 * [문제명] [1차] 비밀지도
 * [풀이 시간] 35분
 * [한줄평] 비트 연산을 활용해야겠다는 것은 빨리 떠올렸는데, 진수 변환/앞에 0 채우기 등 문법 공부를 더 해야겠다고 느꼈다.
 * v1. StringBuilder 와 for 문, if 문으로 0 채우기와 변환 수행(성공)
 * [로직]
 * 1. 비트 OR 연산
 * '#' == 1, ' ' = 0
 * 1) 둘 중 하나라도 1 이면, 1
 * 2) 둘 다 0 이면, 0
 * 2. 연산 결과값을 10진수(int) -> 2진수(String) 변환
 * 3. 자리수를 맞추기 위해 앞에 0(' ') 넣기
 * 4. 변환(0 -> ' ', 1 -> '#') 후 String 으로 저장
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
                if(c == '0')
                    sb.append(' ');
                else
                    sb.append('#');
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}