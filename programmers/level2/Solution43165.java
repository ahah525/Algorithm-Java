package programmers.level2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 타겟 넘버
 * [풀이시간] / 9분
 * [한줄평] / 너무 기초적인 문제라 쉽게 풀 수 있었다. BFS 보다는 DFS 가 수행시간이 빠르다.
 * 1_v1. DFS(성공)
 * 2_v1. DFS(성공) -> 추천
 * - 원래 DFS 에서 중복 선택이 불가하면 visited[] 를 써야하지만 여기서는 선택하는 숫자의 순서를 바꿀 수 없기 때문에 쓸 필요가 없었음
 * 2_v2. BFS(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43165">문제</a>
 */
class Solution43165 {
    static int answer;

    public static void main(String[] args) {
        // 5
        int[] numbers1 = {1, 1, 1, 1, 1};
        System.out.println(solution(numbers1, 3));

        // 2
        int[] numbers2 = {4, 1, 2, 1};
        System.out.println(solution(numbers2, 4));
    }

    /**
     * @param numbers 사용할 수 있는 숫자가 담긴 배열(n개의 음이 아닌 정수)
     * @param target 목표값
     * @return 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수
     */
    public static int solution(int[] numbers, int target) {
        answer = 0;
        dfs(numbers, target, numbers.length, 0, 0);
        return answer;
    }

    // 1_v1, 2_v1
    public static void dfs(int[] numbers, int target, int n, int depth, int value) {
        if(depth == n) {
            if(target == value) answer++;
            return;
        }
        // 이진 트리
        // 1. +
        dfs(numbers, target, n, depth + 1, value + numbers[depth]);
        // 2. -
        dfs(numbers, target, n, depth + 1, value - numbers[depth]);
    }

    // 2_v2
    public int solution2(int[] numbers, int target) {
        answer = 0;
        bfs(numbers, target);
        return answer;
    }

    class Node {
        int sum;
        int depth;

        Node(int sum, int depth) {
            this.sum = sum;
            this.depth = depth;
        }
    }

    public void bfs(int[] numbers, int target) {
        Queue<Node> q = new LinkedList<>();
        // 시작 노드 방문 처리
        q.add(new Node(0, 0));

        while(!q.isEmpty()) {
            Node node = q.poll();
            int sum = node.sum;
            int depth = node.depth;
            if(depth == numbers.length) {
                if(sum == target)
                    answer++;
                continue;
            }
            // 1. +
            q.add(new Node(sum + numbers[depth], depth + 1));
            // 2. -
            q.add(new Node(sum - numbers[depth], depth + 1));
        }
    }
}