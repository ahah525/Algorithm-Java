package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문자열은 {A, B, C, D, E, F} 중 0개 또는 1개로 시작해야 한다.
 * 그 다음에는 A가 하나 또는 그 이상 있어야 한다.
 * 그 다음에는 F가 하나 또는 그 이상 있어야 한다.
 * 그 다음에는 C가 하나 또는 그 이상 있어야 한다.
 * 그 다음에는 {A, B, C, D, E, F} 중 0개 또는 1개가 있으며, 더 이상의 문자는 없어야 한다.
 *
 * A, C, F 는 무조건 1개
 */
public class Boj9342 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String regex = "^[A-F]?A+F+C+[A-F]?$";

        for (int i = 0; i < t; i++) {
            String s = br.readLine();
            if(s.matches(regex)) {
                System.out.println("Infected!");
            } else {
                System.out.println("Good");
            }
        }
    }
}
