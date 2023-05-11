package programmers.level2;


/**
 * [문제명] 문자열 압축
 * [풀이시간] 28분, 20분 / 1시간 5분(1시간 + 5분)
 * [한줄평] 문자열을 다루는 구현 문제로 split() 을 써서 바로 비교했을 때 시간이 훨씬 빨랐다.
 * 1_v1. 문자열 구현(성공)
 * [접근법] 직접 문자열 분리 후 비교
 * 1_v2. 문자열 구현(성공) -> 추천
 * [접근법] split() 으로 분리와 동시에 비교
 * 2_v1. 문자열(실패 - 5 실패)
 * 2_v2. 문자열(성공)
 * [반례] 문자열 길이가 1인 경우, 최솟값 = 1
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/60057">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/20870">반례</a>
 */
class Solution60057 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
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

    // 1_v2
    public int solution2(String s) {
        int answer = s.length();
        // 1. (최소 단위 = 1, 최대 단위 = 문자열 길이 / 2) 만큼 반복
        for(int i = 1; i <= s.length() / 2; i++) {
            StringBuilder sb = new StringBuilder(); // i개 단위로 문자열을 잘라 압축한 문자열
            String prev = "";   // 이전 문자열
            String cur = "";    // 현재 문자열
            int j = 0;      // s 에서 잘라야하는 시작 인덱스
            int cnt = 1;    // prev 가 연속으로 나온 개수
            // 2. 시작 인덱스가 범위를 벗어나지 않을 때까지 반복
            while(j < s.length()) {
                // 3-1. 마지막 문자열이면 끝 인덱스 설정X
                if(j + i >= s.length()) cur = s.substring(j);
                // 3-2. 마지막 문자열이아니면 끝 인덱스 설정
                else cur = s.substring(j, j + i);
                if(!cur.equals(prev)) {
                    // 4. 현재 문자열이 이전 문자열과 다르면
                    // 4-1. 이전 문자열이 몇번 연속되었는지 기록
                    if(cnt != 1) sb.append(cnt);    // 1은 생략
                    sb.append(prev);
                    // 4-2. 현재 문자열을 이전 문자열로 업뎃
                    prev = cur;
                    cnt = 1;
                } else {
                    // 5. 현재 문자열이 이전 문자열과 같으면, 연속 개수만 카운트
                    cnt++;
                }
                // 6. 단위가 i 니까 j = j + i 가 되도록 시작 인덱스 변경
                j += i;
            }
            // 7. 마지막 문자열도 기록
            if(cnt != 1) sb.append(cnt);
            sb.append(prev);
            // 8. ?개 단위로 문자열을 잘라 압축한 문자열의 길이의 최솟값 갱신
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }

    // 2_v1
    public int solution3(String s) {
        int answer = Integer.MAX_VALUE;
        if(s.length() == 1) return 1;
        for(int i = 1; i <= s.length() / 2; i++) {
            answer = Math.min(answer, change(s, i));
        }
        return answer;
    }

    public int change(String s, int n) {
        int len = 0;
        StringBuilder sb = new StringBuilder();
        String prev = "";
        int cnt = 1;
        // String str = "";
        int num = (s.length() / n) * n;
        for(int i = 0; i < num; i++) {
            sb.append(s.charAt(i));
            if(sb.length() == n) {
                if(sb.toString().equals(prev)) {
                    // 이전 문자열과 같으면
                    cnt++;
                } else {
                    // 이전 문자열과 다르면
                    if(cnt > 1) {
                        // str += cnt;
                        len += Integer.toString(cnt).length();
                    }
                    // str += prev;
                    len += prev.length();
                    //
                    prev = sb.toString();
                    cnt = 1;
                }
                sb.setLength(0);
            }
        }
        if(cnt > 1) {
            // str += cnt;
            len += Integer.toString(cnt).length();
        }
        // str += prev;
        len += prev.length();
        //
        for(int i = num; i < s.length(); i++) {
            // str += s.charAt(i);
            len++;;
        }
        return len;
    }
}