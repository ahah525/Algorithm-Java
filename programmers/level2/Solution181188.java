package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] 요격 시스템
 * [풀이시간] 18분 / 14분
 * [한줄평] 범위를 보고 정렬을 해서 풀어야겠다는 아이디어를 떠올렸다. 아이디어를 생각해는데 시간이 더 걸리고 막상 푸는데는 얼마 안걸렸다.
 * / 정렬로 풀어야겠다고 떠올려서 쉽게 풀 수 있었던 문제다.
 * 1_v1. 그리디(성공)
 * [풀이] 시작점, 끝점 오름차순 정렬
 * 2_v1. 그리디(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/181188">문제</a>
 */
class Solution181188 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    public int solution(int[][] targets) {
        int answer = 0;
        // 1. 시작, 끝 지점 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        // 2. 이전 미사일들의 공통 범위의 끝점
        int e = targets[0][1];
        for(int i = 1; i < targets.length; i++) {
            // 현재 미사일의 시작점, 끝점
            int ns = targets[i][0];
            int ne = targets[i][1];
            // 3. 이전 미사일들의 공통 범위와 현재 미사일의 범위가 겹치는 부분이 있는 경우 -> 공통 범위의 끝점 갱신
            if(e > ns) {
                e = Math.min(e, ne);
                continue;
            }
            // 4. 이전 미사일들의 공통 범위와 현재 미사일의 범위가 겹치는 부분이 없으면 -> 새로운 요격 미사일 사용, 공통 범위의 끝점 = 현재 미사일의 끝점
            e = ne;
            answer++;
        }
        // 5. 마지막 범위의 미사일 1개 추가
        ++answer;
        return answer;
    }

    // 2_v1
    public int solution2(int[][] targets) {
        int answer = 0;
        // 1. 정렬
        Arrays.sort(targets, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        //
        int s = targets[0][0];
        int e = targets[0][1];
        for(int i = 1; i < targets.length; i++) {
            if(e <= targets[i][0]) {
                s = targets[i][0];
                e = targets[i][1];
                answer++;
            } else {
                s = targets[i][0];
                e = Math.min(e, targets[i][1]);
            }
        }
        return answer + 1;
    }
}