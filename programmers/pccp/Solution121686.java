package programmers.pccp;


import java.util.*;

/**
 * [문제명] [PCCP 모의고사 #1] 유전법칙
 * [풀이시간] 2시간 이상
 * [한줄평] PriorityQueue 를 써야한다는 건 알았는데 대기시간을 계산하는게 많이 어려웠고 결국 풀이를 보고 이해했던 문제였다. 꼭 다시 한번 풀어봐야할 문제다!
 * v1. List 1개, PriorityQueue 1개(성공)
 * - List: 호출시각 오름차순으로 정렬한 프로그램 리스트(대기큐에 넣을 후보)
 * - PriorityQueue: 점수 오름차순, 호출시각 오름차순 정렬한 대기큐
 * @See <a href="https://school.programmers.co.kr/learn/courses/15008/lessons/121686">문제</a>
 * @See <a href="https://prod.velog.io/@now20412041/PCCP-%EB%AA%A8%EC%9D%98%EA%B3%A0%EC%82%AC-%EC%9A%B4%EC%98%81%EC%B2%B4%EC%A0%9C">풀이 참고</a>
 */
class Solution121686 {
    public static void main(String[] args) {
        // [20, 5, 0, 16, 0, 0, 0, 0, 0, 0, 0]
        int[][] program1 = {{2, 0, 10}, {1, 5, 5}, {3, 5, 3}, {3, 12, 2}};
        System.out.println(solution(program1));

        // [19, 0, 0, 4, 3, 14, 0, 0, 0, 0, 0]
        int[][] program2 = {{3, 6, 4}, {4, 2, 5}, {1, 0, 5}, {5, 0, 5}};
        System.out.println(solution(program2));
    }

    public static class P {
        int score;  // 점수
        int call;   // 호출 시각
        int play;   // 실행 시간
        public P(int score, int call, int play) {
            this.score = score;
            this.call = call;
            this.play = play;
        }
    }

    /**
     * @param program 프로그램의 정보(점수, 호출시각, 실행시간)
     * @return
     */
    public static long[] solution(int[][] program) {
        long[] answer = new long[11];
        // 프로그램 리스트 호출시각 오름차순 정렬
        List<P> list = new ArrayList<>();
        for(int[] p : program) {
            list.add(new P(p[0], p[1], p[2]));
        }
        Collections.sort(list, (o1, o2) -> o1.call - o2.call);
        // 대기큐는 점수 오름차순, 호출시각 오름차순 정렬
        Queue<P> q = new PriorityQueue<>((o1, o2) -> {
            if(o1.score == o2.score) {
                return o1.call - o2.call;
            }
            return o1.score - o2.score;
        });

        int n = program.length; // 프로그램 개수
        int t = 0;          // 현재시각(초)
        int cnt = 0;        // 지금까지 실행된 프로그램 수
        int playEnd = 0;    // 가장 최근에 실행된 프로그램 종료 시각
        int idx = 0;        // list 의 탐색 인덱스
        while(cnt < n) {
            // t 초에 호출된 프로그램이 있으면 대기 큐에 넣기
            for(int i = idx; i < n; i++) {
                if(list.get(i).call == t) {
                    q.add(list.get(i));
                    idx++;
                } else {
                    break;
                }
            }
            // 대기 중인 프로그램이 있으면 큐에서 1개 빼서 프로그램 실행시키기
            if(!q.isEmpty() && playEnd <= t) {
                P poll = q.poll();
                playEnd = t + poll.play;             // 프로그램 종료 시각 계산
                answer[poll.score] += t - poll.call; // 대기 시각 = 실행 시각 - 호출 시각
                cnt++;
            }
            t++;
        }
        answer[0] = playEnd;    // 가장 최근에 실행된 프로그램의 종료시각 = 최종 종료시각

        return answer;
    }
}