package programmers.level1;


/**
 * [문제명] 햄버거 만들기
 * [풀이시간] (10분 + 10분)
 * [한줄평]
 * 1_v1. String.replace()(실패 - 3~12, 18 실패)
 * 1_v2. StringBuilder.indexOf() + delete() (실패 - 4, 5, 12 시간초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/133502">문제</a>
 * @See <a href="https://leejams.github.io/%ED%96%84%EB%B2%84%EA%B1%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0/">반례</a>
 */
class Solution133502 {
    public static void main(String[] args) {
        // 3
        int[] ingredient3 = {1, 2, 1, 2, 3, 1, 3, 1, 2, 3, 1, 2, 3, 1};
        System.out.println(solution(ingredient3));
    }

    /**
     * @param ingredient 상수에게 전해지는 재료의 정보를 나타내는 정수 배열
     * @return 상수가 포장하는 햄버거의 개수
     */
    public static int solution(int[] ingredient) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        for(int n : ingredient) {
            sb.append(n);
        }
        while(sb.indexOf("1231") != -1) {
            int find = sb.indexOf("1231");
            sb.delete(find, find + 4);
            answer++;
        }
        return answer;
    }
}