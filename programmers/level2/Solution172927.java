package programmers.level2;


/**
 * [문제명] 광물 캐기
 * [풀이시간] 29분
 * [한줄평] N의 범위가 크지 않아 완전탐색으로 풀었던 문제다.
 * 1_v1. 완전탐색, DFS(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/172927">문제</a>
 */
class Solution172927 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    int n;  // 사용해야 하는 곡괭이 수
    int[] visited; // 곡괭이 사용 개수
    int answer; // 최소 피로도

    // 다이아몬드, 철, 돌
    int[][] arr = {
            {1, 1, 1},
            {5, 1, 1},
            {25, 5, 1}
    };

    public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;
        visited = new int[3];
        // 1. 사용해야 하는 곡괭이 수 = Math.min(전체 곡괭이 수, 모든 광물을 캐기위해 필요한 곡괭이 수)
        int totalPick = picks[0] + picks[1] + picks[2];
        n = Math.min(totalPick, (int) Math.ceil((double) minerals.length / 5));
        // 2. n개의 곡괭이 선택하기 완전탐색
        perm(0, 0, picks, minerals);
        return answer;
    }

    /**
     * totalPick 개의 곡괭이 중에서 n개 선택하기
     * @param depth 사용한 곡괭이 수
     * @param sum 누적 피로도
     */
    public void perm(int depth, int sum, int[] picks, String[] minerals) {
        // 1. n개의 곡괭이를 모두 선택했으면 최솟값 갱신
        if(depth == n) {
            answer = Math.min(answer, sum);
            return;
        }
        // 2. 3종류의 곡괭이 중 1개 선택하기
        for(int i = 0; i < 3; i++) {
            // 3. i번쨰 곡괭이를 모두 사용했으면 패스
            if(visited[i] == picks[i]) continue;
            // 4. i번쨰 곡괭이 사용
            visited[i]++;
            // 5. i번째 곡괭이를 사용하여 광물을 캐내는데 필요한 피로도 계산
            int s = depth * 5;
            int e = Math.min(minerals.length - 1, depth * 5 + 4);
            int score = getScore(s, e, i, minerals);
            perm(depth + 1, sum + score, picks, minerals);
            visited[i]--;
        }
    }

    /**
     * @param s 광물을 캐야하는 시작 인덱스
     * @param e 광물을 캐야하는 끝 인덱스
     * @param pick 선택한 곡괭이 번호
     * @return pick 번째 곡괭이로 [s, e] 범위의 광물을 캐내는데 필요한 피로도
     */
    public int getScore(int s, int e, int pick, String[] minerals) {
        int sum = 0;
        for(int i = s; i <= e; i++) {
            sum += arr[pick][getMineral(minerals[i])];
        }
        return sum;
    }

    // 광물 이름으로 번호 조회
    public int getMineral(String s) {
        switch(s) {
            case "diamond":
                return 0;
            case "iron":
                return 1;
            default:
                return 2;
        }
    }
}