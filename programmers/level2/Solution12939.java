package programmers.level2;


/**
 * [문제명] 최댓값과 최솟값
 * [풀이시간] 5분
 * [한줄평] 최대, 최솟값을 구하는 아주 기초 문제였다.
 * v1. (성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12939">문제</a>
 */
class Solution12939 {
    public static void main(String[] args) {
        // "1 4"
        String s1 = "1 2 3 4";
        System.out.println(solution(s1));

        // "-4 -1"
        String s2 = "-1 -2 -3 -4";
        System.out.println(solution(s2));

        // "-1 -1"
        String s3 = "-1 -1";
        System.out.println(solution(s3));
    }

    /**
     * @param s 공백으로 구분된 숫자들
     * @return str에 나타나는 숫자 중 최소값과 최대값을 찾아 이를 "(최소값) (최대값)"형태의 문자열을 반환
     */
    public static String solution(String s) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        String[] list = s.split(" ");
        for(String n : list) {
            min = Math.min(Integer.parseInt(n), min);
            max = Math.max(Integer.parseInt(n), max);
        }
        return min + " " + max;
    }
}