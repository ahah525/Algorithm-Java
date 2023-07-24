package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [문제명] [HSAT 3회 정기 코딩 인증평가 기출] 플레이페어 암호
 * [풀이시간] 57분
 * [한줄평] 어렵진 않았으나 복잡한 구현 문제였다.
 * 1_v1. 구현(성공)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=804">문제</a>
 */
class Solution804 {
    static int[] visited;
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg = br.readLine(); // 메시지
        String key = br.readLine(); // 키
        visited = new int[26]; // visited[i]: 표에서 ('A' + i)문자의 인덱스
        Arrays.fill(visited, -1);
        visited['J' - 'A'] = 25;
        char[][] arr = new char[5][5];  // 키를 변환한 표
        int idx = 0;
        // 1. 키를 5*5 표로 변환
        for(char c : key.toCharArray()) {
            if(visited[c - 'A'] != -1) continue;
            arr[idx / 5][idx % 5] = c;
            visited[c - 'A'] = idx;
            idx++;
        }
        for(int i = 0; i < 26; i++) {
            if(idx == 25) break;
            if(visited[i] != -1) continue;
            arr[idx / 5][idx % 5] = (char) ('A' + i);
            visited[i] = idx;
            idx++;
        }
        // for(int i = 0; i < 5; i++) {
        //     System.out.println(Arrays.toString(arr[i]));
        // }
        // 2. 메시지 두글자씩 나누기
        int i = 0;
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while(i < msg.length()) {
            char c = msg.charAt(i);
            if(sb.length() == 0) {
                sb.append(c);
                i++;
            } else if(sb.length() == 1) {
                if(c != sb.charAt(0)) {
                    sb.append(c);
                    i++;
                    continue;
                }
                if(c == 'X') {
                    sb.append('Q');
                } else {
                    sb.append('X');
                }
            } else {
                list.add(sb.toString());
                sb.setLength(0);
            }
        }
        // 남은 문자 처리
        if(sb.length() == 1) {
            sb.append('X');
            list.add(sb.toString());
        } else if(sb.length() == 2) {
            list.add(sb.toString());
        }
        sb.setLength(0);
        // System.out.println(list);
        // 3. 2글자씩 암호화
        for(String s : list) {
            int r1 = getRow(s.charAt(0));
            int c1 = getCol(s.charAt(0));
            int r2 = getRow(s.charAt(1));
            int c2 = getCol(s.charAt(1));
            if(r1 == r2) {
                sb.append(arr[r1][(c1 + 1) % 5]);
                sb.append(arr[r2][(c2 + 1) % 5]);
                continue;
            }
            if(c1 == c2) {
                sb.append(arr[(r1 + 1) % 5][c1]);
                sb.append(arr[(r2 + 1) % 5][c2]);
                continue;
            }
            sb.append(arr[r1][c2]);
            sb.append(arr[r2][c1]);
        }
        System.out.println(sb.toString());
    }

    // 문자의 행 조회
    public static int getRow(char c) {
        return visited[c - 'A'] / 5;
    }

    // 문자의 열 조회
    public static int getCol(char c) {
        return visited[c - 'A'] % 5;
    }
}