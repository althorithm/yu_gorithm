package week1;import java.io.BufferedReader;import java.io.IOException;import java.io.InputStreamReader;import java.util.*;public class BOJ_16946 {    static int[][] map;    static boolean[][] visited;    static Map<Integer, Integer> group;    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};    static int N, M;    public static void main(String[] args) throws IOException {        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        StringTokenizer st = new StringTokenizer(br.readLine());        N = Integer.parseInt(st.nextToken());        M = Integer.parseInt(st.nextToken());        map = new int[N][M];        visited = new boolean[N][M];        for (int i = 0; i < N; i++) {            String line = br.readLine();            for (int j = 0; j < M; j++) {                map[i][j] = line.charAt(j) - '0';            }        }        int num = 2;        group = new HashMap<>();        for (int i = 0; i < N; i++) {            for (int j = 0; j < M; j++) {                if (map[i][j] == 0 && !visited[i][j]) {                    visited[i][j] = true;                    int count = grouping(i, j, num);                    group.put(num, count);                    num++;                }            }        }        StringBuilder sb = new StringBuilder();        for (int i = 0; i < N; i++) {            for (int j = 0; j < M; j++) {                if (map[i][j] == 1) {                    sb.append(count(i, j));                } else {                    sb.append("0");                }            }            sb.append("\n");        }        System.out.println(sb);    }    private static int count(int x, int y) {        Set<Integer> set = new HashSet<>();        int count = 1;        for (int i = 0; i < 4; i++) {            int dx = x + move[i][0];            int dy = y + move[i][1];            if (dx >= 0 && dy >= 0 && dx < N && dy < M) {                int key = map[dx][dy];                if (key > 1) {                    set.add(key);                }            }        }        for (Integer key : set) {            count += group.get(key);        }        return count % 10;    }    private static int grouping(int x, int y, int num) {        Queue<Node> q = new LinkedList<>();        q.add(new Node(x, y));        map[x][y] = num;        int count = 1;        while (!q.isEmpty()) {            Node node = q.poll();            for (int i = 0; i < 4; i++) {                int dx = node.x + move[i][0];                int dy = node.y + move[i][1];                if (dx >= 0 && dy >= 0 && dx < N && dy < M && !visited[dx][dy]) {                    if (map[dx][dy] == 0) {                        visited[dx][dy] = true;                        map[dx][dy] = num;                        q.add(new Node(dx, dy));                        count++;                    }                }            }        }        return count;    }    static class Node {        int x;        int y;        public Node(int x, int y) {            this.x = x;            this.y = y;        }    }}