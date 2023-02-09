package programmers.level1;


/**
 * [문제명] 소수 만들기
 * [풀이시간] 5분
 * [한줄평] 소수 구하기 문제는 너무 기초 문제여서 쉽게 풀었다.
 * 1_v1. for문(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12977">문제</a>
 */
class Solution12977 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param nums 숫자들이 들어있는 배열
     * @return nums에 있는 숫자들 중 서로 다른 3개를 골라 더했을 때 소수가 되는 경우의 개수
     */
    public static int solution(int[] nums) {
        int answer = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    if(isPrime(nums[i] + nums[j] + nums[k])) {
                        answer++;
                    }
                }
            }
        }
        return answer;
    }

    public static boolean isPrime(int n) {
        if(n == 1) return false;
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
}