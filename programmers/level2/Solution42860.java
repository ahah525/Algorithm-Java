package programmers.level2;


/**
 * [문제명] 조이스틱
 * [풀이시간] 1시간 50분(50분 + 1시간)
 * [한줄평] 문제 풀이를 이해하는데에도 너무 어려웠던 문제였다.
 * 1_v1. (실패 - 11,13~14,18,20,22~27 실패)
 * 1_v2. (성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42860">문제</a>
 * @See <a href="https://born2bedeveloper.tistory.com/26">풀이 참고</a>
 */
class Solution42860 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2
    /**
     * @param name 만들고자 하는 이름
     * @return 이름에 대해 조이스틱 조작 횟수의 최솟값
     */
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        int move = len - 1; // 커서 이동 횟수(처음부터 끝까지 계속 오른쪽으로 가는 경우 = 최댓값)
        for(int i = 0; i < len; i++) {
            char c = name.charAt(i);
            // 1. 상, 하 선택
            answer += Math.min(c - 'A', 'Z' - c + 1);
            // 2. 좌, 우 선택
            // 다음 문자가 A일 때 연속된 A의 다음 문자 인덱스 구하기
            int idx = i + 1;
            while(idx < len && name.charAt(idx) == 'A') {
                idx++;
            }
            // 2-1. 현재 위치에서 오른쪽으로 갔다가 왼쪽으로 가는 경우
            move = Math.min(move, 2 * i + (len - idx));
            // 2-2. 현재 위치에서 왼쪽으로 갔다가 오른쪽으로 가는 경우
            move = Math.min(move, 2 * (len - idx) + i);
        }
        return answer + move;
    }
}