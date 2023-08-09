package programmers.level1;


/**
 * [문제명] 2016년
 * [풀이시간] 20분 / 20분 / 11분
 * [한줄평] 쉬운 문제였는데, 윤년이라는 조건을 생각을 못하고 풀어서 생각보다 오래걸렸던 문제였다.
 * / 2번째 풀 때도 윤년을 28일로 착각해서 오래걸렸던 문제였다.
 * / 유사한 문제가 코테에 나와서 복습 차원에서 풀었는데 더 이상 풀어볼 필요는 없을 것 같다.
 * 1_v1. 수학(성공) -> 빠름
 * [풀이] 월 별 일 수를 int[]에 저장
 * 2_v1. 수학(성공)
 * [풀이] 월 별 일 수를 switch문으로 구함
 * 3_v1. 수학(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12901">문제</a>
 */
class Solution12901 {
    public static void main(String[] args) {
        // "TUE"
        int a = 5;
        int b = 24;
        System.out.println(solution(a, b));
    }

    // 1_v1, 3_v1
    /**
     * @param a 월
     * @param b 일
     * @return 2016년 a월 b일이 무슨 요일인지 리턴
     */
    public static String solution(int a, int b) {
        // 1. 월 별 일 수 초기화
        int[] month = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // 2. 지난 일 수에 대한 요일 초기화
        String[] days = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
        // 3. a월 b일이 1월 1일을 기준으로 몇 일이 지났는지 일 수 계산
        int day = 0;
        for(int i = 0; i < a - 1; i++) {
            day += month[i];
        }
        day += (b - 1);
        // 4. 지난 일 수의 요일 = days[지난 일 수 % 7]
        return days[day % 7];
    }

    // 2_v1
    public String solution2(int a, int b) {
        String[] day = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
        int days = b;
        for(int i = 1; i < a; i++) {
            switch(i) {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    days += 31;
                    break;
                case 4: case 6: case 9: case 11:
                    days += 30;
                    break;
                default:
                    days += 29;
                    break;
            }
        }
        return day[days % 7];
    }
}