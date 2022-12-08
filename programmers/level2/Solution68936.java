package programmers.level2;


/**
 * [문제명] 쿼드압축 후 개수 세기
 * [한줄평] 전형적인 분할정복 문제로 쉽게 풀 수 있었던 문제였다.
 * v1. 분할정복(성공)
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/68936">문제</a>
 */
class Solution68936 {
    public static void main(String[] args) {
        // [4,9]
        int[][] arr1 = {
                {1,1,0,0},
                {1,0,0,0},
                {1,0,0,1},
                {1,1,1,1}
        };
        System.out.println(solution(arr1));

        // [10,15]
        int[][] arr2 = {
                {1,1,1,1,1,1,1,1},
                {0,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1},
                {0,1,0,0,1,1,1,1},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,1,0,0,1},
                {0,0,0,0,1,1,1,1}
        };
        System.out.println(solution(arr2));
    }

    /**
     * @param arr 0과 1로 이루어진 2^n x 2^n 크기의 2차원 정수 배열(arr의 행의 개수는 1 이상 1024 이하)
     * @return arr을 압축했을 때, 배열에 최종적으로 남는 0의 개수와 1의 개수를 배열에 담아서 return
     */
    public static int[] solution(int[][] arr) {
        int[] answer = {0, 0};
        divide(0, 0, arr.length, arr, answer);

        return answer;
    }

    /**
     * @param x 시작 x 좌표
     * @param y 시작 y 좌표
     * @param n 탐색 길이
     * @param arr 2차원 정수 원본 배열
     * @param answer (0의 개수, 1의 개수)
     */
    public static void divide(int x, int y, int n, int[][] arr, int[] answer) {
        int target = arr[x][y]; // 시작칸 값
        // 1. 크기가 1이면 해당 칸의 숫자 개수 1 증가 후 리턴
        if(n == 1) {
            answer[target]++;
            return;
        }
        // 2. 모든 수가 같은 수인지 검사
        boolean isSame = true;
        d1:
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(arr[x + i][y + j] != target) {
                    isSame = false;
                    break d1;
                }
            }
        }
        // 3. 모두 같은 수이면 해당 칸의 숫자 개수 1 증가 후 리턴
        if(isSame) {
            answer[target]++;
            return;
        }
        // 4. 하나라도 다르면 4분할 진행
        n /= 2;
        divide(x, y, n, arr, answer);
        divide(x, y + n, n, arr, answer);
        divide(x + n, y, n, arr, answer);
        divide(x + n, y + n, n, arr, answer);
    }
}