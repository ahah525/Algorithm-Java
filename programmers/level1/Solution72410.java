package programmers.level1;


/**
 * [문제명] 신규 아이디 추천
 * [풀이시간] 1시간
 * [한줄평] 정규표현식을 이용하면 쉽게 풀 수 있는 문제였다. 2, 3단계 정규표현식을 잘못해서 결국 정답을 보고 해결했다...
 * 1_v1. 정규표현식(실패 - 7, 12, 16, 24, 26)
 * 1_v2. 정규표현식(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/72410">문제</a>
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/72410/solution_groups?language=java">문제 풀이</a>
 */
class Solution72410 {
    public static void main(String[] args) {
        //
        System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
    }

    public static String solution(String newId) {
        // 1. 모든 대문자를 대응되는 소문자로 치환합니다.
        newId = newId.toLowerCase();
        // 2. 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
        newId = newId.replaceAll("[^a-z0-9-_.]", "");
        // 3. 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
        newId = newId.replaceAll("[.]{2,}", ".");
        // 4. 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
        newId = newId.replaceAll("^[.]|[.]$", "");
        // 5. 빈 문자열이라면, new_id에 "a"를 대입합니다.
        if(newId.length() == 0) {
            newId = "a";
        }
        // 6. 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다. 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
        if(newId.length() >= 16) {
            newId = newId.substring(0, 15);
            newId = newId.replaceAll("[.]$", "");
        }
        // 7. 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
        if(newId.length() <= 2) {
            char end = newId.charAt(newId.length() - 1);
            while(newId.length() < 3) {
                newId += end;
            }
        }
        return newId;
    }
}