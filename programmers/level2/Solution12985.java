package programmers.level2;


/**
 * [문제명] 예상 대진표
 * [풀이시간] 10분 / 5분
 * [한줄평] 쉽게 풀 수 있는 문제였지만 너무 오래걸렸다. / 수식만 세우면 쉽게 풀 수 있어서 더 안풀어봐도 될 것 같다.
 * 1_v1. 수학(성공)
 * 2_v1. 수학(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12985">문제</a>
 */
class Solution12985 {
    public static void main(String[] args) {
        // 3
        System.out.println(solution(8, 4, 7));
    }

    // 1_v1, 2_v1
    /**
     * @param n 게임 참가자 수
     * @param a 참가자 번호
     * @param b 경쟁자 번호
     * @return 처음 라운드에서 A번을 가진 참가자는 경쟁자로 생각하는 B번 참가자와 몇 번째 라운드에서 만나는지
     */
    public static int solution(int n, int a, int b) {
        int answer = 0;
        while(true) {
            // 먼저 붙고
            answer++;
            // 다음판 번호 계산
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            // 다음판 번호가 같다면 현재판에서 붙었다는 의미니까 종료
            if(a == b) {
                break;
            }
        }
        return answer;
    }
}