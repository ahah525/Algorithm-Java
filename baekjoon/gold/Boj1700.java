package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 멀티탭 스케줄링
 * [풀이시간] 21분
 * [한줄평] 아이디어는 쉽게 떠올렸는데 가장 나중에 사용되는 플러그가 무엇인지를 찾는 것을 어떻게 구현할지 고민하느라 시간이 좀 걸렸다.
 * 1_v1. 그리디(성공)
 * [풀이] 플러그를 빼는 횟수를 최소화하는 방법 = 멀티탭에 꽂힌 플러그 중 가장 나중에 사용되는 플러그 빼기
 * @See <a href="https://www.acmicpc.net/problem/1700">문제</a>
 */
class Boj1700 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 멀티탭 구멍 개수
        int k = Integer.parseInt(st.nextToken());   // 전기 용품 총 사용횟수
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[k];
        // 1. (용품 번호, 각 용품이 사용된 순서) 초기화
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for(int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(!map.containsKey(arr[i])) map.put(arr[i], new LinkedList<>());
            map.get(arr[i]).add(i);
        }
        int cnt = 0;    // 플러그를 빼는 최소 횟수
        Set<Integer> set = new HashSet<>();
        for(int num : arr) {
            // 2. 용품의 플러그가 꽂혀있지 않고 더 이상 꽂을 자리가 없을 때
            if(!set.contains((num)) && set.size() >= n) {
                int outNum = 0;     // 빼야할 플러그 번호
                int maxOrder = 0;   // 가장 나중에 사용될 예정인 용품의 순서
                // 2. 멀티탭에 꽂힌 플러그 중 가장 나중에 사용하는 플러그 빼기
                for(int use : set) {
                    // 3. 해당 용품이 더이상 사용되지 않는다면, 해당 플러그를 뺴야할 플러그로 설정
                    if(!map.containsKey(use)) {
                        outNum = use;
                        break;
                    }
                    // 4. 해당 용품이 미래에 또 사용된다면,
                    int order = map.get(use).peek();    // 해당 용품이 가장 빨리 사용되는 시점
                    if(maxOrder < order) {
                        outNum = use;
                        maxOrder = order;
                    }
                }
                // 5. 가장 나중에 사용되는 용품의 플러그 빼기
                set.remove(outNum);
                cnt++;
            }
            // 6. 현재 용품 플러그 꽂기
            map.get(num).poll();
            if(map.get(num).size() == 0) map.remove(num);
            set.add(num);
        }
        System.out.println(cnt);
    }
}