package programmers.level3;


/**
 * [문제명] 광고 삽입
 * [풀이시간] 1시간 30분 / 1시간 10분, 20분 / 50분
 * [한줄평] 접근 방법에 대한 감을 못잡아서 결국 풀이를 봤는데 이해하는데 시간이 너무 오래걸렸다.
 * / 1_v1 풀이는 최악의 상황에 O(30만 * 36만)으로 시간초과가 발생할 수도 있는데 실제로는 해당 테스트 케이스가 포함되지 않아 통과하는 것 같다. 그래서 2_v2 풀이를 참고해서 다시 풀었다.
 * / 어떻게 누적합을 활용할 수 있는지 몰라서 결국 풀이를 봤고 IMOS 알고리즘을 적용해서 풀었다. 꼭 복습이 필요하다!
 * 1_v1. 누적합, 투포인터(성공)
 * [풀이] 특정 초에 시청중인 사람의 수를 계산하는 방법1 : 구간의 모든 값에 +1 하기
 * 1. times[i]++ (start <= i < end)
 * [시간복잡도] O(N * M + M)
 * - N: log 개수(최대 300,000)
 * - M: 동영상 재생 시간(최대 360,000)
 * 2_v1. 누적합, 투포인터(성공)
 * [풀이] 1_v1 동일
 * 2_v2. 누적합, 투포인터(성공) -> 추천
 * [풀이] 특정 초에 시청중인 사람의 수를 계산하는 방법2 : 구간의 시작점에 +1, 끝점에 -1 하고 마지막에 누적합 계산
 * 1. times[start]++, times[end]--
 * 2. times[i] += times[i-1]
 * [시간복잡도] O(N + M)
 * - N: log 개수(최대 300,000)
 * - M: 동영상 재생 시간(최대 360,000)
 * 3_v1. 누적합, 투포인터/슬라이딩 윈도우(성공) -> 추천
 * [풀이] 2_v2 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/72414">문제</a>
 * @See <a href="https://velog.io/@hoonze/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-LEVEL3-%EA%B4%91%EA%B3%A0-%EC%82%BD%EC%9E%85-JAVA">풀이 참고</a>
 * @See <a href="https://blog.encrypted.gg/995">풀이 참고2</a>
 **/
class Solution72414 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    public String solution(String playTime, String advTime, String[] logs) {
        int pt = toInt(playTime);  // 동영상 재생 시간
        int at = toInt(advTime);   // 광고 재생 시간
        int[] times = new int[pt + 1];
        for(String log : logs) {
            String[] arr = log.split("-");
            int start = toInt(arr[0]);
            int end = toInt(arr[1]);
            // start ~ end 구간 누적합
            for(int i = start; i < end; i++) {
                times[i]++;
            }
        }
        // 1. 0 ~ at 초까지의 누적합 계산
        long sum = 0;
        for(int i = 0; i < at; i++) {
            sum += times[i];
        }
        // 2. 최댓값, 구간 시작/끝 값 초기화
        long max = sum;
        int maxStart = 0;
        for(int s = 1, e = at; e <= pt; s++, e++) {
            sum += times[e] - times[s - 1];
            if(max < sum) {
                max = sum;
                maxStart = s;
            }
        }
        return toString(maxStart);
    }

    // 문자열을 초로 변환
    public int toInt(String time) {
        String[] t = time.split(":");
        int hour = Integer.parseInt(t[0]) * 3600;
        int minute = Integer.parseInt(t[1]) * 60;
        int second = Integer.parseInt(t[2]);
        return hour + minute + second;
    }

    // 초를 문자열로 변환
    public String toString(int time) {
        int hour = time / 3600;
        time %= 3600;
        int minute = time / 60;
        time %= 60;
        int second = time;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    // 2_v1
    public String solution2(String playTime, String advTime, String[] logs) {
        // 1. 재생시간, 광고시간 초 단위로 변환
        int pt = timeToInt(playTime);
        int at = timeToInt(advTime);
        // 2. i초 대에 동영상을 재생한 시청자 수 구하기
        int[] times = new int[pt + 1];
        for(String log : logs) {
            String[] t = log.split("-");
            int st = timeToInt(t[0]);
            int et = timeToInt(t[1]);
            for(int i = st; i < et; i++) {
                times[i]++;
            }
        }
        // 3.
        int s = 0;
        int e = at;
        // 3. [0, at) 구간의 시청자들의 누적 재생 시간 초기화
        long sum = 0;
        for(int i = 0; i < at; i++) {
            sum += times[i];
        }
        long max = sum;
        int start = 0;
        while(true) {
            // 4. [s, e) 구간의 시청자들의 누적 재생 시간 계산
            sum = sum + times[e++] - times[s++];
            // 5. 최댓값 갱신
            if(sum > max) {
                max = sum;
                start = s;
            }
            if(e > pt) break;
        }
        // 6. 초를 문자열로 변환
        return timeToString(start);
    }



    public int timeToInt(String time) {
        String[] t = time.split(":");
        int h = Integer.parseInt(t[0]) * 60 * 60;
        int m = Integer.parseInt(t[1]) * 60;
        int s = Integer.parseInt(t[2]);
        return h + m + s;
    }

    public String timeToString(int t) {
        int s = t % 60;
        t /= 60;
        int m = t % 60;
        t /= 60;
        int h = t;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    // 2_v2, 3_v1
    public String solution3(String playTime, String advTime, String[] logs) {
        int pt = timeToInt(playTime);   // 전체 재생 구간
        int at = timeToInt(advTime);    // 광고 재생 구간
        // 1. i초의 시청자수 구하기(누적합)
        int[] viewers = new int[pt + 1];
        for(String log : logs) {
            String[] l = log.split("-");
            int s = timeToInt(l[0]);
            int e = timeToInt(l[1]);
            viewers[s]++;
            viewers[e]--;
        }
        for(int i = 1; i < viewers.length; i++) {
            viewers[i] += viewers[i - 1];
        }
        // 2. [0, at) 구간합 초기화
        long sum = 0;
        for(int i = 0; i < at; i++) {
            sum += viewers[i];
        }
        // 3. [0, pt]에서 at 구간합의 최댓값 구하기
        long max = sum;
        int maxStart = 0;
        for(int i = at, j = 0; i < viewers.length; i++, j++) {
            sum += viewers[i];
            sum -= viewers[j];
            if(max < sum) {
                max = sum;
                maxStart = j + 1;
            }
        }
        return timeToString(maxStart);
    }
}