package baekjoon;

import java.io.*;
import java.util.Stack;

class Boj10828 {
    public static void main(String[] args) throws IOException {
        // BufferedReader, BufferedWriter를 이용한 빠른 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> s = new Stack<>();  // 스택

        int n = Integer.parseInt(br.readLine());  // 명령수
        for(int i = 0; i < n ; i++) {
            String[] input = br.readLine().split(" ");  // 공백 구분 String 한줄 입력
            switch(input[0]) {
                case "push":
                    s.push(Integer.parseInt(input[1]));  // 정수로 변환해서 push
                    break;
                case "pop":
                    if(s.empty()) {
                        bw.write("-1\n");
                    } else{
                        bw.write(s.pop() + "\n");
                    }
                    break;
                case "size":
                    bw.write(s.size() + "\n");
                    break;
                case "empty":
                    if(s.empty()) {
                        bw.write("1\n");
                    } else{
                        bw.write("0\n");
                    }
                    break;
                case "top":
                    if(s.empty()) {
                        bw.write("-1\n");
                    } else{
                        bw.write(s.peek() + "\n");
                    }
                    break;
            }
        }
        bw.flush();  // 남아있는 데이터 모두 출력
        bw.close();  // 스트림 닫기
    }
}
