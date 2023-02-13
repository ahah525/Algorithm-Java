package programmers.level1;


import java.util.Stack;

/**
 * [문제명] 크레인 인형뽑기 게임
 * [풀이시간] 18분
 * [한줄평] 문제 설명대로 구현하기만 하고 stack 으로 풀 수 있는 아주 쉬운 문제였다.
 * 1_v1. stack(성공)
 * - 바구니 용 Stack, 기계용 Stack[] 으로 풀었다. 기계는 굳이 Stack 을 안쓰고 2차원 배열 값을 활용한 풀이가 많았다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/64061">문제</a>
 */
class Solution64061 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param board 게임 화면의 격자의 상태가 담긴 2차원 배열
     * @param moves 인형을 집기 위해 크레인을 작동시킨 위치가 담긴 배열
     * @return 크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수
     */
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int n = board.length;
        Stack<Integer> basket = new Stack<>();      // 바구니용
        Stack<Integer>[] boards = new Stack[n];     // 기계용
        for(int j = 0; j < n; j++) {
            boards[j] = new Stack<>();
            for(int i = n - 1; i >= 0; i--) {
                if(board[i][j] == 0) {
                    break;
                }
                boards[j].push(board[i][j]);
            }
        }
        for(int move : moves) {
            // 기계에 인형이 없으면 패스
            if(boards[move - 1].size() == 0) {
                continue;
            }
            // 기계의 맨위에서 꺼내기
            int v = boards[move - 1].pop();
            // 바구니 맨위에 값과 똑같으면 꺼내고 카운팅
            if(basket.size() != 0 && v == basket.peek()) {
                basket.pop();
                answer += 2;    // 한번에 2개씩 사라짐
                continue;
            }
            basket.push(v);
        }
        return answer;
    }
}