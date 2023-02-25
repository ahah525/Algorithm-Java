package programmers.level2;


/**
 * [문제명] 뒤에 있는 큰 수 찾기
 * [풀이시간] 4분
 * [한줄평]
 * 1_v1. 2중 for문(실패-20, 21, 22, 23 시간초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/154539">문제</a>
 */
class Solution154539 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param numbers 정수로 이루어진 배열(4 ≤ numbers의 길이 ≤ 1,000,000)
     * @return 모든 원소에 대한 뒷 큰수들을 차례로 담은 배열
     * - 뒷큰수 = 배열 의 각 원소들에 대해 자신보다 뒤에 있는 숫자 중에서 자신보다 크면서 가장 가까이 있는 수
     */
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];

        for(int i = 0; i < n; i++) {
            int idx = -1;
            for(int j = i + 1; j < n; j++) {
                if(numbers[i] < numbers[j]) {
                    idx = j;
                    break;
                }
            }
            if(idx == -1)
                answer[i] = -1;
            else
                answer[i] = numbers[idx];
        }
        return answer;
    }
}