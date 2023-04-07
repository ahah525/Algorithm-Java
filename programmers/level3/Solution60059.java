package programmers.level3;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] 자물쇠와 열쇠
 * [풀이시간] 1시간 15분(1시간 10분 + 5분)
 * [한줄평] 복잡했으나 구현에 큰 어려움은 없었고 반례가 없었으면 해결하는데 시간이 오래걸렸을 것 같다.
 * 1_v1. 완전탐색, 구현(실패 - 2,4,12 실패)
 * 1_v2. 완전탐색, 구현(성공)
 * [반례] 열쇠의 홈이 없는 경우 무조건 열 수 있음
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/60059">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/35007">반례</a>
 */
class Solution60059 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2
    class P {
        int x;
        int y;
        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * @param key 열쇠를 나타내는 2차원 배열
     * @param lock 자물쇠를 나타내는 2차원 배열
     * @return 열쇠로 자물쇠를 열수 있으면 true를, 열 수 없으면 false
     */
    public boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;
        // 홈 = 0, 돌기 = 1
        // 1. 자물쇠 홈 좌표 리스트 구하기
        List<P> homs = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(lock[i][j] == 0) {
                    homs.add(new P(i, j));
                }
            }
        }
        // 2. 자물쇠 홈이 0개이면 이미 홈이 다 채워졌으므로 true 리턴
        if(homs.size() == 0) return true;
        // 3. 열쇠 돌기 좌표 리스트 구하기
        List<P> dols = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                if(key[i][j] == 1) {
                    dols.add(new P(i, j));
                }
            }
        }
        // 4. 열쇠 4방향 회전한 상태로 반복
        for(int i = 0; i < 4; i++) {
            // 5. 자물쇠 홈에 열쇠 돌기를 하나씩 맞춰보면서 자물쇠를 열 수 있는지 검증
            for(P hom : homs) {
                for(P dol : dols) {
                    if(isOpen(m, n, homs.size(), hom, dol, key, lock)) return true;
                }
            }
            // 6. 마지막에는 회전할 필요 없음
            if(i == 3) break;
            // 7. 열쇠 90도 회전
            key = turn(key);
            for(int j = 0; j < dols.size(); j++) {
                P dol = dols.get(j);
                dols.set(j, new P(dol.y, m - 1 - dol.x));
            }
        }
        return false;
    }

    // 오른쪽으로 90도 회전한 열쇠 배열 구하기
    public int[][] turn(int[][] key) {
        int m = key.length;
        int[][] tmp = new int[m][m];
        // i 행 -> m - 1 - i 열
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                tmp[j][m - 1 - i] = key[i][j];
            }
        }
        return tmp;
    }

    /**
     * @param m 키 크기
     * @param n 자물쇠 크기
     * @param target 자물쇠 홈 개수
     * @param hom 자물쇠 홈 좌표
     * @param dol 열쇠 돌기 좌표
     * @param key 열쇠 배열
     * @param lock 자물쇠 배열
     * @return hom 에 dol 을 맞췄을 때 자물쇠의 모든 홈을 채울수 있는지 여부
     */
    public boolean isOpen(int m, int n, int target, P hom, P dol, int[][] key, int[][] lock) {
        // 1. 자물쇠 홈에 열쇠 돌기를 맞추려면 열쇠를 (x,y) 방향으로 얼마나 이동해야하는지 계산
        int dx = hom.x - dol.x;
        int dy = hom.y - dol.y;
        int cnt = 0;    // 자물쇠 홈이 채워진 개수
        // 2. 열쇠를 (x,y) 방향으로 이동했을 때 자물쇠를 열 수 있는지 검사
        for(int x = 0; x < m; x++) {
            for(int y = 0; y < m; y++) {
                // 열쇠를 (x,y) 방향으로 이동했을 때 좌표
                int nx = x + dx;
                int ny = y + dy;
                // 3. 해당 좌표가 자물쇠 영역 내이면 4가지 경우(A,B,C,D)에 따라 처리
                if(0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if(lock[nx][ny] == 0) {
                        // A. 자물쇠가 홈, 열쇠가 홈이면 -> 홈을 채울 수 없으므로 break
                        if(key[x][y] == 0) return false;
                            // B. 자물쇠가 홈, 열쇠가 돌기면 -> 홈을 채울 수 있으므로 개수 세기
                        else cnt++;
                    } else {
                        // C. 자물쇠가 돌기, 열쇠가 돌기면 -> 어긋나므로 break
                        if(key[x][y] == 1) return false;
                        // D. 자물쇠가 돌기, 열쇠가 홈이면 -> 상관없음(pass)
                    }
                }
            }
        }
        // 4. 자물쇠의 모든 홈이 채워졌다면 true 리턴
        return (cnt == target) ? true : false;
    }
}