package programmers.pccp;


/**
 * [문제명] [PCCP 모의고사 #1] 외톨이 알파벳
 * [풀이시간] 20분
 * [한줄평] 나름 간단한 문제였지만 연속된 문자를 1개로 처리하고, 알파벳 개수를 저장하는 쉬운 풀이법에 대한 고민을 많이 했던 문제였다.
 * v1. (성공)
 * - 연속된 같은 문자를 1개로 카운팅하는 방법: 이전 문자값을 저장해놓고 이전 문자와 다를 때만 값을 카운팅하는 기초적인 방식을 사용했다.
 * - 알파벳 개수 저장 방법: 처음엔 Map 을 사용할까 하다가, 이후에 정렬을 해야하는 문제도 있고 어차피 알파벳은 개수가 26으로 고정되어있어서 배열을 사용했다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/15008/lessons/121683">문제</a>
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12938/solution_groups?language=java">다른 풀이</a>
 */
class Solution121683 {
    public static void main(String[] args) {
        // "de"
        String input_string1 = "edeaaabbccd";
        System.out.println(solution(input_string1));

        // "e"
        String input_string2 = "eeddee";
        System.out.println(solution(input_string2));

        // "N"
        String input_string3 = "string";
        System.out.println(solution(input_string3));

        // "bz"
        String input_string4 = "zbzbz";
        System.out.println(solution(input_string4));
    }

    /**
     * @param input_string 알파벳 소문자로만 이루어진 어떤 문자열
     * @return 외톨이 알파벳들을 알파벳순으로 이어 붙인 문자열(외톨이 알파벳 없으면 "N")
     * 외톨이 알파벳 = 2회 이상 나타난 알파벳이 2개 이상의 부분으로 나뉘어 있음
     */
    public static String solution(String input_string) {
        int[] alpha = new int[26];
        char prev = ' ';    // 이전 문자
        // 1. 문자열의 알파벳 개수 세기(연속된 같은 문자는 1개로 세기)
        for(char c : input_string.toCharArray()) {
            // 이전 문자와 다를 때만 카운팅
            if(prev != c) {
                alpha[c - 'a']++;
                prev = c;
            }
        }
        // 2. 알파벳 개수가 2개이상인 것만 붙여서 문자열로 반환(어차피 알파벳 순서)
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++) {
            if(alpha[i] >= 2) {
                sb.append((char) (i + 'a'));
            }
        }
        return sb.length() == 0 ? "N" : sb.toString();
    }
}