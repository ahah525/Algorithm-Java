package programmers.level2;


import java.util.HashSet;
import java.util.Set;

/**
 * [문제명] 영어 끝말잇기
 * [풀이시간] 13분 / 8분
 * [한줄평] 조건대로 구현만 하면 되는 아주 쉬운 기초 문제였다. Set 함수와 복잡도에 대해서 복습을 할 수 있었다. / 더 이상 풀어볼 필요가 없는 문제였다.
 * 1_v1. 구현(성공)
 * 2_v1. 구현(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12981">문제</a>
 */
class Solution12981 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    /**
     * @param n 사람의 수
     * @param words 사람들이 순서대로 말한 단어
     * @return 가장 먼저 탈락하는 사람의 번호와 그 사람이 자신의 몇 번째 차례에 탈락하는지
     */
    public static int[] solution(int n, String[] words) {
        int[] answer = {0, 0};  // 번호, 차례
        Set<String> set = new HashSet<>();
        set.add(words[0]);  // 첫번째 순서는 그냥 넣음
        // 2번째 순서부터 검사
        for(int i = 1; i < words.length; i++) {
            // 탈락조건
            if(set.contains(words[i]) ||
                    words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0)) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
            set.add(words[i]);
        }
        return answer;
    }

    // 2_v1
    public int[] solution2(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>();
        char prev = words[0].charAt(0); // 이전 단어의 끝 문자
        for(int i = 0; i < words.length; i++) {
            if(set.contains(words[i]) || prev != words[i].charAt(0)) {
                answer[0] = (i % n) + 1;
                answer[1] = (i / n) + 1;
                break;
            }
            set.add(words[i]);
            prev = words[i].charAt(words[i].length() - 1);
        }
        return answer;
    }
}