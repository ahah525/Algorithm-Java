package baekjoon.divideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 입력 데이터
 * 8
 * 21 10 12 20 25 13 15 22
 */
public class MergeSort {
    static int[] arr, tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        tmp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        mergeSort(0, n-1);
        for(int num : arr) {
            sb.append(num + " ");
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    /***
     * (0, 7) -> mid = 3
     * (0, 3) -> mid = 1
     * (0, 1) -> mid = 0
     * (0, 0) -> 종료
     * (1, 1) -> 종료
     * merge(0, 0, 1) 호출
     */
    static void mergeSort(int left, int right) {
        if(left >= right) return;

        int mid = (left+right)/2;
        // divide
        mergeSort(left, mid);         // 왼쪽 분할
        mergeSort(mid+1, right); // 오른쪽 분할

        // 분할(divide) -> 합병(merge)
        merge(left, mid, right);
    }

    /***
     * (0, 0, 1) -> l = 0, r = 1, idx = 0
     * |10| |21| -> 정렬(while문) -> |10, 21|
     * ...
     * (0, 3, 7) -> l = 0, r = 7, idx = 0
     * |10, 12, 20, 21| |13, 15, 22, 25| -> 정렬 -> |10, 12, 13, 15, 20, 21, 22, 25|
     */
    static void merge(int left, int mid, int right) {
        // 왼쪽 배열 : left ~ mid
        // 오른쪽 배열 : (mid + 1) ~ right
        int l = left;       // 왼쪽 배열 인덱스
        int r = mid + 1;    // 오른쪽 배열 인덱스
        int idx = l;        // 정렬 결과를 저장할 인덱스

        // 왼쪽 배열 시작 인덱스 = mid + 1 && 오른쪽 배열 시작 인덱스 = right -> (left ~ right) 구간 정렬 끝
        while(l <= mid || r <= right) {
            if(r > right || (l <= mid && arr[l] < arr[r])) {
                // 1-1. 오른쪽 배열의 원소를 이미 다 가져온 경우
                // 1-2. 왼쪽 배열에서 가져오지 않은 원소가 있고, 왼쪽 배열 원소(l)가 오른쪽 배열 원소(r)보다 작은 경우
                tmp[idx++] = arr[l++];
            }else {
                // 1-1. 오른쪽 배열의 원소를 이미 다 가져온 경우
                // 1-2. 왼쪽 분할에서 가져오지 않은 원소가 있고, 해당 원소(l)가 오른쪽 분할 원소(r)보다 작은 경우
                tmp[idx++] = arr[r++];
            }
        }

        // left ~ right 구간 정렬한 부분을 원래 배열 arr에 저장한다.
        for(int i = left; i <= right; i++) {
            arr[i] = tmp[i];
        }
    }
}
