package programmers.level1;


/**
 * [문제명] 카드 뭉치
 * [풀이시간] 15분
 * [한줄평] 어렵지 않게 풀 수 있었다. 재귀말고 큐나 리스트로 문제를 푼사람도 있었다.
 * 1_v1. 재귀(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/159994">문제</a>
 */
class Solution159994 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    boolean isFind; // goal 을 만들 수 있는지 여부

    /**
     * @param cards1 문자열로 이루어진 배열1
     * @param cards2 문자열로 이루어진 배열2
     * @param goal 원하는 단어 배열
     * @return cards1과 cards2에 적힌 단어들로 goal를 만들 있다면 "Yes"를, 만들 수 없다면 "No"
     */
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        isFind = false;
        dfs(0, 0, 0, cards1, cards2, goal);

        return isFind ? "Yes" : "No";
    }

    /**
     * @param depth 지금까지 선택한 단어 갯수
     * @param a cards1 에서 선택할 수 있는 인덱스
     * @param b cards2 에서 선택할 수 있는 인덱스
     */
    public void dfs(int depth, int a, int b, String[] cards1, String[] cards2, String[] goal) {
        // 이미 goal 을 만들 수 있는 경우의 수를 찾았으면 종료
        if(isFind) return;
        // 모든 단어를 선택했으면 종료
        if(depth == goal.length) {
            isFind = true;
            return;
        }
        // 1. cards1 에서 선택하기
        if(a < cards1.length && cards1[a].equals(goal[depth]))
            dfs(depth + 1, a + 1, b, cards1, cards2, goal);
        // 2. cards2 에서 선택하기
        if(b < cards2.length && cards2[b].equals(goal[depth]))
            dfs(depth + 1, a, b + 1, cards1, cards2, goal);
    }
}