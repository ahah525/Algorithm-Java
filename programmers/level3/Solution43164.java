package programmers.level3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [문제명] 여행경로
 * [풀이시간] 35분 / 1시간 45분
 * [한줄평] DFS 로 풀어야겠다는 것은 알았지만 경로를 리스트 대신 문자열로 다룬다는 힌트를 얻었기 때문에 다시 한번 풀어봐야할 문제다. /
 * 1_v1. DFS(성공)
 * 2_v1. DFS(성공) -> 더 빠름
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43164">문제</a>
 * @See <a href="https://velog.io/@rari_1110/DFS-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%97%AC%ED%96%89%EA%B2%BD%EB%A1%9C-JAVA">힌트</a>
 */
class Solution43164 {
    public static void main(String[] args) {
        // ["ICN", "JFK", "HND", "IAD"]
        String[][] tickets1 = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        System.out.println(solution(tickets1));

        // ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
        String[][] tickets2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        System.out.println(solution(tickets2));
    }

    // 1_v1
    private static String bestRoute;

    /**
     * @param tickets 항공권 정보가 담긴 2차원 배열(출발지, 도착지)
     * @return 방문하는 공항 경로를 배열에 담아 return(가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return)
     */
    public static List<String> solution(String[][] tickets) {
        List<String> answer = new ArrayList<>();
        bestRoute = "Z";    // 최적 경로 초기화(ICN 보다 뒷순서인 임의의 문자열)
        int n = tickets.length;
        dfs(0, new boolean[n], "ICN", "ICN", n, tickets);
        // 3글자씩 분리
        for(int i = 0; i < bestRoute.length() / 3; i++) {
            answer.add(bestRoute.substring(3 * i, 3 * (i + 1)));
        }
        return answer;
    }

    /**
     * @param depth 사용한 항공권 개수
     * @param visited 항공권 사용여부
     * @param start 현재 출발지
     * @param route 경로
     * @param n 항공권 개수
     * @param tickets 항공권
     */
    public static void dfs(int depth, boolean[] visited, String start, String route, int n, String[][] tickets) {
        // 항공권을 모두 사용했을 때
        if(depth == n) {
            // 현재 경로가 최적 경로보다 알파벳순서로 앞설 때
            if(route.compareTo(bestRoute) < 0)
                bestRoute = route;
        }
        // 남은 선택권 중 현재 출발지에서 갈 수 있는 곳 탐색
        for(int i = 0; i < n; i++) {
            if(!visited[i] && tickets[i][0].equals(start)) {
                visited[i] = true;
                dfs(depth + 1, visited, tickets[i][1], route + tickets[i][1], n, tickets);
                visited[i] = false;
            }
        }
    }

    // 2_v1
    String[] answer;
    boolean find;
    public String[] solution2(String[][] tickets) {
        int n = tickets.length;
        answer = new String[n + 1];
        // 1.
        Arrays.sort(tickets, (o1, o2) -> {
            if(o1[0].equals(o2[0])) {
                return o1[1].compareTo(o2[1]);
            }
            return o1[0].compareTo(o2[0]);
        });
        //
        String[] path = new String[n + 1];
        path[0] = "ICN";
        dfs2(1, "ICN", n, tickets, new boolean[n], path);

        return answer;
    }

    public void dfs2(int depth, String start, int n, String[][] tickets, boolean[] visited, String[] path) {
        if(find) return;
        // n 개를 다 선택했으면
        if(depth == n + 1) {
            for(int i = 0; i < n + 1; i++) {
                answer[i] = path[i];
            }
            find = true;
            return;
        }
        for(int i = 0; i < n; i++) {
            if(!visited[i] && tickets[i][0].equals(start)) {
                visited[i] = true;
                path[depth] = tickets[i][1];
                dfs2(depth + 1, tickets[i][1], n, tickets, visited, path);
                visited[i] = false;
            }
        }
    }
}