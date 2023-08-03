package programmers.level1;


import java.util.*;

/**
 * [문제명] 실패율
 * [풀이시간] 40분(30분 + 10분) / 38분 / 1시간 10분 / 31분
 * [한줄평] 30분 내에 충분히 풀 수 있는 문제였는데, 생각보다 시간이 걸렸다. 문제 조건을 잘 확인하자!!
 * / 처음에 PriorityQueue 로 풀려고 했는데 이상하게 계속 정렬이 제대로 안돼서 결국 리스트를 정렬해서 해결했다. 아직까지 무슨이유에서인지 파악하지 못했다.
 * / 예전에 풀었던 문제임에도 푸는데 너무 오래걸렸기 때문에 다시 꼭 풀어봐야겠다.
 * / 이전에 PriorityQueue로 정렬에 실패했던 이유는 for-each문으로 탐색을 했기 때문인 것 같다.
 * 1_v1. 정렬(실패 - 7, 9, 13 테스트 실패)
 * [해결] "스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0 으로 정의한다." 라는 조건 고려
 * 1_v2. 정렬(성공)
 * [풀이] Collections.sort()로 list 정렬
 * 2_v1. 정렬(성공)
 * [풀이] Arrays.sort(stages)로 원본배열 오름차순 정렬 후 로직 구현
 * 3_v1. 정렬(성공)
 * [풀이] 2_v1 동일
 * 4_v1. 정렬(성공) -> 빠름
 * [풀이] PriorityQueue로 정렬
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42889">문제</a>
 */
class Solution42889 {
    public static void main(String[] args) {
        // [3,4,2,1,5]
        int[] stages1 = {2, 1, 2, 6, 2, 4, 3, 3};
        System.out.println(solution(5, stages1));

        // [4,1,2,3]
        int[] stages2 = {4,4,4,4,4};
        System.out.println(solution(4, stages2));
    }

    static class Stage {
        int num;        // 번호
        double fail;   // 실패율

        public Stage(int num, double fail) {
            this.num = num;
            this.fail = fail;
        }
    }

    // 1_v2
    /**
     * @param N 전체 스테이지의 개수
     * @param stages 게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열
     * @return 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열
     */
    public static List<Integer> solution(int N, int[] stages) {
        List<Integer> answer = new ArrayList<>();
        // 1. 각 스테이지 번호별 실패율 계산
        List<Stage> list = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            int a = 0;  // i번 스테이지에 있는 사람수
            int b = 0;  // i번 이상의 스테이지에 있는 사람수
            double c = 0; // 실패율 = 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수 = a / b
            for(int n : stages) {
                if(i == n) {
                    a++;
                    b++;
                } else if(i < n) {
                    b++;
                }
            }
            // b 가 0이 아닐 때만 연산해야함(b가 0일때는 런타임 오류)
            if(b != 0)
                c = (double) a / b;
            list.add(new Stage(i, c));
        }
        // 2. 정렬
        Collections.sort(list, (o1, o2) -> {
            // 2. 번호 오름차순
            if(o1.fail == o2.fail) {
                return o1.num - o2.num;
            }
            // 1. 실패율 내림차순
            return (o1.fail > o2.fail) ? -1 : 1;
        });
        // 3. 정렬된 스테이지 번호 리턴
        for(Stage stage : list) {
            answer.add(stage.num);
        }
        return answer;
    }

    // 2_v1
    public int[] solution2(int N, int[] stages) {
        int[] answer = new int[N];
        List<Stage> list =new ArrayList<>();
        // 1. 정렬
        Arrays.sort(stages);
        // 2. 스테이지별 실패율 계산
        int clear = stages.length; // 스테이지에 도달한 플레이어 수
        int noClear; // 스테이지에 도달했으나 아직 클리어하지 못한 플레이어 수
        int j = 0;
        for(int i = 1; i <= N; i++) {
            noClear = 0;
            while(j < stages.length && i == stages[j]) {
                noClear++;
                j++;
            }
            double fail = 0;
            // 스테이지에 도달한 유저가 있는 경우
            if(noClear > 0) {
                fail = (double) noClear / clear;
                clear -= noClear;
            }
            list.add(new Stage(i, fail));
        }
        // 3. 실패율 내림차순, 번호 오름차순 정렬
        Collections.sort(list, (o1, o2) -> {
            if(o1.fail == o2.fail) return o1.num - o2.num;
            return o2.fail > o1.fail ? 1 : -1;
        });
        int i = 0;
        for(Stage stage : list) {
            answer[i] = stage.num;
            i++;
        }
        return answer;
    }

    // 3_v1
    public int[] solution3(int N, int[] stages) {
        // 1. 오름차순 정렬
        Arrays.sort(stages);
        // 2. 각 스테이지의 실패율 계산
        List<Stage> list = new ArrayList<>();
        int idx = 0;
        for(int i = 1; i <= N; i++) {
            // 3. 스테이지에 도달한 플레이어수 구하기
            int dodal = stages.length - idx;
            // 4. 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 구하기
            int notClear = 0;
            while(idx < stages.length && stages[idx] == i) {
                notClear++;
                idx++;
            }
            // 5. 실패율 계산 및 리스트에 추가
            double fail = (dodal == 0) ? 0 : (double) notClear / dodal;
            list.add(new Stage(i, fail));
        }
        // 6. 실패율 내림차순, 번호 오름차순 정렬
        Collections.sort(list, (o1, o2) -> {
            if(o1.fail == o2.fail) return o1.num - o2.num;
            return (o1.fail > o2.fail) ? -1 : 1;
        });
        // 7. 리스트를 배열로 변환
        int[] answer = new int[N];
        for(int i = 0; i < N; i++) {
            answer[i] = list.get(i).num;
        }
        return answer;
    }

    // 4_v1

}