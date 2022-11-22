package programmers.level2;


import java.util.*;

/**
 * [문제명] [3차] 파일명 정렬
 * [한줄평]
 * v1. List<String>를 Comparator 로 정렬하기(실패 - 6, 7, 8, 9)
 *
 * @See <a href="https://school.programmers.co.kr/questions/33195">반례</a>
 */
class Solution17686 {
    public static void main(String[] args) {
        // ["img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"]
        String[] files1 = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        List<String> answer1 = solution(files1);
        System.out.println(answer1);

        // ["A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"]
        String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        List<String> answer2 = solution(files2);
        System.out.println(answer2);
    }

    /**
     *
     * @param files 1000 개 이하의 파일명을 포함하는 문자열 배열
     * - 각 파일명은 100 글자 이하 길이로, 영문 대소문자, 숫자, 공백(" "), 마침표("."), 빼기 부호("-")만으로 이루어져 있다. 파일명은 영문자로 시작하며, 숫자를 하나 이상 포함하고 있다.
     * - 중복된 파일명은 없으나, 대소문자나 숫자 앞부분의 0 차이가 있는 경우는 함께 주어질 수 있다. (muzi1.txt, MUZI1.txt, muzi001.txt, muzi1.TXT는 함께 입력으로 주어질 수 있다.)
     * @return 아래 기준에 따라 정렬된 배열을 리턴
     * - 파일명은 우선 HEAD 부분을 기준으로 사전 순으로 정렬한다. 이때, 문자열 비교 시 대소문자 구분을 하지 않는다. MUZI와 muzi, MuZi는 정렬 시에 같은 순서로 취급된다.
     * - 파일명의 HEAD 부분이 대소문자 차이 외에는 같을 경우, NUMBER의 숫자 순으로 정렬한다. 9 < 10 < 0011 < 012 < 13 < 014 순으로 정렬된다. 숫자 앞의 0은 무시되며, 012와 12는 정렬 시에 같은 같은 값으로 처리된다.
     * - 두 파일의 HEAD 부분과, NUMBER의 숫자도 같을 경우, 원래 입력에 주어진 순서를 유지한다. MUZI01.zip과 muzi1.png가 입력으로 들어오면, 정렬 후에도 입력 시 주어진 두 파일의 순서가 바뀌어서는 안 된다.
     */
    public static List<String> solution(String[] files) {
        List<String> answer = new ArrayList<>();
        for(String file : files) {
            answer.add(file);
        }

        Collections.sort(answer, (o1, o2) -> {
            StringBuilder sbh = new StringBuilder();
            StringBuilder sbn = new StringBuilder();
            boolean findNumber = false; // 숫자 찾음 여부
            for(int i = 0; i < o1.length(); i++) {
                char c = o1.charAt(i);
                if(('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z')) {
                    if(findNumber)
                        break;
                    sbh.append(c);
                } else if('0' <= c && c <= '9') {
                    sbn.append(c);
                    findNumber = true;
                }
            }
            String head1 = sbh.toString().toUpperCase();
            Integer number1 = Integer.parseInt(sbn.toString());

            sbh = new StringBuilder();
            sbn = new StringBuilder();
            findNumber = false; // 숫자 찾음 여부
            for(int i = 0; i < o2.length(); i++) {
                char c = o2.charAt(i);
                if(('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z')) {
                    if(findNumber)
                        break;
                    sbh.append(c);
                } else if('0' <= c && c <= '9') {
                    sbn.append(c);
                    findNumber = true;
                }
            }
            String head2 = sbh.toString().toUpperCase();
            Integer number2 = Integer.parseInt(sbn.toString());

//            System.out.println("head1 = " + head1);
//            System.out.println("head2 = " + head2);
//            System.out.println("number1 = " + number1);
//            System.out.println("number2 = " + number2);
//            System.out.println("-----------------------");

            if(head1.equals(head2)) {
                return number1.compareTo(number2);
            }
            return head1.compareTo(head2);
        });
        return answer;
    }
}