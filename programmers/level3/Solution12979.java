package programmers.level3;


import java.util.Arrays;

/**
 * [문제명] 기지국 설치
 * [풀이시간] / 23분
 * [한줄평] n 의 범위가 크기때문에, 배열에 하나씩 체크하면 안되고 각 범위를 커버할 수 있는 기지국의 개수를 수식으로 만들어 계산해야 하는 구현 문제였다.
 * 아이디어는 나름 쉽게 떠올렸는데, 생각보다 구현하는데 시간이 오래걸렸던 문제로 다시 한번 꼭 풀어봐야겠다.
 * 1_v1. 수학적 접근(성공)
 * 2_v1. (실패 - 효율성 테스트 2~3 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12979">문제</a>
 * @See <a href="https://i.postimg.cc/mDkjcvyW/3.png">그림 참고</a>
 */
class Solution12979 {
    public static void main(String[] args) {
        // 3
        int n1 = 11;
        int[] stations1 = {4, 11};
        int w1 = 1;
        System.out.println(solution(n1, stations1, w1));

        // 3
        int n2 = 16;
        int[] stations2 = {9};
        int w2 = 2;
        System.out.println(solution(n2, stations2, w2));
    }

    // 1_v1
    /**
     * @param n 아파트의 개수
     * @param stations 현재 기지국이 설치된 아파트의 번호가 담긴 1차원 배열
     * @param w 전파의 도달 거리
     * @return 모든 아파트에 전파를 전달하기 위해 증설해야 할 기지국 개수의 최솟값
     */
    public static int solution(int n, int[] stations, int w) {
        int answer = 0;
        int cnt = stations.length;      // 현재 설치된 기지국 개수
        int first = stations[0];        // 첫번째 값
        int dis = 0;                    // 탐색 범위의 아파트 개수
        int range = 2 * w + 1;          // 기지국의 전파 범위
        // 1. 첫번째 값 이전 범위에 대한 처리(이전 기지국이 존재하지 않음)
        // 첫번째 값이 1이 아닌 경우, 이전 범위에 대한 처리 필요
        if(first != 1) {
            dis = first - w - 1;
            answer += Math.ceil((double) dis / range);
        }

        int prev = first;           // 이전 기지국 설치 지점
        // 2. 두번째 이후 값에 대한 처리(이전 기지국이 무조건 존재함)
        for(int i = 1; i < cnt; i++) {
            dis = stations[i] - prev - range;
            answer += Math.ceil((double) dis / range);
            prev = stations[i];
        }
        // 3. 마지막 값 이후 범위에 대한 처리
        // 마지막 값이 끝 값이 아닐 경우, 이후 범위에 대한 처리 필요
        if(stations[cnt - 1] != n) {
            dis = n - prev - w;
            answer += Math.ceil((double) dis / range);
        }
        return answer;
    }

    // 2_v1
    int size;
    public int solution2(int n, int[] stations, int w) {
        int answer = 0;
        Arrays.sort(stations);
        size = 2 * w + 1;
        answer += calc(1, stations[0] - w - 1);
        for(int i = 0; i < stations.length - 1; i++) {
            answer += calc(stations[i] + w + 1, stations[i + 1] - w - 1);
        }
        answer += calc(stations[stations.length - 1] + w + 1, n);
        return answer;
    }

    public int calc(int s, int e) {
        return (s > e) ? 0 : (int) Math.ceil((double) (e - s + 1) / size);
    }
}