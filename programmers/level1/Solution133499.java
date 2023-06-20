package programmers.level1;


/**
 * [문제명] 옹알이 (2)
 * [풀이시간] 8분 / 24분
 * [한줄평] 아이디어만 잘 떠올리면 쉽게 풀 수 있었던 문제였다. / 처음과 다르게 그냥 구현으로 풀었고 한 번 더 풀어보면 좋을 문제다.
 * 1_v1. 문자열(성공)
 * [풀이] replaceAll()
 * 2_v1. 문자열(성공) -> 빠름
 * [풀이] s.charAt()로 각 문자 비교
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/133499">문제</a>
 */
class Solution133499 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    /**
     * @param babbling 문자열 배열
     * @return 머쓱이의 조카가 발음할 수 있는 단어의 개수
     * - "aya", "ye", "woo", "ma" 네 가지 발음과 네 가지 발음을 조합해서 만들 수 있는 발음밖에 하지 못하고 연속해서 같은 발음을 하는 것을 어려워합니다.
     */
    public int solution(String[] babbling) {
        int answer = 0;
        String[] arr = {"aya", "ye", "woo", "ma"};
        for(String s : babbling) {
            if(isPossible(s, arr))
                answer++;
        }
        return answer;
    }

    // 해당 단어를 발음할 수 있는지 여부
    public boolean isPossible(String s, String[] arr) {
        // 1. 발음 가능한 단어를 종류(단어의 인덱스)로 치환하기
        for(int i = 0; i < 4; i++) {
            s = s.replaceAll(arr[i], Integer.toString(i));
        }
        // 2. 발음 가능한 단어 여부 검사
        char prev = ' ';
        for(char c : s.toCharArray()) {
            // 발음 가능한 단어가 아니면 false
            if('a' <= c && c <= 'z') return false;
            // 이전 단어와 같은 단어면 false
            if(prev == c) return false;
            prev = c;
        }
        return true;
    }

    // 2_v1
    public int solution2(String[] babbling) {
        int answer = 0;
        for(String s : babbling) {
            if(isOk(s)) answer++;
        }
        return answer;
    }

    public boolean isOk(String s) {
        int prev = -1;
        int i = 0;
        while(i < s.length()) {
            if(s.charAt(i) == 'a') {
                if(i + 2 >= s.length()) return false;
                if(s.charAt(i + 1) != 'y' || s.charAt(i + 2) != 'a') return false;
                if(prev == 0) return false;
                prev = 0;
                i += 3;
            } else if(s.charAt(i) == 'y') {
                if(i + 1 >= s.length()) return false;
                if(s.charAt(i + 1) != 'e') return false;
                if(prev == 1) return false;
                prev = 1;
                i += 2;
            } else if(s.charAt(i) == 'w') {
                if(i + 2 >= s.length()) return false;
                if(s.charAt(i + 1) != 'o' || s.charAt(i + 2) != 'o') return false;
                if(prev == 2) return false;
                prev = 2;
                i += 3;
            } else if(s.charAt(i) == 'm') {
                if(i + 1 >= s.length()) return false;
                if(s.charAt(i + 1) != 'a') return false;
                if(prev == 3) return false;
                prev = 3;
                i += 2;
            } else {
                return false;
            }
        }
        return true;
    }
}