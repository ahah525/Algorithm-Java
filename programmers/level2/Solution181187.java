package programmers.level2;


/**
 * [문제명] 두 원 사이의 정수 쌍
 * [풀이시간] (26분)
 * [한줄평]
 * 1_v1. (실패 - 7~10 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/">문제</a>
 */
class Solution181187 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    public long solution(int r1, int r2) {
        int rr1 = r1 * r1;
        int rr2 = r2 * r2;
        long answer = (r2 - r1 + 1) * 2 + 2;
        for(int x = 1; x < r2; x++) {
            int y2 = (int) Math.floor(Math.sqrt(rr2 - x * x));
            if(r1 <= x) {
                answer += 4 * y2 + 2;
            } else {
                int y1 = (int) Math.ceil(Math.sqrt(rr1 - x * x));
                answer += 4 * (y2 - y1 + 1);
            }
        }
        return answer;
    }
}