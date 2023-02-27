package programmers.level1;


/**
 * [문제명] 햄버거 만들기
 * [풀이시간] (10분)
 * [한줄평]
 * 1_v1. String.replace()(실패 - 3~12, 18 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/133502">문제</a>
 */
class Solution133502 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param ingredient 상수에게 전해지는 재료의 정보를 나타내는 정수 배열
     * @return 상수가 포장하는 햄버거의 개수
     */
    public int solution(int[] ingredient) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        for(int n : ingredient) {
            sb.append(n);
        }
        String s = sb.toString();
        while(s.contains("1231")) {
            s = s.replace("1231", "");
            answer++;
        }
        return answer;
    }
}