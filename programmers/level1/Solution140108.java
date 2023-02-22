package programmers.level1;


/**
 * [문제명] 문자열 나누기
 * [풀이시간] 15분
 * [한줄평] 쉬운 문제이긴 했으나 처음에 반례를 잡지 못해 틀렸던 문제였다.
 * 1_v1. (성공)
 * [반례] 마지막에 분해되고 남은 글자는 카운팅 안되는 문제
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/140108">문제</a>
 */
class Solution140108 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param s 문자열
     * @return 문자열들로 분해하고, 분해한 문자열의 개수
     */
    public int solution(String s) {
        int answer = 0;
        boolean split = true;
        char first = ' ';
        // x 가 나온 횟수, x 가 아닌 문자가 나온 횟수
        int a = 0, b = 0;
        for(char c : s.toCharArray()) {
            // 1. 이전에 분해가 되었으면 x 지정
            if(split) {
                first = c;
                split = false;
            }
            // 2. x와 x가 아닌 다른 글자 개수 세기
            if(c == first) {
                a++;
            } else {
                b++;
            }
            // 3. 두 개수가 같으면 분해
            if(a == b) {
                answer++;
                split = true;
                a = 0;
                b = 0;
            }
        }
        // 분해하고 남은 문자가 있으면 1개 추가로 카운트
        if(!split) answer++;
        return answer;
    }
}