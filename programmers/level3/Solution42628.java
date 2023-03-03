package programmers.level3;

import java.util.*;

/**
 * [문제명] 이중우선순위큐
 * [풀이시간] / 45분
 * [한줄평] / 3번째 풀다보니 어렵지 않게 풀 수 있었던 것 같다.
 * 1_v1. 최소힙, 최대힙, Map(성공)
 * 2_v1. 최소힙, 최대힙(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42628">문제</a>
 */
class Solution42628 {
    public static void main(String[] args) {
        String[] operations1 = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        String[] operations2 = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};

        // [0, 0]
        System.out.println(Arrays.toString(solution(operations1)));
        System.out.println(Arrays.toString(solution2(operations1)));

        // [45, -333]
        System.out.println(Arrays.toString(solution(operations2)));
        System.out.println(Arrays.toString(solution2(operations2)));
    }

    /**
     * @param operations 이중 우선순위 큐가 할 연산
     * @return 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return
     */
    public static int[] solution(String[] operations) {
        int[] answer = {0, 0};
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // 최대힙
        Queue<Integer> minHeap = new PriorityQueue<>(); // 최소힙
        Map<Integer, Integer> map = new HashMap();

        for(String s : operations) {
            String[] split = s.split(" ");
            String command = split[0];          // 명령어
            int n = Integer.parseInt(split[1]); // 숫자

            if(command.equals("I")) {
                // 1. 삽입
                maxHeap.add(n);
                minHeap.add(n);
                map.put(n, map.getOrDefault(n, 0) + 1);
            } else if(command.equals("D")) {
                if(n == 1) {
                    // 2. 최댓값 삭제
                    // 최댓값 삭제하기 전에 해당 값이 이전에 삭제되었어야할 값인지 검사
                    while(!maxHeap.isEmpty()) {
                        int target = maxHeap.poll();
                        // 최댓값으로 꺼낸 값이 최소힙에는 없으면(원래 삭제되었어야할 값이면) 1개 더 꺼내기
                        if(minHeap.contains(target)) {
                            break;
                        }
                    }
                } else if(n == -1) {
                    // 3. 최솟값 삭제
                    while(!minHeap.isEmpty()) {
                        int target = minHeap.poll();
                        // 최솟값으로 꺼낸 값이 최대힙에는 없으면(원래 삭제되었어야할 값이면) 1개 더 꺼내기
                        if(maxHeap.contains(target)) {
                            break;
                        }
                    }
                }
            }
        }

        // 최대힙 삭제했어야할 값 삭제처리
        while(!maxHeap.isEmpty()) {
            int target = maxHeap.peek();
            // 최댓값으로 꺼낸 값이 원래 삭제되었어야할 값이면 삭제하고 1개 더 꺼내기
            if(!minHeap.contains(target)) {
                maxHeap.poll();
            } else {
                break;
            }
        }
        if(maxHeap.isEmpty()) {
            return answer;
        } else if(maxHeap.size() == 1) {
            answer[0] = maxHeap.peek();
            answer[1] = answer[0];
        } else {
            // 최소힙 삭제했어야할 값 삭제처리
            while(!minHeap.isEmpty()) {
                int target = minHeap.peek();
                // 최댓값으로 꺼낸 값이 원래 삭제되었어야할 값이면 삭제하고 1개 더 꺼내기
                if(!maxHeap.contains(target)) {
                    minHeap.poll();
                } else {
                    break;
                }
            }
            answer[0] = maxHeap.peek();
            answer[1] = minHeap.peek();
        }
        return answer;
    }

    public static int[] solution2(String[] operations) {
        int[] answer = new int[2];  // (최댓값, 최솟값)
        Queue<Integer> minHeap = new PriorityQueue<>(); // 최소힙
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 최대힙

        for(String operation : operations) {
            String[] split = operation.split(" ");
            String cmd = split[0];  // 명령어
            int n = Integer.parseInt(split[1]);
            switch(cmd) {
                case "I":
                    // 최소힙, 최대힙에 모두 삽입
                    minHeap.add(n);
                    maxHeap.add(n);
                    break;
                case "D":
                    if(n == -1) {
                        // 최솟값 삭제
                        while(!minHeap.isEmpty()) {
                            int min = minHeap.poll();
                            // 최소힙에서 poll 한 값이 최대힙에 없는 경우 이미 삭제되었어야할 값이므로 한 번 더 삭제
                            if(maxHeap.contains(min)) break;
                        }
                    } else {
                        // 최댓값 삭제
                        while(!maxHeap.isEmpty()) {
                            int max = maxHeap.poll();
                            // 최대힙에서 poll 한 값이 최소힙에 없는 경우 이미 삭제되었어야할 값이므로 한 번 더 삭제
                            if(minHeap.contains(max)) break;
                        }
                    }
                    break;
            }
        }
        // 최소힙에서 최솟값을 구하기 전에 이미 삭제되었어야 할 값 삭제하기
        while(!minHeap.isEmpty()) {
            int min = minHeap.peek();
            if(maxHeap.contains(min)) break;
            minHeap.poll();
        }
        // 최대힙에서 최댓값을 구하기 전에 이미 삭제되었어야 할 값 삭제하기
        while(!maxHeap.isEmpty()) {
            int max = maxHeap.peek();
            if(minHeap.contains(max)) break;
            maxHeap.poll();
        }
        // 둘 중 하나라도 비었으면 최솟값, 최댓값이 존재하지 않으므로 {0,0} 리턴
        if(minHeap.isEmpty() || maxHeap.isEmpty())
            return answer;
        answer[0] = maxHeap.poll();
        answer[1] = minHeap.poll();

        return answer;
    }
}