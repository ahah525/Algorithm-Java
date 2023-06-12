package programmers.level1;

/**
 * [문제명] 문자열 내 특정 문자 개수 구하기
 * [풀이시간] / 1분 30초
 * [한줄평] 더 이상 풀어볼 필요가 없는 단순 구현 문제다.
 * 1_v1. 반복문 이용(추천)
 * 1_v2. 람다식 이용(속도 가장 느림)
 * 1_v3. replaceAll() 이용(속도 느림)
 * - replace(특정 문자열, 변환할 문자열)
 * - replaceAll(정규식, 변환할 문자열)
 * 2_v1. 반복문(성공)
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12916/solution_groups?language=java">풀이 참고</a>
 * @See <a href="https://hianna.tistory.com/540">문자열에서 특정 문자 개수 구하는 방법</a>
 */
class Solution12916 {
    public static void main(String[] args) {
        String s1 = "pPoooyY";
        // true
        boolean answer1 = solution3(s1);
        System.out.println(answer1);

        String s2 = "Pyy";
        // false
        boolean answer2 = solution3(s2);
        System.out.println(answer2);
    }

    /**
     * @param s 대문자와 소문자가 섞여있는 문자열
     * @return s에 'p'의 개수와 'y'의 개수를 비교해 같으면 True, 다르면 False를 return
     */
    public static boolean solution1(String s) {
        // 카운트 변수 1개만 사용하는 방법도 있음(p는 +, y는 -)
        int p = 0;
        int y = 0;
        s = s.toLowerCase();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'p') {
                p++;
            } else if(s. charAt(i) == 'y') {
                y++;
            }
        }

        if(p == y) return true;

        return false;
    }

    public static boolean solution3(String s) {
        // 문자의 개수 = 원본 문자열의 길이 - 찾는 문자를 모두 공백으로 변경한 문자열의 길이
        return s.replaceAll("[pP]", "").length()
                - s.replaceAll("[yY]", "").length() == 0;
    }
}