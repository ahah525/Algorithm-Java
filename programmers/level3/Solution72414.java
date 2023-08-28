package programmers.level3;


/**
 * [문제명] 광고 삽입
 * [풀이시간] 1시간 30분 / 1시간 10분
 * [한줄평] 접근 방법에 대한 감을 못잡아서 결국 풀이를 봤는데 이해하는데 시간이 너무 오래걸렸다.
 * / 1_v1 풀이가 최악의 상황에 O(30만 * 36만)으로 시간초과가 발생할 수도 있을 거라고 생각했는데, 통과를 하는 걸보니 해당 테스트 케이스는 포함이 되지 않은 것같다.
 * 1_v1. 누적합, 투포인터(성공)
 * [풀이]
 * 2_v1. 누적합, 투포인터(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/72414">문제</a>
 * @See <a href="https://velog.io/@hoonze/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-LEVEL3-%EA%B4%91%EA%B3%A0-%EC%82%BD%EC%9E%85-JAVA">풀이 참고</a>
 */
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
}