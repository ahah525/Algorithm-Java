package programmers.level3;


import java.util.*;

/**
 * [문제명] 인사고과
 * [풀이시간] 1시간 45분(33분+1시간 12분) / (50분)
 * [한줄평] 결국 풀이를 보고 해결했고 인센티브를 받을 수 있는지 여부를 확인하기 위한 로직을 이해하기가 어려웠기 떄문에 꼭 다시 풀어봐야겠다.
 * 1_v1. (실패 - 3,8,10~11,14,16,22)
 * 2_v1. 정렬(성공)
 * [풀이] 근무 태도 점수를 내림차순 정렬, 동료 평가 점수를 오름차순 정렬하여 인센티브를 받을 수 있는지 여부를 확인한다.
 * 3_v1. (실패 - 11 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/152995">문제</a>
 * @See <a href="https://velog.io/@qodlstjd12/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%9D%B8%EC%82%AC%EA%B3%A0%EA%B3%BC-java">풀이 참고</a>
 */
class Solution152995 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2
    public int solution(int[][] scores) {
        int answer = 1;
        // 1. 완호 점수, 합 저장
        int targetA = scores[0][0];
        int targetB = scores[0][1];
        int targetSum = targetA + targetB;
        // 2. a 내림차순 정렬, b 오름차순 정렬
        Arrays.sort(scores, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o2[0] - o1[0];
        });
        // 3. 각 사원의 점수가 인센티브를 받을 수 있는지 검사
        int maxB = 0; // b의 최댓값
        for(int[] score : scores) {
            int a = score[0];
            int b = score[1];
            // 현재 a는 이전 a보다 작거나 같음, 현재 b가 지금까지 b의 최댓값보다
            if(b < maxB) {
                // 현재 b < 지금까지 b의 최댓값 -> 현재 a는 이전 a보다 작음 -> 인센티브X
                if(a == targetA && b == targetB) {
                    // a, b가 완호의 점수이면
                    return -1;
                }
            } else {
                // 현재 b >= 지금까지 b의 최댓값 -> 인센티브O
                maxB = b;
                // 현재 사원의 점수 > 완호 점수 -> 카운트
                if(a + b > targetSum) answer++;
            }
        }
        return answer;
    }

    // 3_v1
    public int solution2(int[][] scores) {
        int answer = 1;
        int[] target = new int[] {scores[0][0], scores[0][1]};
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o2[0] + o2[1]) - (o1[0] + o1[1]));
        //
        Arrays.sort(scores, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o2[0] - o1[0];
        });
        // 이전 근무태도 점수
        // int prevA = score[0][0];
        boolean start = true;
        //
        int maxB = -1;
        int i = 1;
        pq.add(new int[] {scores[0][0], scores[0][1]});
        while(i < scores.length) {
            if(scores[i] != scores[i - 1]) {
                maxB = Math.max(maxB, scores[i - 1][1]);
            }
            if(maxB <= scores[i][1]) {
                pq.add(new int[] {scores[i][0], scores[i][1]});
            }
            i++;
        }

        while(!pq.isEmpty()) {
            int[] score = pq.poll();
            if(score[0] == target[0] && score[1] == target[1]) return answer;
            answer++;
        }
        return -1;
    }
}