package programmers.level2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [한줄평] map 을 사용해서 그대로 구현하기만 하면 쉽게 풀 수 있는 문제였다.
 * - 사전에 등록된 단어 중 가장 긴 문자열을 계속해서 찾아야하기 때문에, 현재 입력(msg)에서 substring() 으로 길이를 하나씩 줄여가며 사전에 해당 단어가 있는지 검사해야한다!!
 * 가정) 현재입력: ABCD, 사전: A,B,C,..., ABC
 * ABCD, ABC, AB, A 순서로 각 단어가 사전에 있는지 검사해야한다!
 *
 * v1. map(사전 관리용) 1개, list(정답 리턴용) 1개 사용 (성공)
 *
 * >> LZW 압축방법
 * 1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
 * 2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
 * 3. w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
 * 4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
 * 5. 단계 2로 돌아간다.
 */
class Solution17684 {
    public static void main(String[] args) {
        // [11, 1, 27, 15]
        String msg1 = "KAKAO";
        List<Integer> answer1 = solution(msg1);
        System.out.println(answer1);

        // [20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34]
        String msg2 = "TOBEORNOTTOBEORTOBEORNOT";
        List<Integer> answer2 = solution(msg2);
        System.out.println(answer2);

        // [1, 2, 27, 29, 28, 31, 30]
        String msg3 = "ABABABABABABABAB";
        List<Integer> answer3 = solution(msg3);
        System.out.println(answer3);
    }

    /**
     *
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
//        System.out.println(map);
        return answer;
    }
}