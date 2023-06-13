package programmers.level3;


import java.util.*;

/**
 * [문제명] 메뉴 리뉴얼
 * [풀이시간] 2시간 이상 / 50분
 * [한줄평] 결국 풀이를 보고 이해했던 문제였다. 꼭 다시 한번 풀어봐야할 문제다!! / 구현하는게 조금 복잡했던 문제였다.
 * 1_v1. HashMap 2개, ArrayList 1개(성공)
 * - Map<String, Integer>: (코스명, 주문수)
 * - Map<Integer, PriorityQueue<Course>>: (코스에 속한 메뉴 수, 코스정보 -> 점수 내림차순, 이름 오름차순 정렬)
 * - List<String>: 코스명 길이별로 가장 주문수가 많은 결과를 오름차순 정렬한 리스트
 * 2_v1. DFS(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/15008/lessons/72411">문제</a>
 */
class Solution72411 {
    public static void main(String[] args) {
        // ["AC", "ACDE", "BCFG", "CDE"]
        String[] orders1 = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course1 = {2,3,4};
        System.out.println(solution(orders1, course1));

        // ["ACD", "AD", "ADE", "CD", "XYZ"]
        String[] orders2 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course2 = {2,3,5};
        System.out.println(solution(orders2, course2));

        // ["WX", "XY"]
        String[] orders3 = {"XYZ", "XWY", "WXA"};
        int[] course3 = {2,3,4};
        System.out.println(solution(orders3, course3));
    }

    // 1_v1
    public static class Course {
        String name;    // 코스 이름
        int cnt;      // 주문수

        public Course(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }
    }

    static Map<String, Integer> map;
    static StringBuilder sb;

    public static List<String> solution(String[] orders, int[] course) {
        maps = new HashMap<>();  // (코스명, 주문수)
        Map<Integer, PriorityQueue<Course>> courseMap = new HashMap<>();   // (코스에 속한 메뉴 수, 코스정보)
        List<String> answer = new ArrayList<>();
        // 1. 코스메뉴 구성별 주문수 구하기(해당 메뉴들로 만들 수 있는 코스 조합을 미리 다 구함)
        for(String order : orders) {
            char[] menus = order.toCharArray();
            Arrays.sort(menus);     // 오름차순 정렬
            // r 개 뽑기
            int n = menus.length;   // 메뉴 개수
            sb = new StringBuilder();
            for(int r : course) {
                dfs(0, 0, menus, n, r);
            }
        }
        // 2. 주문수가 최소 2회이상인 코스메뉴를 대상으로 우선순위 큐에 코스명 길이별로 코스 넣기
        for(String name : map.keySet()) {
            int len = name.length();    // 코스명 길이
            int order = map.get(name);
            // 2회 미만 주문건 패스
            if(order < 2)
                continue;
            // 해당 코스명 길이에 대한 우선순위큐가 없으면 생성
            if(!courseMap.containsKey(len)) {
                // 주문수 내림차순, 코스명 오름차순 정렬
                PriorityQueue<Course> q = new PriorityQueue<>((o1, o2) -> {
                    if(o1.cnt == o2.cnt) {
                        return o1.name.compareTo(o2.name);
                    }
                    return o2.cnt - o1.cnt;
                });
                courseMap.put(len, q);
            }
            courseMap.get(len).add(new Course(name, order));
        }
        // 3. 우선순위 큐에서 코스명 길이별로 주문수가 가장 많은 코스 뽑아서 리스트에 넣기
        for(int len : courseMap.keySet()) {
            PriorityQueue<Course> pq = courseMap.get(len);
            // 3-1. 1개 뽑아서 리스트에 넣기
            Course poll = pq.poll();
            answer.add(poll.name);
            // 3-2. 주문수가 같은 것 추가로 더 뽑아서 리스트에 넣기
            int order = poll.cnt;
            while(!pq.isEmpty()) {
                Course c = pq.poll();
                if(c.cnt != order)
                    break;
                answer.add(c.name);
            }
        }
        // 4. 결과 리스트 오름차순 정렬
        Collections.sort(answer);
        return answer;
    }

    // n 개 메뉴에서 r개 뽑기
    public static void dfs(int depth, int start, char[] menus, int n, int r) {
        if(depth == r) {
            String comb = sb.toString();
//            System.out.println(comb);
            map.put(comb, map.getOrDefault(comb, 0) + 1);
            return;
        }
        for(int i = start; i < n; i++) {
            sb.append(menus[i]);
            dfs(depth + 1, i + 1, menus, n, r);
            sb.deleteCharAt(depth);
        }
    }

    // 2_v1
    static List<String> list;
    static Map<Integer, Queue<Course>> maps;
    public static String[] solution2(String[] orders, int[] course) {
        list = new ArrayList<>();
        maps = new HashMap<>();
        for(int r : course) {
            maps.put(r, new PriorityQueue<>((o1, o2) -> o2.cnt - o1.cnt));
            // System.out.println(r);
            dfs2(0, -1, r, new boolean[26], orders);
            Queue<Course> pq = maps.get(r);
            int max = 0;
            while(!pq.isEmpty()) {
                Course c = pq.poll();
                if(max > c.cnt) continue;
                list.add(c.name);
                max = c.cnt;
            }
        }
        //
        Collections.sort(list);
        String[] answer = new String[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    // r개 뽑아서 코스 만들기
    public static void dfs2(int depth, int prev, int r, boolean[] visited, String[] orders) {
        if(depth == r) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < visited.length; i++) {
                if(visited[i]) sb.append((char) (i + 'A'));
            }
            // System.out.println(sb.toString());
            int cnt = getCnt(sb.toString(), orders);
            // 해당 코스의 주문수가 2이상일 때만 넣기
            if(cnt >= 2) {
                maps.get(r).add(new Course(sb.toString(), cnt));
            }
            return;
        }
        //
        for(int i = prev + 1; i < visited.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs2(depth + 1, i, r, visited, orders);
                visited[i] = false;
            }
        }
    }

    // 해당 코스가 몇개의 주문에 포함되었는지
    public static int getCnt(String course, String[] orders) {
        int cnt = 0;
        for(String order : orders) {
            if(course.length() > order.length()) continue;
            boolean flag = true;
            for(char c : course.toCharArray()) {
                if(!order.contains(c + "")) {
                    flag = false;
                    break;
                }
            }
            if(flag) cnt++;
        }
        return cnt;
    }
}