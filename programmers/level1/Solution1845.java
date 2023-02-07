package programmers.level1;


import java.util.HashSet;
import java.util.Set;

/**
 * [문제명] 포켓몬
 * [풀이시간] 10분 / 3분
 * [한줄평] Set 을 사용해 풀 수 있는 아주 쉬운 문제였다.
 * 1_v1. Set(성공)
 * 2_v1. Set(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/1845">문제</a>
 */
class Solution1845 {
    public static void main(String[] args) {
        // 2
        int[] nums1 = {3,1,2,3};
        System.out.println(solution(nums1));

        // 3
        int[] nums2 = {3,3,3,2,2,4};
        System.out.println(solution(nums2));

        // 2
        int[] nums3 = {3,3,3,2,2,2};
        System.out.println(solution(nums3));
    }

    /**
     * @param nums 폰켓몬의 종류 번호가 담긴 1차원 배열
     * @return 선택할 수 있는 폰켓몬 종류 개수의 최댓값
     */
    public static int solution(int[] nums) {
        // 포켓몬 종류 구하기
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }
        return Math.min(nums.length / 2, set.size());
    }
}