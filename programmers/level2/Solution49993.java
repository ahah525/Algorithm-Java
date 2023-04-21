package programmers.level2;


import java.util.*;

/**
 * [문제명] 스킬트리
 * [풀이시간] 30분 / 13분, 10분
 * [한줄평] 문제를 잘못 이해해서 구현하느데 생각보다 시간이 오래걸렸던 문제였다. / 그냥 구현으로 풀었는데, 다른 풀이를 보니 정규 표현식으로 많이 푼 것 같다.
 * 1_v1. 문자열, 구현(성공)
 * 2_v1. 문자열, 구현(성공) -> 빠름
 * [접근법] skill 과 skillTree 문자 하나씩 비교
 * 2_v2. 문자열(성공)
 * [접근법] skill 이 아닌 문자 제거 후 비교
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/49993">문제</a>
 */
class Solution49993 {
    public static void main(String[] args) {
        // 2
        System.out.println(solution2("CBD", new String[] {"BACDE", "CBADF", "AECB", "BDA"}));
    }

    // 1_v1
    /**
     * @param skill 선행 스킬 순서
     * @param skillTrees 유저들이 만든 스킬트리1를 담은 배열
     * @return 가능한 스킬트리 개수
     */
    public static int solution(String skill, String[] skillTrees) {
        int answer = 0;
        List<Character> skills = new ArrayList<>();
        for(char c : skill.toCharArray()) {
            skills.add(c);
        }
        for(String s : skillTrees) {
            if(isPossible(skills, s)) {
                answer++;
            }
        }
        return answer;
    }

    public static boolean isPossible(List<Character> skills, String s) {
        int i = 0;  // 선행 스킬 인덱스
        for(char c : s.toCharArray()) {
            if(skills.contains(c)) {
                // 현재 스킬이 선행 스킬 순서상 맞지 않는 경우
                if(c != skills.get(i)) return false;
                i++;
            }
        }
        return true;
    }

    // 2_v1
    static Set<Character> set;
    static char[] arr;
    public static int solution2(String skill, String[] skillTrees) {
        int answer = 0;
        set = new HashSet<>(); // 선행 스킬 집합
        arr = skill.toCharArray(); // 선행 스킬 배열
        for(char c : arr) {
            set.add(c);
        }
        for(String skillTree : skillTrees) {
            if(isPossible(skillTree)) {
                answer++;
            }
        }
        return answer;
    }

    public static boolean isPossible(String skillTree) {
        int i = 0;
        for(char c : skillTree.toCharArray()) {
            // 1. 선행 스킬에 없는 문자면 패스
            if(!set.contains(c)) continue;
            // 2. 이미 선행 스킬 문자가 모두 나왔으면 종료
            if(i == arr.length) break;
            // 3. 선행 스킬 순서가 맞지 않으면 false 리턴
            if(c != arr[i]) return false;
            // 4. 선행 스킬 순서 다음으로
            i++;
        }
        return true;
    }

    // 2_v2
    public int solution3(String skill, String[] skillTrees) {
        int answer = 0;
        for(String skillTree : skillTrees) {
            // 1. 스킬트리에서 스킬에 없는 문자 제거
            String s = skillTree.replaceAll("[^"+ skill + "]", "");
            // 2. 스킬이 스킬트리로 시작하면 카운트
            if(skill.startsWith(s))
                answer++;
        }
        return answer;
    }
}