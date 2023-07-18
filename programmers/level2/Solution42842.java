package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] 카펫
 * [풀이시간] 20분 / 17분 / 5분
 * [한줄평] 10분 내로 풀 수 있는 쉬운 문제였는데, 생각보다 오래걸렸다.. 쉬운문제에서 무조건 시간단축을 하자!
 * / 방정식만 세우면 쉽게 풀 수 있는 문제다.
 * / 더 이상 안풀어봐도 될 쉬운 문제다.
 * 1_v1. 수학(성공)
 * 2_v1. 수학(성공)
 * 3_v1. 완전탐색, 수학(성공)
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

    // 1_v1
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

    // 2_v1
    public int[] solution2(int brown, int yellow) {
        // 가로, 세로
        int sum = brown / 2 + 2; // 가로 세로 길이의 합
        int n = 3;          // 세로 길이
        int m = sum - n;    // 가로 길이
        while(true) {
            if((n - 2) * (m - 2) == yellow) break;
            n++;
            m--;
        }
        return new int[] {m, n};
    }

    // 3_v1
    public int[] solution3(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown + yellow;
        for(int h = 3; h <= Math.sqrt(sum); h++) {
            if(sum % h != 0) continue;
            int w = sum / h;
            if((w - 2) * (h - 2) == yellow) {
                answer[0] = w;
                answer[1] = h;
                break;
            }
        }
        return answer;
    }
}