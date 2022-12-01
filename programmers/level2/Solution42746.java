package programmers.level2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [문제명] 가장 큰 수
 * [한줄평]
 * v1. (실패-11)
 * <a href=""></a>
 */
class Solution42746 {
    public static void main(String[] args) {
        // "6210"
        int[] numbers1 = {6, 10, 2};
        System.out.println(solution(numbers1));

        // "9534330"
        int[] numbers2 = {3, 30, 34, 5, 9};
        System.out.println(solution(numbers2));
    }

    /**
     *
     * @param numbers 0 또는 양의 정수가 담긴 배열
     * - 길이는 1 이상 100,000 이하
     * - 원소는 0 이상 1,000 이하
     * @return 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return
     */
    public static String solution(int[] numbers) {
        List<String> list = new ArrayList<>();
        for(int n : numbers) {
            list.add(Integer.toString(n));
        }
        Collections.sort(list);
        Collections.sort(list, (o1, o2) -> {
            // 1. 2번째 수가 1번째 수를 포함하고 있으면
            if(o2.contains(o1) || o1.contains(o2)) {
                int n1 = Integer.parseInt(o1 + o2);
                int n2 = Integer.parseInt(o2 + o1);
                return n2 - n1;
            }
            // 2. 2번째 수가 1번쨰 수를 포함하고 있지 않으면
            return o2.compareTo(o1);
        });
        StringBuilder sb = new StringBuilder();
        for(String s : list) {
            System.out.println(s);
            sb.append(s);
        }
        return sb.toString();
    }
}