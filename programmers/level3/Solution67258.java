package programmers.level3;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * [문제명] [카카오 인턴] 보석 쇼핑
 * [풀이시간] 1시간 / 18분
 * [한줄평] 구현을 하면서도 시간초과가 날 것 같다고 생각했는데, 결국 시간초과가 났다. 결국 힌트를 얻어 투포인터로 해결했는데 투포인터는 무조건 복습이 필요하다!!
 * 투포인터에서 잊지밀아야할 점은 end = n 이 되기전까지 반복한다는것(종료조건)이고, start++ 와 end++ 시점이 중요하다.
 * 1_v1. Set, Map, 2중 for 문 - 완전탐색(실패 - 효율성 테스트 1~5, 7~15 실패)
 * - 시작 인덱스, 끝 인덱스를 바꿔가며 완전 탐색 수행
 * 1_v2. Set, Map, 투포인터(성공)
 * 2_v1. HashSet, HashMap, 투포인터(실패 - 정확성 2,3,7~9,12,13,15, 효율성 1~3,5~11,13~14)
 * @See <a href="https://school.programmers.co.kr/learn/courses/15008/lessons/67258">문제</a>
 * @See <a href="https://velog.io/@fantastik/48">풀이 참고</a>
 */
class Solution67258 {
    public static void main(String[] args) {
        // [3, 7]
        String[] gems1 = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        System.out.println(solution2(gems1));

        // [1, 3]
        String[] gems2 = {"AA", "AB", "AC", "AA", "AC"};
        int[] course2 = {2,3,5};
        System.out.println(solution2(gems2));

        // [1, 1]
        String[] gems3 = {"XYZ", "XYZ", "XYZ"};
        System.out.println(solution2(gems3));

        // [1, 5]
        String[] gems4 = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        System.out.println(solution2(gems4));
    }

    public static int[] solution1(String[] gems) {
        int[] answer = {1, 1};
        int n = gems.length;   // 보석 개수
        int minLen = n;     // 최소 구간 길이
        int minIdx = -1;    // 최소 구간 시작 인덱스
        // 1. 보석 종류 구하기
        Set set = new HashSet();
        for(String s : gems) {
            set.add(s);
        }
        int kind = set.size(); // 보석 종류수
        if(kind == 1) return answer;
        // 2. 시작점
        for(int i = 0; i < n; i++) {
            // 현재 범위 = start ~ (end - 1)
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

    /**
     * @param gems 진열대 번호 순서대로 보석들의 이름이 저장된 배열
     * @return 모든 보석을 하나 이상 포함하는 가장 짧은 구간의 시작 진열대 번호와 끝 진열대 번호를 담은 배열
     */
    public static int[] solution2(String[] gems) {
        int[] answer = {1, 1};
        int n = gems.length;    // 보석 개수
        int minLen = Integer.MAX_VALUE;     // 최소 구간 길이
        int minS = 0;    // 최소 구간 시작 인덱스
        int minE = 0;    // 최소 구간 끝 인덱스
        // 1. 보석 종류 구하기
        Set set = new HashSet();
        for(String s : gems) {
            set.add(s);
        }
        int kind = set.size(); // 보석 종류수
        // 2. 투포인터로 시작, 끝 인덱스 옮겨가며 끝 인덱스가 n 이 될 때까지 반복
        int start = 0;  // 시작 인덱스
        int end = 0;    // 끝 인덱스
        Map<String, Integer> map = new HashMap<>(); // (보석명, 개수)
        while(true) {
            // 현재 범위 = start ~ (end - 1) -> end 를 포함하지 않음!!
            if(map.size() == kind) {
                // 1. 보석 종류를 모두 포함하고 있는 경우 -> start 값 빼기
                String startName = gems[start++];
                map.put(startName, map.get(startName) - 1);
                if(map.get(startName) == 0) {
                    map.remove(startName);
                }
                // 1-2. 최솟값 업데이트
                if(minLen > end - start) {
                    minLen = end - start;
                    minS = start - 1;   // 위에서 start++ 을 해준 것을 고려해 (start - 1) 값이 최소 구간의 진짜 start 값이다.
                    minE = end - 1;     // 해당 범위에서 실제로 end 값은 포함되지 않은 상태이므로 (end - 1) 값이 최소 구간의 end 값이다.
                }
            } else if(end == n) {
                // 2. end 가 n 이 되면 종료 -> 위치 중요!!
                break;
            }else {
                // 3. 보석 종류가 아직 부족한 경우 -> end 값 넣기
                String endName = gems[end++];
                map.put(endName, map.getOrDefault(endName, 0) + 1);
            }
        }
        // 3. 실제 값은 +1 연산한 값으로 반환
        answer[0] = minS + 1;
        answer[1] = minE + 1;
        return answer;
    }

    public int[] solution(String[] gems) {
        // (보석, 개수)
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        int s = 0;
        int e = 0;
        for(String gem : gems) {
            set.add(gem);
        }
        int n = set.size(); // 보석 개수
        // n 개를 모두 모을때까지 e 를 오른쪽으로
        while(true) {
            map.put(gems[e], map.getOrDefault(gems[e], 0) + 1);
            if(map.size() == n) break;
            e++;
        }
        // 최소 길이로 줄이기위해서 s 를 오른쪽으로
        while(true) {
            if(map.get(gems[s]) == 1) break;
            map.put(gems[s], map.get(gems[s]) - 1);
            s++;
        }

        return new int[]{s + 1, e + 1};
    }
}