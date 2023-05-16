package programmers.level2;


/**
 * [문제명] 이진 변환 반복하기
 * [풀이시간] 10분 / 6분
 * [한줄평] 문제에 있는 그대로 구현하고 2진법으로 변환하는 메서드 사용법만 알면 쉽게 풀 수 있는 문제다. /
 * 1_v1. 문자열(성공)
 * 2_v1. 문자열(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/70129">문제</a>
 */
class Solution70129 {
    public static void main(String[] args) {
        // [3,8]
        String s1 = "110010101001";
        System.out.println(solution(s1));

        // [3,3]
        String s2 = "01110";
        System.out.println(solution(s2));

        // [4,1]
        String s3 = "1111111";
        System.out.println(solution(s3));
    }

    // 1_v1, 2_v1
    /**
     * @param s 0과 1로 이루어진 어떤 문자열
     * @return s가 "1"이 될 때까지 계속해서 s에 이진 변환을 가했을 때, 이진 변환의 횟수와 변환 과정에서 제거된 모든 0의 개수를 각각 배열에 담아 return
     * 1. x의 모든 0을 제거합니다.
     * 2. x의 길이를 c라고 하면, x를 "c를 2진법으로 표현한 문자열"로 바꿉니다.
     */
    public static int[] solution(String s) {
        // 변환 횟수, 0 제거 개수
        int[] answer = new int[2];
        while(!s.equals("1")) {
            answer[1] += s.length();
            int c = s.replace("0", "").length();
            answer[1] -= c;
            s = Integer.toString(c, 2);
            answer[0]++;
        }
        return answer;
    }
}