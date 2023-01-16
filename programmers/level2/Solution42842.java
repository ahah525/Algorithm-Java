package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] 카펫
 * [풀이시간] 20분
 * [한줄평] 10분 내로 풀 수 있는 쉬운 문제였는데, 생각보다 오래걸렸다.. 쉬운문제에서 무조건 시간단축을 하자!
 * v1. 수학(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42842">문제</a>
 */
class Solution42842 {
    public static void main(String[] args) {
        // [4, 3]
        System.out.println(Arrays.toString(solution(10, 2)));

        // [3, 3]
        System.out.println(Arrays.toString(solution(8, 1)));

        // [8, 6]
        System.out.println(Arrays.toString(solution(24, 24)));
    }

    /**
     * @param brown 갈색 격자의 수(8 이상 5,000 이하인 자연수)
     * @param yellow 노란색 격자의 수(1 이상 2,000,000 이하인 자연수)
     * @return 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return
     * 카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.
     */
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];  // 가로, 세로
        int xy = brown + yellow;    // 카펫의 격자 개수
        // x: 가로길이, y: 세로길이
        for(int y = 3; y <= Math.sqrt(xy); y++) {
            if(xy % y == 0) {
                int x = xy / y;
                if(2 * (x + y) - 4 == brown) {
                    answer[0] = x;
                    answer[1] = y;
                    break;
                }
            }
        }
        return answer;
    }
}