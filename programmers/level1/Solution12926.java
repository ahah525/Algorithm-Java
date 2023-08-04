package programmers.level1;

/**
 * [문제명] 시저 암호
 * [풀이시간] / 11분 / 5분
 * [한줄평] 알파벳을 밀었을 때 대문자->소문자, 소문자->대문자로 바뀔 수 있다고 문제를 잘못 이해해서 생각보다 시간이 조금 걸렸던 문제였다.
 * / 아스키코드와 char 연산에 대한 복습이 꼭 필요하다!
 * / 쉽게 풀 수 있는 문제로 더 이상 안풀어봐도 될 것 같다.
 * 1_v1. 문자열(성공)
 * [풀이]
 * 1. 대문자: c = (char) ('A' + (c + n - 'A') % 26)
 * 2. 소문자: c = (char) ('a' + (c + n - 'a') % 26)
 * 2_v1. 문자열(성공)
 * 3_v1. 문자열(성공)
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12926">문제</a>
 */
class Solution12926 {
    public static void main(String[] args) {
        // "BC"
        String s1 = "AB";
        int n1 = 1;
        System.out.println(solution(s1, n1));

        // "a"
        String s2 = "z";
        int n2 = 1;
        System.out.println(solution(s2, n2));

        // "e F d"
        String s3 = "a B z";
        int n3 = 4;
        System.out.println(solution(s3, n3));
    }

    // 1_v1, 2_v1, 3_v1
    /**
     * @param s 문자열(알파벳 소문자, 대문자, 공백으로만 이루어짐)
     * @param n 거리(1 이상, 25이하인 자연수)
     * @return s를 n만큼 민 암호문
     */
    public static String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(c == ' ') {
                // 1. 공백인 경우
                sb.append(c);
                continue;
            }
            if(Character.isUpperCase(c)) {
                // 2. 대문자인 경우
                c = (char) ('A' + (c + n - 'A') % 26);
            } else {
                // 3. 소문자인 경우
                c = (char) ('a' + (c + n - 'a') % 26);
            }
            sb.append(c);
        }
        return sb.toString();
    }
}