package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3079 {
    static int N, M;
    static int[] arr;
    static long result = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int max = 0;
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        bs(1, (long) max * M);
        System.out.println(result);
    }

    private static void bs(long left, long right) {
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            for (int time : arr) {
                count += (mid / time);

                if (count >= M) {
                    break;
                }
            }

            if (count >= M) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }
}
