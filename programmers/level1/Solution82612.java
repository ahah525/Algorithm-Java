package programmers.level1;


/**
 * [문제명] 부족한 금액 계산하기
 * [풀이시간] 4분
 * [한줄평] 수학으로 쉽게 풀 수 있는 문제였다.
 * 1_v1. 수학(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/82612">문제</a>
 */
class Solution82612 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param price 놀이기구의 이용료
     * @param money 처음 가지고 있던 금액
     * @param count 놀이기구의 이용 횟수
     * @return 놀이기구를 count번 타게 되면 현재 자신이 가지고 있는 금액에서 얼마가 모자라는지를 return(금액이 부족하지 않으면 0을 return)
     */
    public long solution(int price, int money, int count) {
        long res = (long) price * count * (count + 1) / 2;
        return (money >= res) ? 0 : res - money;
    }
}