package programmers.level3;


/**
 * [문제명] 풍선 터트리기
 * [풀이시간] 45분 / 17분 / 8분
 * [한줄평] N의 크기를 보고 완전탐색으로 풀 수 있는 문제는 아니라 생각했는데 어떤 식으로 접근해야할지 몰라서 결국 풀이를 보고 해결했던 문제다.
 * / 아이디어만 떠올리면 쉽게 풀 수 있는 문제였다.
 * / 누적합 아이디어로 풀면 쉽게 풀 수 있는 문제다.
 * 1_v1. 구현(성공)
 * [경우의 수] 현재값이 살아남을 수 있는 경우의 수
 * 1. 왼쪽 구간의 최솟값과 오른쪽 구간의 최솟값이 모두 현재값보다 작은 경우, 불가능(작은 값 삭제 2번을 해야 현재값을 유지할 수 있음)
 * [-1, 0, -2] -> 불가능
 * 2. 왼쪽 구간의 최솟값 혹은 오른쪽 구간의 최솟값 중 하나가 현재값보다 큰 경우, 가능(작은 값 삭제 1번 사용)
 * [-1, 0, 2] -> 가능
 * [1, 0, -2] -> 가능
 * 3. 왼쪽 구간의 최솟값과 오른쪽 구간의 최솟값이 모두 현재값보다 큰 경우, 가능(작은 값 삭제 사용X)
 * [1, 0, 2] -> 가능
 * 2_v1. 구현(성공)
 * [풀이] 1_v1 동일
 * 3_v1. 구현(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/68646">문제</a>
 * @See <a href="https://moonsbeen.tistory.com/180">풀이 참고</a>
 */
class Solution68646 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1, 3_v1
    /**
     * @param a 일렬로 나열된 풍선들의 번호가 담긴 배열
     * @return 풍선들을 1개만 남을 때까지 터트렸을 때 최후까지 남기는 것이 가능한 풍선들의 개수
     */
    public int solution(int[] a) {
        int answer = 0;
        // 1. i번째 기준 왼쪽 구간의 최솟값 구하기
        int[] minL = new int[a.length];
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < a.length; i++) {
            minL[i] = min;
            min = Math.min(min, a[i]);
        }
        // 2. i번째 기준 오른쪽 구간의 최솟값 구하기
        int[] minR = new int[a.length];
        min = Integer.MAX_VALUE;
        for(int i = a.length - 1; i >= 0; i--) {
            minR[i] = min;
            min = Math.min(min, a[i]);
        }
        // 3. 각 값이 생존할 수 있는지 검사
        for(int i = 0; i < a.length; i++) {
            // 왼쪽 구간의 최솟값과 오른쪽 구간의 최솟값이 현재 값보다 작은 경우 -> 현재 값은 생존 가능(작은 값 삭제는 1번만 되기 때문에)
            if(minL[i] < a[i] && minR[i] < a[i]) continue;
            answer++;
        }
        return answer;
    }
}