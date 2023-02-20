package programmers.level1;


import java.util.Arrays;

/**
 * [문제명] 체육복
 * [풀이시간] (20분)
 * [한줄평]
 * 1_v1. 그리디(실패 - 11, 13, 14 실패)
 * 1_v2. 그리디(실패 - 11 실패)
 * - Arrays.sort() 추가
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42862">문제</a>
 */
class Solution42862 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
        // 도난당한 학생번호 오름차순 정렬
        Arrays.sort(lost);
        // 여벌 체육복이 필요한 학생들
        boolean[] losts = new boolean[n + 1];
        for(int num : lost) {
            losts[num] = true;
        }
        // 다른 학생에게 빌려줄 수 있는지 여부
        boolean[] reserves = new boolean[n + 1];
        for(int num : reserve) {
            if(!losts[num]) {
                // 도난당하지 않았고 여벌옷이 있으면
                reserves[num] = true;
            } else {
                // 도난당한 학생인데 여벌옷이 있으면
                losts[num] = false;
            }
        }

        for(int num : lost) {
            // 자기옷으로 대체했으면
            if(!losts[num]) {
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