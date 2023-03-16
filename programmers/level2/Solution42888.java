package programmers.level2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [문제명] 오픈채팅방
 * [풀이시간] / 19분
 * [한줄평] 주어진 문제대로 풀기만하면 되는 쉬운 구현 문제였다. 다양한 풀이 방법이 있을 것 같다. / 쉬운 구현문제였고 효율을 높이기 위한 방법만 조금 고민하면 된다.
 * 1_v1. HashMap, ArrayList(성공)
 * 2_v1. HashMap, ArrayList 2, Info 클래스(성공)
 * 2_v2. HashMap, ArrayList 2, Info 클래스(성공) -> 추천
 * [개선] Info 클래스의 type 변수의 타입을 String -> char 로 바꿈(비교할 때 equals() 로 안해도 됨!)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42888">문제</a>
 */
class Solution42888 {
    public static void main(String[] args) {
        // ["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."]
        System.out.println(solution2(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"}));
    }

    // 1_v1
    /**
     * @param record 채팅방에 들어오고 나가거나, 닉네임을 변경한 기록이 담긴 문자열 배열
     * - 배열의 길이는 1 이상 100,000 이하
     * - 각 단어는 공백으로 구분됨, 알파벳 대문자&소문자&숫자로만 이루어짐
     * - 유저 아이디와 닉네임은 알파벳 대문자, 소문자를 구별
     * - 유저 아이디와 닉네임의 길이는 1 이상 10 이하
     * 1) 입장: Enter [유저 아이디] [닉네임]
     * 2) 퇴장: Leave [유저 아이디]
     * 3) 닉네임 변경: Change [유저 아이디] [닉네임]
     * @return 모든 기록이 처리된 후, 최종적으로 방을 개설한 사람이 보게 되는 메시지를 문자열 배열 형태로 return
     */
    public static List<String> solution(String[] record) {
        List<String> answer = new ArrayList<>();
        Map<String, String> map = new HashMap(); // (아이디, 닉네임)

        // 1. (아이디, 닉네임) 쌍 저장하기
        for(String s : record) {
            String[] split = s.split(" ");
            String type = split[0];
            String id = split[1];

            if(type.equals("Enter") || type.equals("Change")) {
                String nickname = split[2];
                map.put(id, nickname);
            }
        }

        // 2. 바뀐 닉네임을 적용해서 결과 문자열 리스트 만들기
        for(String s : record) {
            String[] split = s.split(" ");
            String type = split[0];
            String id = split[1];
            StringBuilder sb = new StringBuilder();
            if(type.equals("Enter")) {
                sb.append(map.get(id));
                sb.append("님이 들어왔습니다.");
                answer.add(sb.toString());
            } else if(type.equals("Leave")) {
                sb.append(map.get(id));
                sb.append("님이 나갔습니다.");
                answer.add(sb.toString());
            }
        }
        return answer;
    }

    // 2_v2
    static class Info {
        char type;  // 시간 단축을 위해 char 로 관리
        String id;

        Info(char type, String id) {
            this.type = type;
            this.id = id;
        }
    }
    public static List<String> solution2(String[] record) {
        // 1. map, list 기록
        Map<String, String> map = new HashMap<>();
        List<Info> list = new ArrayList<>();
        for(String r : record) {
            String[] info = r.split(" ");
            String type = info[0];
            String id = info[1];
            String nickname;
            // Enter, Change -> map 에 nickname 기록
            // Enter, Leave -> 들어오고 나간 정보 기록
            switch (type) {
                case "Enter":
                    nickname = info[2];
                    map.put(id, nickname);
                    list.add(new Info('E', id));
                    break;
                case "Leave":
                    list.add(new Info('L', id));
                    break;
                default:
                    nickname = info[2];
                    map.put(id, nickname);
            }
        }
        // 2. 문자열 만들어서 리스트에 넣고 리턴
        List<String> answer = new ArrayList<>();
        for(Info info : list) {
            if(info.type == 'E')
                answer.add(map.get(info.id) + "님이 들어왔습니다.");
            else
                answer.add(map.get(info.id) + "님이 나갔습니다.");
        }
        return answer;
    }
}