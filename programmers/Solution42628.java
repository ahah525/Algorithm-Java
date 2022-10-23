package programmers;

import java.util.*;

class Solution42628 {
    public static void main(String[] args) {
        String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
//        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};

        int[] answer = solution(operations);
        // [0, 0]
        System.out.println(answer[0] + ", " + answer[1]);
    }
    
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
}