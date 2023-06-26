package programmers.level3;


import java.util.*;

/**
 * [문제명] 인사고과
 * [풀이시간] (33분)
 * [한줄평]
 * 1_v1. (실패 - 3,8,10~11,14,16,22)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/152995">문제</a>
 */
class Solution152995 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    int[] target;
    int targetSum;
    List<Integer> keys;
    Map<Integer, List<int[]>> map;
    public int solution(int[][] scores) {
        int answer = 0;
        target = new int[] {scores[0][0], scores[0][1]};
        targetSum = target[0] + target[1];
        // System.out.println(targetSum);
        // (합, [근무태도점수, 동료평가점수])
        map = new HashMap<>();
        for(int[] score : scores) {
            int sum = score[0] + score[1];
            if(!map.containsKey(sum))
                map.put(sum, new ArrayList<>());
            map.get(sum).add(score);
        }
        // System.out.println(map);
        keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        // System.out.println(keys);

        int i = keys.size() - 1;
        while(true) {
            int sum = keys.get(i);
            if(sum == targetSum) break;
            answer += map.get(sum).size();
            i--;
        }
//         Arrays.sort(scores, (o1, o2) -> (o2[0] + o2[1]) - (o1[0] + o1[1]));
//         for(int[] arr : scores) {
//             System.out.println(Arrays.toString(arr));
//         }
//         for(int[] arr : ) {

//         }
        if(isPossible()) return answer + 1;
        return -1;
    }

    public boolean isPossible() {
        int i = keys.size() - 1;
        while(true) {
            int sum = keys.get(i);
            if(sum < targetSum + 2) return true;
            for(int[] s : map.get(sum)) {
                if(s[0] > target[0] && s[1] > target[1]) return false;
            }
            i--;
        }
    }
}