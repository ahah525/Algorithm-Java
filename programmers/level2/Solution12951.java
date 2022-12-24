package programmers.level2;


/**
 * [문제명] JadenCase 문자열 만들기
 * [풀이시간] 15분
 * [한줄평] 간단했는데 어떻게 풀어야 더 효율적일지 고민하다가 시간이 좀 오래걸렸던 문제였다.
 * v1. StringBuilder 사용(성공)
 * - 경우에 따라 각 문자를 변환작업하여 넣어 새로운 문자열을 만드는 방식으로 해결
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12951">문제</a>
 */
class Solution12951 {
    public static void main(String[] args) {
        // "3people Unfollowed Me"
        String s1 = "3people unFollowed me";
        System.out.println(solution(s1));

        // "For The Last Week"
        String s2 = "for the last week";
        System.out.println(solution(s2));

    }

    /**
     * @param s 길이 1 이상 200 이하인 문자열(알파벳과 숫자, 공백문자(" ")로 이루어져 있습니다.)
     * @return s를 JadenCase로 바꾼 문자열을 리턴
     * JadenCase란 모든 단어의 첫 문자가 대문자이고, 그 외의 알파벳은 소문자인 문자열입니다. 단, 첫 문자가 알파벳이 아닐 때에는 이어지는 알파벳은 소문자로 쓰면 됩니다.
     */
    public static String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean start = true;
        for(char c : s.toCharArray()) {
            if(Character.isDigit(c)) {
                // 숫자는 무조건 첫번째 자리에만 올수 있음
                sb.append(c);
                start = false;
            } else {
                if(c == ' ') {
                    // 공백 뒤에 나오는 문자는 무조건 첫번째 자리
                    sb.append(c);
                    start = true;
                } else {
                    if(start) {
                        sb.append(Character.toUpperCase(c));
                        start = false;
                    } else {
                        sb.append(Character.toLowerCase(c));
                    }
                }
            }
        }
        return sb.toString();
    }
}