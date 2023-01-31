package programmers.level1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [문제명] 실패율
 * [풀이시간] (30분 + )
 * [한줄평]
 * v1. 정렬(실패 - 7, 9, 13 테스트 실패)
 * - 각 stage 번호별로 실패율을 모두 계산한 후에 정렬을 하는 방식으로 구현했는데, 3개의 테스트에서 실패했다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons42889/">문제</a>
 */
class Solution42889 {
    public static void main(String[] args) {
        //
        int[] stages1 = {2, 1, 2, 6, 2, 4, 3, 3};
        System.out.println(solution(5, stages1));
    }

    static class Stage {
        int num;    // 번호
        double fail;   // 실패율

        public Stage(int num, double fail) {
            this.num = num;
            this.fail = fail;
        }
    }

    public static List<Integer> solution(int N, int[] stages) {
        List<Integer> answer = new ArrayList<>();
        List<Stage> list = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            int a = 0;  // i번에 있는 사람수
            int b = 0;  // i번 이후에 있는 사람 수
            for(int n : stages) {
                if(i == n) {
                    a++;
                } else if(i < n) {
                    b++;
                }
            }
            list.add(new Stage(i, (double) a / b));
        }

        Collections.sort(list, (o1, o2) -> {
            // 2. 번호 오름차순
            if(o1.fail == o2.fail) {
                return o1.num - o2.num;
            }
            // 1. 실패율 내림차순
            return (o1.fail > o2.fail) ? -1 : 1;
        });

        for(Stage stage : list) {
            answer.add(stage.num);
        }
        return answer;
    }
}