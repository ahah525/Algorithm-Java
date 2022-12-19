package programmers.level3;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * [문제명] [카카오 인턴] 보석 쇼핑
 * [풀이시간] 1시간
 * [한줄평] 구현을 하면서도 시간초과가 날 것 같다고 생각했는데, 결국 시간초과가 났다.
 * v1. 2중 for 문 - 완전탐색(실패 - 효율성 테스트 1~5, 7~15 실패)
 * - 시작 인덱스, 끝 인덱스를 바꿔가며 완전 탐색 수행
 * @See <a href="https://school.programmers.co.kr/learn/courses/15008/lessons/67258">문제</a>
 */
class Solution67258 {
    public static void main(String[] args) {
        // [3, 7]
        String[] gems1 = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        System.out.println(solution1(gems1));

        // [1, 3]
        String[] gems2 = {"AA", "AB", "AC", "AA", "AC"};
        int[] course2 = {2,3,5};
        System.out.println(solution1(gems2));

        // [1, 1]
        String[] gems3 = {"XYZ", "XYZ", "XYZ"};
        System.out.println(solution1(gems3));

        // [1, 5]
        String[] gems4 = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        System.out.println(solution1(gems4));
    }

    /**
     *
     * @param gems
     * @return
     */
    public static int[] solution1(String[] gems) {
        int[] answer = {1, 1};
        int n = gems.length;
        int minLen = n; // 최소 구간 길이
        int minIdx = -1; // 최소 구간 시작 인덱스
        // 1. 보석 종류 구하기
        Set set = new HashSet();
        for(String s : gems) {
            set.add(s);
        }
        int kind = set.size(); // 보석 종류수
        if(kind == 1) return answer;
        // 2. 시작점
        for(int i = 0; i < n; i++) {
            String start = gems[i]; // 시작값
            Map<String, Integer> map = new HashMap<>(); // (보석명, 개수)
            for(int j = i; j < n; j++) {
                String name = gems[j];  // 보석명
                map.put(name, map.getOrDefault(name, 0) + 1);
                // 모든 보석이 속해있고 현재 최소 구간보다 더 짧으면 업데이트
                if(map.size() == kind) {
                    if(minLen > j - i) {
                        minLen = j - i;
                        minIdx = i;
                    }
                    break;
                }
            }
            // 맨 앞 인덱스 개수 빼기
            int cnt = map.get(start);
            if(cnt == 1) {
                map.remove(start);
            } else {
                map.put(start, cnt - 1);
            }
        }
        answer[0] = minIdx + 1;
        answer[1] = answer[0] + minLen;
        return answer;
    }
}