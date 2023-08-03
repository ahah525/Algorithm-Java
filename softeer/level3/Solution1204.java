package softeer.level3;


/**
 * [문제명] [HSAT 4회 정기 코딩 인증평가 기출] 슈퍼컴퓨터 클러스터
 * [풀이시간] (25분)
 * [한줄평]
 * 1_v1. (실패 - 오답)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=1204">문제</a>
 */
class Solution1204 {
    static long b;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 컴퓨터 수
        b = Long.parseLong(st.nextToken());   // 예산
        int[] power = new int[n]; // 컴퓨터 성능
        st = new StringTokenizer(br.readLine());
        int start = 0;
        int end = 1000000000;
        for(int i = 0; i < n; i++) {
            power[i] = Integer.parseInt(st.nextToken());
            // start = Math.min(start, power[i]);
        }
        // System.out.println(start);
        // System.out.println(end);

        int max = 0;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(isPossible(mid, power)) {
                start = mid + 1;
                max = Math.max(max, mid);
            } else {
                end = mid - 1;
            }
        }
        System.out.println(max);
    }

    public static boolean isPossible(int target, int[] power) {
        Queue<Integer> pq = new PriorityQueue<>();
        for(int p : power) {
            pq.add(p);
        }
        //
        long budget = b;
        while(!pq.isEmpty()) {
            int p = pq.poll();
            int d = target - p;
            if(d <= 0) return true;
            long cost = (long) d * d;
            if(cost > budget) return false;
            budget -= cost;
        }
        return true;
    }
}