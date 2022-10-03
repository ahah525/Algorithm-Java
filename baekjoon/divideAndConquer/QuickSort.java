package baekjoon.divideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QuickSort {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        quickSort(0,n-1);

        for(int num : arr) {
            sb.append(num + " ");
        }
        System.out.println(sb.toString());
    }

    static void quickSort(int left, int right) {
        if(left>= right) {
            return;
        }

        int pivot = partition(left, right);
        quickSort(left,pivot-1);
        quickSort(pivot+1,right);
    }

    // 왼쪽 피봇 선택하는 경우
    static int partition(int left, int right) {
        int pivot = arr[left];
        int i = left;   // 왼쪽에서부터 찾는 작은값 인덱스
        int j = right;  // 오른쪽에서부터 찾는 큰 값 인덱스

        // 왼쪽에서 찾는 값과 오른쪽에서 찾는 값의 위치가 서로 엇갈리면 swap(pivo작은 값t, )
        while(i < j) {
            while(arr[j] > pivot&& i<j) {
                j--;
            }
            while(arr[i] <= pivot && i<j) {
                i++;
            }
            swap(i , j);
        }
        swap(left, i);
        return i;
    }

    static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
