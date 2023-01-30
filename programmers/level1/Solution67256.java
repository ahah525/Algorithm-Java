package programmers.level1;


/**
 * [문제명] [카카오 인턴] 키패드 누르기
 * [풀이시간] 28분
 * [한줄평] 문제 설명대로 구현하기만 하면 되는 나름 간단한 문제였다. static 하게 좌표값을 미리 저장해두는 방법도 있지만 내가 푼 방식이 더 시간이 빨랐다.
 * v1. 구현(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/67256">문제</a>
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/67256/solution_groups?language=java">다른 풀이</a>
 */
class Solution67256 {
    public static void main(String[] args) {
        // "LRLLLRLLRRL"
        int[] numbers1 = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        System.out.println(solution(numbers1, "right"));

        // "LRLLRRLLLRR"
        int[] numbers2 = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        System.out.println(solution(numbers2, "left"));

        // "LLRLLRLLRL"
        int[] numbers3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println(solution(numbers3, "right"));
    }

    /**
     * @param numbers 순서대로 누를 번호가 담긴 배열
     * @param hand 왼손잡이인지 오른손잡이인 지를 나타내는 문자열
     * @return 각 번호를 누른 엄지손가락이 왼손인 지 오른손인 지를 나타내는 연속된 문자열 형태로 return
     */
    public static String solution(int[] numbers, String hand) {
        // 1. 오른손잡이 여부 검사(아래에서 왼손/오른손잡이를 검사할 때 equals 연산 반복을 줄이기 위함)
        boolean isRight = hand.equals("right") ? true : false;
        StringBuilder sb = new StringBuilder();
        // 2. * -> 10, # -> 12 로 치환하여 초기 위치 세팅
        int l = 10; // 현재 누르고 있는 숫자(*)
        int r = 12; // 현재 누르고 있는 숫자(#)
        // 3. 순서대로 해당 번호를 어떤 손가락으로 누를지 판별
        for(int n : numbers) {
            // 0 -> 11 로 치환(거리 계산을 위함)
            if(n == 0) n = 11;
            if(n % 3 == 1) {
                // 3-1. 1, 4, 7 -> 왼손
                sb.append("L");
                l = n;
            } else if(n % 3 == 2) {
                // 3-2. 2, 5, 8, 0 -> 추가 검사
                // 4. 해당 번호에서 거리 가까운 손으로
                int nx = n / 3;         // 해당 번호의 x좌표
                int ny = (n + 2) % 3;   // 해당 번호의 y좌표
                int dl = Math.abs((l - 1) / 3 - nx) + Math.abs((l + 2) % 3 - ny);   // (해당 번호 ~ 왼손) 거리
                int dr = Math.abs((r - 1) / 3 - nx) + Math.abs((r + 2) % 3 - ny);   // (해당 번호 ~ 오른손) 거리
                if(dl < dr) {
                    sb.append("L");
                    l = n;
                } else if(dl > dr) {
                    sb.append("R");
                    r = n;
                } else {
                    // 5. 오른손잡이 -> 오른손, 왼손잡이 -> 왼손
                    if(isRight){
                        sb.append("R");
                        r = n;
                    } else {
                        sb.append("L");
                        l = n;
                    }
                }
            } else {
                // 3-3. 3, 6, 9 -> 오른손
                sb.append("R");
                r = n;
            }
        }
        return sb.toString();
    }
}