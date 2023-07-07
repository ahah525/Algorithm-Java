package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] 행렬 테두리 회전하기
 * [풀이시간] 1시간 / 26분, 20분 / 19분
 * [한줄평] 행렬 회전 문제는 많이 풀어봤던 문제 유형이었지만 푸는데 1시간 정도가 걸려서 다음에는 시간을 좀 더 단축해봐야겠다.
 * / 익숙한 유형이라 빨리 풀기는 했지만 회전한 결과를 저장할 때 굳이 tmp[][] 배열을 사용할 필요가 없었다.
 * / 복사본 배열을 따로 만들지 않고 풀었던 기억이 나서 쉽게 풀었다.
 * 1_v1. 구현(성공)
 * [풀이] 회전할 때 tmp[][] 에 원본을 복사하고 회전한 결과를 저장함
 * 2_v1. 구현(성공)
 * [풀이] 1_v1 동일
 * 2_v2. 구현(성공) -> 추천
 * [풀이] 회전할 때 map[][] 에 덮어쓰기(첫번째로 덮어쓰여지는 곳의 값을 저장해두고 마지막에 입력하기)
 * 3_v1. 구현(성공)
 * [풀이] 2_v2 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/77485">문제</a>
 * @See <a href="https://velog.io/@ckr3453/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%96%89%EB%A0%AC-%ED%85%8C%EB%91%90%EB%A6%AC-%ED%9A%8C%EC%A0%84%ED%95%98%EA%B8%B0-Java">다른 풀이</a>
 */
class Solution77485 {
    public static void main(String[] args) {
        // [8, 10, 25]
        int[][] queries1 = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
        System.out.println(solution(6, 6, queries1));
    }

    // 1_v1
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

    // 2_v2, 3_v1
    int[][] map;
    public int[] solution2(int rows, int columns, int[][] queries) {
        // 1. map 초기화
        map = new int[rows][columns];
        int n = 1;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                map[i][j] = n++;
            }
        }
        // 2. 회전
        int[] answer = new int[queries.length];
        for(int i = 0; i < queries.length; i++) {
            answer[i] = rotate(queries[i][0] - 1, queries[i][1] - 1, queries[i][2] - 1, queries[i][3] - 1);
        }
        return answer;
    }

    // (x1, y1), (x2, y2) 직사각형을 시계방향으로 회전했을 떄 최솟값
    public int rotate(int x1, int y1, int x2, int y2) {
        int tmp = map[x1][y2];   // 첫번째로 덮어쓰여진 값(덮어쓰기를 하기 때문에 보존해야함)
        int min = tmp;  // 최솟값
        // 1. 오른쪽
        for(int y = y2 - 1; y >= y1; y--) {
            map[x1][y + 1] = map[x1][y];
            min = Math.min(min, map[x1][y]);
        }
        // 2. 위쪽
        for(int x = x1 + 1; x <= x2; x++) {
            map[x - 1][y1] = map[x][y1];
            min = Math.min(min, map[x][y1]);
        }
        // 3. 왼쪽
        for(int y = y1 + 1; y <= y2; y++) {
            map[x2][y - 1] = map[x2][y];
            min = Math.min(min, map[x2][y]);
        }
        // 4. 아래
        for(int x = x2 - 1; x > x1; x--) {
            map[x + 1][y2] = map[x][y2];
            min = Math.min(min, map[x][y2]);
        }
        // (x1, y2) 값
        map[x1 + 1][y2] = tmp;

        return min;
    }

    // 3_v1
    public int[] solution3(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        // 1. 초기화
        int[][] map = new int[rows][columns];
        int num = 1;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                map[i][j] = num++;
            }
        }
        int idx = 0;
        for(int[] q : queries) {
            int x1 = q[0] - 1;
            int y1 = q[1] - 1;
            int x2 = q[2] - 1;
            int y2 = q[3] - 1;
            // 2. 첫번째 행의 맨 오른쪽 값 저장, 최솟값 초기화
            int tmp = map[x1][y2];
            int min = tmp;
            // 3. 왼 -> 오
            for(int y = y2; y >= y1 + 1; y--) {
                map[x1][y] = map[x1][y - 1];
                min = Math.min(min, map[x1][y]);
            }
            // 4. 아래 -> 위
            for(int x = x1; x <= x2 - 1; x++) {
                map[x][y1] = map[x + 1][y1];
                min = Math.min(min, map[x][y1]);
            }
            // 5. 오 -> 왼
            for(int y = y1; y <= y2 - 1; y++) {
                map[x2][y] = map[x2][y + 1];
                min = Math.min(min, map[x2][y]);
            }
            // 6. 위 -> 아래
            for(int x = x2; x >= x1 + 2; x--) {
                map[x][y2] = map[x - 1][y2];
                min = Math.min(min, map[x][y2]);
            }
            // 7. 처음에 저장해두었던 값 기록
            map[x1 + 1][y2] = tmp;
            // 8. 최솟값 기록
            answer[idx++] = min;
        }
        return answer;
    }
}