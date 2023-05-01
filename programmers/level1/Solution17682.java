package programmers.level1;


/**
 * [문제명] [1차] 다트 게임
 * [풀이시간] 60분 / 40분
 * [한줄평] 이렇게까지 오래걸릴 줄 몰랐는데, 어떻게 풀어야 효율적일지 고민하다가 시간을 많이 지체했다. 다음에 한번 더 풀어보면 좋을 것 같다.
 * / 간단한 구현 문제이긴 했지만 조건 처리할게 많아서 조금 오래걸렸다.
 * 1_v1. 구현(성공)
 * 1_v2. 문자열(성공) -> 빠름
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17682">문제</a>
 */
class Solution17682 {
    public static void main(String[] args) {
        // 37
        System.out.println(solution("1S2D*3T\t"));
    }

    // 1_v1
    /**
     * @param dartResult "점수|보너스|[옵션]"으로 이루어진 문자열 3세트
     * @return 총점수
     */
    public static int solution(String dartResult) {
        int answer = 0;
        String curStr = ""; // 현재 점수(문자열)
        int cur = 0;        // 현재 점수(정수)
        int i = -1;         // 현재 순서
        int[] scores = {0, 0, 0};   // 계산로직을 적용한 각 점수
        // 모든 문자 검사
        for(char c : dartResult.toCharArray()) {
            if('0' <= c && c <= '9') {
                // 1. 점수(숫자) -> 다음에 또 숫자가 나올 수도 있으므로 일단 문자열로 저장
                curStr += c;
            } else if('A' <= c && c <= 'Z') {
                // 2. 보너스(알파벳)
                // 2-1. 현재가 몇번째 순서인지 업데이트
                i++;
                // 2-2. 현재 점수를 정수형으로 변환하고 초기화
                cur = Integer.parseInt(curStr);
                curStr = "";
                // 2-3. 종류에 따라 점수 계산해서 저장
                if(c == 'S') {
                    scores[i] = cur;
                } else if(c == 'D') {
                    scores[i] = (int) Math.pow(cur, 2);
                } else if(c == 'T') {
                    scores[i] = (int) Math.pow(cur, 3);
                }
            } else {
                // 3. 옵션
                if(c == '*') {
                    scores[i] *= 2;
                    // 맨 첫번째 순서일 경우 이전 점수에는 적용X
                    if(i != 0)
                        scores[i - 1] *= 2;
                } else if(c == '#') {
                    scores[i] *= -1;
                }
            }
        }
        // 최종 점수 합산
        for(int score : scores) {
            answer += score;
        }
        return answer;
    }

    // 2_v1
    public int solution2(String dartResult) {
        int answer = 0;
        int prevNum = 0; // 이전 숫자
        int nowNum = 0; // 현재 숫자
        int i = 0;
        while(i < dartResult.length()) {
            char cur = dartResult.charAt(i); // 현재 문자
            if(Character.isDigit(cur)) {
                // 1. 숫자인 경우
                if(dartResult.charAt(i + 1) == '0') {
                    // 1.1. 그 다음 문자가 0이면 현재 숫자 = 10
                    nowNum = 10;
                    i++; // 그 다음 문자 검사할 필요 없으므로 +1
                } else {
                    // 1.2. 그 다음 문자가 0이 아니면 현재 숫자 그대로 저장
                    nowNum = cur - '0';
                }
            } else if('A' <= cur && cur <= 'Z') {
                // 2. 문자인 경우
                if(cur == 'D') {
                    // 2.1. 제곱
                    nowNum *= nowNum;
                } else if(cur == 'T') {
                    // 2.2. 세제곱
                    nowNum = nowNum * nowNum * nowNum;
                }
                // 3. 그 다음 문자가 옵션인 경우
                if(i + 1 != dartResult.length()) {
                    if(dartResult.charAt(i + 1) == '*') {
                        // 3.1. 이전 숫자, 현재 숫자 x2배
                        prevNum *= 2;
                        nowNum *= 2;
                        i++;
                    } else if(dartResult.charAt(i + 1) == '#') {
                        // 3.2. 현재 숫자 -1배
                        nowNum = -nowNum;
                        i++;
                    }
                }
                // 4. 이전숫자 정산
                answer += prevNum;
                prevNum = nowNum;
            }
            i++;
        }
        // 5. 마지막 이전 숫자 정산
        answer += prevNum;
        return answer;
    }
}