package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] 행렬 테두리 회전하기
 * [풀이시간] 1시간
 * [한줄평] 행렬 회전 문제는 많이 풀어봤던 문제 유형이었지만 푸는데 1시간 정도가 걸려서 다음에는 시간을 좀 더 단축해봐야겠다.
 * v1. 구현(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/77485">문제</a>
 */
class Solution77485 {
    public static void main(String[] args) {
        // [8, 10, 25]
        int[][] queries1 = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
        System.out.println(solution(6, 6, queries1));
    }

    /**
     * @param rows 세로(행개수)
     * @param columns 가로(열개수)
     * @param queries 회전들의 목록
     * @return 각 회전들을 배열에 적용한 뒤, 그 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 배열에 담아 return
     */
    public static List<Integer> solution(int rows, int columns, int[][] queries) {
        List<Integer> answer = new ArrayList<>();
        // 1. 행렬 값 초기화하기
        int[][] map = new int[rows][columns];
        int cnt = 1;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                map[i][j] = cnt++;
            }
        }
        // 2. 각 회전 후 최솟값 구하기
        for(int[] q : queries) {
            int x1 = q[0] - 1;
            int y1 = q[1] - 1;
            int x2 = q[2] - 1;
            int y2 = q[3] - 1;
            int w1 = y2 - y1;   // 가로
            int h1 = x2 - x1;   // 세로
            // 2-1. 회전결과를 담아 놓을 2차원 배열에 원본 배열을 복사해놓기
            int[][] tmp = new int[rows][columns];
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < columns; j++) {
                    tmp[i][j] = map[i][j];
                }
            }
            // 2-2. 각 방향마다 회전한 결과를 tmp 배열에 저장하고 최솟값 구하기
            int min = Integer.MAX_VALUE;
            // 오른쪽
            for(int i = 0; i < w1; i++) {
                int v = map[x1][y1 + i];
                tmp[x1][y1 + i + 1] = v;
                min = Math.min(min, v);
            }
            // 아래쪽
            for(int i = 0; i < h1; i++) {
                int v = map[x1 + i][y2];
                tmp[x1 + i + 1][y2] = v;
                min = Math.min(min, v);
            }
            // 왼쪽
            for(int i = 0; i < w1; i++) {
                int v = map[x2][y2 - i];
                tmp[x2][y2 - i - 1] = v;
                min = Math.min(min, v);
            }
            // 위쪽
            for(int i = 0; i < h1; i++) {
                int v = map[x2 - i][y1];
                tmp[x2 - i - 1][y1] = v;
                min = Math.min(min, v);
            }
            // 2-3. 최솟값을 리스트에 넣고 회전결과를 원본배열에 복사하기
            answer.add(min);
            map = tmp;
        }
        return answer;
    }
}