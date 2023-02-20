package programmers.level1;


import java.util.Arrays;

/**
 * [문제명] 체육복
 * [풀이시간] 37분(20분 + 15분 + 2분)
 * [한줄평] 조건(여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다)을 잘 신경쓰지 않아서 푸는데 너무 오래걸렸던 문제였다.
 * 1_v1. 그리디(실패 - 11, 13, 14 실패)
 * 1_v2. 그리디(실패 - 11 실패)
 * - 입력이 오름차순 정렬된 상태라는 보장이 없음 -> Arrays.sort() 추가
 * 1_v3. 그리디(성공)
 * - reserves[] 배열의 마지막 인덱스가 (n - 1) 이 아닌 n 이라는 것을 간과함 -> 마지막
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42862">문제</a>
 */
class Solution42862 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param n 전체 학생의 수
     * @param lost 체육복을 도난당한 학생들의 번호가 담긴 배열
     * @param reserve 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열
     * @return 체육수업을 들을 수 있는 학생의 최댓값
     */
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
        // 1. 도난당한 학생번호 오름차순 정렬
        Arrays.sort(lost);
        // 2. 학생이 다른 학생에게 체육복을 빌려야하는지 여부
        boolean[] losts = new boolean[n + 1];
        // 도난 당한 학생은 무조건 다른 학생에게 빌려야함
        for(int num : lost) {
            losts[num] = true;
        }
        // 3. 다른 학생에게 여벌 체육복을 빌려줄 수 있는지 여부
        boolean[] reserves = new boolean[n + 1];
        for(int num : reserve) {
            if(!losts[num]) {
                // 본인이 체육복을 도난당하지 않았고 여벌 체육복이 있으면 -> 다른 학생에게 빌려줄 수 있음
                reserves[num] = true;
            } else {
                // 본인이 체육복을 도난당했는데 여벌 체육복이 있으면 -> 본인은 다른 학생에게 빌릴 필요가 없음(다른 학생에게 빌려줄 수 없음)
                losts[num] = false;
            }
        }
        // 4. 체육복을 빌려야하는 학생들 중에 체육복을 빌릴 수 있는지 검사
        for(int num : lost) {
            // 1. 다른 학생에게 빌릴 필요가 없는 경우(도난은 당했지만 본인 여벌옷이 있음)
            if(!losts[num])
                continue;
            if(num > 1 && reserves[num - 1]) {
                // 왼쪽 학생에게 빌리기
                reserves[num - 1] = false;
            } else if(num < n && reserves[num + 1]) {
                // 오른쪽 학생에게 빌리기
                reserves[num + 1] = false;
            } else {
                // 왼쪽/오른쪽 학생에게 빌리지 못한 경우
                answer--;
            }
        }
        return answer;
    }
}