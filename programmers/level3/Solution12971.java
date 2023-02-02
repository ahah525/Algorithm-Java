package programmers.level3;


/**
 * [문제명] 스티커 모으기(2)
 * [풀이시간] 40분
 * [한줄평] 처음에는 DFS 로 접근했는데 그러기엔 N이 10만이라 시간초과가 날 것 같았다. 결국엔 문제 풀이를 보고서야 해결을 했고 꼭 복습이 필요하다!
 * v1. DP(성공)
 * - 이 문제의 핵심은 DP 로 푸는 것인데, 일반적인 DP 문제처럼 점화식을 1개 세운다고 바로 풀 수 있는 문제는 아니다.
 * 1. 점화식 세우기: d[i] = Math.max(d[i - 1], d[i - 2] + sticker[i])
 * - d[i]: i번째 스티커까지 스티커를 똈을 때 최대합(i 번째는 뗼 수도 안 뗼 수도 있음)
 * 2. 첫번째와 마지막 스티커는 동시에 선택할 수 없으므로 2가지 경우로 나누어 생각한다.
 * 1) 첫번째 스티커를 떼는 경우, 마지막 스티커는 뗄 수 없음
 * 2) 첫번째 스티커를 뗴지 않는 경우, 마지막 스티커는 떼어도 안떼어도 상관없음
 * [점화식]
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12971">문제</a>
 * @See <a href="https://iron-jin.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%8A%A4%ED%8B%B0%EC%BB%A4-%EB%AA%A8%EC%9C%BC%EA%B8%B02">문제 풀이</a>
 */
class Solution12971 {
    public static void main(String[] args) {
        // 36
        int[] sticker1 = {14, 6, 5, 11, 3, 9, 2, 10};
        System.out.println(solution(sticker1));

        // 8
        int[] sticker2 = {1, 3, 2, 5, 4};
        System.out.println(solution(sticker2));
    }

    /**
     * @param sticker 원형으로 연결된 스티커의 각 칸에 적힌 숫자가 순서대로 들어있는 배열
     * @return
     */
    public static int solution(int sticker[]) {
        int n = sticker.length; // 스티커 개수
        // 1. 길이가 1인 경우, 무조건 하나를 떼기
        if(n == 1) {
            return sticker[0];
        }
        // 2. 첫번째 스티커를 떼는 경우
        int[] d1 = new int[n];
        d1[0] = sticker[0];
        d1[1] = sticker[0];
        // 마지막 스티커는 뗄 수 없음(n - 2 까지인 이유)
        for(int i = 2; i < n - 1; i++) {
            d1[i] = Math.max(d1[i - 1], d1[i - 2] + sticker[i]);
        }
        // 3. 첫번째 스티커를 뗴지 않는 경우
        int[] d2 = new int[n + 1];
        d2[0] = 0;
        d2[1] = sticker[1];
        // 마지막 스티커는 떼어도 안떼어도 상관없음(n - 1 까지인 이유)
        for(int i = 2; i < n; i++) {
            d2[i] = Math.max(d2[i - 1], d2[i - 2] + sticker[i]);
        }
        return Math.max(d1[n - 2], d2[n - 1]);
    }
}