package programmers.level1;


/**
 * [문제명] 바탕화면 정리
 * [풀이시간] 10분 / 7분
 * [한줄평] 배열을 모두 탐색해서 풀 수 있는 쉬운 구현 문제였다. / 더 이상 풀어볼 필요는 없는 문제다.
 * 1_v1. 완전탐색(성공)
 * 2_v1. 완전탐색(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/161990">문제</a>
 */
class Solution161990 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1
    /**
     * @param wallpaper 머쓱이의 컴퓨터 바탕화면의 상태를 나타내는 문자열 배열
     * @return 바탕화면의 파일들을 한 번에 삭제하기 위해 최소한의 이동거리를 갖는 드래그의 시작점과 끝점을 담은 정수 배열
     */
    public int[] solution(String[] wallpaper) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;
        // x, y 최솟값, 최댓값 찾기
        for(int i = 0; i < wallpaper.length; i++) {
            for(int j = 0; j < wallpaper[0].length(); j++) {
                if(wallpaper[i].charAt(j) == '#') {
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, j);
                    maxX = Math.max(maxX, i);
                    maxY = Math.max(maxY, j);
                }
            }
        }
        return new int[] {minX, minY, maxX + 1, maxY + 1};
    }
}