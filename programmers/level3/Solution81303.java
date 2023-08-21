package programmers.level3;


import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [문제명] 표 편집
 * [풀이시간] 2시간(50분 + 1시간 10분) / 1시간 5분(47분, 18분)
 * [한줄평] 양방향 연결리스트를 직접 구현해서 풀어야하는 것을 풀이를 보고 이해했던 문제로 반드시 복습이 필요하다.
 * / 양방향 연결 리스트를 구현하는 코드 힌트를 보고 풀었기 때문에 꼭 복습이 필요하다.
 * 1_v1. (실패 - 정확성 테스트 5,9,11~22,24,27,29~30 실패, 효율성 테스트 모두 실패)
 * [풀이] ArrayList 에 행 번호 저장, 삭제
 * 1_v2. 자료구조(성공)
 * [풀이] 양방향 연결리스트 구현
 * 2_v1. 구현(성공)
 * [풀이] 1_v2 동일
 * 2_v2. 구현(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/81303">문제</a>
 * @See <a href="https://jangcenter.tistory.com/125">풀이 참고</a>
 */
class Solution81303 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2, 2_v1
    class Node {
        Node prev; // 이전 노드
        Node next; // 다음 노드
        boolean isDeleted; // 노드 삭제 여부
    }

    /**
     * @param n 처음 표의 행 개수
     * @param k 처음에 선택된 행의 위치를 나타내는 정수
     * @param cmd 수행한 명령어들이 담긴 문자열 배열
     * @return 모든 명령어를 수행한 후 표의 상태와 처음 주어진 표의 상태를 비교하여 삭제되지 않은 행은 O, 삭제된 행은 X로 표시하여 문자열 형태로 return
     */
    public String solution(int n, int k, String[] cmd) {
        // 1. 노드 배열 초기화
        Node[] arr = new Node[n];
        // 1.1. 0번째 노드 생성
        arr[0] = new Node();
        // 1.2. 1~(n-1)번째 노드 생성
        for(int i = 1; i < n; i++) {
            // i번째 노드 생성
            arr[i] = new Node();
            // i번째 노드의 이전 노드 = (i - 1)번째 노드
            arr[i].prev = arr[i - 1];
            // (i - 1)번째 노드의 다음 노드 = i번째 노드
            arr[i - 1].next = arr[i];
        }
        Stack<Node> stack = new Stack<>(); // 삭제된 노드가 쌓여있는 스택
        Node cur = arr[k]; // 현재 노드
        for(String s : cmd) {
            StringTokenizer st = new StringTokenizer(s);
            String c = st.nextToken();
            switch(c) {
                case "U": {
                    // 현재 노드 기준 x번 이전 노드 선택
                    int x = Integer.parseInt(st.nextToken());
                    for(int i = 0; i < x; i++) {
                        cur = cur.prev;
                    }
                    break;
                }
                case "D": {
                    // 현재 노드 기준 x번 다음 노드 선택
                    int x = Integer.parseInt(st.nextToken());
                    for(int i = 0; i < x; i++) {
                        cur = cur.next;
                    }
                    break;
                }
                case "C": {
                    // 현재 노드 삭제, 스택에 현재 노드 넣기
                    cur.isDeleted = true;
                    stack.push(cur);
                    // 현재 노드의 이전 노드, 다음 노드
                    Node prev = cur.prev;
                    Node next = cur.next;
                    // 이전 노드가 있으면, 이전 노드 -> 다음 노드 연결
                    if(prev != null) prev.next = next;
                    if(next != null) {
                        // 다음 노드가 있으면, 이전 노드 <- 다음 노드 연결, 아래 행 선택
                        next.prev = prev;
                        cur = next;
                    } else {
                        // 다음 노드가 없으면, 이전 행 선택
                        cur = prev;
                    }
                    break;
                }
                case "Z": {
                    // 가장 최근에 삭제된 노드(복구 노드)
                    Node backUp = stack.pop();
                    backUp.isDeleted = false;
                    // 복구 노드의 이전 노드, 다음 노드
                    Node prev = backUp.prev;
                    Node next = backUp.next;
                    // 이전 노드가 있으면, 이전 노드 -> 복구 노드 연결
                    if(prev != null) prev.next = backUp;
                    // 다음 노드가 있으면, 복구 노드 <- 다음 노드 연결
                    if(next != null) next.prev = backUp;
                    break;
                }
            }
        }
        // 2. 삭제 여부를 문자열로 만들어 반환
        StringBuilder sb = new StringBuilder();
        for(Node node : arr) {
            if(node.isDeleted) sb.append("X");
            else sb.append("O");
        }
        return sb.toString();
    }

    // 2_v2
//    class Node {
//        int idx;
//        Node prev;
//        Node next;
//
//        Node(int idx) {
//            this.idx = idx;
//        }
//    }
//
//    public String solution2(int n, int k, String[] cmd) {
//        boolean[] isDeleted = new boolean[n];
//
//        Node now = new Node(0);
//        Node cur = null;
//        for(int i = 1; i < n; i++) {
//            Node next = new Node(i);
//            if(i == k) cur = next;
//            next.prev = now;
//            now.next = next;
//            now = next;
//        }
//        Stack<Node> stack = new Stack<>();
//        for(String s : cmd) {
//            StringTokenizer st = new StringTokenizer(s);
//            String type = st.nextToken();
//            switch(type) {
//                case "U": {
//                    // 현재 선택된 행에서 X칸 위에 있는 행 선택
//                    int x = Integer.parseInt(st.nextToken());
//                    for(int i = 0; i < x; i++) {
//                        cur = cur.prev;
//                    }
//                    break;
//                }
//                case "D": {
//                    // 현재 선택된 행에서 X칸 아래에 있는 행 선택
//                    int x = Integer.parseInt(st.nextToken());
//                    for(int i = 0; i < x; i++) {
//                        cur = cur.next;
//                    }
//                    break;
//                }
//                case "C": {
//                    // 현재 선택된 행 삭제, 아래 행 선택
//                    isDeleted[cur.idx] = true;
//                    stack.push(cur);
//                    // 현재 노드의 이전 노드&다음 노드 연결하기
//                    Node prev = cur.prev;
//                    Node next = cur.next;
//                    // 이전 노드가 있으면
//                    if(prev != null) prev.next = next;
//                    if(next == null) {
//                        // 다음 노드가 없다면(가장 마지막 행이라면)
//                        cur = prev;
//                    } else {
//                        // 다음 노드가 있으면
//                        next.prev = prev;
//                        cur = next;
//                    }
//                    break;
//                }
//                case "Z": {
//                    // 가장 최근에 삭제된 행 원상복구
//                    Node node = stack.pop();
//                    isDeleted[node.idx] = false;
//                    //
//                    Node prev = node.prev;
//                    Node next = node.next;
//                    //
//                    if(prev != null) prev.next = node;
//                    if(next != null) next.prev = node;
//                    break;
//                }
//            }
//        }
//        //
//        StringBuilder sb = new StringBuilder();
//        for(boolean flag : isDeleted) {
//            if(flag) sb.append("X");
//            else sb.append("O");
//        }
//        return sb.toString();
//    }
}