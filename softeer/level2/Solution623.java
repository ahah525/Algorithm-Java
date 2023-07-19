package softeer.level2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [문제명] [21년 재직자 대회 예선] 비밀 메뉴
 * [풀이시간] 5분, 20분
 * [한줄평] contains()로는 너무나 쉽게 풀 수 있었던 문제고 정석적으로 하나씩 비교하는 방식으로도 풀어봤다.
 * 1_v1. 문자열(성공)
 * [풀이] 대상 문자열에 secret 문자열이 포함되었는지 확인하는 방법: contains()
 * 1_v2. 문자열(성공)
 * [풀이] 대상 문자열에 secret 문자열이 포함되었는지 확인하는 방법: 반복문으로 문자 직접 비교
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=623">문제</a>
 */
class Solution623 {
    // 1_v1
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        String secret = br.readLine();
        String s = br.readLine();
        if(s.contains(secret)) System.out.println("secret");
        else System.out.println("normal");
    }

    // 1_v2
    static String secret;
    static String user;
    public static void main2(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        secret = br.readLine().replace(" ", "");
        user = br.readLine().replace(" ", "");
        boolean find = false;
        int p = 0;
        for(int i = 0; i < user.length() - secret.length() + 1; i++) {
            if(isSame(i)) {
                find = true;
                break;
            }
        }
        if(find) System.out.println("secret");
        else System.out.println("normal");
    }

    public static boolean isSame(int s) {
        for(int i = 0; i < secret.length(); i++) {
            if(secret.charAt(i) != user.charAt(s + i)) return false;
        }
        return true;
    }
}