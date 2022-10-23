package programmers;

class Solution43105 {
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int answer = solution(triangle);
        System.out.println(answer);
    }

    public static int solution(int[][] arr) {
        int answer = 0;
        int n = arr.length;
        int max = 0;
        int[][] d = new int[n][n];
        d[0][0] = arr[0][0];
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                if(j == 0) {
                    max = d[i - 1][j];
                } else if(j == i) {
                    max = d[i - 1][j - 1];
                } else {
                    max = Math.max(d[i - 1][j - 1], d[i - 1][j]);
                }
                d[i][j] = max + arr[i][j];
            }
        }

        for(int i = 0; i < n; i++) {
            answer = Math.max(answer, d[n - 1][i]);
        }

        return answer;
    }
}