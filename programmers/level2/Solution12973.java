package programmers.level2;


/**
 * [문제명] 짝지어 제거하기
 * [풀이시간] 15분
 * [한줄평]
 * v1. StringBuilder(실패 - 효율성 테스트 모두 시간초과)
 * - 시간초과가 날 것을 고려해 StringBuilder 를 써서 포인터를 옮겨가는 방식으로 풀었는데 시간초과가 났다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12973">문제</a>
 */
class Solution12973 {
    public static void main(String[] args) {
        // 1
        System.out.println(solution("baabaa"));

        // 0
        System.out.println(solution("cdcd"));
    }

    public static int solution(String s) {
        int i = 0;  // 포인터
        StringBuilder sb = new StringBuilder(s);
        while(true) {
            // 1. 문자열의 길이가 0이면 모두 제거된 것이므로 1 리턴
            if(sb.length() == 0) {
                return 1;
            }
            // 2. 문자열의 길이가 0이 아닌데, 포인터가 마지막에 도달했으면 모두 제거할 수 없으므로 0 리턴
            if(i == sb.length() - 1) {
                return 0;
            }
            if(sb.charAt(i) == sb.charAt(i + 1)) {
                // 3. 같으면 삭제, 포인터 왼쪽 이동
                sb.delete(i, i + 2);
                i--;
                if(i < 0) i = 0;    // 포인터 음수값에 대한 처리
            } else {
                // 4. 다르면 포인터 오른쪽 이동
                i++;
            }
        }
    }
}