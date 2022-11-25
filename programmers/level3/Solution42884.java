package programmers.level3;


import java.util.Arrays;

/**
 * [문제명] 단속 카메라
 * [한줄평]
 * v1.(실패)
 * {-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}
 * {-20, -15}, {-18, -13}, {-14, -5}, {-5, -3}
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/84512/solution_groups?language=java">다른 풀이</a>
 */
class Solution42884 {

    public static void main(String[] args) {
        // 2
        int[][] routes1 = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        int answer1 = solution(routes1);
        System.out.println(answer1);
    }

    /**
     *
     * @param routes 고속도로를 이동하는 차량의 경로
     * - 차량의 대수는 1대 이상 10,000대 이하
     * - routes[i][0]: i번째 차량이 고속도로에 진입한 지점
     * - routes[i][1]: i번째 차량이 고속도로에서 나간 지점
     * - 차량의 진입 지점, 진출 지점은 -30,000 이상 30,000 이하
     * @return 모든 차량이 한 번은 단속용 카메라를 만나도록 하려면 최소 몇 대의 카메라를 설치해야 하는지를 return
     */
    public static int solution(int[][] routes) {
        int answer = 0;
        int n = routes.length;  // 차량 대수
        // (진입 지점, 진출 지점) 오름차순 정렬
        Arrays.sort(routes, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        int prev = -30001;    // 이전 카메라 지점
        for(int i = 0; i < n; i++) {
            int start = routes[i][0];
            int end = routes[i][1];
            if(start <= prev && prev <= end) {
                // 1. 현재 차량 경로가 이전 카메라 지점에 포함되면 설치할 필요X

            } else {
                // 2. 현재 차량 경로가 이전 카메라 지점에 포함되면 진출지점에 설치
                prev = end;
                System.out.println("prev = " + prev);
                answer++;
            }
        }
        return answer;
    }
}