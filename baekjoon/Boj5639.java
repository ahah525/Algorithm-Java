package baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// 이진 탐색 트리의 전위 순회 결과 -> 중위 순회, 후위 순회 결과 구하기
public class Boj5639 {
    static BufferedWriter bw;
    static List<Integer> nodeList;  // 전위 순회 결과
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        nodeList = new ArrayList<Integer>();
        String s;   // 노드 번호
        // 전위 순회 결과 저장
        while(true) {
            // 입력값 널이면 종료
            s = br.readLine();
            if(s == null)
                break;
            nodeList.add(Integer.parseInt(s));
        }
        // 이진 검색 트리 후위 순회 결과 출력
        postOrder(0, nodeList.size() - 1);
        bw.flush();
        bw.close();
    }
    // 후위 순회(탐색 시작 인덱스, 탐색 끝 인덱스)
    public static void postOrder(int start, int end) throws IOException {
        // 엇갈리면 종료
        if(start > end) return;
        int pivot = end + 1;            // 오른쪽 서브트리의 루트 노드
        int cur = nodeList.get(start);  // 현재 노드
        for(int i = start; i <= end; i++) {
            // 현재 노드보다 큰 값을 가진 노드가 나타나면
            if(cur < nodeList.get(i)) {
                pivot = i;
                break;
            }
        }
        postOrder(start + 1, pivot - 1);    // 왼쪽 서브트리
        postOrder(pivot, end);              // 오른쪽 서브트리
        bw.write(cur + "\n");           // 현재 노드값 출력
    }
}
