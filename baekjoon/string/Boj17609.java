package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 회문(palindrome): 앞 뒤 방향으로 볼 때 같은 순서의 문자로 구성된 문자열
 * ----------------------------------------------------------
 * 회문: 0
 * 유사 회문: 1
 * 둘다 아님: 2
 */
public class Boj17609 {
    private static int len, res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());    // 문자열 개수

        for(int i = 0; i < t; i++) {
            String s = br.readLine();

            int l = 0;
            int r = s.length() - 1;
            res = 0;
            len = s.length();   // 문자열 길이

            while(l <= r) {
                if(s.charAt(l) == s.charAt(r)) {
                    // 1. 문자가 서로 같으면 다음 문자 검사
                    l++;
                    r--;
                } else {
                    // 2. 문자가 서로 다르면 삭제 수행
                    res = 1;
                    // 왼쪽 1개 삭제한 경우 & 오른쪽 1개 삭제한 경우를 모두 체크했을 때 팰린드롬이 아니면
                    if(check(s, l + 1, r) == false && check(s, l, r - 1) == false) {
                        res = 2;
                    }
                    break;
                }
            }
            System.out.println(res);
        }
    }

    // 문자 1개를 삭제한 후 팰린드롬인지 검사
    public static boolean check(String s, int l, int r) {
        while(l <= r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
               return false;
            }
        }
        return true;
    }
}
