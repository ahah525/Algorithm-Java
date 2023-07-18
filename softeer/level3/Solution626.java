package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] [21년 재직자 대회 예선] 회의실 예약
 * [풀이시간] 44분
 * [한줄평] 문제를 구현하는 것은 어렵지 않았으나 출력문을 정리하는데 시간이 많이 걸렸던 문제다. 처음에 PriorityQueue 로 정렬이 안된 이유가 궁금하다..
 * 1_v1. HashMap, 구현(성공)
 * [풀이] Map에 (회의실 이름, 회의실 예약 리스트) 저장
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=626">문제</a>
 */
class Solution626 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // 1. map 초기화
        Map<String, List<int[]>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(br.readLine(), new ArrayList<>());
        }
        // 2. m개의 예약 정보 리스트에 저장
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            map.get(type).add(new int[] {s, e});
        }
        // 3. 회의실 이름 오름차순 정렬
        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        int cnt = 0;
        for(String key : keys) {
            // 4. 시작 시각 기준 오름차순 정렬
            List<int[]> list = map.get(key);
            Collections.sort(list, (o1, o2) -> o1[0] - o2[0]);
            System.out.println(String.format("Room %s:", key));
            // 5. 가능한 시간대 출력
            System.out.print(check(map.get(key)));
            cnt++;
            if(cnt == keys.size()) break;
            System.out.println("-----");
        }
    }

    public static String check(List<int[]> list) {
        StringBuilder sb = new StringBuilder();
        int available = 0;
        int prev = 9;
        for(int[] t : list) {
            if(prev < t[0]) {
                sb.append(String.format("%02d-%02d\n", prev, t[0]));
                available++;
            }
            prev = t[1];
        }
        if(prev < 18) {
            sb.append(String.format("%02d-%02d\n", prev, 18));
            available++;
        }
        if(available == 0) sb.insert(0, "Not available\n");
        else sb.insert(0, String.format("%d available:\n", available));
        return sb.toString();
    }
}