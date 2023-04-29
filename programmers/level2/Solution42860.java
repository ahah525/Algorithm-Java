package programmers.level2;


/**
 * [문제명] 조이스틱
 * [풀이시간] (50분)
 * [한줄평]
 * 1_v1. (실패 - 11,13~14,18,20,22~27 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42860">문제</a>
 */
class Solution42860 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    public int solution(String name) {
        int answer = 0;
        boolean isFind = false;

        // 왼쪽으로 갈 때
        int a = 0;  // 제일 왼쪽에 있는 'A'가 아닌 문자
        // 오른쪽으로 갈 때
        int b = 0;  // 제일 오른쪽에 있는 'A'가 아닌 문자
        for(int i = 1; i < name.length(); i++) {
            if(name.charAt(i) == 'A') continue;
            if(!isFind) {
                isFind = true;
                a = i;
            }
            b = i;
        }
        boolean right = (b <= name.length() - a) ? true : false;
        int cursor = 0;
        // System.out.println(a);
        // System.out.println(b);
        if(right) {
            while(true) {
                int c = name.charAt(cursor) - 'A';
                int d = 'Z' - name.charAt(cursor) + 1;
                answer += Math.min(c, d);
                // System.out.println(name.charAt(cursor));
                // System.out.println(c);
                // System.out.println(d);
                if(cursor == b) break;
                cursor++;
                answer++;
            }
        } else {
            while(true) {
                int c = name.charAt(cursor) - 'A';
                int d = 'Z' - name.charAt(cursor) + 1;
                answer += Math.min(c, d);
                // System.out.println(name.charAt(cursor));
                // System.out.println(c);
                // System.out.println(d);
                if(cursor == a) break;
                if(cursor == 0) cursor = name.length() - 1;
                else cursor--;
                answer++;
            }
        }

        return answer;
    }
}