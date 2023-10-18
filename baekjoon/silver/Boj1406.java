package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 에디터
 * [풀이시간] 40분(20분+20분,15분)
 * [한줄평] 결국 시간초과를 해결하지 못해 풀이를 봤는데, 스택으로 풀 수 있을거라고는 전혀 생각못했다. 꼭 다시 한 번 풀어봐야할 좋은 문제다.
 * 1_v1. LinkedList(실패-시간초과)
 * [풀이] LinkedList의 remove(index, value) 함수를 사용했다.
 * 1_v2. LinkedList(성공)
 * [풀이] ListIterator를 사용하여 커서를 양방향으로 움직여서 값을 삭제할 수 있도록 한다.
 * 1_v3. Stack(성공)
 * [풀이] 커서를 기준으로 왼쪽에 있는 문자는 left 스택에서 관리하고, 오른쪽에 있는 문자는 right 스택에서 관리한다.
 * @See <a href="https://www.acmicpc.net/problem/1406">문제</a>
 * @See <a href="https://minhamina.tistory.com/17">풀이</a>
 */
class Boj1406 {
    // 1_v2
    public static void main1(String[] args) throws IOException {
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

    // 1_v3
    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int m = Integer.parseInt(br.readLine());
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        for(char c : s.toCharArray()) {
            left.add(c);
        }
        //
        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();    // 명령어
            switch(cmd) {
                case "L":
                    if(left.isEmpty()) continue;
                    // 1. 왼쪽 맨 위에 있는 것을 오른쪽으로
                    right.push(left.pop());
                    break;
                case "D":
                    if(right.isEmpty()) continue;
                    // 2. 오른쪽 맨 위에 있는 것을 왼쪽으로
                    left.push(right.pop());
                    break;
                case "B":
                    if(left.isEmpty()) continue;
                    // 3. 왼쪽 맨 위 문자 꺼내기
                    left.pop();
                    break;
                case "P":
                    String c = st.nextToken();
                    // 4. 왼쪽에 넣기
                    left.push(c.charAt(0));
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char c : left) {
            sb.append(c);
        }
        while(!right.isEmpty()) {
            sb.append(right.pop());
        }
        System.out.println(sb);
    }
}