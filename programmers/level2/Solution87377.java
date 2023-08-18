package programmers.level2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [문제명] 교점에 별 만들기
 * [풀이시간] (1시간 3분)
 * [한줄평]
 * 1_v1. (실패-정확성 29 실패)
 * [해결] A, B, C는 -100,000 이상 100,000 이하인 정수이므로 두 수의 곱이 최대 10^10으로 int 범위를 초과하기 때문에 long타입으로 계산한다.
 * 1_v2. (실패-정확성 28 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/87377">문제</a>
 */
class Solution87377 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    public String[] solution(int[][] line) {
        List<int[]> list = new ArrayList<>();

        int MAX = Integer.MAX_VALUE;
        int MIN = Integer.MIN_VALUE;
        int minX = MAX;
        int minY = MAX;
        int maxX = MIN;
        int maxY = MIN;
        // ax+by+c=0
        for(int i = 0; i < line.length; i++) {
            long a = line[i][0];
            long b = line[i][1];
            long e = line[i][2];
            for(int j = i + 1; j < line.length; j++) {
                long c = line[j][0];
                long d = line[j][1];
                long f = line[j][2];
                //
                double x = (double) (b * f - e * d) / (a * d - b * c);
                double y = (double) (e * c - a * f) / (a * d - b * c);
                // System.out.println(x + "," + y);
                // 둘 다 정수이면
                if(x % 1 == 0 && y % 1 == 0) {
                    int px = (int) x;
                    int py = (int) y;
                    list.add(new int[] {px, py});
                    minX = Math.min(minX, px);
                    minY = Math.min(minY, py);
                    maxX = Math.max(maxX, px);
                    maxY = Math.max(maxY, py);
                }
            }
            //

        }
        // System.out.println(list.size());
        // System.out.println(String.format("%d %d, %d %d", minX, minY, maxX, maxY));

        int h = Math.abs(maxY - minY) + 1;
        int w = Math.abs(maxX - minX) + 1;
        String[] answer = new String[h];
        char[][] arr = new char[h][w];
        for(char[] row : arr) {
            Arrays.fill(row, '.');
        }
        for(int[] p : list) {
            // System.out.println(p[0] +"," + p[1]);
            int px = p[0] - minX;
            int py = maxY - p[1];
            // System.out.println(px +"," + py);
            arr[py][px] = '*';
        }
        int i = 0;
        for(char[] row : arr) {
            StringBuilder sb = new StringBuilder();
            for(char c : row) {
                sb.append(c);
            }
            answer[i++] = sb.toString();
        }
        return answer;
    }
}