package programmers.level3;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [문제명] 달리기 경주
 * [풀이시간] 14분
 * [한줄평] 레벨3 문제였지만 너무 기초적인 BFS 문제여서 쉽게 풀었다.
 * 1_v1. BFS(성공)
 * [풀이] 인접리스트
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/132266">문제</a>
 */
class Solution132266 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    List<Integer>[] graph; // 인접 리스트

    /**
     * @param n 강철부대가 위치한 지역을 포함한 총지역의 수
     * @param roads 두 지역을 왕복할 수 있는 길 정보를 담은 2차원 정수 배열
     * @param sources 각 부대원이 위치한 서로 다른 지역들을 나타내는 정수 배열
     * @param destination 강철부대의 지역
     * @return 주어진 sources의 원소 순서대로 강철부대로 복귀할 수 있는 최단시간을 담은 배열
     */
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 1. 인접 리스트 초기화
        graph = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] road : roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
        // 2. 출발지 -> 목적기 최단거리 계산, 저장
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++) {
            answer[i] = bfs(sources[i], destination, new int[n + 1]);
        }
        return answer;
    }

    // start -> destination 까지 최단거리
    public int bfs(int start, int destination, int[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = 1;
        while(!q.isEmpty()) {
            int v = q.poll();
            if(v == destination) return visited[v] - 1;
            for(int nv : graph[v]) {
                if(visited[nv] != 0) continue;
                q.add(nv);
                visited[nv] = visited[v] + 1;
            }
        }
        return -1;
    }
}