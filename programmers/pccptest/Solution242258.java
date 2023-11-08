package programmers.pccptest;

/**
 * [문제명] [PCCP 기출문제] 1번
 * [풀이시간] 34분
 * [한줄평] 쉽게 풀 수 있는 구현 문제였다.
 * 1_v1. 구현(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/19344/lessons/242258">문제</a>
 */
public class Solution242258 {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0];  // 시전 시간
        int x = bandage[1];  // 초당 회복량
        int y = bandage[2];  // 추가 회복량
        int power = health; // 현재 체력
        int success = 0;    // 연속 성공
        int idx = 0;
        int time = 1;       // 시각
        // 1. 모든 공격이 들어올 때까지 반복
        while(idx < attacks.length) {
//            System.out.println(String.format("%d %d %d", time, power, success));
            int at = attacks[idx][0];   // 공격 시각
            int av = attacks[idx][1];   // 공격량
            if(time < at) {
                // 2. 회복
                power += x;
                if(power > health) power = health;
                success++;
                if(success == t) {
                    power += y;
                    success = 0;
                }
            } else if(time == at) {
                // 3. 공격
                power -= av;
                if(power <= 0) return -1;
                success = 0;
                idx++;
            }
            time++;
        }
        return power;
    }
}
