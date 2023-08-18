package programmers.level2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [문제명] 교점에 별 만들기
 * [풀이시간] 1시간 20분(1시간 3분+7분+10분)
 * [한줄평] 교점을 구하는 식도 다 제공이 되기 때문에 그대로 구현만 하면 되는 문제였는데 생각보다 복잡하고 반례를 잡는데 오래걸렸다.
 * 1_v1. 구현(실패-정확성 29 실패)
 * [해결] A, B, C는 -100,000 이상 100,000 이하인 정수이므로 두 수의 곱이 최대 10^10으로 int 범위를 초과하기 때문에 long 타입으로 계산한다.
 * 1_v2. 구현(실패-정확성 28 실패)
 * [해결] 교점의 좌표가 int 범위를 벗어날 수도 있기 때문에 모두 long 타입으로 변경한다.
 * 1_v3. 구현(성공)
 * [풀이] n개의 직선 중에서 2개씩 선택했을 때 교점의 좌표를 구한다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/87377">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/21062">반례</a>
 */
class Solution87377 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    public String[] solution(int[][] line) {
        long MAX = Long.MAX_VALUE;  // 최댓값
        long MIN = Long.MIN_VALUE;  // 최솟값
        long minX = MAX;
        long minY = MAX;
        long maxX = MIN;
        long maxY = MIN;

        List<long[]> list = new ArrayList<>();  // (x,y)가 정수인 교점 좌표 리스트
        for(int i = 0; i < line.length; i++) {
            long a = line[i][0];
            long b = line[i][1];
            long e = line[i][2];
            for(int j = i + 1; j < line.length; j++) {
                long c = line[j][0];
                long d = line[j][1];
                long f = line[j][2];
                // 1. 두 직선의 교점 구하기
                double x = (double) (b * f - e * d) / (a * d - b * c);
                double y = (double) (e * c - a * f) / (a * d - b * c);
                // 2. 하나라도 정수가 아니면 패스
                if(x % 1 != 0 || y % 1 != 0) continue;
                // 3. 리스트에 교점 추가, x와 y의 최솟값, 최댓값 갱신
                long px = (long) x;
                long py = (long) y;
                list.add(new long[] {px, py});
                minX = Math.min(minX, px);
                minY = Math.min(minY, py);
                maxX = Math.max(maxX, px);
                maxY = Math.max(maxY, py);
            }
        }
        // 4. 별을 표시할 최소한의 크기 계산, (h * w) 2차원 배열 초기화
        int h = (int) (maxY - minY + 1);
        int w = (int) (maxX - minX + 1);
        char[][] arr = new char[h][w];
        for(char[] row : arr) {
            Arrays.fill(row, '.');
        }
        // 5. 교점에 별 찍기
        for(long[] p : list) {
            // 6. 왼쪽 최상단 점(minX, maxY)을 기준으로 (p[0], p[1])이 arr 에서 어디에 위치하는지 좌표 계산
            int px = (int) (p[0] - minX);
            int py = (int) (maxY - p[1]);
            arr[py][px] = '*';
        }
        // 7. char[][] -> String[] 변환
        String[] answer = new String[h];
        for(int i = 0; i < arr.length; i++) {
            StringBuilder sb = new StringBuilder();
            for(char c : arr[i]) sb.append(c);
            answer[i] = sb.toString();
        }
        return answer;
    }
}