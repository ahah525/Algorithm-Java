package programmers.level2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * [문제명] [3차] 파일명 정렬
 * [풀이시간] / (40분)
 * [한줄평] 구현만하면 되는 쉬운문제에 속했지만 문제 조건을 꼼꼼하게 체크하지 않아 틀렸던 문제였다. 숫자를 판별하는 로직 부분이 다양할 것 같다.
 * 1_v1. List<String>을 Comparator 로 정렬하기(실패 - 6, 7, 8, 9)
 * - HEAD는 숫자가 아닌 문자로 이루어져 있다는 사실을 간과함(공백, 마침표, 빼기 부호에 대한 처리가 없음)
 * 1_v2. List<String>을 Comparator 로 정렬하기(성공)
 * - HEAD 는 숫자가 아닌 모든 문자, NUMBER 는 HEAD 발견 이후 최초로 발견된 연속 숫자
 * 2_v1. Arrays.sort(), Comparator (실패 - 4~20 실패)
 *
 *
 * >> 숫자 판별법
 * 1. 아스키코드 조건문
 * 2. Character.isDigit(?)
 * 3. 정규 표현식
 *
 * @See <a href="https://school.programmers.co.kr/questions/33195">반례</a>
 * @See <a href="https://wonit.tistory.com/143">Comparable vs Comparator</a>
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17686/solution_groups?language=java">다른 풀이</a>
 */
class Solution17686 {
    public static void main(String[] args) {
//        // ["img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"]
//        String[] files1 = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
//        List<String> answer1 = solution(files1);
//        System.out.println(answer1);
//
//        // ["A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"]
//        String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
//        List<String> answer2 = solution(files2);
//        System.out.println(answer2);

        // ["img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"]
        String[] files1 = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        System.out.println(Arrays.toString(solution2(files1)));

        // ["A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"]
        String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        System.out.println(Arrays.toString(solution2(files2)));
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
                if('0' <= c && c <= '9') {
                    sbn.append(c);
                    findNumber = true;
                } else {
                    if(findNumber)
                        break;
                    sbh.append(c);
                }
            }
            String head1 = sbh.toString().toUpperCase();
            Integer number1 = Integer.parseInt(sbn.toString());

            sbh = new StringBuilder();
            sbn = new StringBuilder();
            findNumber = false; // 숫자 찾음 여부
            for(int i = 0; i < o2.length(); i++) {
                char c = o2.charAt(i);
                if('0' <= c && c <= '9') {
                    sbn.append(c);
                    findNumber = true;
                } else {
                    if(findNumber)
                        break;
                    sbh.append(c);
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

    public static String[] solution2(String[] files) {
//         for(String file : files) {
//             String[] split = split(file.toLowerCase());
//             System.out.println(Arrays.toString(split));
//         }
        Arrays.sort(files, (o1, o2) -> {
            String[] arr1 = split(o1.toLowerCase());
            String[] arr2 = split(o2.toLowerCase());

            if(arr1[0].equals(arr2[0])) {
                if(arr1[1].equals(arr2[1])) {
                    // 순서 안바꿈
                    return -1;
                }
                // NUMBER 비교
                return Integer.parseInt(arr1[1]) - Integer.parseInt(arr2[1]);
            }
            // HEAD 비교
            return arr1[0].compareTo(arr2[0]);
        });

        return files;
    }

    public static String[] split(String s) {
        int i = 0;
        String[] arr = new String[3];
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(i == 0 && Character.isDigit(c)) {
                arr[0] = sb.toString();
                sb.setLength(0);
                i = 1;
            } else if(i == 1 && !Character.isDigit(c)) {
                arr[1] = sb.toString();
                sb.setLength(0);
                i = 2;
            }
            sb.append(c);
        }
        arr[2] = sb.toString();
        return arr;
    }
}