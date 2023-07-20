package softeer.level2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] GBC
 * [풀이시간] 54분(40분+14분)
 * [한줄평] 생각보다 푸는데 오래걸려서 나중에 다시 풀어보면 좋을 문제다.
 * 1_v1. 구현(실패)
 * [해결] 운행 구간 정보가 [종료지점, 속도]로 입력된다고 착각하고 식을 잘못 세웠다. [길이, 속도]로 입력되기 때문에 현재 종료지점 = (이전 종료지점 + 길이)다.
 * 2_v1. 구현
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=584">문제</a>
 */
class Solution584 {
    // 1_v2
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // 1. 구간 종료 지점, 속도 저장
        int[] end = new int[n];
        int[] speed = new int[n];
        int sum = 0;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            sum += Integer.parseInt(st.nextToken());
            end[i] = sum;
            speed[i] = Integer.parseInt(st.nextToken());
        }
        int idx = 0; // 현재 속한 엘베 구간
        int max = 0;
        int de = 0;
        int ds = 0;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            de += Integer.parseInt(st.nextToken());     // 운행 종료 지점
            ds = Integer.parseInt(st.nextToken());  // 운행 속도
            // System.out.println(String.format("[운행] 종료:%d 속도:%d", de, ds));
            while(true) {
                // System.out.println(String.format("[엘베] 순서:%d 종료:%d 속도:%d", idx, end[idx], speed[idx]));
                if(de <= end[idx]) {
                    // 현재 구간이 idx 구간에 모두 포함되는 경우
                    max = Math.max(max, ds - speed[idx]);
                    // 엘베 구간 종료 지점 & 운행 구간 종료 지점이 동일하면, 다음 구간의 엘베 선택
                    if(de == end[idx]) idx++;
                    break;
                }
                // 현재 구간이 idx 구간에 일부분만 포함되는 경우
                max = Math.max(max, ds - speed[idx]);
                // 다음 구간의 엘베 선택
                idx++;
            }
        }
        System.out.println(max);
    }
}