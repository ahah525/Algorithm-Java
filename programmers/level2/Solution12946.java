package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] 하노이의 탑
 * [풀이시간] (45분)
 * [한줄평]
 * 1_v1. 재귀(실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12946">문제</a>
 * @See <a href="https://shoark7.github.io/programming/algorithm/tower-of-hanoi">하노이의 탑 원리</a>
 */
class Solution12946 {
    public static void main(String[] args) {
        //
        System.out.println(solution(3));
    }

    // 1_v1
    static List<int[]> answer;

    /**
     * @param n 1번 기둥에 있는 원판의 개수
     * @return n개의 원판을 3번 원판으로 최소로 옮기는 방법
     */
    public static List<int[]> solution(int n) {
        answer = new ArrayList<>();

        hanoi(n, 1, 3, 2);

        return answer;
    }

    // n 개의 선반을 via 를 거쳐 from -> to 로 옮기는 방법
    public static void hanoi(int n, int from , int to, int via) {
        if(n == 1) {
            answer.add(new int[]{from, to});
//             System.out.println(n + "," + from + "," + to + "," + via);
            return;
        }
        hanoi(n - 1, from, via, to);
        hanoi(n - 1, from , to, 0);
        hanoi(n - 1, via, to, from);
    }
}