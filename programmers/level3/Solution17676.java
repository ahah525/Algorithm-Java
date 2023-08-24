package programmers.level3;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] [1차] 추석 트래픽
 * [풀이시간] 1시간 30분
 * [한줄평] 감을 잡지 못해서 결국 풀이를 보고 이해했던 문제로 꼭 복습이 필요하다.
 * 1_v1. (성공)
 * [풀이] 한 로그의 시작점, 끝점을 기준으로 [시작점,시작점+999], [끝점,끝점+999] 두 구간에 대해 각 로그가 구간에 걸쳐있는지를 검사한다.
 * [시간 복잡도] O(2 * n^2)
 * - n: 로그의 개수
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17676">문제</a>
 * @See <a href="https://velog.io/@pppp0722/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-Level3-1%EC%B0%A8-%EC%B6%94%EC%84%9D-%ED%8A%B8%EB%9E%98%ED%94%BD-Java">풀이 참고1</a>
 * @See <a href="https://j-sik.tistory.com/117">풀이 참고2</a>
 */
class Solution17676 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    class Work {
        int start;
        int end;
        Work(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int solution(String[] lines) {
        int max = 0;
        // 1. 모든 로그의 시작 시각, 끝 시각 구하기
        List<Work> list = new ArrayList<>();
        for(String line : lines) {
            list.add(calc(line));
        }
        // 2. 각 로그로부터 2개의 구간 추출하기
        for(Work w : list) {
            int s1 = w.start;
            int e1 = w.start + 999;
            int s2 = w.end;
            int e2 = w.end + 999;
            int cnt1 = 0;   // [s1, e1] 구간의 처리량
            int cnt2 = 0;   // [s2, e2] 구간의 처리량
            // 3. [s1, e1], [s2, e2] 두 구간의 처리량 계산
            for(Work nw : list) {
                if(isPossible(s1, e1, nw.start, nw.end)) cnt1++;
                if(isPossible(s2, e2, nw.start, nw.end)) cnt2++;
            }
            // 4. 최대 처리량 갱신
            max = Math.max(max, Math.max(cnt1, cnt2));
        }
        return max;
    }

    // ms 단위로 변환
    public Work calc(String line) {
        int MS = 1000;
        String[] arr = line.substring(11).split(" ");
        // 1. 처리 시간 ms 단위로 환산
        int t = (int) (Double.parseDouble(arr[1].substring(0, arr[1].length() - 1)) * MS);
        arr = arr[0].split(":");
        // 2. 시분초 ms 단위로 환산
        int h = Integer.parseInt(arr[0]) * 60 * 60 * MS;
        int m = Integer.parseInt(arr[1]) * 60 * MS;
        int s = (int) (Double.parseDouble(arr[2]) * MS);
        // 3. 시작 시각, 끝 시각 계산
        int end = h + m + s;
        int start = end - t + 1;    // t = end - start + 1
        return new Work(start, end);
    }

    // [s, e] 구간에 [ns, ne] 구간이 걸치고 있는지 여부
    public boolean isPossible(int s, int e, int ns, int ne) {
        if(e < ns || ne < s) return false;
        return true;
    }
}