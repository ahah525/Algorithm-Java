package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] 테이블 해시 함수
 * [풀이시간] 21분 / 10분
 * [한줄평] 쉬운 구현 문제에 속했는데, XOR 연산에 대한 이해가 부족해서 틀렸던 문제다. / XOR 연산에 대한 이해만 있으면 쉽게 풀 수 있는 문제였다.
 * 1_v1. 구현, 정렬(실패 - 2~3,5~10 실패)
 * 1_v2. 구현, 정렬(성공)
 * [해결법] 0 XOR A = A
 * 2_v1. 구현, 정렬(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/147354">문제</a>
 */
class Solution147354 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2, 2_v1
    /**
     * @param data 테이블의 데이터
     * @param col 정렬 기준 컬럼
     * @param rowBegin 시작행
     * @param rowEnd 끝행
     * @return 테이블의 해시 값
     */
    public int solution(int[][] data, int col, int rowBegin, int rowEnd) {
        // 1. col 번째 컬럼 기준 오름차순 정렬
        // 2. 첫 번째 컬럼 기준 내림차순 정렬
        int c = col - 1;
        Arrays.sort(data, (o1, o2) -> {
            if(o1[c] == o2[c]) return o2[0] - o1[0];
            return o1[c] - o2[c];
        });
        //
        int answer = 0; // 0과 A를 XOR 연산한 결과는 A 이므로 0으로 초기화하는 게 중요함!
        for(int i = rowBegin - 1; i <= rowEnd - 1; i++) {
            int si = 0;
            for(int j = 0; j < data[0].length; j++) {
                si += data[i][j] % (i + 1);
            }
            answer ^= si;
        }
        return answer;
    }
}