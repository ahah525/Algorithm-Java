package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

/**
 * [문제명] 에디터
 * [풀이시간] 40분(20분+20분)
 * [한줄평]
 * 1_v1. LinkedList(실패-시간초과)
 * [풀이] LinkedList의 remove(index, value) 함수를 사용했다.
 * 1_v2. LinkedList(성공)
 * [풀이] ListIterator를 사용하여 커서를 양방향으로 움직여서 값을 삭제할 수 있도록 한다.
 * @See <a href="https://www.acmicpc.net/problem/1406">문제</a>
 * @See <a href="https://minhamina.tistory.com/17">풀이</a>
 */
class Boj1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int m = Integer.parseInt(br.readLine());
        List<String> list = new LinkedList<>();
        for(char c : s.toCharArray()) {
            list.add(c + "");
        }
        // 1. 커서를 맨 오른쪽으로 이동
        ListIterator<String> iter = list.listIterator();
        while(iter.hasNext()) {
            iter.next();
        }
        //
        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();    // 명령어
            switch(cmd) {
                case "L":
                    // 커서 왼쪽으로 1칸 이동
                    if(iter.hasPrevious()) iter.previous();
                    break;
                case "D":
                    // 커서 오른쪽으로 1칸 이동
                    if(iter.hasNext()) iter.next();
                    break;
                case "B":
                    // 커서 왼쪽에 있는 문자 삭제
                    if(iter.hasPrevious()) {
                        iter.previous();
                        iter.remove();
                    }
                    break;
                case "P":
                    // 커서 왼쪽에 문자 추가
                    iter.add(st.nextToken());
            }
        }
        //
        StringBuilder sb = new StringBuilder();
        for(String c : list) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}