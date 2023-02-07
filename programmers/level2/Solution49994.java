package programmers.level2;


/**
 * [문제명] 방문 길이
 * [풀이시간] 48분
 * [한줄평] 그대로 구현하기만 하면 되는 문제였는데, 갔던 좌표가 아니라 길을 어떻게 효율적으로 체크할지 고민하다가 시간이 좀 오래 걸렸다.
 * 1_v1. 구현(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/49994">문제</a>
 */
class Solution49994 {
    public static void main(String[] args) {
        // 7
        System.out.println(solution("ULURRDLLU"));

        // 7
        System.out.println(solution("LULLLLLLU"));
    }

    public static int solution(String dirs) {
        int answer = 0;
        /// (x, y, 방향) 방문여부
        boolean[][][] visited = new boolean[11][11][4];
        // U, D, R, L
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        // (5, 5) 에서 출발
        int x = 5, y = 5;
        for(char dir : dirs.toCharArray()) {
            int[] d = getDir(dir);
            int d1 = d[0];
            int d2 = d[1];
            int nx = x + dx[d1];
            int ny = y + dy[d1];
            // 좌표 내이면
            if(0 <= nx && nx <= 10 && 0 <= ny && ny <= 10) {
                // 가봤던 길이 아니면 카운트
                if(!visited[x][y][d1]) {
                    // 방문처리
                    visited[x][y][d1] = true;
                    visited[nx][ny][d2] = true;
                    answer++;
                }
                // 이동
                x = nx;
                y = ny;
            }
        }
        return answer;
    }

    public static int[] getDir(char dir) {
        int[] d = new int[2];   // (원래 방향, 반대 방향)
        switch(dir) {
            case 'U':
                d[0] = 0;
                d[1] = 1;
                break;
            case 'D':
                d[0] = 1;
                d[1] = 0;
                break;
            case 'R':
                d[0] = 2;
                d[1] = 3;
                break;
            case 'L':
                d[0] = 3;
                d[1] = 2;
                break;
        }
        return d;
    }
}