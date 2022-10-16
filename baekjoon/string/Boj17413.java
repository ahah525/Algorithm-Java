package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj17413 {
    private static StringBuilder sb = new StringBuilder();
    private static Stack<Character> st = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        boolean isWord = true;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '<') {
                printWord();
                sb.append(c);
                isWord = false;
            } else if(c == '>') {
                sb.append(c);
                isWord = true;
            } else if(c == ' ') {
                printWord();
                sb.append(c);
            } else {
                if(!isWord) {
                    // 태그 내 문자이면 그냥 출력
                    sb.append(c);
                } else {
                    // 단어 내 문자이면 스택에 넣기
                    st.push(c);
                }
            }
            if(i == s.length() - 1) {
                printWord();
            }
        }
        System.out.println(sb);
    }

    // 스택에서 단어 꺼내서 출력하기
    public static void printWord() {
        while(!st.isEmpty()) {
            char c1 = st.pop();
            sb.append(c1);
        }
    }
}
