package programmers.level1;

/**
 * 정수의 각 자리수의 합 구하기
 * v1. 나머지 연산 반복(추천)
 * v2. Integer.toString(정수).chars() 사용
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12947/solution_groups?language=java">풀이 참고</a>
 */
class Solution12947 {
    public static void main(String[] args) {
        // true
        int arr1 = 10;
        boolean answer1 = solution1(arr1);
        System.out.println(answer1);

        // true
        int arr2 = 12;
        boolean answer2 = solution1(arr2);
        System.out.println(answer2);

        // false
        int arr3 = 11;
        boolean answer3 = solution1(arr3);
        System.out.println(answer3);

        // false
        int arr4 = 13;
        boolean answer4 = solution1(arr4);
        System.out.println(answer4);
    }

    /**
     * @param x 1 이상, 10000 이하인 정수
     * @return x 가 하샤드 수인지(양의 정수 x가 하샤드 수이려면 x의 자릿수의 합으로 x가 나누어져야 합니다.)
     */
    public static boolean solution1(int x) {
        int sum = 0;
        int n = x;
        while(n != 0) {
            sum += n % 10;
            n /= 10;
        }
        return x % sum == 0;
    }
}