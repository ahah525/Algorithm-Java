package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] H-Index
 * [풀이시간] 10분 / (34분)
 * [한줄평] 그냥 구현만 하면 되는 굉장히 쉬운 문제였다. 다만 h 의 최댓값을 구하기 위해 어떤 값부터 탐색할 것인지를 잘 정해야 효율성을 높일 수 있다.
 * 1_v1. 구현(성공)
 * 최대 논문수 = n, 최대 논문 인용 횟수 = 배열의 최댓값
 * "h번 이상 인용된 논문이 h편 이상" 인 조건을 만족하는 h 는 최대 논문수와 최대 논문 인용 횟수의 최솟값 보다 큰 값일 수는 없다.
 * -> 두 값의 최솟값부터 -1 을 하며 반복했을 때, 해당 조건을 만족하는 h 가 최댓값이다.
 * 2_v1. (실패 - 9 실패)
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

    // 2_v1
    public int solution2(int[] citations) {
        int answer = 0;
        //
        Arrays.sort(citations);
        int n = citations.length;
        int h = Math.min(n - 1, citations[n - 1]);
        while(true) {
            //
            int cnt = 0;
            for(int i = 0; i < n; i++) {
                // 논문의 인용 횟수가 h이상인 곳
                if(citations[i] >= h) {
                    cnt = n - i;
                    break;
                }
            }
            if(h <= cnt) return h;
            h--;
        }
        // return answer;
    }
}