package programmers;

class Solution87390 {
    public static void main(String[] args) {
        int n = 1000000;
        long left = 100000;
        long right = 199999;
        int len = (int) (right - left + 1);

        int[] answer = solution(n, left, right);
        // [3,2,2,3]
        for(int i = 0; i < len; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    public static int[] solution(int n, long left, long right) {
        int len = (int) (right - left + 1);
        int[] answer = new int[len];
        int cnt = 0;
        int lx = (int) left / n;
        int ly = (int) left % n;
        int rx = (int) right / n;
        int ry = (int) right % n;

        if(lx == rx) {
            for(int j = ly; j <= ry; j++) {
                answer[cnt++] = Math.max(lx + 1, j + 1);
            }
        } else {
            // 맨 처음
            for(int j = ly; j < n; j++) {
                answer[cnt++] = Math.max(lx + 1, j + 1);
            }
            // 중간
            for(int i = lx + 1; i <= rx - 1; i++) {
                for(int j = 0; j < n; j++) {
                    answer[cnt++] = Math.max(i + 1, j + 1);
                }
            }
            // 맨 마지막
            for(int j = 0; j <= ry; j++) {
                answer[cnt++] = Math.max(rx + 1, j + 1);
            }

        }

        return answer;
    }
}