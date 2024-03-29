package programmers.level2;


/**
 * [문제명] 마법의 엘리베이터
 * [풀이시간] 1시간 10분 / 55분(50분 + 5분) / 45분
 * [한줄평] 처음에는 DP 문제인가 했는데, 수학으로 규칙을 찾아서 풀 수 있을 것 같았는데 결국 풀이를 보고 이해했던 문제다.
 * / 예전에 풀었던 기억에 의존해서 풀었지만 여전히 어려웠던 문제였다.
 * / 규칙을 찾기가 어려운 문제로 복습이 필요하다.
 * 1_v1. 수학(성공)
 * [풀이] 끝자리를 0으로 만들기 위해 최선책(+1 or -1)을 반복적으로 선택한다.
 * 2_v1. (실패 - 1,3 실패)
 * 2_v2. 수학(성공)
 * 3_v1. 수학(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/148653">문제</a>
 * @See <a href="https://velog.io/@isayaksh/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Programmers-%EB%A7%88%EB%B2%95%EC%9D%98-%EC%97%98%EB%A6%AC%EB%B2%A0%EC%9D%B4%ED%84%B0-Python">풀이</a>
 */
class Solution148653 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 3_v1
    /**
     * @param storey 민수와 마법의 엘리베이터가 있는 층을 나타내는 정수
     * @return 0층으로 가기 위해 필요한 마법의 돌의 최소값
     */
    public int solution(int storey) {
        int answer = 0;
        while(storey > 0) {
            // 일의 자리 숫자
            int n = storey % 10;
            if(n >= 6) {
                // 6 ~ 9 -> (+1) 연산
                answer += (10 - n);
                storey += (10 - n);
            } else if(n <= 4) {
                // 0 ~ 4 -> (-1) 연산
                answer += n;
            } else {
                // 5 -> 10의 자리수 비교
                int m = (storey % 100) / 10;
                if(m >= 5) {
                    // 5 ~ 9 -> (+1) 연산
                    answer += (10 - n);
                    storey += (10 - n);
                } else {
                    // 0 ~ 4 -> (-1) 연산
                    answer += n;
                }
            }
            storey /= 10;
        }
        return answer;
    }

    // 2_v2
    public int solution2(int storey) {
        int answer = 0;
        while(storey != 0) {
            int n = storey % 10;
            if(n <= 4) {
                answer += n;
                storey /= 10;
            } else if(n == 5) {
                answer += n;
                storey /= 10;
                if(storey % 10 >= 5) {
                    storey++;
                }
            } else {
                answer += (10 - n);
                storey /= 10;
                storey++;
            }
        }
        return answer;
    }
}