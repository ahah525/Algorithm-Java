package programmers.level4;


import java.util.Arrays;

/**
 * [문제명] 징검다리
 * [풀이시간] 1시간
 * [한줄평] 거리의 최솟값을 구하기위해 이분탐색을 써야한다는 것은 알았는데, 어떻게 조건을 설정해야할지 몰라 결국 풀이를 보고 풀었던 문제다.
 * 1_v1. 이분탐색(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43236">문제</a>
 * @See <a href="https://velog.io/@cgw0519/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%AC%B8%EC%A0%9C%ED%92%80%EC%9D%B4-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A7%95%EA%B2%80%EB%8B%A4%EB%A6%AC">풀이 참고</a>
 */
class Solution43236 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        // 1. 바위 위치 오름차순 정렬
        Arrays.sort(rocks);
        // 2. 시작점, 끝점 초기화
        int left = 1;
        int right = distance;
        while(left <= right) {
            int mid = (left + right) / 2;
            int remove = 0; // 최솟값이 mid 이상이 되기 위해 제거해야하는 바위 수
            int prev = 0;   // 이전 바위의 위치
            for(int cur : rocks) {
                // 3. 현재와 이전 사이의 거리가 mid 보다 작으면, 현재 바위 제거
                if(cur - prev < mid) remove++;
                // 4. 그렇지 않으면, 현재 바위를 제거할 필요가 없으므로 이전 바위 위치만 갱신
                else prev = cur;
            }
            // 5. 도착 지점과 마지막 바위 사이의 거리가 mid 보다 작으면, 마지막 바위 제거
            if(distance - prev < mid) remove++;
            if(remove > n) {
                // 6. 제거해야하는 바위수가 n개 보다 크면, 왼쪽 범위 탐색
                right = mid - 1;
            } else {
                // 7. 제거해야하는 바위수가 n개 이하면, 오른쪽 범위 탐색
                left = mid + 1;
                answer = Math.max(answer, mid);
            }
        }
        return answer;
    }
}