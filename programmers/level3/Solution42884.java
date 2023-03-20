package programmers.level3;


import java.util.Arrays;

/**
 * [문제명] 단속 카메라
 * [풀이시간] / 1시간 20분(50분 + 30분)
 * [한줄평] 보자마자 정렬을 해야겠다는 것은 알았는데 설치 조건을 어떻게 해야할지 고민하느라 시간을 많이 쏟았던 문제였다. 결국 반례를 보고 문제를 해결할 수 있었다. / 결국 반례를 잡지 못해 풀이를 참고했다.
 * v1. 경우의 수를 2가지로 나눔(실패)
 * - 1. 현재 차량 경로가 이전 카메라 지점에 포함되면 설치할 필요X
 * - 2. 현재 차량 경로가 이전 카메라 지점에 포함되면 진출지점에 설치
 * -> 오답 원인: 현재 차량 경로가 이전 차량 경로 내에 포함되는 경우를 고려하지 않음
 * v2. 경우의 수를 3가지로 나눔(성공)
 * - 1. 현재 범위와 카메라 범위가 아예 안겹치는 경우, "현재 범위 끝" 에 설치, "카메라 범위" 재조정
 * - 2. 현재 범위가 카메라 범위에 아예 포함되는 경우, "현재 범위 끝" 에 재설치, "카메라 범위" 재조정
 * - 3. 현재 범위가 카메라 범위와 일부 겹치는 경우, "카메라 끝 범위" 재조정
 * 2_v1. 그리디(실패- 정확성 1, 효율성 5 실패)
 * 2_v2. 그리디(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42884">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/6899">반례</a>
 * @See <a href="https://postimg.cc/K4Z8gfRS">풀이 정리 이미지</a>
 * @See <a href="https://velog.io/@ahnick/programmers-%EB%8B%A8%EC%86%8D%EC%B9%B4%EB%A9%94%EB%9D%BC">풀이</a>
 */
class Solution42884 {

    public static void main(String[] args) {
        // 2
        int[][] routes1 = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        System.out.println(solution(routes1));

        // 2
        int[][] routes2 = {{-2, -1}, {1, 2}, {-3, -0}};
        System.out.println(solution(routes2));

        // 1
        int[][] routes3 = {{0, 0}};
        System.out.println(solution(routes3));

        // 1
        int[][] routes4 = {{0, 1}, {0, 1}, {1, 2}};
        System.out.println(solution(routes4));

        // 4
        int[][] routes5 = {{0, 1}, {2, 3}, {4, 5}, {6, 7}};
        System.out.println(solution(routes5));

        // 2(15, -13,
        int[][] routes6 = {{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}};
        System.out.println(solution(routes6));
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
        int answer = 0;         // 설치한 카메라 개수
        int n = routes.length;  // 차량 대수
        // (진입 지점, 진출 지점) 오름차순 정렬
        Arrays.sort(routes, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
//        int cam = routes[0][1];    // 첫번째 차량의 끝 지점에 카메라 설치
        // 첫번째 차량의 범위 = 카메라 범위
        int camS = routes[0][0];
        int camE = routes[0][1];
        answer++;
        for(int i = 1; i < n; i++) {
            // 현재 차량의 범위
            int nowS = routes[i][0];
            int nowE = routes[i][1];
            if(camE < nowS) {
                // 1. 현재 범위와 카메라 범위가 아예 안겹치는 경우, "현재 범위 끝" 에 설치, "카메라 범위" 재조정
//                cam = nowE;
                camS = nowS;
                camE = nowE;
                answer++;   // 1번 경우에만 카메라 설치 개수 카운팅
            } else if(camS <= nowS && nowE <= camE) {
                // 2. 현재 범위가 카메라 범위에 아예 포함되는 경우, "현재 범위 끝" 에 재설치, "카메라 범위" 재조정
//                cam = nowE;
                camS = nowS;
                camE = nowE;
            } else if(nowS <= camE) {
                // 3. 현재 범위가 카메라 범위와 일부 겹치는 경우, "카메라 끝 범위" 재조정
                camS = nowS;
            }
        }
        return answer;
    }

    // 2_v2
    public static int solutin2(int[][] routes) {
        // 1. 진출지점 오름차순 정렬
        Arrays.sort(routes, (o1, o2) -> {
            return o1[1] - o2[1];
        });

        int answer = 0;
        int cam = -30001;   // 캠이 설치된 위치
        for(int[] route : routes) {
            if(cam < route[0]) {
                // 2. 캠 위치 < 차량의 진입 지점 -> 캠 설치
                cam = route[1];
                answer++;
            }
        }
        return answer;
    }
}