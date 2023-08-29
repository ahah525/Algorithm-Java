package programmers.level2;


/**
 * [문제명] 양궁대회
 * [풀이시간] 3시간 / 1시간 10분(35분 + 35분)
 * [한줄평] DFS로 완전탐색해야 한다는 것은 알았는데, 생각보다 구현하기가 복잡해서 어려웠던 문제였다.
 * / 시간초과가 나지 않는 방식으로 완전탐색을 하는 것이 어려웠고 결국 풀이를 참고했었던 문제로 꼭 복습이 필요하다.
 * 1_v1. DFS, 완전탐색(성공)
 * [풀이] 라이언이 i점에 맞힌 화살 개수의 경우의 수 = 0 or (어피치가 맞힌 i점수의 화살 개수 + 1)
 * - 라이언이 i 점수를 획득하기 위해서 맞혀야 하는 화살의 최소 개수 = 어피치가 맞힌 i점수의 화살 개수 + 1
 * 2_v1. DFS, 완전탐색(실패-1~18,20~22,24~25 실패)
 * [풀이] 라이언이 i점에 맞힌 화살 개수의 경우의 수 = (1~남은 화살개수)
 * 2_v2. DFS, 완전탐색(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/92342">문제</a>
 * @See <a href="https://velog.io/@pppp0722/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-Level2-%EC%96%91%EA%B6%81%EB%8C%80%ED%9A%8C-Java">풀이 참고</a>
 */
class Solution92342 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v2
    int[] answer; // 라이언이 가장 큰 점수 차이로 우승할 수 있는 방법
    int max; // 라이언과 어피치의 최대 점수차
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        max = 0;
        // 1. 라이언이 가장 큰 점수 차이로 우승할 수 있는 방법 구하기
        dfs(0, -1, new int[11], n, info);
        // 라이언이 어피치에게 이길 수 없는 경우 [-1] 리턴
        if(max == 0) return new int[] {-1};
        return answer;
    }

    /**
     * @param depth 사용한 화살 개수
     * @param prev 이전에 선택한 점수의 인덱스(순서)
     * @param path 라이언의 화살 사용 방법(10~0점에 각각 몇 개를 사용했는지)
     * @param n 사용해야하는 화살 개수
     * @param info 어피치의 화살 사용 방법
     */
    public void dfs(int depth, int prev, int[] path, int n, int[] info) {
        // 1. n개를 다 선택했으면
        if(depth == n) {
            // 1. (라이언 점수 - 어피치 점수) 계산
            int diff = getScoreDiff(info, path);
            // 2. 어피치 점수 >= 라이언 점수, 어피치 승리
            if(diff <= 0) return;
            // 3. 최고의 방법으로 갱신하기
            // 1) 기존 최대 점수차보다 현재 점수차가 크고
            // 2) 기존 최대 점수차와 현재 점수차가 같고, 현재 방법이 가장 낮은 점수를 더 많이 맞힌 경우라면
            if((max < diff) || (max == diff && canChange(path)))  {
                max = diff;
                answer = copy(path);
            }
            return;
        }
        // 2. 이전에 선택한 이후 점수에 대해 선택
        for(int i = prev + 1; i < 11; i++) {
            // 3. 라이언이 i번째 점수에 사용해야 하는 화살 개수 구하기
            int cnt;
            if(i < 10) cnt = info[i] + 1;
            else cnt = n - depth;
            // 4. 현재까지 사용한 개수와 이번에 사용해야하는 개수의 합이 기준을 넘으면, 패스
            if(depth + cnt > n) continue;
            // 5. i번째 점수에 cnt개 사용
            path[i] = cnt;
            dfs(depth + cnt, i, path, n, info);
            // 6. i번째 점수에 0개 사용(원상복귀)
            path[i] = 0;
        }
    }

    /**
     * @param path 라이언의 새로운 우승 방법
     * @return 해당 방법이 우승 방법 갱신 조건에 만족하면 true, 아니면 false
     */
    public boolean canChange(int[] path) {
        for(int i = 10; i >= 0; i--) {
            if(answer[i] == path[i]) continue;
            // 현재 방법이 가장 낮은 점수를 더 많이 맞힌 경우
            if(answer[i] < path[i]) return true;
            return false;
        }
        return false;
    }

    // 원복 배열을 복사한 배열 리턴
    public int[] copy(int[] arr) {
        int[] tmp = new int[11];
        for(int i = 0; i < 11; i++) {
            tmp[i] = arr[i];
        }
        return tmp;
    }

    /**
     * @param pathA 어피치 화살 사용 방법
     * @param pathB 라이언 화살 사용 방법
     * @return (라이언 점수 - 어피치 점수)
     */
    public int getScoreDiff(int[] pathA, int[] pathB) {
        int a = 0, b = 0;
        for(int i = 0; i < 11; i++) {
            if(pathA[i] == 0 && pathB[i] == 0) continue;
            if(pathA[i] >= pathB[i]) a += (10 - i);
            else b += (10 - i);
        }
        // System.out.println((b-a) + ":" + a + "," + b);
        // System.out.println(Arrays.toString(pathA));
        // System.out.println(Arrays.toString(pathB));
        return b - a;
    }


    // 2_v1
//    int[] answer;
//    int[] path;
//    int max;
//    public int[] solution2(int n, int[] info) {
//        path = new int[11];
//        max = 0;
//        answer = new int[11];
//
//        dfs(0, 0, n, info);
//        if(max == 0) return new int[] {-1};
//        return answer;
//    }
//
//    public void dfs(int depth, int prev, int n, int[] info) {
//        if(depth == n) {
//            int a = 0;
//            int b = 0;
//            for(int i = 0; i <= 10; i++) {
//                // 둘 다 점수 획득X
//                if(info[i] == 0 && path[i] == 0) continue;
//                if(info[i] >= path[i]) a += (10 - i);
//                else b += (10 - i);
//            }
//            // 최종 점수 어피치 < 라이언, 라이언 우승
//            int diff = b - a;
//            if(diff > 0 && max < diff) {
//                for(int i = 10; i >= 0; i--) {
//                    if(info[i] == path[i]) continue;
//                    if(info[i] < path[i]) {
//                        // 최댓값 갱신, 복사
//                        max = diff;
//                        deepCopy();
//                    }
//                    break;
//                }
//            }
//            return;
//        }
//
//        for(int i = prev; i <= 10; i++) {
//            path[i]++;
//            dfs(depth + 1, prev, n, info);
//            path[i]--;
//        }
//    }
//
//    public void deepCopy() {
//        for(int i = 0; i <= 10; i++) {
//            answer[i] = path[i];
//        }
//    }
}