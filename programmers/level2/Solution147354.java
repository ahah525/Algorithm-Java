package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] 테이블 해시 함수
 * [풀이시간] (20분)
 * [한줄평]
 * 1_v1. (실패 - 2~3,5~10 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/147354">문제</a>
 */
class Solution147354 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    public int solution(int[][] data, int col, int rowBegin, int rowEnd) {
        int c = col - 1;
        // col번째 컬럼 기준 오름차순 정렬
        // 첫번째 컬럼 기준 내림차순 정렬
        Arrays.sort(data, (o1, o2) -> {
            if(o1[c] == o2[c]) return o2[0] - o1[0];
            return o1[c] - o2[c];
        });
        //
        // for(int[] arr : data) {
        //     System.out.println(Arrays.toString(arr));
        // }
        int answer = 0;
        //
        for(int i = rowBegin - 1; i <= rowEnd - 1; i++) {
            int si = 0;
            for(int j = 0; j < data[0].length; j++) {
                si += data[i][j] % (i + 1);
            }
            if(i == rowBegin) answer = si;
            else answer ^= si;
        }
        return answer;
    }
}