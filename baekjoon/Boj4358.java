package baekjoon;

import java.io.*;
import java.util.*;

public class Boj4358 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = 0;    // 나무 총 개수
        // 종이름, 개수
        HashMap<String, Integer> trees = new HashMap<>();
        ArrayList<String> arr = new ArrayList<>();
        // 입력이 없으면 종료
        while(true) {
            String s = br.readLine();   // 종이름
            // 종료조건(입력 없음)
            if(s == null || s.equals("")) break;
            if (!trees.containsKey(s)) {
                // 1. 입력한 종이 없으면 새로 넣기
                trees.put(s, 1);
                arr.add(s);
            } else {
                // 2. 입력한 종이 있으면 개수 1 증가
                trees.replace(s, trees.get(s) + 1);
            }
            num++;
        }
        // 사전 순으로 이름, 비율 출력
        Collections.sort(arr);  // 오름차순 정렬
        for (String name : arr) {
            double per = (double)trees.get(name) * 100.0 / num;     // 비율
            bw.write(name + " " + String.format("%.4f", per) + "\n");  // 소수점 4번쨰자리까지 반올림
        }
        bw.flush();
        bw.close();
    }
}
