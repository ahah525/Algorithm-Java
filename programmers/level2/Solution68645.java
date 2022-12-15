package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] 삼각 달팽이
 * [풀이 시간] 1시간 10분
 * [한줄평] 예전에 백준에서 비슷한 문제를 풀었던 적이 있었던 것 같다. 시계 반대방향으로 하나씩 값을 기록한 후 2차원 배열 값을 리스트에 옮기는 방식으로 구현했다.
 * 다만 각 경우에 따라 좌표값을 정확히 설정하지 않으면 인덱스 오류가 날 수 있기 때문에 정확한 수식을 도출해내는게 무엇보다 중요했던 문제였다!
 * v1. 구현(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12938">문제</a>
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12938/solution_groups?language=java">다른 풀이</a>
 */
class Solution68645 {
    public static void main(String[] args) {
        // [1,2,9,3,10,8,4,5,6,7]
        int n1 = 4;
        System.out.println(solution(n1));

        // [1,2,12,3,13,11,4,14,15,10,5,6,7,8,9]
        int n2 = 5;
        System.out.println(solution(n2));

        // [1,2,15,3,16,14,4,17,21,13,5,18,19,20,12,6,7,8,9,10,11]
        int n3 = 6;
        System.out.println(solution(n3));
    }

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
}