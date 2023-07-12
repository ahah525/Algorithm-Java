package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] H-Index
 * [풀이시간] 10분 / 44분(34분 + 10분) / 40분(35분, 5분)
 * [한줄평] 그냥 구현만 하면 되는 굉장히 쉬운 문제였다. 다만 h 의 최댓값을 구하기 위해 어떤 값부터 탐색할 것인지를 잘 정해야 효율성을 높일 수 있다.
 * / 첫번째 풀이와 달리 정렬을 하고 풀었는데 로직을 짜는데 너무 시간이 오래걸렸다.
 * / h 값을 citations 의 원소값으로만 설정하도록 로직을 짜서 반례를 찾지 못해 틀렸던 문제로 꼭 복습이 필요하다!
 * 1_v1. 구현(성공)
 * 최대 논문수 = n, 최대 논문 인용 횟수 = 배열의 최댓값
 * "h번 이상 인용된 논문이 h편 이상" 인 조건을 만족하는 h 는 최대 논문수와 최대 논문 인용 횟수의 최솟값 보다 큰 값일 수는 없다.
 * -> 두 값의 최솟값부터 -1 을 하며 반복했을 때, 해당 조건을 만족하는 h 가 최댓값이다.
 * 2_v1. 정렬(실패 - 9 실패)
 * 2_v2. 정렬(성공) -> 실패
 * [풀이] h의 최댓값 = Math.min(논문 개수, 논문 인용 횟수의 최댓값)
 * 3_v1. 정렬(실패)
 * [풀이] h의 값을 인용수 값으로만 설정함
 * 3_v2. 정렬(성공)
 * [풀이] h번 이상 인용된 논문 수 구하기 => for 문으로 탐색
 * 3_v3. 정렬, 이분탐색(성공)
 * [풀이] h번 이상 인용된 논문 수 구하기 => 이분탐색
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42747">문제</a>
 */
class Solution42747 {
    public static void main(String[] args) {
        // 3
        int[] citations1 = {3, 0, 6, 1, 5};
        System.out.println(solution(citations1));
    }

    // 1_v1
    /**
     * @param citations 어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열
     * @return 이 과학자의 H-Index
     * H-Index = n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최댓값
     */
    public static int solution(int[] citations) {
        int max = 0;    // 논문 인용횟수 최댓값
        for(int cnt : citations) {
            max = Math.max(cnt, max);
        }
        // 논문수 & 논문 인용횟수 최댓값 중 최솟값을 h 초기값으로 설정해서 반복
        int min = Math.min(citations.length, max);
        for(int h = min; h >= 0; h--) {
            int n = 0;  // h번 이상 인용된 논문수
            for(int cnt : citations) {
                // h번 이상 인용됐으면
                if(h <= cnt) {
                    n++;
                }
                //
                if(h <= n)
                    return h;
            }
        }
        return 0;
    }

    // 2_v2, 3_v2
    public int solution2(int[] citations) {
        int n = citations.length;
        // 1. 오름차순 정렬
        Arrays.sort(citations);
        // 2. h의 최댓값 초기화
        int h = Math.min(n, citations[n - 1]);
        // 3. h의 최댓값 찾기
        while(h >= 0) {
            // 논문의 인용 횟수가 h 이상인 논문의 수 세기
            int cnt = 0;
            for(int i = n - 1; i >= 0; i--) {
                if(citations[i] < h) break;
                cnt++;
            }
            // 논문의 인용 횟수가 h 이상인 논문의 수가 h 이상이면
            if(h <= cnt) return h;
            h--;
        }
        return 0;
    }

    // 3_v3
    int n;
    public int solution3(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        n = citations.length;
        int h = Math.min(citations[n - 1], n);
        while(h >= 0) {
            int cnt = getCount(h, citations);
            if(cnt >= h) return h;
            h--;
        }
        return 0;
    }

    // h번 이상 인용된 논문수
    public int getCount(int h, int[] citations) {
        int start = 0;
        int end = n - 1;
        int idx = 0;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(citations[mid] >= h) {
                end = mid - 1;
                idx = mid;
            } else {
                start = mid + 1;
            }
        }
        return n - idx;
    }
}