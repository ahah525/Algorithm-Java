package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] 스킬트리
 * [풀이시간] 30분
 * [한줄평] 문제를 잘못 이해해서 구현하느데 생각보다 시간이 오래걸렸던 문제였다.
 * v1. (성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/49993">문제</a>
 */
class Solution49993 {
    public static void main(String[] args) {
        // 2
        String[] skillTrees1 = {"BACDE", "CBADF", "AECB", "BDA"};
        System.out.println(solution("CBD", skillTrees1));
    }

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
}