package programmers.level3;


import java.util.*;

/**
 * [문제명] [카카오 인턴] 보석 쇼핑
 * [풀이시간] 1시간 / 1시간 8분(18분 + 50분) / 11분
 * [한줄평] 구현을 하면서도 시간초과가 날 것 같다고 생각했는데, 결국 시간초과가 났다. 결국 힌트를 얻어 투포인터로 해결했는데 투포인터는 무조건 복습이 필요하다!!
 * / 결국 스스로 풀기는 했지만 투포인터 문제라는 것을 알고 풀어도 조건을 처리하기가 어려웠다.
 * / 일반적인 투포인터 문제를 풀 수 있다면 쉽게 풀 수 있던 문제였다.
 * 1_v1. 완전탐색(실패 - 효율성 테스트 1~5, 7~15 실패)
 * [풀이] 시작 인덱스, 끝 인덱스를 바꿔가며 완전 탐색 수행
 * 1_v2. HashMap, 투포인터(성공)
 * [해결] 투포인터에서 잊지밀아야할 점은 end = n 이 되기전까지 반복한다는것(종료조건)이고, start++ 와 end++ 시점이 중요하다.
 * 2_v1. HashMap, 투포인터(실패 - 정확성 2,3,7~9,12,13,15, 효율성 1~3,5~11,13~14)
 * 2_v2. HashMap, 투포인터(성공)
 * 3_v1. HashMap, 투포인터(성공)
 * [풀이] 2_v2 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/15008/lessons/67258">문제</a>
 * @See <a href="https://velog.io/@fantastik/48">풀이 참고</a>
 */
class Solution67258 {
    public static void main(String[] args) {
        // [3, 7]
        System.out.println(Arrays.toString(solution3(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));

        // [1, 3]
        System.out.println(Arrays.toString(solution3(new String[]{"AA", "AB", "AC", "AA", "AC"})));

        // [1, 1]
        System.out.println(Arrays.toString(solution3(new String[]{"XYZ", "XYZ", "XYZ"})));

        // [1, 5]
        System.out.println(Arrays.toString(solution3(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"})));
    }

    // 1_v2
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

    // 2_v2, 3_v1
    public static int[] solution3(String[] gems) {
        // 1. 보석 종류수 구하기
        Set<String> set = new HashSet<>();
        for(String gem : gems)
            set.add(gem);
        int n = set.size();
        // (보석, 개수)
        Map<String, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;    // 가장 짧은 구간의 길이
        int s = 0;  // 시작번호
        int e = 0;  // 끝번호
        int[] answer = new int[2];  // (시작 진열대 번호, 끝 진열대 번호)
        //
        while(true) {
            if(map.size() < n) {
                // 1. 현재 구간의 보석 종류가 n개 미만이면
                // 1.1. 끝 번호가 진열대 번호를 넘어갔으면 break
                if(e == gems.length) break;
                // 1.2. 끝 번호가 아직 진열대 번호내에 있으면 e 를 오른쪽으로 1칸 이동
                map.put(gems[e], map.getOrDefault(gems[e], 0) + 1);
                e++;
            } else {
                // 2. 현재 구간의 보석 종류가 n개 이상이면
                // 2.1. 가장 짧은 구간의 길이 > 현재 구간의 길이 이면, 가장 짧은 구간 갱신
                if(min > e - s) {
                    answer[0] = s + 1;
                    answer[1] = e;
                    min = e - s;
                }
                // 3. s 를 오른쪽으로 1칸 이동
                if(map.get(gems[s]) == 1) {
                    // 3.1. s 가 구간에 1개 있으면 삭제
                    map.remove(gems[s]);
                } else {
                    // 3.2. s 가 구간에 2개 이상이면 개수 하나 줄이기
                    map.put(gems[s], map.get(gems[s]) - 1);
                }
                s++;
            }
//            System.out.println("(" + s + "," + e + ")");
//            System.out.println(map);
        }
        return answer;
    }
}