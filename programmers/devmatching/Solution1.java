package programmers.devmatching;


class Solution1 {

    public static void main(String[] args) {
        String[] img = {".####..###"};
        int answer = solution(0, 0, img);
        System.out.println(answer);
    }

    /**
     *
     * @param low 이상
     * @param high 미만
     * @param img 최소(3*3), 최대(50*50) #(검정색), .(흰색)
     * @return QR 코드로 인식될 수 있는 정사각형의 총 개수
     */
    public static int solution(int low, int high, String[] img) {
        int answer = 0;
        int n = img.length; // n
        // ### 위치 찾기
        for(int l = 3; l < n; l++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {

                }
            }
        }
        return answer;
    }
}