package baekjoon.binarySearch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Level {
    String name;    // 칭호 이름
    int power;      // 전투력 상한값

    public Level(String name, int power) {
        this.name = name;
        this.power = power;
    }
}

public class Boj19637 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 칭호 개수
        int m = Integer.parseInt(st.nextToken());   // 캐릭터 개수
        List<Level> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int power = Integer.parseInt(st.nextToken());
            // 같은 power 값이 들어오면 저장X
            if(arr.size() == 0 || !arr.get(arr.size() - 1).equals(name)) {
                arr.add(new Level(name, power));
            }
        }

        for (int i = 0; i < m; i++) {
            int p = Integer.parseInt(br.readLine());
            String s = binarySearch(arr, p, 0, arr.size() - 1);
            bw.write(s);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
    public static String binarySearch(List<Level> arr, int target, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if(target <= arr.get(mid).power) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return arr.get(start).name;
    }
}
