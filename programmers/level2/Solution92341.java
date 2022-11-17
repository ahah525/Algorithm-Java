package programmers.level2;


import java.util.*;

/**
 * 00:00부터 23:59까지의 입/출차 내역을 바탕으로 차량별 누적 주차 시간을 계산하여 요금을 일괄로 정산
 * - 어떤 차량이 입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주
 *
 * v1. TreeMap<String, Integer>(각 차의 누적 주차시간), ArrayList<String>(출차 기록없는 차량 번호 리스트)
 * v2. TreeMap<String, Car>
 * - TreeMap 은 자동으로 key 값 기준 오름차순 정렬됨
 */
class Solution92341 {
    static int standardTime; // 기본시간
    static int standardFee;  // 기본요금
    static int unitTime;     // 단위시간
    static int unitFee;      // 단위요금
    static final int END_TIME = 23 * 60 + 59; // 11:59 분으로 환산

    public static void main(String[] args) {
        int[] fees1 = {180, 5000, 10, 600};
        String[] records1 = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        // [14600, 34400, 5000]
        List<Integer> answer1 = solution(fees1, records1);
        System.out.println(answer1);

        int[] fees2 = {120, 0, 60, 591};
        String[] records2 = {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"};
        // [0, 591]
        List<Integer> answer2 = solution(fees2, records2);
        System.out.println(answer2);

        int[] fees3 = {1, 461, 1, 10};
        String[] records3 = {"00:00 1234 IN"};
        // [14841]
        List<Integer> answer3 = solution(fees3, records3);
        System.out.println(answer3);
    }

    /**
     * @param fees 주차 요금을 나타내는 정수 배열 [기본 시간, 기본 요금, 단위 시간, 단위 요금]
     * @param records 자동차의 입/출차 내역을 나타내는 문자열 배열 [시각, 차량번호, 내역]
     * @return 차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아 리턴
     */
    public static List<Integer> solution(int[] fees, String[] records) {
        List<Integer> answer = new ArrayList<>();       // 각 차의 주차 요금
        Map<String, Integer> timeMap = new TreeMap<>(); // 각 차의 누적 주차 시간
        List<String> outList = new ArrayList<>();       // 출차 시켜야 할 차 번호 리스트

        standardTime = fees[0]; // 기본시간
        standardFee = fees[1];  // 기본요금
        unitTime = fees[2];     // 단위시간
        unitFee = fees[3];      // 단위요금

        for(String record : records) {
            String[] split = record.split(" ");
            String[] times = split[0].split(":");   // 입/출차시각
            int time = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);    // 분으로 환산
            String number = split[1];   // 차량 번호
            String type = split[2];     // 입/출차 여부

            if(type.equals("IN")) {
                // 1. 입차
                timeMap.put(number, timeMap.getOrDefault(number, 0) -time);
                outList.add(number);
            } else {
                // 2. 출차
                timeMap.put(number, timeMap.getOrDefault(number, 0) + time);
                outList.remove(number);
            }
        }
//        System.out.println(outList);
//        System.out.println(timeMap);

        // 각 차의 주차 요금 계산
        for(String number : timeMap.keySet()) {
            int time = timeMap.get(number);
            // 출차 기록없는 경우 요금 추가 계산
            if(outList.contains(number)) {
                time += END_TIME;
            }
//            System.out.println("time = " + time);
            answer.add(calcFee(time));
        }
        return answer;
    }

    // 주차요금 계산
    public static int calcFee(int time) {
        int fee = standardFee;
        if(standardTime < time) {
            // 추가 요금 계산
            fee += Math.ceil((double) (time - standardTime) / unitTime) * unitFee;
        }
        return fee;
    }
}