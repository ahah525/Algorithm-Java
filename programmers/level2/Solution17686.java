package programmers.level2;


import java.util.*;

/**
 * [문제명] [3차] 파일명 정렬
 * [풀이시간] / 50분(40분 + 10분)
 * [한줄평] 구현만하면 되는 쉬운문제에 속했지만 문제 조건을 꼼꼼하게 체크하지 않아 틀렸던 문제였다. 숫자를 판별하는 로직 부분이 다양할 것 같다.
 * 1_v1. List<String>을 Comparator 로 정렬하기(실패 - 6, 7, 8, 9)
 * - HEAD는 숫자가 아닌 문자로 이루어져 있다는 사실을 간과함(공백, 마침표, 빼기 부호에 대한 처리가 없음)
 * 1_v2. List<String>을 Comparator 로 정렬하기(성공)
 * - HEAD 는 숫자가 아닌 모든 문자, NUMBER 는 HEAD 발견 이후 최초로 발견된 연속 숫자
 * 2_v1. Arrays.sort(), Comparator (실패 - 4~20 실패)
 * 2_v2. Arrays.sort(), Comparator (성공)
 * [해결] tail 부분이 없는 경우 split() 메서드에서 arr[1]에 number 값이 저장되지 않는 문제 해결
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17686">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/33195">반례</a>
 * @See <a href="https://wonit.tistory.com/143">Comparable vs Comparator</a>
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
        System.out.println(Arrays.toString(
                solution2(new String[] {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"})));

        // ["A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"]
        System.out.println(Arrㅎays.toString(
                solution2(new String[] {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"})));
    }

    // 1_v2
    /**
     * @param files 1000 개 이하의 파일명을 포함하는 문자열 배열
     * @return 아래 기준에 따라 정렬된 배열을 리턴
     * 1. 파일명은 우선 HEAD 부분을 기준으로 사전 순으로 정렬한다. 이때, 문자열 비교 시 대소문자 구분을 하지 않는다. MUZI와 muzi, MuZi는 정렬 시에 같은 순서로 취급된다.
     * 2. 파일명의 HEAD 부분이 대소문자 차이 외에는 같을 경우, NUMBER의 숫자 순으로 정렬한다. 9 < 10 < 0011 < 012 < 13 < 014 순으로 정렬된다. 숫자 앞의 0은 무시되며, 012와 12는 정렬 시에 같은 같은 값으로 처리된다.
     * 3. 두 파일의 HEAD 부분과, NUMBER의 숫자도 같을 경우, 원래 입력에 주어진 순서를 유지한다. MUZI01.zip과 muzi1.png가 입력으로 들어오면, 정렬 후에도 입력 시 주어진 두 파일의 순서가 바뀌어서는 안 된다.
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

            if(head1.equals(head2)) {
                return number1.compareTo(number2);
            }
            return head1.compareTo(head2);
        });
        return answer;
    }

    // 2_v2
    public static String[] solution2(String[] files) {
        Arrays.sort(files, (o1, o2) -> {
            // 1. 문자열을 [HEAD, NUMBER] 로 분리하기
            String[] arr1 = split(o1);
            String[] arr2 = split(o2);
            // 2. 기준에 따라 비교
            if(arr1[0].equals(arr2[0])) {
                // HEAD 같을 때, NUMBER 다를 때 -> NUMBER 오름차순
                // HEAD, NUMBER 같을 때 -> 순서 안바꿈
                return Integer.parseInt(arr1[1]) - Integer.parseInt(arr2[1]);
            }
            // HEAD 다를 때 -> HEAD 오름차순
            return arr1[0].compareTo(arr2[0]);
        });
        return files;
    }

    // HEAD, NUMBER 로 문자열 분리
    public static String[] split(String s) {
        int i = 0;
        String[] arr = new String[2];   // [HEAD, NUMBER]
        StringBuilder sb = new StringBuilder();
        for(char c : s.toLowerCase().toCharArray()) {
            if(i == 0 && Character.isDigit(c)) {
                arr[0] = sb.toString();
                sb = new StringBuilder();
                i++;
            } else if(i == 1 && !Character.isDigit(c)) {
                break;
            }
            sb.append(c);
        }
        arr[1] = sb.toString();
        return arr;
    }
}