package programmers.level1;


import java.util.Arrays;

/**
 * [문제명] 로또의 최고 순위와 최저 순위
 * [풀이시간] 13분
 * [한줄평] 아주 간단한 문제였는데, 어떻게 하면 더 효율적으로 풀 수 있을지 생각하다가 조금 더 걸린 것 같다.
 * v1. (성공)
 * 최고 순위 = 알아볼 수 없는 번호가 모두 맞은 경우
 * 최저 순위 = 알아볼 수 없는 번호가 모두 틀린 경우
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/">문제</a>
 */
class Solution77484 {
    public static void main(String[] args) {
        // [3, 5]
        int[] lottos1 = {44, 1, 0, 0, 31, 25};
        int[] winNums1 = {31, 10, 45, 1, 6, 19};
        System.out.println(Arrays.toString(solution(lottos1, winNums1)));

        // [1, 6]
        int[] lottos2 = {0, 0, 0, 0, 0, 0};
        int[] winNums2 = {38, 19, 20, 40, 15, 25};
        System.out.println(Arrays.toString(solution(lottos2, winNums2)));

        // [1, 1]
        int[] lottos3 = {45, 4, 35, 20, 3, 9};
        int[] winNums3 = {20, 9, 3, 45, 4, 35};
        System.out.println(Arrays.toString(solution(lottos3, winNums3)));
    }

    public static int[] solution(int[] lottos, int[] winNums) {
        int[] answer = new int[2];  // 최고 순위, 최저 순위
        int same = 0;   // 맞힌 개수
        int zero = 0;   // 알아볼 수 없는 번호 개수
        int[] ranking = {6, 6, 5, 4, 3, 2, 1};  // 맞힌 개수별 순위
        // 1. 맞힌 개수와 알아볼 수 없는 번호 개수 세기
        for(int lotto : lottos) {
            if(lotto == 0) {
                zero++;
                continue;
            }
            for(int winNum : winNums) {
                if(lotto == winNum) {
                    same++;
                    break;
                }
            }
        }
        // 2. 최고 순위, 최저 순위 계산
        answer[0] = ranking[same + zero];
        answer[1] = ranking[same];
        return answer;
    }
}