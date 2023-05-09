package programmers.level2;


/**
 * [문제명] 124 나라의 숫자
 * [풀이시간] / 15분
 * [한줄평] BFS 가 아니라 수학적으로 접근해야겠다는 것은 빨리 알아차렸지만 수식을 세우는데 시간이 좀 걸렸던 문제였다.
 * 나머지가 0인 경우에 대한 처리를 하는 부분에서 시간이 걸렸고 부모와 자식간의 관계를 수식으로 써보고 나서야 바로 깨달았다.
 * / 부모와 자식의 관계를 수식으로 세우면 쉽게 풀 수 있는 문제다.
 * 1_v1. 수학(성공)
 * 2_v1. 수학(성공)
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12899">문제</a>
 * <a href="https://postimg.cc/hzTvgLnh">그림 풀이</a>
 */
class Solution12899 {
    public static void main(String[] args) {
        // 1
        int n1 = 1;
        System.out.println(solution(n1));

        // 2
        int n2 = 2;
        System.out.println(solution(n2));

        // 4
        int n3 = 3;
        System.out.println(solution(n3));

        // 11
        int n4 = 4;
        System.out.println(solution(n4));
    }

    // 1_v1, 2_v1
    /**
     * @param n 50,000,000이하의 자연수
     * @return n을 124 나라에서 사용하는 숫자로 바꾼 값을 return
     */
    public static String solution(int n) {
        // 1 = 나머지 1
        // 2 = 나머지 2
        // 4 = 나머지 0
        int[] arr = {4, 1, 2};
        StringBuilder sb = new StringBuilder();
        while(n != 0) {
            int r = n % 3;  // 3으로 나눈 나머지
            sb.append(arr[r]);
            // 1. 나머지가 1 or 2 일 경우, 부모값 = 3으로 나눈 몫
            n /= 3;
            // 2. 나머지가 0 일 경우, 부모값 = 3으로 나눈 몫 - 1
            if(r == 0) n--;
        }
        return sb.reverse().toString();
    }
}