package programmers;

/**
 * phoneBook: 전화번호 리스트
 * answer: 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false, 그렇지 않으면 true
 * ---------------------------------------
 * - phone_book의 길이는 1 이상 1,000,000 이하입니다.
 * - 각 전화번호의 길이는 1 이상 20 이하입니다.
 * - 같은 전화번호가 중복해서 들어있지 않습니다.
 */
class Solution42577 {
    public static void main(String[] args) {
//        List<String> phoneBook = Arrays.asList("119", "97674223", "1195524421");
        String[] phoneBook = {"119", "97674223", "1195524421"};

        boolean answer = solution(phoneBook);
        // false
        System.out.println(answer);
    }

    public static boolean solution(String[] phoneBook) {
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