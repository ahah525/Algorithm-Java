package baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Student implements Comparable<Student> {
    int id; // 학생 번호
    int cnt;    // 추천 수
    int time;   // 게시된 시점

    public Student(int id, int cnt, int time) {
        this.id = id;
        this.cnt = cnt;
        this.time = time;
    }

    @Override
    public int compareTo(Student o) {
        // 추천수가 같으면 게시된 시점 오름차순
        if(cnt == o.cnt)
            return time - o.time;
        // 추천수 오름차순 정렬
        return cnt - o.cnt;
    }
}

public class Boj1713 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 사진틀 개수(후보 수)
        int m = Integer.parseInt(br.readLine());    // 총 추천 횟수
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Student> list = new ArrayList<>();  // 후보자 리스트
        for (int i = 0; i < m; i++) {
            int id = Integer.parseInt(st.nextToken());
            boolean isPosted = false;   // 게시 여부
            for (Student s : list) {
                // 이미 게시가 되었으면 추천수 1 증가
                if (s.id == id) {
                    isPosted = true;
                    s.cnt++;
                    break;
                }
            }
            // 게시가 되지 않았으면 게시
            if (!isPosted) {
                // 이미 후보자가 꽉찼으면 빼기
                if (n == list.size()) {
                    Collections.sort(list);
                    list.remove(0);
                }
                // 게시
                list.add(new Student(id, 1, i));
            }
        }
        // id 오름차순 정렬
//        Collections.sort(list, ((o1, o2) -> {
//            return o1.id - o2.id;
//        }));
        Collections.sort(list, (Comparator.comparingInt(o -> o.id)));

        for (Student s : list) {
            System.out.print(s.id + " ");
        }
    }
}
