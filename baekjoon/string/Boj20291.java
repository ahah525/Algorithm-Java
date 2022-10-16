package baekjoon.string;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Boj20291 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String, Integer> map = new HashMap<>();
        int n = Integer.parseInt(br.readLine());    // 파일 개수
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            String s = br.readLine();   // 파일 이름
            String[] split = s.split("\\.");
            String file = split[1];    // 확장자

            int cnt = map.getOrDefault(file, 0);
            map.put(file, cnt + 1);
        }

        Object[] keys = map.keySet().toArray();
        Arrays.sort(keys);

        for(Object key : keys) {
            sb.append(key).append(" ").append(map.get(key)).append("\n");
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
    }
}
