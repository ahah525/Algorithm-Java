package programmers.level3;


import java.util.*;

/**
 * [문제명] 베스트 앨범
 * [풀이시간] / 40분 / 18분 / 20분
 * [한줄평] 문제가 어렵지는 않았으나 Map 을 Value 값을 기준으로 정렬하는 부분은 검색을 통해 해결했던 약간은 까다로운 문제였다.
 * / Set 을 정렬하려면 List 로 바꿔서 정렬해아한다. 6번에서 PriorityQueue 의 값을 역순으로 리스트에 넣을 때 이렇게 로직을 짜도 괜찮은건지 궁금하다. 만약 최대 2개가 아니라 n개 라면 어떻게 로직을 바꿔야할지 생각해봐야겠다.
 * / 3번째 푸니까 확실히 쉽게 풀 수 있었던 문제다.
 * / 더 이상 안풀어도 쉽게 풀 수 있는 문제다.
 * / 익숙한 문제라 더 안풀어도 봐도 될 것 같다.
 * 1_v1. HashMap 2개, ArrayList 1개(성공)
 * - HashMap : (장르명, 장르별 총재생횟수), (장르명, 장르별 앨범에 들어갈 노래)
 * - ArrayList : 앨범에 들어갈 노래의 고유번호 리스트
 * 2_v1. HashMap, 정렬(성공)
 * [풀이] 우선순위 큐에 최대 2개만 넣는다.
 * 3_v1. HashMap, 정렬(성공) -> 빠름
 * [풀이] 우선순위 큐에 모든 곡을 넣고 2개만 꺼낸다.
 * 4_v1. HashMap, 정렬(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42579">문제</a>
 */
class Solution42579 {
    public static void main(String[] args) {
        // [4, 1, 3, 0]
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        System.out.println(solution(genres, plays));
    }

    /**
     * @param genres 노래의 장르를 나타내는 문자열 배열
     * - 장르 종류는 100개 미만
     * @param plays 노래별 재생 횟수를 나타내는 정수 배열
     * - genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하
     * @return 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return
     * [노래를 수록하는 기준]
     * 1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
     * 2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
     * 3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
     */
    public static List<Integer> solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();          // 앨범에 들어갈 노래의 고유번호 리스트
        Map<String, Integer> totalPlay = new HashMap<>();  // (장르명, 장르별 총재생횟수)
        Map<String, Queue<Music>> album = new HashMap<>(); // (장르명, 장르별 앨범에 들어갈 노래)

        int n = genres.length;
        for(int i = 0; i < n; i++) {
            String genre = genres[i];   // 장르
            int play = plays[i];        // 재생횟수
            // 1. 장르별 총 재생횟수에 반영
            totalPlay.put(genre, totalPlay.getOrDefault(genre, 0) + play);
            // 앨범에 들어갈 수 있는지 검사
            // 2. 앨범에 장르가 없으면 PriorityQueue 생성
            if(!album.containsKey(genre)) {
                album.put(genre, new PriorityQueue<>((o1, o2) -> {
                    // 재생 순위 오름차순 -> 고유번호 내림차순 정렬(4번 때문에)
                    if(o1.play == o2.play) {
                        return o2.id - o1.id;
                    }
                    return o1.play - o2.play;
                }));
            }
            // 3. 해당 장르별 큐에 노래 삽입
            album.get(genre).add(new Music(i, play));
            // 4. 수록곡이 2개 보다 많으면 1개 빼기(가장 재생 순위가 적고, 고유 번호가 높은 노래가 삭제됨)
            if(album.get(genre).size() > 2) {
                album.get(genre).poll();
            }
        }
        // 5. 장르별 총재생횟수(value) 기준으로 내림차순 정렬
        List<String> keySet = new ArrayList<>(totalPlay.keySet());
        keySet.sort((o1, o2) -> totalPlay.get(o2) - totalPlay.get(o1));
        // 6. 수록 기준에 따라 앨범 리스트에 노래 고유번호 삽입
        for(String key : keySet) {
            Queue<Music> q = album.get(key);
            // 큐에는 재생 순위 오름차순 -> 고유번호 내림차순 정렬된 상태로 저장되어있기 때문에, 정답 리스트에는 역순으로 넣기
            if(q.size() == 2) {
                // 크기가 2인 경우 맨 마지막 값부터 넣기
                int a = q.poll().id;
                int b = q.poll().id;
                answer.add(b);
                answer.add(a);
            } else {
                // 크기가 1이면 꺼낸 값 바로 넣기
                answer.add(q.poll().id);
            }
        }
        return answer;
    }

    public static class Music {
        int id;     // 고유 번호
        int play;   // 재생 횟수

        public Music(int id, int play) {
            this.id = id;
            this.play = play;
        }
    }

    // 2_v1
    public int[] solution2(String[] genres, int[] plays) {
        // (장르, 총 재생횟수)
        Map<String, Integer> playMap = new HashMap<>();
        // (장르, 베스트 앨범에 들어갈 노래 번호 우선순위큐)
        Map<String, Queue<Integer>> map = new HashMap<>();
        for(int i = 0; i < genres.length; i++) {
            if(!map.containsKey(genres[i])) {
                playMap.put(genres[i], 0);
                // 1. 재생 횟수 오름차순 정렬, 번호 내림차순 정렬
                map.put(genres[i], new PriorityQueue<>((o1, o2) -> {
                    if(plays[o1] == plays[o2]) return o2 - o1;
                    return plays[o1] - plays[o2];
                }));
            }
            Queue<Integer> pq = map.get(genres[i]);
            // 2. 넣기, 총 재생횟수 계산
            pq.add(i);
            playMap.put(genres[i], playMap.get(genres[i]) + plays[i]);
            // 3. 3개이상이면 1개 빼기
            if(pq.size() > 2) pq.poll();
        }
        // 4. 개수 계산
        int n = 0;
        for(Queue<Integer> q : map.values()) {
            n += q.size();
        }
        // 5. 장르별 총 재생횟수 별로 내림차순 정렬
        List<String> keys = new ArrayList<>(playMap.keySet());
        Collections.sort(keys, (o1, o2) -> {
            return playMap.get(o2) - playMap.get(o1);
        });
        // 6.
        int[] answer = new int[n];
        int i = 0;
        for(String key : keys) {
            Queue<Integer> q = map.get(key);
            if(q.size() == 2) {
                answer[i + 1] = q.poll();
                answer[i] = q.poll();
                i += 2;
            } else {
                answer[i++] = q.poll();
            }
        }
        return answer;
    }

    // 3_v1, 4_v1
    public int[] solution3(String[] genres, int[] plays) {
        // (장르, 총 재생 횟수)
        Map<String, Integer> map2 = new HashMap<>();
        // (장르, 노래 고유 번호)
        Map<String, Queue<Integer>> map1 = new HashMap<>();
        for(int i = 0; i < plays.length; i++) {
            if(!map1.containsKey(genres[i])) {
                map1.put(genres[i], new PriorityQueue<>((o1, o2) -> {
                    if(plays[o1] == plays[o2]) return o1 - o2;
                    return plays[o2] - plays[o1];
                }));
            }
            Queue<Integer> pq = map1.get(genres[i]);
            pq.add(i);
            map2.put(genres[i], map2.getOrDefault(genres[i], 0) + plays[i]);
        }
        // 장르의 총 재생횟수 내림차순 정렬
        List<String> keys = new ArrayList<>(map2.keySet());
        Collections.sort(keys, (o1, o2) -> map2.get(o2) - map2.get(o1));
        //
        List<Integer> list = new ArrayList<>();
        for(String key : keys) {
            Queue<Integer> pq = map1.get(key);
            int cnt = 0;
            while(!pq.isEmpty() && cnt < 2) {
                list.add(pq.poll());
                cnt++;
            }
        }
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}