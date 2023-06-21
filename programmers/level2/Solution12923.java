package programmers.level2;


/**
 * [문제명] 숫자 블록
 * [풀이시간] (17분)
 * [한줄평]
 * 1_v1. (실패 - 정확성 1~14 실패, 효율성 1~6 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12923">문제</a>
 */
class Solution12923 {
    public static void main(String[] args) {
        //
        System.out.println();
    }
    // 1_v1
    /**
     * @param begin 시작점
     * @param end 끝점
     * @return 그 구간에 깔려 있는 블록의 숫자 배열
     */
    public int[] solution(long begin, long end) {
        int len = (int) (end - begin + 1);
        int[] answer = new int[len];
        for(int i = 1; i < len; i++) {
            answer[i] = getNum((int) (begin + i));
        }
        return answer;
    }

    // 원본 값을 제외하고 제일 큰 약수
    public int getNum(int n) {
        int i = 2;
        double sqrt = Math.sqrt(n);
        while(i <= sqrt) {
            if(n % i == 0) return n / i;
            i++;
        }
        return 1;
    }
}