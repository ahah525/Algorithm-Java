package programmers.level2;


/**
 * [문제명] 연속된 부분 수열의 합
 * [풀이시간] 21분 / (9분)
 * [한줄평] 투포인토 기초 문제였다.
 * 1_v1. 투포인터(성공)
 * 2_v1. 투포인터(실패 - 1, 16, 25 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/178870">문제</a>
 */
class Solution178870 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param sequence 수열을 나타내는 정수 배열
     * @param k 부분 수열의 합을 나타내는 정수
     * @return 위 조건을 만족하는 부분 수열의 시작 인덱스와 마지막 인덱스를 배열에 담아 return
     */
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int left = 0;
        int right = 0;
        int n = sequence.length;
        int sum = 0;    // 연속된 부분 수열의 합
        int len = sequence.length + 1;  // 연속된 부분 수열의 길이
        // 1/ left 가 배열의 범위를 벗어나기 전까지 반복
        while(left < n) {
            // System.out.println(left + "," + right);
            if(sum < k) {
                if(right == n) break;
                sum += sequence[right++];
            } else if(sum == k) {
                // 더 길이가 짧은 수열일 때만 기록
                if(len > right - left) {
                    answer[0] = left;
                    answer[1] = right - 1;
                    len = right - left;
                }
                if(right == n) break;
                sum += sequence[right++];
            } else {
                sum -= sequence[left++];
            }
        }
        return answer;
    }

    // 2_v1
    public int[] solution2(int[] sequence, int k) {
        int minLen = sequence.length;
        int[] answer = new int[2];
        int l = 0;
        int r = 0;
        int sum = 0;
        while(l <= r) {
            if(sum < k) {
                if(r == sequence.length) break;
                sum += sequence[r++];
            } else if(sum == k) {
                if(minLen > r - l) {
                    minLen = r - l;
                    answer[0] = l;
                    answer[1] = r - 1;
                }
                if(r == sequence.length) break;
                sum += sequence[r++];
            } else {
                sum -= sequence[l++];
            }
        }
        return answer;
    }
}