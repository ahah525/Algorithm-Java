package programmers.level2;


import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 호텔 대실
 * [풀이시간] 21분(19분+2분) / 15분(13분 + 2분)
 * [한줄평] 문제를 꼼꼼하게 읽자. / 문제를 꼼꼼히 읽지 않아서 처음에 틀렸던 문제였다.
 * 1_v1. PriorityQueue(실패 - 3~4,6~18 실패)
 * 1_v2. PriorityQueue(성공)
 * [반례] 대실 종료 시각 10분 후 새로운 대실을 받을 수 있음
 * 2_v1. PriorityQueue(실패 - 3~4, 6~18 실패)
 * 2_v2. PriorityQueue(성공) -> 빠름
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/155651">문제</a>
 */
class Solution155651 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2
    class Book {
        int start;
        int end;
        Book(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return start + "," + end;
        }
    }

    /**
     * @param bookTime 예약 시각이 문자열 형태로 담긴 2차원 배열
     * @return 코니에게 필요한 최소 객실의 수
     */
    public int solution(String[][] bookTime) {
        // 1. 대실시작 시각 기준 오름차순 정렬
        Queue<Book> wait = new PriorityQueue<>((o1, o2) -> {
            return o1.start - o2.start;
        });
        for(String[] book : bookTime) {
            String[] s = book[0].split(":");
            String[] e = book[1].split(":");
            int start = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
            int end = Integer.parseInt(e[0]) * 60 + Integer.parseInt(e[1]);
            wait.add(new Book(start, end));
        }
        // 2. 예약 룸 배정
        Queue<Integer> room = new PriorityQueue<>();    // 룸에 배정된 예약의 대실 종료시각
        while(!wait.isEmpty()) {
            // 3. 대기큐에서 예약 1개 꺼내기
            Book book = wait.poll();
            if(room.isEmpty()) {
                // 4. 룸이 비었으면 바로 넣기
                room.add(book.end);
            } else {
                // 5. 가장 빨리 대실 종료되는 시각 + 방 청소 시간 <= 예약의 대실 시작 시각 -> 해당 방으로 배정
                // 6. 가장 빨리 대실 종료되는 시각 + 방 청소 시간 > 예약의 대실 시작 시각 -> 새로운 방으로 배정
                int end = room.peek();
                if(end + 10 <= book.start)
                    room.remove(end);
                room.add(book.end);
            }
        }
        return room.size();
    }

    // 2_v1
    public int solution2(String[][] bookTime) {
        int answer = 0;
        // 1. 시작 시각 오름차순 정렬
        Arrays.sort(bookTime, (o1, o2) -> {
            return o1[0].compareTo(o2[0]);
        });
        Queue<Integer> pq = new PriorityQueue<>();
        for(String[] time : bookTime) {
            int start = getTime(time[0]);
            int end = getTime(time[1]);
            if(!pq.isEmpty() && pq.peek() <= start) pq.poll();
            pq.add(end + 10);
            answer = Math.max(answer, pq.size());
        }
        return answer;
    }

    // 시간을 분으로 환산
    public int getTime(String s) {
        String[] t = s.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}