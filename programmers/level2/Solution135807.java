package programmers.level2;


/**
 * [문제명] 숫자 카드 나누기
 * [풀이시간] 20분, 20분 / 25분
 * [한줄평] 최대 공약수 구하는 것을 응용해서 풀 수 있는 문제였다. / 유클리드 호제법을 구현하는데 좀 시간이 걸렸다.
 * 1_v1. 수학(성공)
 * [접근법] 일반적인 방법
 * 1_v2. 수학(성공) -> 빠름
 * [접근법] 유클리드 호제법
 * 2_v1. 수학(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/135807">문제</a>
 */
class Solution135807 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2
    /**
     * @param arrayA 철수가 가진 카드에 적힌 숫자들을 나타내는 정수 배열
     * @param arrayB 영희가 가진 카드에 적힌 숫자들을 나타내는 정수 배열
     * @return 주어진 조건을 만족하는 가장 큰 양의 정수 a
     */
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = gcd(arrayA);
        int gcdB = gcd(arrayB);
        if(gcdA == gcdB) return 0;
        if(gcdA > gcdB) {
            if(canDivide(gcdA, arrayB)) return 0;
        } else {
            if(canDivide(gcdB, arrayA)) return 0;
        }
        return Math.max(gcdA, gcdB);
    }

    // arr의 원소가 하나라도 gcd로 나누어지는지
    public boolean canDivide(int gcd, int[] arr) {
        for(int n : arr) {
            if(n % gcd == 0) return true;
        }
        return false;
    }

    // 배열의 모든 원소의 최대공약수
    public int gcd(int[] arr) {
        int a = arr[0];
        for(int i = 1; i < arr.length; i++) {
            a = gcd(a, arr[i]);
        }
        return a;
    }

    // 두 수의 최대 공약수
    public int gcd(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        int tmp;
        while(min != 0) {
            tmp = max % min;
            max = min;
            min = tmp;
        }
        return max;
    }

    // 2_v1
    public int solution2(int[] arrayA, int[] arrayB) {
        int answer = 0;
        // 1. 각 배열의 최대공약수 구하기
        int gcdA = gcd(arrayA); // arrayA의 최대공약수
        int gcdB = gcd(arrayB); // arrayB의 최대공약수
        // 2. 조건을 만족하는 수 중에서 최댓값 구하기
        if(!canDivide(gcdA, arrayB))
            answer = Math.max(answer, gcdA);
        if(!canDivide(gcdB, arrayA))
            answer = Math.max(answer, gcdB);
        return answer;
    }
}