package programmers.level2;


/**
 * [문제명] 숫자 문자열과 영단어
 * [풀이시간] 17분 / 4분
 * [한줄평] replaceAll() 을 떠올리기만 한다면 로직은 굉장히 짧은 쉬운 문제였다. / 쉽게 풀 수 있는 문제였다.
 * 1_v1. 문자열(성공)
 * 2_v1. 문자열(성공) -> 빠름
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/81301">문제</a>
 */
class Solution81301 {
    public static void main(String[] args) {
        // 1478
        String s1 = "one4seveneight";
        System.out.println(solution(s1));

        // 234567
        String s2 = "23four5six7";
        System.out.println(solution(s2));

        // 234567
        String s3 = "2three45sixseven";
        System.out.println(solution(s3));

        // 123
        String s4 = "123";
        System.out.println(solution(s4));
    }

    // 1_v1
    /**
     * @param s 숫자의 일부 자릿수가 영단어로 바뀌어졌거나, 혹은 바뀌지 않고 그대로인 문자열
     * @return s가 의미하는 원래 숫자
     */
    public static int solution(String s) {
        String[] arr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for(int i = 0; i < 10; i++) {
            s = s.replaceAll(arr[i], Integer.toString(i));
        }
        return Integer.parseInt(s);
    }

    // 2_v1
    public int solution2(String s) {
        s = s.replace("zero", "0")
                .replace("one", "1")
                .replace("two", "2")
                .replace("three", "3")
                .replace("four", "4")
                .replace("five", "5")
                .replace("six", "6")
                .replace("seven", "7")
                .replace("eight", "8")
                .replace("nine", "9");
        return Integer.parseInt(s);
    }
}