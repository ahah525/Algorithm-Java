package programmers.level3;


import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] [1차] 셔틀버스
 * [풀이시간] (1시간 8분)
 * [한줄평]
 * 1_v1. PriorityQueue, 이분탐색(실패 - 19~22, 24 실패)
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
        // 1. timetable 분 단위로 환산(오름차순 정렬)
        Queue<Integer> q = new PriorityQueue<>();
        for(String time : timetable) {
            String[] tt = time.split(":");
            q.add(Integer.parseInt(tt[0]) * 60 + Integer.parseInt(tt[1]));
        }
        // 2.
        int start = Math.min(q.peek() - 1, 540);    // 첫차시각
//        System.out.println("start = " + start);
        int end = 540 + t * (n - 1);    // 막차시각
        int answer = 0;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(canTake(q, mid, n, t, m)) {
                start = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                end = mid - 1;
            }
        }
//        System.out.println("answer = " + answer);
        return String.format("%02d:%02d", answer / 60, answer % 60);
    }

    // 콘이 셔틀타고 갈 수 있는지
    public static boolean canTake(Queue<Integer> queue, int con, int n, int t, int m) {
//        System.out.println("con = " + con);
        int shuttle = 540;  // 셔틀 시각
        Queue<Integer> q = new PriorityQueue<>(queue);
        q.add(con);
//        System.out.println(q);

        // 셔틀 태우기
        for(int i = 0; i < n; i++) {
            // 해당 시각에 탈 수 있는 사람 태우기
            int person = 0;
            if(q.isEmpty()) break;
            while(!q.isEmpty() && shuttle >= q.peek() && person < m) {
                q.poll();
                person++;
            }
            shuttle += t;
        }
        //
//        System.out.println(q);

//        System.out.println("------------------");
        return (q.isEmpty() || q.peek() != con) ? true : false;
    }
}