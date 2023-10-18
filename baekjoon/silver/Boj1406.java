package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [문제명] 에디터
 * [풀이시간] (20분)
 * [한줄평] 에디터
 * 1_v1. 구현(실패-시간초과)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/1406">문제</a>
 */
class Boj1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int m = Integer.parseInt(br.readLine());
        int cursor = s.length();    // 커서 맨 뒤에
        List<String> list = new LinkedList<>();
        for(char c : s.toCharArray()) {
            list.add(c + "");
        }
        StringBuilder sb = new StringBuilder();
        //
        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();    // 명령어
            switch(cmd) {
                case "L":
                    if(cursor == 0) continue;
                    cursor--;
                    break;
                case "D":
                    if(cursor == list.size()) continue;
                    cursor++;
                    break;
                case "B":
                    if(cursor == 0) continue;
                    list.remove(cursor - 1);
                    cursor--;
                    break;
                case "P":
                    String c = st.nextToken();
                    list.add(cursor, c);
                    cursor++;
            }
        }
        //
        for(String c : list) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}