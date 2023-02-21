package programmers.level1;


/**
 * [문제명] 기사단원의 무기
 * [풀이시간] 8분
 * [한줄평] 그냥 있는 그대로 구현하기만 하면 되는 문제였다.
 * 1_v1. (성공)
 * - 각 숫자의 약수의 개수를 구하는 방식
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/136798">문제</a>
 */
class Solution136798 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param number 기사단원의 수를 나타내는 정수
     * @param limit 이웃나라와 협약으로 정해진 공격력의 제한수치
     * @param power 제한수치를 초과한 기사가 사용할 무기의 공격력
     * @return 무기점의 주인이 무기를 모두 만들기 위해 필요한 철의 무게
     */
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for(int i = 1; i <= number; i++) {
            int n = getCnt(i);
            if(n <= limit)
                answer += n;
            else
                answer += power;
        }
        return answer;
    }

    // 약수의 개수
    public int getCnt(int n) {
        int cnt = 1;
        double sqrt = Math.sqrt(n);
        for(int i = 2; i <= sqrt; i++) {
            if(n % i == 0)
                cnt++;
        }
        return sqrt % ((int) sqrt) == 0 ? 2 * cnt - 1 : 2 * cnt;
    }
}