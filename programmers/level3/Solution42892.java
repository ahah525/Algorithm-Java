package programmers.level3;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [문제명] 길 찾기 게임
 * [풀이시간] 1시간(50분 + 10분)
 * [한줄평] 이진탐색트리를 구성하는 방법에 대한 풀이를 참고 해서 풀었다. PriorityQueue 를 사용했을 때 제대로 정렬이 되지 않은 이유를 모르겠다..
 * 1_v1. 이진탐색트리(실패)
 * [풀이] PriorityQueue 에 정렬 기준을 설정해서 노드 정렬
 * 1_v2. 이진탐색트리(성공)
 * [풀이] ArrayList 에 노드를 넣고 Collections.sort()로 정렬
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42892">문제</a>
 * @See <a href="https://moonsbeen.tistory.com/175"></a>
 */
class Solution42892 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    class Node {
        Node left;
        Node right;
        int x;
        int y;
        int v;

        Node(int x, int y, int v) {
            this.left = null;
            this.right = null;
            this.x = x;
            this.y = y;
            this.v = v;
        }

        public String toString() {
            return "[" + x + "," + y + "]";
        }
    }

    int idx;
    int[][] answer;

    // 1_v2
    /**
     * @param nodeInfo 이진트리를 구성하는 노드들의 좌표가 담긴 배열
     * @return 노드들로 구성된 이진트리를 전위 순회, 후위 순회한 결과를 2차원 배열에 순서대로 담아 return
     */
    public int[][] solution(int[][] nodeInfo) {
        int n = nodeInfo.length;
        answer = new int[2][n];
        List<Node> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(new Node(nodeInfo[i][0], nodeInfo[i][1], i + 1));
        }
        // 1. y 내림차순, x 오름차순 정렬
        Collections.sort(list, (o1, o2) -> {
            if(o1.y == o2.y) return o1.x - o2.x;
            return o2.y - o1.y;
        });
        // 2. 루트 노드 설정
        Node root = list.get(0);
        // 3. 이진 트리 구성
        for(int i = 1; i < n; i++) {
            addNode(root, list.get(i));
        }
        // 4. 전위 순회
        idx = 0;
        preOrder(root);
        // 5. 후위 순회
        idx = 0;
        postOrder(root);

        return answer;
    }

    // 노드 삽입
    public void addNode(Node parent, Node child) {
        if(parent.x > child.x) {
            if(parent.left == null) parent.left = child;
            else addNode(parent.left, child);
        } else {
            if(parent.right == null) parent.right = child;
            else addNode(parent.right, child);
        }
    }

    // 전위 순회
    public void preOrder(Node node) {
        if(node == null) return;
        answer[0][idx++] = node.v;
        preOrder(node.left);
        preOrder(node.right);
    }

    // 후위 순회
    public void postOrder(Node node) {
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        answer[1][idx++] = node.v;
    }

    // 1_v1
//    public int[][] solution(int[][] nodeInfo) {
//        int n = nodeInfo.length;
//        answer = new int[2][n];
//        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
//            if(o1.y == o2.y) return o1.x - o2.x;
//            return o2.y - o1.y;
//        });
//        for(int i = 0; i < n; i++) {
//            pq.add(new Node(nodeInfo[i][0], nodeInfo[i][1], i + 1));
//        }
//        // System.out.println(pq);
//        // 이진 트리 구성
//        Node root = pq.poll();
//        for(Node node : pq) {
//            // System.out.println(node.x +"," + node.y);
//            addNode(root, node);
//        }
//        //
//        idx = 0;
//        preOrder(root);
//        idx = 0;
//        postOrder(root);
//        return answer;
//    }
}