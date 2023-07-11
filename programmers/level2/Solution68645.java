package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] 삼각 달팽이
 * [풀이 시간] 1시간 10분 / 30분 / 24분
 * [한줄평] 예전에 백준에서 비슷한 문제를 풀었던 적이 있었던 것 같다. 시계 반대방향으로 하나씩 값을 기록한 후 2차원 배열 값을 리스트에 옮기는 방식으로 구현했다.
 * 다만 각 경우에 따라 좌표값을 정확히 설정하지 않으면 인덱스 오류가 날 수 있기 때문에 정확한 수식을 도출해내는게 무엇보다 중요했던 문제였다!
 * / 문제에 나온 규칙대로 값을 기록하기만 하면 되는 쉬운 구현 문제였다.
 * / 마지막에 한 점만 기록하는 경우를 처리해주는 것 때문에 시간이 걸리긴 했지만 규칙만 찾으면 쉽게 풀 수 있는 문제였다. 다시 풀어봐도 좋을 문제다.
 * 1_v1. 구현(성공)
 * 2_v1. 구현(성공)
 * [풀이] 세로 -> 가로 -> 대각선 순서로 2차원 배열에 기록, 1차원 배열에 옮기기
 * 3_v1. 구현(성공)
 * [풀이] 2_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/68645">문제</a>
 */
class Solution68645 {
    public static void main(String[] args) {
        // [1,2,9,3,10,8,4,5,6,7]
        System.out.println(solution(4));

        // [1,2,12,3,13,11,4,14,15,10,5,6,7,8,9]
        System.out.println(solution(5));

        // [1,2,15,3,16,14,4,17,21,13,5,18,19,20,12,6,7,8,9,10,11]
        System.out.println(solution(6));
    }

    // 1_v1
    /**
     * @param n 정수(1 이상 1,000 이하)
     * @return 밑변의 길이와 높이가 n인 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 달팽이 채우기를 진행한 후, 첫 행부터 마지막 행까지 모두 순서대로 합친 새로운 배열
     */
    public static List<Integer> solution(int n) {
        List<Integer> answer = new ArrayList<>();
        int[][] arr = new int[n][n];    // 값 기록용
        int x = 0;              // 기록값
        int r = (n + 2) / 3;    // 반복 횟수
        // n 의 값에 의해 구한 r 번만큼 반복하기
        for(int i = 0; i < r; i++) {
            int len = n - (3 * i + 1);  // i번째 턴에 아래/오른쪽/대각선 으로 움직여서 값을 채워야 하는 개수
            if(len == 0) {
                // 1. 길이가 0일 때는 1개 값 바로 입력
                arr[2 * i][i] = ++x;
            } else {
                // 2. 길이가 1이상일 때는 len 만큼 3가지 경우를 각각 수행
                // 2-1. 아래
                for(int j = 0; j < len; j++) {
                    arr[2 * i + j][i] = ++x;
                }
                // 2-2. 오른쪽
                for(int j = 0; j < len; j++) {
                    arr[n - (i + 1)][i + j] = ++x;
                }
                // 2-3. 대각선
                for(int j = 0; j < len; j++) {
                    arr[n - (i + 1) - j][n - (2 * i + 1) - j] = ++x;
                }
            }
        }
        // 값 옮겨 담기
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                answer.add(arr[i][j]);
            }
        }
        return answer;
    }

    // 2_v2
    public int[] solution2(int n) {
        int[] answer = new int[n * (n + 1) / 2];
        int[][] arr = new int[n][n];
        // (x,y): 기준 좌표
        int x = 0;
        int y = 0;
        int m = n - 1;  // 길이
        int cnt = 1;    // 기록할 숫자
        while(true) {
            // 1. 길이가 음수이면 break
            if(m < 0) break;
            // 2. 길이가 0이면 (x,y) 값만 기록
            if(m == 0) {
                arr[x][y] = cnt;
                break;
            }
            // 3. 세로 -> 가로 -> 대각선 순서로 기록
            // 3-1. 세로
            for(int i = 0; i < m; i++) {
                arr[x + i][y] = cnt++;
            }
            // 3-2. 가로
            for(int i = 0; i < m; i++) {
                arr[x + m][y + i] = cnt++;
            }
            // 3-3. 대각선
            for(int i = 0; i < m; i++) {
                arr[x + m - i][y + m - i] = cnt++;
            }
            // 4. 다음 턴의 (x,y), m 갱신
            x += 2;
            y += 1;
            m -= 3;
        }
        // 5. 2차원 배열 -> 1차원 배열로 옮기기
        int idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                answer[idx++] = arr[i][j];
            }
        }
        return answer;
    }

    // 3_v1
    public int[] solution3(int n) {
        int[][] map = new int[n][n];
        int x = 0, y = 0;   // 기준점
        int num = 0; // 기록한 숫자 개수
        int len = n - 1;
        while(len > 0) {
            // 1. 위 -> 아래
            for(int j = 0; j < len; j++) {
                map[x + j][y] = ++num;
            }
            // 2. 왼 -> 오
            for(int j = 0; j < len; j++) {
                map[x + len][y + j] = ++num;
            }
            // 3. 오른쪽 아래 -> 왼쪽 위
            for(int j = 0; j < len; j++) {
                map[x + len - j][y + len - j] = ++num;
            }
            // 4. 기준점, 길이 갱신
            x += 2;
            y += 1;
            len -= 3;
        }
        // 5. 길이가 0인 경우 기준점만 기록
        if(len == 0) map[x][y] = ++num;
        // 6. 1차원 배열로 복사
        int[] answer = new int[num];
        int idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i + 1; j++) {
                answer[idx++] = map[i][j];
            }
        }
        return answer;
    }
}