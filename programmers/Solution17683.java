package programmers;

/**
 * m: 멜로디를 담은 문자열
 * musicinfos: 방송된 곡의 정보를 담고 있는 배열(시작 시각, 끝 시각, 음악 제목, 악보 정보)
 * answer: 조건과 일치하는 음악 제목
 * - 조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환
 * - 재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환
 * - 조건이 일치하는 음악이 없을 때에는 “(None)”을 반환
 * ---------------------------------------
 * - 음악 제목, 재생이 시작되고 끝난 시각, 악보
 * - 멜로디와 악보에 사용되는 음은 C, C#, D, D#, E, F, F#, G, G#, A, A#, B 12개
 */
class Solution17683 {
    public static void main(String[] args) {
        String m1 = "ABCDEFG";
        String[] musicinfos1 = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        String m2 = "CC#BCC#BCC#BCC#B";
        String[] musicinfos2 = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};

        String m3 = "ABC";
        String[] musicinfos3 = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        String answer = solution(m3, musicinfos3);
        // HELLO
        System.out.println(answer);
    }

    public static String solution(String m, String[] musicinfos) {
        String answerTitle = "";
        int answerPlayTime = -1;
        m = change(m);

        for(String musicinfo : musicinfos) {
            String[] info = musicinfo.split(",");
            String[] start = info[0].split(":");    // 시작 시각
            String[] end = info[1].split(":");      // 끝 시각
            // 재생 시간
            int playTime = (Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1])) - (Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]));

            String title = info[2];         // 제목
            String music = change(info[3]); // 악보 정보
            int musicTime = music.length(); // 실제 음악 길이
            String realMusic;               // 재생된 악보 정보

            // 1. 실제 재생된 악보 정보 계산
            if(playTime <= musicTime) {
                realMusic = music.substring(0, playTime);
            } else {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < playTime / musicTime; i++) {
                    sb.append(music);
                }
                sb.append(music.substring(0, playTime % musicTime));
                realMusic = sb.toString();
            }
            // 2. 기억한 멜로디가 해당 재생된 악보 정보에 있는지 확인
            if(realMusic.contains(m)) {
                if(answerPlayTime < playTime) {
                    answerPlayTime = playTime;
                    answerTitle = title;
                }
            }
        }
        if(answerTitle.equals("")) {
            return "(None)";
        }
        return answerTitle;
    }

    public static String change(String m) {
        return m.replaceAll("C#", "H")
                .replaceAll("D#", "I")
                .replaceAll("F#", "J")
                .replaceAll("G#", "K")
                .replaceAll("A#", "L");
    }
}