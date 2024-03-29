package programmers.level2;


import java.util.*;

/**
 * [문제명] 주차 요금 계산
 * [풀이시간] / 45분 / 35분 / 30분(24분+6분)
 * [한줄평] 처음에 문제 조건을 꼼꼼하게 체크하지 않아 생각보다 시간이 오래걸렸던 문제였다. Map 2개를 쓰는 것과 Map 1개, Car 클래스를 쓰는 것 중 어느 것이 더 좋을지 고민해봐야할 문제
 * / 다시 푸니까 좀 더 쉬웠던 구현 문제였다.
 * / 해시를 이용해 풀 수 있는 쉬운 구현 문제였다. TreeMap 에 대한 복습이 필요하다.
 * 1_v1. TreeMap, 구현(성공)
 * [풀이] TreeMap(차번호, 누적주차시간), ArrayList(출차하지 않은 차번호 목록)
 * 1_v2. TreeMap, 구현(성공)
 * [풀이] TreeMap(차번호, 누적주차시간 & 출차여부),
 * 2_v1. HashMap(성공)
 * [풀이] HashMap(차번호, 누적주차시간 & 출차여부)
 * 3_v1. HashMap, 구현(성공)
 * [풀이] HashMap(차번호, 누적주차시간), LinkedList(출차하지 않은 차번호 목록)
 * 4_v1. HashMap(실패 - 1,3~12 런타임 에러)
 * [반례] 런타임 에러 원인 = map2.keySet()을 for-each로 탐색하면서 map2.remove()로 삭제한 것
 * 4_v2. HashMap(성공)
 * [풀이] HashMap(차번호, 누적주차시간), HashMap(차번호, 입차시각)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/92341">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/26732">반례</a>
 */
class Solution92341 {
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

    // 1_v1
    static int standardTime; // 기본시간
    static int standardFee;  // 기본요금
    static int unitTime;     // 단위시간
    static int unitFee;      // 단위요금
    static final int END_TIME = 23 * 60 + 59; // 11:59 분으로 환산

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

    // 2_v1
    class Car {
        int total;      // 누적 주차 시간
        boolean isOut;  // 출차 여부

        Car(int total, boolean isOut) {
            this.total = total;
            this.isOut = isOut;
        }
    }

    public List<Integer> solution2(int[] fees, String[] records) {
        // 1. 기본 시간, 기본 요금, 단위 시간, 단위 요금
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        // 2.(차량번호, 주차 정보) 계산
        Map<String, Car> map = new HashMap<>();
        for(String record : records) {
            String[] r = record.split(" ");
            String[] times = r[0].split(":");
            int time = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]); // 시각(분)
            String num = r[1];  // 차량 번호
            String type = r[2]; // 내역
            // 3. 기록에 따라 해당 차량의 누적 주차 시각, 출차 여부 업데이트
            // 입차 = 현재누적시간 - 시각, 출차 = 현재누적시간 + 시각
            if(!map.containsKey(num)) {
                // map 에 없으면 무조건 입차
                map.put(num, new Car(-time, false));
            } else {
                int total = map.get(num).total;
                if(type.equals("IN")) {
                    // 입차인 경우
                    map.put(num, new Car(total - time, false));
                } else {
                    // 출차인 경우
                    map.put(num, new Car(total + time, true));
                }
            }
        }
        // 4. Key 값 오름차순 정렬
        List<String> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet);
        // 5. 차량번호별 주차요금 계산
        List<Integer> answer = new ArrayList<>();
        for(String num : keySet) {
            Car car = map.get(num);
            // 6. 출차 기록이 없는 차량은 23:59에 출차했으므로 누적 시간에 반영
            int total = car.total;
            if(!car.isOut) total += 1439;   // 23:59 = 1439분
            // 7. 모든 차량 = 기본 요금, 단위 시간 초과한 차량 = 초과 요금 계산
            int fee = basicFee;
            if(basicTime < total)
                fee += unitFee * Math.ceil((double) (total - basicTime) / unitTime);
            answer.add(fee);
        }
        return answer;
    }

    // 3_v1
//    Queue<String> q; // 출차하지 않은 차 목록
//    int END_TIME = 23 * 60 + 59;
//    int STANDARD_TIME, STANDARD_FEE, PER_TIME, PER_FEE;
//    public int[] solution3(int[] fees, String[] records) {
//        STANDARD_TIME = fees[0]; // 기본시간
//        STANDARD_FEE = fees[1]; // 기본요금
//        PER_TIME = fees[2]; // 단위시간
//        PER_FEE = fees[3]; // 단위요금
//        // 자동차 번호, 누적 주차 시간
//        Map<String, Integer> map = new HashMap<>();
//        // 출차하지 않은 차 목록
//        q = new LinkedList<>();
//        // 자동차 번호, 출차 여부
//        for(String record : records) {
//            String[] r = record.split(" ");
//            int time = getTime(r[0]);
//            String num = r[1];
//            String type = r[2];
//            int t = calc(num, time, type);
//            map.put(num, map.getOrDefault(num, 0) + t);
//        }
//        // 출차하지 않은 차의 주차시간 정산
//        while(!q.isEmpty()) {
//            String num = q.poll();
//            map.put(num, map.get(num) + END_TIME);
//        }
//        // 차번호 오름차순 정렬
//        List<String> keys = new ArrayList<>(map.keySet());
//        Collections.sort(keys);
//        // 최종 주차 요금 계산
//        int[] answer = new int[keys.size()];
//        int i = 0;
//        for(String num : keys) {
//            answer[i++] = getFee(map.get(num));
//        }
//        return answer;
//    }
//
//    public int getFee(int time) {
//        int fee = STANDARD_FEE;
//        int addTime = time - STANDARD_TIME;
//        if(addTime <= 0) return fee;
//        return fee + (int) Math.ceil((double) addTime / PER_TIME) * PER_FEE;
//    }
//
//    public int getTime(String time) {
//        String[] t = time.split(":");
//        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
//    }
//
//    public int calc(String num, int time, String type) {
//        if(type.equals("IN")) {
//            q.add(num);
//            return -time;
//        }
//        q.remove(num);
//        return time;
//    }

    // 4_v2
    public int[] solution4(int[] fees, String[] records) {
        int END = 23 * 60 + 59;
        int standardTime = fees[0];
        int standardFee = fees[1];
        int perTime = fees[2];
        int perFee = fees[3];
        // (차량 번호, 누적 주차 시간)
        Map<String, Integer> map1 = new HashMap<>();
        // (차량 번호, 입차 시각)
        Map<String, Integer> map2 = new HashMap<>();
        for(String record : records) {
            String[] r = record.split(" ");
            int time = timeToInt(r[0]);
            String num = r[1];
            String type = r[2];
            if(type.equals("IN")) {
                // 입차
                map2.put(num, time);
            } else {
                // 출차
                int parking = time - map2.get(num); // 주차 시간
                // 주차시간 합산
                map1.put(num, map1.getOrDefault(num, 0) + parking);
                map2.remove(num);
            }
        }
        // 출차 기록 없는 차들 정산
        for(String num : map2.keySet()) {
            int parking = END - map2.get(num); // 주차 시간
            // 주차시간 합산
            map1.put(num, map1.getOrDefault(num, 0) + parking);
//            map2.remove(num); // 런타임 에러
        }
        //
        List<String> keys = new ArrayList<>(map1.keySet());
        Collections.sort(keys);
        int[] answer = new int[keys.size()];
        for(int i = 0; i < keys.size(); i++) {
            String num = keys.get(i);
            int time = map1.get(num);
            int fee = standardFee;
            // 기준 시간 초과했으면,
            if(time > standardTime) {
                fee += Math.ceil((double) (time - standardTime) / perTime) * perFee;
            }
            answer[i] = fee;
        }
        return answer;
    }

    public int timeToInt(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}