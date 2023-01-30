package programmers.level1;


/**
 * [문제명] 푸드 파이트 대회
 * [풀이시간] 5분
 * [한줄평] 문제는 길지만 쉽게 풀 수 있는 문제였다.
 * v1. StringBuilder(성공)
 * - 문자열을 이어붙이는 연산의 효율성을 고려해 StringBuilder 를 사용했다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/">문제</a>
 */
class Solution134240 {
    public static void main(String[] args) {
        // "1223330333221"
        int[] food1 = {1, 3, 4, 6};
        System.out.println(solution(food1));

        // "111303111"
        int[] food2 = {1, 7, 1, 2};
        System.out.println(solution(food2));
    }

    /**
     * @param food 수웅이가 준비한 음식의 양을 칼로리가 적은 순서대로 나타내는 정수 배열
     * @return 대회를 위한 음식의 배치를 나타내는 문자열
     */
    public static String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < food.length; i++) {
            for(int j = 0; j < food[i] / 2; j++) {
                sb.append(i);
            }
        }
        return sb.toString() + "0" + sb.reverse().toString();
    }
}