package programmers.level4;


import java.util.Arrays;

/**
 * [문제명] [3차] 자동완성
 * [풀이시간] 1시간 10분(52분+18분)
 * [한줄평] 결국 시간초과 문제를 해결하지 못해서 풀이를 보고 해결했다.
 * 1_v1. 문자열,해시(실패-14,19,21,22 메모리 초과)
 * [풀이] 문자열의 모든 접두어를 HashMap에 저장해서 관리했다.
 * 1_v2. 문자열,정렬(성공)
 * [풀이] 문자열을 사전순으로 정렬하고 뒤의 문자열과 하나씩 비교하면 값을 계산해주는 것이 핵심이다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17685">문제</a>
 * @See <a href="https://wellbell.tistory.com/166">풀이</a>
 */
class Solution17685 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2
    public int solution(String[] words) {
        int answer = 0;
        // 1. 문자열 사전순 정렬
        Arrays.sort(words);
        int[] cnts = new int[words.length];
        for(int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            int minLen = Math.min(cur.length(), next.length());
            // 2. 현재 문자열과 다음 문자열의 연속된 같은 문자열의 길이 구하기
            int same = 0;
            for(int j = 0; j < minLen; j++) {
                if(cur.charAt(j) != next.charAt(j)) break;
                same++;
            }
            if(same == minLen) {
                // 3. 열 문자열이 짧은 문자열을 완전히 포함하고 있다면, 순서상 현재문자열=짧은문자열, 다음문자열=긴문자
                // 현재 문자열의 최소 입력 개수 = 현재 문자열을 그대로 입력했을 때 길이
                cnts[i] = same;
            } else {
                // 4. 현재 문자열의 최소 입력 개수 = 같은 문자열에서 추가로 1문자를 더 입력했을 때 길이
                cnts[i] = Math.max(cnts[i], same + 1);
            }
            // 5. 다음 문자열의 최소 입력 개수 = 같은 문자열에서 추가로 1문자를 더 입력했을 때 길이
            cnts[i + 1] = same + 1;
        }
        // System.out.println(Arrays.toString(cnts));
        // 6. 합 계산
        for(int cnt : cnts) {
            answer += cnt;
        }
        return answer;
    }
}