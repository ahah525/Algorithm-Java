package programmers.level3;


/**
 * [문제명] 가장 긴 팰린드롬
 * [풀이시간] 30분(26분 + 4분) / 15분
 * [한줄평] 처음에는 split() 으로 부분 문자열을 만들고 reverse() 로 확인하려고 했는데 너무 효율성이 떨어질 것 같아 인덱스로 하나씩 값을 가져오는 것으로 구현했다.
 * / 그냥 구현하면 되는 쉬운 문제였다.
 * 1_v1. (실패 - 정확성 테스트 1~10, 13~16,21 실패, 효율성 테스트 1 실패)
 * 1_v2. 완전탐색(성공)
 * [접근법] 팰린드롬인지 검사할 때 charAt() 로 한 문자씩 비교
 * 2_v1. 완전탐색(성공) -> 빠름
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12904">문제</a>
 */
class Solution12904 {
    public static void main(String[] args) {
        // 7
        System.out.println(solution("abcdcba"));

        // 3
        System.out.println(solution("abacde"));

        // 3
        System.out.println(solution("abbb"));
    }

    // 1_v2
    /**
     * @param s 문자열(문자열 s의 길이 : 2,500 이하의 자연수)
     * @return s의 부분문자열(Substring)중 가장 긴 팰린드롬의 길이
     */
    public static int solution(String s) {
        int len = s.length();
        // 1. 부분문자열의 길이를 (원본 문자열의 길이 ~ 1)까지 반복
        for(int i = len; i > 0; i--) {
            // 2. 원본 문자열에서 문자열의 길이가 i 인 부분 문자열의 개수 = len - i + 1
            for(int j = 0; j < len - i + 1; j++) {
                // 3. 원본 문자열에서 인덱스 j 부터 길이가 i 인 부분 문자열이 팰린드롬이면 길이 리턴
                if(isPalindrome(s, j, i))
                    return i;
            }
        }
        return 1;
    }

    /**
     * @param s 원본 문자열
     * @param a 시작 인덱스
     * @param len 부분 문자열의 길이
     * @return 원본 문자열의 a 인덱스부터 len 길이의 부분 문자열이 팰린드롬인지 여부
     */
    public static boolean isPalindrome(String s, int a, int len) {
        for(int i = 0; i < len / 2; i++) {
            if(s.charAt(a + i) != s.charAt(a + len - 1 - i))
                return false;
        }
        return true;
    }

    // 2_v1
    char[] arr;
    public int solution2(String s) {
        int len = s.length();
        arr = s.toCharArray();
        for(int i = len; i >= 2; i--) {
            for(int j = 0; j <= len - i; j++) {
                if(isPalin(j, j + i - 1)) {
                    return i;
                }
            }
        }
        // 최솟값 = 1
        return 1;
    }

    public boolean isPalin(int start, int end) {
        while(start <= end) {
            if(arr[start] != arr[end]) return false;
            start++;
            end--;
        }
        return true;
    }
}