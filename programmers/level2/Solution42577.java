package programmers.level2;

import java.util.Arrays;

/**
 * [문제명] 전화번호 목록
 * [한줄평] 간단한 구현문제였는데, 시간초과 문제를 해결하지 못해 힌트를 참고했던 문제였다. 시간초과를 해결하기 위한 로직을 떠올리는 것이 핵심이다.
 * v1. 2중 for문(실패- 시간초과)
 * - 첫번째 원소를 기준으로 2개씩 계속 비교했다.
 * v2. 배열 오름차순 정렬, for문(성공)
 * - 문자열 정렬 특성상 사전식으로 정렬되기 때문에, 인접한 문자열끼리만 검사하면 된다!!
 *  가정) 112, 1123, 12, 15, 166
 *  기존 방식에서는 12를 기준으로 15, 166 을 모두 비교했는데,
 *  오름차순 정렬을 한 방식에서는 12의 인접한 문자열(15)가 12로 시작하지 않을 경우,
 *  당연하게 이후의 문자열은 모두 12로 시작하지 않는다는 것을 증명할 수 있기 때문에 인접한 문자열만 검사하면된다.
 */
class Solution42577 {
    public static void main(String[] args) {
        // false
        String[] phoneBook = {"119", "97674223", "1195524421"};
        boolean answer = solution2(phoneBook);
        System.out.println(answer);
    }

    /**
     *
     * @param phoneBook 전화번호 리스트
     * - phone_book의 길이는 1 이상 1,000,000 이하입니다.
     * - 각 전화번호의 길이는 1 이상 20 이하입니다.
     * - 같은 전화번호가 중복해서 들어있지 않습니다.
     * @return 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false, 그렇지 않으면 true
     */
    public static boolean solution2(String[] phoneBook) {
        // 배열 오름차순 정렬
        Arrays.sort(phoneBook);

        for(int i = 0; i < phoneBook.length - 1; i++) {
            // A 번호가 B 번호로 시작하는지 검증
            if(phoneBook[i].length() == phoneBook[i + 1].length()) {
                continue;
            }
            if(phoneBook[i + 1].startsWith(phoneBook[i])) {
                return false;
            }
        }
        return true;
    }

    // 시간초과
    public static boolean solution1(String[] phoneBook) {
        for(int i = 0; i < phoneBook.length - 1; i++) {
            for(int j = i + 1; j < phoneBook.length; j++) {
                // A 번호가 B 번호로 시작하는지 검증
                if(phoneBook[i].length() == phoneBook[j].length()) {
                    continue;
                }
                if(phoneBook[i].startsWith(phoneBook[j]) || phoneBook[j].startsWith(phoneBook[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}