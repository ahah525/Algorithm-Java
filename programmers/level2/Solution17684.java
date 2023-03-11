package programmers.level2;


import java.util.*;

/**
 * [문제명] [3차] 압축
 * [풀이시간] / 40분
 * [한줄평] map 을 사용해서 그대로 구현하기만 하면 쉽게 풀 수 있는 문제였다. / 가장 긴 문자열을 찾는데 시간을 줄이기 위해 고민하느라 오래 걸렸던 구현 문제였다.
 * v1. map(사전 관리용) 1개, list(정답 리턴용) 1개 사용 (성공)
 * [접근법] 가장 긴 문자열을 찾기 위해 substring() 을 사용
 * - 현재 입력(msg)에서 substring() 으로 길이를 하나씩 줄여가며 사전에 해당 단어가 있는지 검사한다.
 * - 가정) 현재입력: ABCD, 사전: A,B,C,..., ABC => ABCD, ABC, AB, A 순서로 각 단어가 사전에 있는지 검사한다.
 * 2_v1. HashMap(사전), LinkedList(문자열) (성공) -> 추천(빠름)
 * [접근법] 가장 긴 문자열을 찾기 위해 LinkedList 를 사용
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17684">문제</a>
 */
class Solution17684 {
    public static void main(String[] args) {
        // [11, 1, 27, 15]
        System.out.println(solution2("KAKAO"));

        // [20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34]
        System.out.println(solution2("TOBEORNOTTOBEORTOBEORNOT"));

        // [1, 2, 27, 29, 28, 31, 30]
        System.out.println(solution2("ABABABABABABABAB"));
    }

    // 1_v1
    /**
     * @param msg 영문 대문자로만 이뤄진 문자열 msg(1 글자 이상, 1000 글자 이하)
     * @return 주어진 문자열을 압축한 후의 사전 색인 번호를 배열로 출력
     */
    public static List<Integer> solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(); // (단어, 색인번호)

        // 1. 사전 초기화(A ~ Z)
        int idx = 1;
        for(int i = 0; i < 26; i++) {
            map.put((char) ('A' + i) + "", idx++);
        }

        while(msg.length() != 0) {
            // 2. 사전에서 현재 입력과 일치하는 가장 긴 문자열(w) 찾기
            for(int i = msg.length(); i > 0; i--) {
                String w = msg.substring(0, i);
                if(map.containsKey(w)) {
                    // 3. w에 해당하는 사전의 색인번호 출력, 현재 입력에서 w 제거
                    answer.add(map.get(w));
                    msg = msg.substring(i);
                    // 4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록
                    if(msg.length() != 0) {
                        map.put(w + msg.charAt(0), idx++);
                    }
                    break;
                }
            }
        }
        return answer;
    }

    // 2_v1
    public static List<Integer> solution2(String msg) {
        List<Integer> answer = new ArrayList<>();   //
        Queue<String> q = new LinkedList<>();   // 메시지의 각 문자가 들어있는 큐
        Map<String, Integer> map = new HashMap<>(); // 사전(단어, 인덱스)
        int i;  // 사전 인덱스
        // 1. 사전 초기화
        for(i = 1; i <= 26; i++) {
            map.put(String.valueOf((char) ('A' + i - 1)), i);
        }
        for(char c : msg.toCharArray()) {
            q.add(Character.toString(c));
        }
        // 2. 메시지 큐가 빌때까지 반복
        while(!q.isEmpty()) {
            // 3. w, c 찾기
            StringBuilder w = new StringBuilder();  // 현재 입력과 일치하는 가장 긴 문자열
            String c = "";  // 다음 글자
            while(!q.isEmpty()) {
                if(!map.containsKey(w + q.peek())) break;
                // 큐에서 꺼낸 문자를 w 뒤에 붙임
                w.append(q.poll());
                // 다음 글자가 있을 때만 갱신
                if(!q.isEmpty()) c = q.peek();
            }
            // 4. w 의 인덱스 리스트에 추가
            answer.add(map.get(w.toString()));
            // 5. 다음 글자가 있을 때만 사전에 등록
            if(!c.equals(""))
                map.put(w + c, i++);
        }
        return answer;
    }
}