package programmers.level3;


import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] [1차] 셔틀버스
 * [풀이시간] 1시간 20분(1시간 8분 + 12분)
 * [한줄평] 이분탐색으로 풀 수 있다는 힌트를 얻긴했지만 혼자서 풀었던 문제. 다행히 반례도 빨리 찾았다.
 * 1_v1. PriorityQueue, 이분탐색(실패 - 19~22, 24 실패)
 * 2_v1. PriorityQueue, 이분탐색(성공)
 * [반례] 태울 수 있는 사람을 모두 태우고 대기열에 사람이 남아있을 경우, 콘이 첫번째에 있을 거라는 보장은 없음 => q.peek() 이 아닌 q.contains() 로 검사해야함!!
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17678">문제</a>
 */
class Solution17678 {
    public static void main(String[] args) {
        // 09:00
        System.out.println(solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));

        // 09:09
        System.out.println(solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));

        // 08:59
        System.out.println(solution(2, 1, 2, new String[]{"09:00", "09:00", "09:00", "09:00"}));

        // 00:00
        System.out.println(solution(1, 1, 5, new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"}));

        // 09:00
        System.out.println(solution(1, 1, 1, new String[]{"23:59"}));

        // 18:00
        System.out.println(solution(10, 60, 45, new String[]{"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
    }

    /**
     * @param n 셔틀 운행 횟수
     * @param t 셔틀 운행 간격
     * @param m 한 셔틀에 탈 수 있는 최대 크루 수
     * @param timetable 크루가 대기열에 도착하는 시각을 모은 배열
     * @return 콘이 무사히 셔틀을 타고 사무실로 갈 수 있는 제일 늦은 도착 시각
     */
    public static String solution(int n, int t, int m, String[] timetable) {
        // 1. timetable 분 단위로 환산해서 우선순위 큐에 넣기(오름차순 정렬)
        Queue<Integer> q = new PriorityQueue<>();
        for(String time : timetable) {
            String[] tt = time.split(":");
            q.add(Integer.parseInt(tt[0]) * 60 + Integer.parseInt(tt[1]));
        }
        // 2. 콘의 도착시각 최솟값 최댓값 초기화
        int start = 0;  // 최솟값 = 00:00
        int end = 540 + t * (n - 1);    // 최댓값 = 막차시각(막차시각보다 늦으면 탈 수 없음)
        // 3. 제일 늦은 도착시각 구하기
        int answer = 0; // 제일 늦은 도착시각
        while(start <= end) {
            int mid = (start + end) / 2;
            if(canTake(q, mid, n, t, m)) {
                // 4. 콘이 mid 시점에 도착해서 셔틀을 탈 수 있으면 더 늦게 도착해도 됨 -> 오른쪽 탐색, 정답 갱신
                start = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                // 5. 콘이 mid 시점에 도착해서 셔틀을 탈 수 없으면 더 빨리 도착해야 함 -> 왼쪽 탐색
                end = mid - 1;
            }
        }
        // 6. 분 -> 시각으로 변환해 리턴
        return String.format("%02d:%02d", answer / 60, answer % 60);
    }

    /**
     * @param queue 대기열
     * @param con 콘의 도착시각
     * @return 콘이 con 시점에 도착했을 때 셔틀을 탈 수 있는지 여부
     */
    public static boolean canTake(Queue<Integer> queue, int con, int n, int t, int m) {
        int shuttle = 540;  // 셔틀 시각(첫차는 무조건 09:00)
        // 1. 기존 대기열에 콘 도착시각을 포함한 우선순위 큐 생성
        Queue<Integer> q = new PriorityQueue<>(queue);
        q.add(con);
        for(int i = 0; i < n; i++) {
            // 2. 해당 셔틀 도착 시점에 대기열에서 사람 태우기
            int person = 0; // 셔틀에 탑승한 사람수
            // 3. 대기열이 비었으면 모든 사람이 더 탔으므로 break
            if(q.isEmpty()) break;
            // 4. 셔틀에 m 명 보다 적게 타있고 해당 사람을 현재 시점에 태울 수 있으면, 셔틀에 태우기
            while(!q.isEmpty() && shuttle >= q.peek() && person < m) {
                q.poll();
                person++;
            }
            shuttle += t;
        }
        // 5. 큐가 비었거나 콘의 도착시각이 대기열에 없으면 콘이 탑승했다는 의미
        return (q.isEmpty() || !q.contains(con)) ? true : false;
    }
}