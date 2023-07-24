package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 조립라인
 * [풀이시간] (27분)
 * [한줄평]
 * 1_v1. 구현(실패 - 오답)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=403">문제</a>
 */
class Solution403 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] play = new int[2][n]; // Ai 작업장의 작업시간
        int[][] move = new int[2][n - 1]; // Ai -> Bi+1 작업장으로 이동시간
        // int[] moveB = new int[n - 1]; // Bi -> Ai+1 작업장으로 이동시간
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            play[0][i] = Integer.parseInt(st.nextToken());
            play[1][i]  = Integer.parseInt(st.nextToken());
            if(i == n - 1) break;
            move[0][i] = Integer.parseInt(st.nextToken());
            move[1][i] = Integer.parseInt(st.nextToken());
        }
        // System.out.println(Arrays.toString(moveA));
        // System.out.println(Arrays.toString(moveB));
        int sum = 0;
        int type = 0; // A1 시작
        int idx = 0;    //
        while(idx < n - 1) {
            // 현재 작업 수행
            sum += play[type][idx];
            // 다음 작업을 어디서 할지 정하기
            // 다음 작업을 같은 작업장에서 하는 경우
            int a = play[type][idx + 1];
            // 다른 작업장으로 이동한 후 다음 작업을 수행하는 경우
            int b = move[type][idx] + play[getDifferentType(type)][idx + 1];
            if(a > b) {
                sum += move[type][idx];
                type = getDifferentType(type);
            }
            idx++;
        }
        sum += play[type][n - 1];
        System.out.println(sum);
    }

    public static int getDifferentType(int type) {
        if(type == 0) return 1;
        return 0;
    }
}