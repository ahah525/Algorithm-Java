package programmers.level2;


/**
 * [문제명] 두 원 사이의 정수 쌍
 * [풀이시간] 33분(26분 + 7분)
 * [한줄평] 수식만 세우면 쉽게 풀 수 있는 문제였는데 은근 오래걸렸다. 다행히 자료형 문제라는 것을 빨리 알아차려서 반례를 해결할 수 있었다.
 * 1_v1. 수학(실패 - 7~10 실패)
 * [풀이] r1, r2의 제곱값을 담은 변수를 int 로 선언함
 * 1_v2. 수학(성공)
 * [반례] r1, r2 제곱값은 최대 10^12 이므로 변수를 long 으로 선언함
 * 1_v3. 수학(성공) -> 추천
 * [풀이] 두 원 사이의 점 개수 = 1사분면 점 개수 * 4
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/181187">문제</a>
 * @See <a href="https://zangsu.tistory.com/12">다른 풀이</a>
 */
class Solution181187 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2
    /**
     * @param r1 작은 반지름
     * @param r2 큰 반지름
     * @return 두 원 사이의 공간에 x좌표와 y좌표가 모두 정수인 점의 개수
     */
    public long solution(int r1, int r2) {
        long rr1 = (long) r1 * r1;
        long rr2 = (long) r2 * r2;
        long answer = (r2 - r1 + 1) * 2 + 2;
        for(int x = 1; x < r2; x++) {
            int y2 = (int) Math.floor(Math.sqrt(rr2 - (long) x * x));
            if(r1 <= x) {
                answer += (4 * y2 + 2);
            } else {
                int y1 = (int) Math.ceil(Math.sqrt(rr1 - (long) x * x));
                answer += 4 * (y2 - y1 + 1);
            }
        }
        return answer;
    }

    // 1_v3
    public long solution2(int r1, int r2) {
        long rr1 = (long) r1 * r1;
        long rr2 = (long) r2 * r2;
        long answer = 0;
        for(int x = 1; x <= r2; x++) {
            int y1 = (int) Math.ceil(Math.sqrt(rr1 - (long) x * x));
            int y2 = (int) Math.floor(Math.sqrt(rr2 - (long) x * x));
            answer += (y2 - y1 + 1);
        }
        return answer * 4;
    }
}