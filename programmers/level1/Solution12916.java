package programmers.level1;

/**
 * 문자열 내 특정 문자 개수 구하기
 * v1. 반복문 이용(추천)
 * v2. 람다식 이용(속도 느림)
 * v3. replace() 이용
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12916/solution_groups?language=java">풀이 참고</a>
 */
class Solution12916 {
    public static void main(String[] args) {
        String s1 = "pPoooyY";
        // true
        boolean answer1 = solution(s1);
        System.out.println(answer1);

        String s2 = "Pyy";
        // false
        boolean answer2 = solution(s2);
        System.out.println(answer2);
    }

    /**
     * @param s 대문자와 소문자가 섞여있는 문자열
     * @return s에 'p'의 개수와 'y'의 개수를 비교해 같으면 True, 다르면 False를 return
     */
    public static boolean solution(String s) {
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
}