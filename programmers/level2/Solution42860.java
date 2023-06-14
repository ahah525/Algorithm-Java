package programmers.level2;


/**
 * [문제명] 조이스틱
 * [풀이시간] 1시간 50분(50분 + 1시간) / 1시간 10분
 * [한줄평] 문제 풀이를 이해하는데에도 너무 어려웠던 문제였다. / 결국 또 풀이를 보고 해결했고 다시 풀어도 너무 어려웠다.
 * 1_v1. (실패 - 11,13~14,18,20,22~27 실패)
 * 1_v2. (성공)
 * 2_v1. (성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42860">문제</a>
 * @See <a href="https://born2bedeveloper.tistory.com/26">풀이 참고</a>
 */
class Solution42860 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2, 2_v1
    /**
     * @param name 만들고자 하는 이름
     * @return 이름에 대해 조이스틱 조작 횟수의 최솟값
     */
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        // 1. 커서 좌우 이동 횟수를 최댓값으로 초기화(처음부터 끝까지 계속 오른쪽으로 가는 경우)
        int move = len - 1;
        // 모든 인덱스 위치에서 이동 횟수 구하기
        for(int i = 0; i < len; i++) {
            char c = name.charAt(i);
            // 2. 상, 하 선택
            answer += Math.min(c - 'A', 'Z' - c + 1);
            // 3. 좌, 우 선택
            // 4. 현재 위치(i)의 오른쪽 범위에서 'A'가 아닌 가장 가까운 문자의 인덱스(idx) 구하기
            int idx = i + 1;
            while(idx < len && name.charAt(idx) == 'A') {
                idx++;
            }
            // 5. 시작점(0)에서 2가지 방법(i를 거쳐 idx로 가거나 idx를 거쳐 i로 가기)으로 이동했을 때 최솟값 갱신
            move = Math.min(move, Math.min(
                    // 5.1. 0->i 오른쪽 이동, i->0 왼쪽 이동, 0->idx 왼쪽 이동
                    2 * i + (len - idx),
                    // 5.2. 0->idx 왼쪽 이동, idx->0 오른쪽 이동, 0->i 오른쪽 이동
                    2 * (len - idx) + i
            ));
        }
        return answer + move;
    }
}