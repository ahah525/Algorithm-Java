package programmers.level2;


/**
 * [문제명] 점찍기
 * [풀이시간] 30분(25분 + 5분)
 * [한줄평] 2중 for문으로 풀면 시간초과가 날 것 같아서
 * 1_v1. 수학, for 문(실패-11,13,14 실패)
 * 1_v2. 수학, for 문(성공)
 * [반례] int 범위를 벗어날 경우를 고려해 long 으로 변환
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/140107">문제</a>
 */
class Solution140107 {
    public static void main(String[] args) {
        // 6
        System.out.println(solution(2, 4));

        // 26
        System.out.println(solution(1, 5));
    }

    // 1_v2
    /**
     * @param k 정수(1 ≤ k ≤ 1,000,000)
     * @param d 원점과의 거리(1 ≤ d ≤ 1,000,000)
     * @return 점이 총 몇 개 찍히는지
     */
    public static long solution(int k, int d) {
        long answer = 0;
        long dd = (long) d * d;

        // a 행에 있는 좌표 중 (0,0) 까지의 거리가 d 이하인 좌표 개수 더하기
        for(int a = 0; a <= d; a += k) {
            // (Math.sqrt(dd - (long) a * a) / k) 의 결과값을 int -> long 으로 변환 필요!
            answer += (long) (Math.sqrt(dd - (long) a * a) / k) + 1;
        }
        return answer;
    }
}