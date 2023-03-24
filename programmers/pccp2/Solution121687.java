package programmers.pccp2;


/**
 * [문제명] [PCCP 모의고사 #2] 실습용 로봇
 * [풀이시간] / 15분
 * [한줄평] / 쉽게 풀 수 있는 문제였다.
 * 1_v1. 구현(성공)
 * 2_v1. 구현(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/15009/lessons/121687">문제</a>
 */
class Solution121687 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1
    /**
     * @param command 로봇에 입력된 명령어를 순서대로 담고 있는 문자열
     * @return 로봇이 주어진 명령어들을 순서대로 모두 수행한 뒤 도착한 최종 위치의 좌푯값 x, y를 순서대로 배열에 담아서 return
     */
    public int[] solution(String command) {
        // U, R, D, L 방향에서 직진했을 때 좌표 변화
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        // 1. 방향, 좌표 초기화
        int d = 0;
        int x = 0;
        int y = 0;
        // 2. 이동
        for(char c : command.toCharArray()) {
            switch(c) {
                case 'R':
                    // 오른쪽 회전
                    d = (d + 1) % 4;
                    break;
                case 'L':
                    // 왼쪽 회전
                    d = (d + 3) % 4;
                    break;
                case 'G':
                    // 한칸 전진
                    x += dx[d];
                    y += dy[d];
                    break;
                case 'B':
                    // 한칸 후진
                    x -= dx[d];
                    y -= dy[d];
                    break;
            }
        }
        return new int[] {x, y};
    }
}