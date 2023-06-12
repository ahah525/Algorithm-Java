package programmers.level1;


/**
 * [문제명] 덧칠하기
 * [풀이시간] 10분 / 4분
 * [한줄평] 단순한 아이디어로 빨리 풀 수 있는 문제였다. / 쉬운 그리디 문제였고 더 풀어볼 필요까진 없을 것 같다.
 * 1_v1. 그리디(성공)
 * [접근법] 페인트를 칠할 때 색칠해야하는 번호를 시작점으로 해서 칠한다.
 * 2_v1. 그리디(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/161989">문제</a>
 */
class Solution161989 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1
    /**
     * @param n 벽의 길이
     * @param m 롤러의 길이
     * @param section 다시 페인트를 칠하기로 정한 구역들의 번호가 담긴 정수 배열
     * @return 롤러로 페인트칠해야 하는 최소 횟수
     */
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int prev = 0;   // 이전에 페인트 색칠된 구역의 마지막 번호
        for(int num : section) {
            if(prev < num) {
                // 이전에 페인트가 색칠된 구역에 현재 번호가 포함되지 않으면 새로 색칠하기
                prev = num + m - 1; // num 을 포함해 m 칸 색칠
                answer++;
            }
        }
        return answer;
    }
}