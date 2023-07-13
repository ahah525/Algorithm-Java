package programmers.level2;


/**
 * [문제명] 조이스틱
 * [풀이시간] 1시간 50분(50분 + 1시간) / 1시간 10분 / 43분(38분 + 5분)
 * [한줄평] 문제 풀이를 이해하는데에도 너무 어려웠던 문제였다.
 * / 결국 또 풀이를 보고 해결했고 다시 풀어도 너무 어려웠다.
 * 1_v1. (실패 - 11,13~14,18,20,22~27 실패)
 * 1_v2. (성공)
 * 2_v1. (성공)
 * 3_v1. (실패 - 17 실패)
 * [반례] "AAA" >> 0
 * [해결] 최솟값을 (문자열 길이 - 1)이 아니라 (가장 마지막에 나타난 'A'가 아닌 문자의 인덱스)로 초기화한다.
 * 3_v2. (성공)
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

    // 3_v1
    public int solution2(String name) {
        int answer = 0;
        int n = name.length();
        // 1. 문자열에서 'A'가 아닌 문자의 가장 마지막 인덱스 구하기
        int end = 0;
        for(int i = 0; i < n; i++) {
            if(name.charAt(i) == 'A') continue;
            answer += moveUpAndDown(name.charAt(i));
            end = i;
        }
        //
        int min = end;
        for(int i = 0; i < n - 1; i++) {
            if(name.charAt(i) != 'A' && name.charAt(i + 1) == 'A') {
                // 연속된 A의 다음 인덱스
                int j = i + 2;
                while(j < n && name.charAt(j) == 'A') {
                    j++;
                }
                //
                min = Math.min(min, 2 * i + (n - j));
                min = Math.min(min, i + 2 * (n - j));
            }
        }
        return answer + min;
    }

    // 'A'를 c 로 바꿀 때 조작 횟수의 최솟값
    public int moveUpAndDown(char c) {
        return Math.min(c - 'A', 'Z' - c + 1);
    }
}