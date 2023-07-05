package programmers.level1;


/**
 * [문제명] 신규 아이디 추천
 * [풀이시간] 1시간 / 18분 / 23분
 * [한줄평] 정규표현식을 이용하면 쉽게 풀 수 있는 문제였다. 2, 3단계 정규표현식을 잘못해서 결국 정답을 보고 해결했다...
 * / 정규표현식 활용만 잘하면 쉽게 풀 수있는 문제다.
 * / 정규표현식을 사용하는 법을 익히면 쉽게 풀 수 있는 문제였다.
 * 1_v1. 문자열(실패 - 7, 12, 16, 24, 26)
 * 1_v2. 문자열(성공)
 * [풀이] 정규표현식
 * 2_v1. 문자열, 구현(성공)
 * [풀이] 정규표현식
 * 2_v2. 문자열, 구현(성공) -> 빠름
 * [풀이] StringBuilder 로 시간 단축
 * 3_v1. 문자열(성공)
 * [풀이] 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거하기 -> for 문으로 한글자씩 검사
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/72410">문제</a>
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/72410/solution_groups?language=java">문제 풀이</a>
 */
class Solution72410 {
    public static void main(String[] args) {
        //
        System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
    }

    // 1_v2
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

    // 2_v1
    public String solution2(String newId) {
        newId = newId.toLowerCase()
                .replaceAll("[^a-z0-9-_.]", "")
                .replaceAll("[.]{2,}", ".");
        if(newId.charAt(0) == '.')
            newId = newId.substring(1);
        if(newId.length() != 0 && newId.charAt(newId.length() - 1) == '.')
            newId = newId.substring(0, newId.length() - 1);

        if(newId.length() == 0) {
            newId = "a";
        } else if(newId.length() >= 16) {
            newId = newId.substring(0, 15);
            if(newId.charAt(14) == '.')
                newId = newId.substring(0, 14);
        }
        if(newId.length() <= 2) {
            char end = newId.charAt(newId.length() - 1);
            while(newId.length() < 3) {
                newId += end;
            }
        }
        return newId;
    }

    // 2_v2
    public String solution3(String newId) {
        newId = newId.toLowerCase()
                .replaceAll("[^a-z0-9-_.]", "")
                .replaceAll("[.]{2,}", ".");
        StringBuilder sb = new StringBuilder(newId);
        if(sb.charAt(0) == '.')
            sb.deleteCharAt(0);
        if(sb.length() != 0 && sb.charAt(sb.length() - 1) == '.')
            sb.deleteCharAt(sb.length() - 1);
        if(sb.length() == 0) {
            sb.append("a");
        } else if(sb.length() >= 16) {
            sb.delete(15, sb.length());
            if(sb.charAt(14) == '.')
                sb.deleteCharAt(14);
        }
        if(sb.length() <= 2) {
            char end = sb.charAt(sb.length() - 1);
            while(sb.length() < 3) {
                sb.append(end);
            }
        }
        return sb.toString();
    }

    // 3_v1
    public String solution4(String newId) {
        newId = newId.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for(char c : newId.toCharArray()) {
            if(('a' <= c && c <= 'z') || Character.isDigit(c) || c == '-' || c == '_' || c == '.')
                sb.append(c);
        }
        newId = sb.toString();
        newId = newId.replaceAll("[.]{2,}+", ".")
                .replaceAll("^[.]", "")
                .replaceAll("[.]$", "");
        if(newId.length() == 0) newId = "a";
        else if(newId.length() >= 16) {
            if(newId.charAt(14) == '.') {
                newId = newId.substring(0, 14);
            } else {
                newId = newId.substring(0, 15);
            }
        }
        if(newId.length() <= 2) {
            char end = newId.charAt(newId.length() - 1);
            while(newId.length() < 3) {
                newId += end;
            }
        }
        return newId;
    }
}