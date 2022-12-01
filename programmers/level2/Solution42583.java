package programmers.level2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 다리를 지나는 트럭
 * [한줄평] 문제 설명이 조금 부실해서 문제를 이해하고 푸는데 생각보다 시간이 오래걸렸던 구현 문제였다. 나중에 다시 한번 꼭 풀어봐야겠다.
 * v1. (실패-테스트 11)
 * [용어 정의]
 * - 진입: 다리를 건너기 시작함
 * - 진입완료: 다리에 완전히 오름
 * - 진출: 다리를 빠져나가기 시작함
 * - 진출완료: 다리를 완전히 빠져나감
 */
class Solution42583 {
    public static void main(String[] args) {
        // 8
        int bridgeLength1 = 2;
        int weight1 = 10;
        int[] truckWeights1 = {7,4,5,6};
        System.out.println(solution(bridgeLength1, weight1, truckWeights1));

        // 101
        int bridgeLength2 = 100;
        int weight2 = 100;
        int[] truckWeights2 = {10};
        System.out.println(solution(bridgeLength2, weight2, truckWeights2));

        // 110
        int bridgeLength3 = 100;
        int weight3 = 100;
        int[] truckWeights3 = {10,10,10,10,10,10,10,10,10,10};
        System.out.println(solution(bridgeLength3, weight3, truckWeights3));
    }

    /**
     *
     * @param bridgeLength 다리에 올라갈 수 있는 트럭 수(1 이상 10,000 이하)
     * @param weight 다리가 견딜 수 있는 무게, 다리의 길이(1 이상 10,000 이하)
     * - 문제에서는 다리 길이에 대한 설명이 누락됨
     * @param truckWeights 트럭 별 무게(길이는 1 이상 10,000 이하)
     * @return 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return
     */
    public static int solution(int bridgeLength, int weight, int[] truckWeights) {
        int truck = truckWeights.length;    // 진입 가능한 최대 트럭수
        int t = 0;  // 경과 시간
        int n = 0;  // 진출 완료된 트럭 개수
        int w = 0;  // 진입 완료된 트럭의 무게
        Queue<Truck> pass = new LinkedList<>();     // 진입 완료된 트럭의 정보(무게, 진출 시점)
        Queue<Integer> wait = new LinkedList<>();   // 대기 중인 트럭의 무게
        for(int tw : truckWeights) {
            wait.add(tw);
        }
        // 모든 트럭이 진출 완료할 때 까지
        while(n < truck) {
            boolean isOut = false;  // 진출 트럭 여부
            if(pass.size() > 0) {
                Truck peek = pass.peek();
                // 진출 시점이 되었다면 진출 처리
                if(peek.passTime == t) {
                    isOut = true;
                    w -= peek.weight;
                }
            }
            // 대기 중인 트럭이 있으면 진입할 수 있는지 검사
            boolean isIn = false;   // 진입 트럭 여부
            if(wait.size() > 0) {
                // 진입 가능 조건 = 진입 완료된 트럭 무게, 트럭수 비교
                if(pass.size() < bridgeLength && w + wait.peek() <= weight) {
                    isIn = true;
                }
            }
            t++;
            // 진출 완료 처리
            if(isOut) {
                // 진입 완료된 트럭에서 삭제, 진출 완료 트럭수 1 증가
                pass.poll();
                n++;
            }
            // 진입 완료 처리
            if(isIn) {
                // 대기 트럭에서 삭제, 진입 완료에 추가
                int nw = wait.poll();
                int pt = t - 1 + bridgeLength;
                pass.add(new Truck(nw, pt));
                w += nw;
//                System.out.println("t = " + t);
//                System.out.println("nw = " + nw);
//                System.out.println("pt = " + pt);
            }
        }
        return t;
    }

    public static class Truck {
        int weight;     // 무게
        int passTime;   // 진출 시점

        public Truck(int weight, int passTime) {
            this.weight = weight;
            this.passTime = passTime;
        }
    }
}