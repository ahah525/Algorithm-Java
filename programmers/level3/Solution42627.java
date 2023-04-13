package programmers.level3;


import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 디스크 컨트롤러
 * [풀이시간] 1시간 30분 / (1시간 + 50분)
 * [한줄평] 저번에 비슷한 문제를 풀어봤음에도 생각보다 구현하는게 너무 어려웠던 문제였다. 결국 답을 보고 풀이를 할 수 밖에 없없다..
 * 1_v1. PriorityQueue(성공)
 * 2_v1. PriorityQueue(실패 - 1~16,18 실패)
 * 2_v2. PriorityQueue(실패 - 3,6~15,17~19 런타임 에러)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/">문제</a>
 * @See <a href="https://codevang.tistory.com/316">풀이 참고</a>
 */
class Solution42627 {
    public static void main(String[] args) {
        //
        System.out.println(solution2(new int[][]{{0, 3}, {1, 9}, {2, 6}}));
    }

    public static int solution(int[][] jobs) {
        // 1. 원본 배열 요청시점 오름차순 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        // 2. 대기큐 작업시간 오름차순 정렬
        Queue<int[]> q = new PriorityQueue<>((o1, o2) -> {
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        int times = 0;  // 모든 작업의
        int cnt = 0;    // 완료된 작업 개수
        int end = 0;    // 이전 작업의 종료 시점
        int idx = 0;    // 작업 배열 인덱스
        // 모든 작업이 완료될까지 반복
        while(cnt < jobs.length) {
            // 3. 이전 작업 처리 중에 요청된 모든 작업을 대기큐에 추가
            while(idx < jobs.length && jobs[idx][0] <= end) {
                q.add(jobs[idx++]);
            }

            if(q.isEmpty()) {
                // 4. 이전 작업 처리 중에 요청된 작업이 없는 경우
                end = jobs[idx][0]; // 다음 작업 요청 시점으로 갱신
            } else {
                // 5. 이전 작업 처리 중에 요청된 작업이 있는 경우
                // 5-1. 대기큐에서 1개 작업 꺼내서 실행시키기
                int[] poll = q.poll();
                // 5-2. 현재 작업의 요청부터 종료까지 걸린 시간 = 현재 작업의 (대기시간 + 작업시간) = (이전 작업의 종료 시점 - 현재 작업의 요청 시점) + 작업시간
                times += (end - poll[0]) + poll[1];
                // 5-3. 이전 작업 완료 처리
                end += poll[1]; // 현재 작업의 종료시점 = 이전 작업의 종료 시점 + 현재 작업의 작업 시간
                cnt++;          // 완료된 작업 개수 카운팅
            }
        }
        return times / jobs.length;
    }

    // 2_v2
    static class Job {
        int request;
        int play;

        Job(int request, int play) {
            this.request = request;
            this.play = play;
        }
    }
    public static int solution2(int[][] jobs) {
        int answer = 0;
        // 1. 요청 시점 오름차순 정렬
        Arrays.sort(jobs, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        // 2. 실행 시간, 요청 시점 오름차순 정렬
        Queue<Job> wait = new PriorityQueue<>((o1, o2) -> {
            if(o1.play == o2.play) return o1.request - o2.request;
            return o1.play - o2.play;
        });
        int i = 0;     // 예약 배열 인덱스
        int end = 0;   // 현재 작업 종료 시점
        int cnt = 0;   // 종료한 작업의 개수
        // 예약 배열 -> 대기큐 -> 실행(현재 작업)
        while(cnt < jobs.length) {
            if(i < jobs.length && jobs[i][0] <= end) {
                // 3. 현재 작업이 끝나기 전에 요청된 작업이 있음: 예약 배열에서 꺼낸 1개의 작업 -> 대기큐로
                wait.add(new Job(jobs[i][0], jobs[i][1]));
                i++;
            } else {
                // 4. 현재 작업이 끝나기 전에 요청된 작업이 없음: 대기큐에서 꺼낸 1개의 작업 -> 실행 상태로
                // 4-1. 대기큐에서 실행 시간이 가장 짧은 작업 1개 꺼내기
                Job job = wait.poll();
                // 4-2. 실행 상태가 된 작업의 요청~종료 시간 = 대기시간 + 실행시간
                answer += (end - job.request) + job.play;
                // 4-3. 현재 작업의 종료 시점 = 이전 작업의 종료 시점 + 현재 작업 실행 시간
                end += job.play;
                cnt++;
            }
        }
        return answer / jobs.length;
    }
}