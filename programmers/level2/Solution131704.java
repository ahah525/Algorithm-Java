package programmers.level2;


import java.util.Stack;

/**
 * [문제명] 택배상자
 * [풀이시간] 50분(25분 + 25분) / 35분(30분 + 5분)
 * [한줄평] 문제 이해하기가 조금 까다로웠고 생각보다 푸는데 더 어려웠던 문제였다.. 다음에 한번 더 풀어볼만한 문제다.
 * 1_v1. Stack(실패 - 6, 7, 9, 10 시간초과)
 * 1_v2. Stack(성공)
 * 2_v1. Stack(실패 - 6~10 실패)
 * 2_v2. Stack(성공)
 * [해결법] 트럭에 싣는 경우에도 container++ 하기
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/131704">문제</a>
 * @See <a href="https://velog.io/@biny22/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%83%9D%EB%B0%B0%EC%83%81%EC%9E%90">문제 설명</a>
 */
class Solution131704 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    public int solution(int[] order) {
        int idx = 0;    // 현재 실어야 하는 택배가 몇 개째인지
        Stack<Integer> stack = new Stack<>();
        // i = 컨베이너 벨트에서 내리는 택배 번호
        // order[idx] = 현재 실어야하는 택배 번호
        for(int i = 1; i <= order.length; i++) {
            // 1. 컨베이너 벨트에서 내린 택배를 실을 수 있는지 검사
            // 현재 실어야하는 택배 번호 == 컨베이너 벨트에서 내리는 택배 번호 -> 택배를 보조 컨베이너 벨트로 이동
            if(order[idx] != i) {
                stack.push(i);
                continue;
            }
            // 현재 실어야하는 택배 번호 != 컨베이너 벨트에서 내리는 택배 번호 -> 컨베이너 벨트에서 택배 싣기
            idx++;
            // 2. 보조 컨베이너에서 내린 택배를 실을 수 있는지 검사
            // 보조 컨테이어 벨트에 있는 택배 중 실을 수 있는 택배 모두 싣기
            while(!stack.isEmpty()) {
                if(order[idx] != stack.peek())
                    // 현재 실어야 하는 택배 번호 != 보조 컨베이너 벨트에서 꺼낸 택배 번호 -> 택배를 더이상 실을 수 없으므로 break
                    break;
                // 현재 실어야 하는 택배 번호 != 보조 컨베이너 벨트에서 꺼낸 택배 번호 -> 보조 컨테이어 벨트에서 택배 싣기
                stack.pop();
                idx++;
            }
        }
        return idx;
    }

    // 2_v1
    public int solution2(int[] order) {
        int answer = 0;
        // 보조 컨베이어
        Stack<Integer> stack = new Stack<>();
        int container = 1; // 컨테이너 맨 앞에 있는 박스 번호
        // n = 트럭에 실어야 하는 박스 번호
        for(int n : order) {
            // 트럭에 실어야 하는 박스 번호 < 컨테이너 맨 앞 박스 번호 -> 컨테이너에 원하는 박스가 있음
            while(container < n) {
                stack.push(container);
                container++;
            }
            if(n == container) {
                // 트럭에 실어야 하는 박스 번호 == 컨테이너 맨 앞 박스 번호 -> 트럭에 싣기
                container++;
                answer++;
            } else if(n < container) {
                // 트럭에 실어야 하는 박스 번호 > 컨테이너 맨 앞 박스 번호 -> 보조 컨베이어벨트에 원하는 박스가 있음
                if(n == stack.peek()) {
                    stack.pop();
                    answer++;
                } else {
                    break;
                }
            }
        }
        return answer;
    }
}