package programmers.level2;


/**
 * [문제명] 점찍기
 * [풀이시간] (25분 + )
 * [한줄평]
 * 1_v1. (실패-11,13,14 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/140107">문제</a>
 */
class Solution140107 {
    public static void main(String[] args) {
        // 6
        System.out.println(solution(2, 4));

        // 26
        System.out.println(solution(1, 5));
    }

    // 1_v1
    /**
     * @param k 정수
     * @param d 원점과의 거리
     * @return 점이 총 몇 개 찍히는지
     */
    public static long solution(int k, int d) {
        long answer = 0;
        long dd = (long) Math.pow(d, 2);

        for(int a = 0; a <= d; a += k) {
//            System.out.println("a = " + a);
//            System.out.println((Math.sqrt(dd - (long) Math.pow(a, 2)) / k) + 1);
            answer += (Math.sqrt(dd - (long) Math.pow(a, 2)) / k) + 1;
            // for(int b = 0; b <= d; b += k) {
            //     if(a + b > d) break;
            //     answer++;
            // }
        }
        return answer;
    }
}