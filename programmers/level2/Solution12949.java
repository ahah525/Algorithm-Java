package programmers.level2;


/**
 * [문제명] 행렬의 곱셈
 * [풀이시간] 9분
 * [한줄평] 행렬 곱셈 방식에 대해 검색해보고 풀었던 문제였다.
 * 1_v1. 수학(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/">문제</a>
 * @See <a href="https://mathbang.net/562">행렬 곱셈 참고</a>
 */
class Solution12949 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param arr1 2차원 행렬
     * @param arr2 2차원 행렬
     * @return arr1에 arr2를 곱한 결과
     */
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int m = arr1.length;    // arr1 행 개수
        int l = arr2.length;    // arr1 열 개수 = arr2 행 개수
        int n = arr2[0].length; // arr2 열 개수
        int[][] arr = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < l; k++) {
                    arr[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return arr;
    }
}