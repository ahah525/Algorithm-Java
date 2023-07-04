package programmers.level3;


/**
 * [문제명] 순위
 * [풀이시간] 1시간(40분 + 20분) / 19분 / 18분
 * [한줄평] 그래프 문제임을 알아도 감을 못잡아 결국 풀이를 보고 해결했던 문제다.
 * / 플루이드 와샬 알고리즘이라는 걸 모르고 그냥 풀었다.
 * / 간단한 규칙만 알면 쉽게 풀 수 있는 문제였다.
 * 1_v1. 그래프(실패)
 * 1_v2. 그래프, 인접행렬(성공)
 * [풀이] 플루이드-와샬 알고리즘
 * 2_v1. 그래프(성공)
 * [풀이] A->B, B->C => A->C
 * 3_v1. 그래프(성공)
 * [풀이] 2_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/49191">문제</a>
 * @See <a href="https://gom20.tistory.com/178">풀이</a>
 */
class Solution49191 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2
    public int solution(int n, int[][] results) {
        int answer = 0;
        // 1. 인접행렬 초기화
        int[][] map = new int[n + 1][n + 1];
        for(int[] result : results) {
            // A 는 B 를 이김 -> map[A][B] = 1
            // B 는 A 에게 짐 -> map[B][A] = -1
            map[result[0]][result[1]] = 1;
            map[result[1]][result[0]] = -1;
        }
        // 2. 인접행렬에 A -> B, B -> C = A -> C 규칙을 적용
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                for(int k = 1; k <= n; k++) {
                    // 2-1. A 가 B 를 이김, B가 C 를 이김 -> A 가 C 를 이김
                    if(map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                        map[j][i] = -1;
                    }
                    // 2-2. A 가 B 에게 짐, B가 C 에게 짐 -> A 가 C 에게 짐
                    if(map[i][k] == -1 && map[k][j] == -1) {
                        map[i][j] = -1;
                        map[j][i] = 1;
                    }
                }
            }
        }
        // 3. 각 선수의 순위를 매길수 있는지 여부 계산
        for(int i = 1; i <= n; i++) {
            // 4. i 와 경기 결과를 알고 있는 선수 세기
            int cnt = 0;
            for(int j = 1; j <= n; j++) {
                if(map[i][j] != 0) cnt++;
            }
            // 5. i 가 (n - 1)명의 선수와의 경기 결과를 알고 있으면 순위를 매길 수 있음
            if(cnt == n - 1) answer++;
        }
        return answer;
    }

    // 2_v1, 3_v1
    public int solution2(int n, int[][] results) {
        int answer = 0;
        int[][] map = new int[n + 1][n + 1];
        for(int[] res : results) {
            map[res[0]][res[1]] = 1;
            map[res[1]][res[0]] = -1;
        }
        //
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                int value = map[i][j];
                if(value == 0) continue;
                for(int k = 1; k <= n; k++) {
                    if(k != i && value == map[j][k]) {
                        // i -> j, j -> k => i -> k
                        map[i][k] = value;
                        // i <- j, j <- k => i <- k
                        map[k][i] = -value;
                    }
                }
            }
        }
        //
        for(int i = 1; i <= n; i++) {
            boolean flag = true;
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                if(map[i][j] == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag) answer++;
        }
        return answer;
    }
}