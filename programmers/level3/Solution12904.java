package programmers.level3;


/**
 * [문제명] 가장 긴 팰린드롬
 * [풀이시간] (26분)
 * [한줄평]
 * 1_v1. (실패 - 정확성 테스트 1~10, 13~16,21 실패, 효율성 테스트 1 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12904">문제</a>
 */
class Solution12904 {
    public static void main(String[] args) {
        // 7
        System.out.println(solution("abcdcba"));

        // 3
        System.out.println(solution("abacde"));
    }

    public static int solution(String s)
    {
        int len = s.length();
        for(int i = len; i > 0; i--) {
            // (len - i + 1) 반복
            for(int j = 0; j < len - i + 1; j++) {
                if(isPalindrome(s, j, i)) {
                    System.out.println("i = " + i);
                    System.out.println("j = " + j);
                    return i;
                }
            }
        }
        return 1;
    }

    public static boolean isPalindrome(String s, int a, int len) {
        int n = len / 2;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) != s.charAt(a + len - 1 - i))
                return false;
        }
        return true;
    }
}