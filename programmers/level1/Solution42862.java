package programmers.level1;


/**
 * [문제명] 체육복
 * [풀이시간] (20분)
 * [한줄평]
 * 1_v1. 그리디(실패 - 11, 13, 14 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42862">문제</a>
 */
class Solution42862 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
        // 도난 당한 학생 명단
        boolean[] losts = new boolean[n + 1];
        for(int num : lost) {
            losts[num] = true;
        }
        // 다른 학생에게 빌려줄 수 있는지 여부
        boolean[] reserves = new boolean[n + 1];
        for(int num : reserve) {
            // 도난당하지 않은 학생만 빌려줄 수 있음
            // if(!losts[num])
            reserves[num] = true;
        }

        for(int num : lost) {
            // 여벌 옷을 가져왔는데 도난당했다면 패스
            if(reserves[num]) {
                reserves[num] = false;
                continue;
            }
            if(num > 1 && reserves[num - 1]) {
                reserves[num - 1] = false;
            } else if(num < n - 1 && reserves[num + 1]) {
                reserves[num + 1] = false;
            } else {
                // 빌려주지 못한 경우
                answer--;
            }
        }
        return answer;
    }
}