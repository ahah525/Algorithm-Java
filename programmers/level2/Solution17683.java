package programmers.level2;


/**
 * [문제명] [3차] 방금그곡
 * [풀이시간] / 27분 / 23분
 * [한줄평] #이 붙은 음을 하나의 다른 음으로 치환해서 풀어야한다는 힌트를 받고 풀었던 문제였다. / 2번째 풀때 바로 치환을 해야겠다고 떠올려서 빨리 풀 수 있었던 것 같다.
 * / 문자열을 이용해 구현하는 문제였다.
 * 1_v1. replaceAll()(성공)
 * 2_v1. replace()(성공)
 * 3_v1. 구현, 문자열(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17683">문제</a>
 */
public class Solution17683 {
    public static void main(String[] args) {
        // HELLO
        String m1 = "ABCDEFG";
        String[] musicinfos1 = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String answer1 = solution(m1, musicinfos1);
        System.out.println(answer1);

        // FOO
        String m2 = "CC#BCC#BCC#BCC#B";
        String[] musicinfos2 = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        String answer2 = solution(m2, musicinfos2);
        System.out.println(answer2);

        // WORLD
        String m3 = "ABC";
        String[] musicinfos3 = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String answer3 = solution(m3, musicinfos3);
        System.out.println(answer3);
    }

    // 2_v1
    /**
     * @param m 멜로디를 담은 문자열
     * @param musicinfos 방송된 곡의 정보를 담고 있는 배열(시작 시각, 끝 시각, 음악 제목, 악보 정보)
     * - 멜로디와 악보에 사용되는 음은 C, C#, D, D#, E, F, F#, G, G#, A, A#, B 12개
     * @return 조건과 일치하는 음악 제목
     * - 조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환
     * - 재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환
     * - 조건이 일치하는 음악이 없을 때에는 “(None)”을 반환
     */
    public static String solution(String m, String[] musicinfos) {
        int answerPlay = 0;
        String answerTitle = "";
        // 1. 멜로디 변환
        m = change(m);
        for(String musicInfo : musicinfos) {
            String[] info = musicInfo.split(",");
            String[] start = info[0].split(":");    // 음악 시작 시각
            String[] end = info[1].split(":");      // 음악 끝 시각
            String title = info[2]; // 음악 제목
            // 1. 재생 시간 계산
            int play = (Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]))
                    - (Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]));
            // 2. 악보 정보 변환
            String sheet = change(info[3]);
            // 3. 실제로 재생된 악보 정보 만들기
            int len = sheet.length();   // 재생된 음악 길이
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < play / len; i++) {
                sb.append(sheet);
            }
            sb.append(sheet.substring(0, play % len));
            sheet = sb.toString();
            // 4. 실제로 재생된 악보에 멜로디가 있는지 검사
            if(sheet.contains(m)) {
                if(play > answerPlay) {
                    // 재생길이가 더 길 때만 답 수정
                    answerPlay = play;
                    answerTitle = title;
                }
            }
        }
        return answerPlay == 0 ? "(None)" : answerTitle;
    }

    // 악보 변환
    public static String change(String s) {
        return s.replace("C#", "H")
                .replace("D#", "I")
                .replace("F#", "J")
                .replace("G#", "K")
                .replace("A#", "L");
    }

    // 3_v1
    public String solution2(String m, String[] musicInfos) {
        int len = 0;
        String title = "(None)";
        m = change(m);
        for(String musicInfo : musicInfos) {
            String[] info = musicInfo.split(",");
            int play = getTime(info[1]) - getTime(info[0]);
            String playMusic = getPlayMusic(change(info[3]), play);
            if(playMusic.contains(m) && len < play) {
                title = info[2];
                len = play;
            }
        }
        return title;
    }

    // 시간 정보를 분으로 변환
    public int getTime(String s) {
        String[] t = s.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }

    // 실제 재생된 악보 문자열 리턴
    public String getPlayMusic(String music, int play) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < play / music.length(); i++) {
            sb.append(music);
        }
        sb.append(music, 0, play % music.length());
        return sb.toString();
    }
}
