package programmers.level2;


/**
 * [문제명] 쿼드압축 후 개수 세기
 * [풀이시간] / 14분
 * [한줄평] 전형적인 분할정복 문제로 쉽게 풀 수 있었던 문제였다. / 쉬운 문제였지만 오랜만에 푸니까 생각보다 시간이 걸렸던 문제였다.
 * 1_v1. 분할정복(성공)
 * 2_v1. 분할정복(성공)
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

    // 1_v1, 2_v1
    static int[] answer;
    /**
     * @param arr 0과 1로 이루어진 2^n x 2^n 크기의 2차원 정수 배열(arr의 행의 개수는 1 이상 1024 이하)
     * @return arr을 압축했을 때, 배열에 최종적으로 남는 0의 개수와 1의 개수를 배열에 담아서 return
     */
    public static int[] solution(int[][] arr) {
        answer = new int[2];
        divide(0, 0, arr.length, arr);
        return answer;
    }

    /**
     * @param x 시작 x 좌표
     * @param y 시작 y 좌표
     * @param n 탐색 길이
     * @param arr 2차원 정수 원본 배열
     */
    public static void divide(int x, int y, int n, int[][] arr) {
        int target = arr[x][y]; // 시작칸 값
        // 1. 크기가 1이면 해당 칸의 숫자 개수 1 증가 후 리턴
        if(n == 1) {
            answer[target]++;
            return;
        }
        // 2. 모든 수가 같은 수인지 검사
        boolean isSame = true;
        l1:
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(arr[x + i][y + j] != target) {
                    isSame = false;
                    break l1;
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
        divide(x, y, n, arr);
        divide(x, y + n, n, arr);
        divide(x + n, y, n, arr);
        divide(x + n, y + n, n, arr);
    }
}