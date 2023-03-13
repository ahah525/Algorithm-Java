package programmers.level2;


/**
 * [문제명] 문자열 압축
 * [풀이시간] 28분
 * [한줄평] 문자열을 다루는 구현 문제였다.
 * 1_v1. 문자열 구현(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/60057">문제</a>
 */
class Solution60057 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param s 압축할 문자열(s의 길이는 1 이상 1,000 이하)
     * @return 위에 설명한 방법으로 1개 이상 단위로 문자열을 잘라 압축하여 표현한 문자열 중 가장 짧은 것의 길이
     */
    public int solution(String s) {
        int answer = s.length();

        for(int i = 1; i <= s.length() / 2; i++) {
            String[] arr = split(s, i);
            // System.out.println(Arrays.toString(arr));
            StringBuilder sb = new StringBuilder();
            String prev = "";
            int cnt = 1;
            for(String str : arr) {
                if(!str.equals(prev)) {
                    // 이전 문자
                    if(cnt != 1)
                        sb.append(cnt);
                    sb.append(prev);
                    //
                    prev = str;
                    cnt = 1;
                } else {
                    cnt++;
                }
            }
            // 마지막 처리
            if(cnt != 1)
                sb.append(cnt);
            sb.append(prev);
            // System.out.println(sb);
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }

    // s 를 n 글자씩 분리하여 String[] 리턴
    public String[] split(String s, int n) {
        int len = (int) Math.ceil((double) s.length() / n);
        int idx = 0;
        String[] arr = new String[len];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(i != 0 && i % n == 0) {
                arr[idx++] = sb.toString();
                // 비우기
                sb.setLength(0);
            }
            sb.append(s.charAt(i));
        }
        // 남은 문자
        if(sb.length() != 0)
            arr[idx] = sb.toString();
        return arr;
    }
}