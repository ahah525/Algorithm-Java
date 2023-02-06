package programmers.level1;


/**
 * [문제명] 신규 아이디 추천
 * [풀이시간]
 * [한줄평]
 * v1. (실패 - 7, 12, 16, 24, 26)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/72410">문제</a>
 */
class Solution72410 {
    public static void main(String[] args) {
        //
        System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
    }

    public static String solution(String newId) {
        // 1. 소문자 치환
        newId = newId.toLowerCase();
        // 2. 치환
        newId = newId.replaceAll("[^a-z0-9-_.]", "");
        // System.out.println("2:" + newId);
        // 3. 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
        newId = newId.replaceAll("((.)\\2{1,})", ".");
        // System.out.println("3:" + newId);
        // 4. 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
        if(newId.charAt(0) == '.') {
            newId = newId.substring(1);
        }
        if(newId.length() != 0 && newId.charAt(newId.length() - 1) == '.') {
            newId = newId.substring(0, newId.length() - 1);
        }
        // System.out.println("4:" + newId);
        // 5. 빈 문자열이라면, new_id에 "a"를 대입합니다.
        if(newId.length() == 0) {
            newId = "a";
        }
        // System.out.println("5:" + newId);
        // 6. 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다. 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
        if(newId.length() >= 16) {
            newId = newId.substring(0, 15);
            if(newId.charAt(14) == '.') {
                newId = newId.substring(0, 14);
            }
        }
        // System.out.println("6:" + newId);
        // 7. 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
        if(newId.length() <= 2) {
            char end = newId.charAt(newId.length() - 1);
            int len = newId.length();
            for(int i = 0; i < 3 - len; i++) {
                newId += end;
            }
        }
        // System.out.println("7:" + newId);
        return newId;
    }
}