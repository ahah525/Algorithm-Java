package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [문제명] [21년 재직자 대회 본선] 비밀 메뉴2
 * [풀이시간] 25분
 * [한줄평]
 * 1_v1. 문자열(실패)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=633">문제</a>
 */
class Solution633 {
    static String secret, user;
    static int max;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        secret = br.readLine().replace(" ", "");
        user = br.readLine().replace(" ", "");
        max = 0;
        l1:
        for(int len = secret.length(); len > 0; len--) {
            int s = 0;
            int e = len;
            while(true) {
                if(e >= user.length()) break;
                String target = secret.substring(s, e);
                if(user.contains(target)) {
                    max = len;
                    break l1;
                }
                s++;
                e++;
            }
        }
        System.out.println(max);
    }
}