package programmers.level2;


import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 호텔 대실
 * [풀이시간] (19분)
 * [한줄평]
 * 1_v1. (실패 - 3~4,6~18 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/155651">문제</a>
 */
class Solution155651 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

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
    public int solution(String[][] bookTime) {
        Queue<Book> wait = new PriorityQueue<>((o1, o2) -> {
            if(o1.start == o2.start) return o1.end - o2.end;
            return o1.start - o2.start;
        });
        for(String[] book : bookTime) {
            String[] s = book[0].split(":");
            String[] e = book[1].split(":");
            int start = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
            int end = Integer.parseInt(e[0]) * 60 + Integer.parseInt(e[1]);
            wait.add(new Book(start, end));
        }
        // System.out.println(wait);
        Queue<Integer> room = new PriorityQueue<>();
        // List<Integer> room = new ArrayList<>();
        while(!wait.isEmpty()) {
            Book book = wait.poll();
            if(room.isEmpty()) {
                room.add(book.end);
            } else {
                // 가장 빨리 끝나는 방에 배정
                int end = room.peek();
                if(end <= book.start) {
                    room.remove(end);
                }
                room.add(book.end);
            }
        }

        return room.size();
    }
}