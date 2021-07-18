
/**
 * The dungeon has a size of R x C and you start
 * at cell 'S' and there's an exit at cell 'E'.
 * A cell full of rock is indicated by a '#' and
 * empty cells are represented by a '.'
 *
 * */

import java.util.*;

public class DungeonProblem {

    public boolean isValid(int R, int C, int x, int y) {
        return (   x < 0 
                || y < 0 
                || x >= R 
                || y >= C);
    }

    public void solve(int R, int C) {
        int[][] grid = new int[R][C];
        int sx;
        int sy;
        int ex;
        int ey;

        int[][] visited = new int[R][C];

        int[] dx = {1, 1, -1, -1};
        int[] dy = {1, -1, 1, -1};

        Queue<Integer> xqueue = new LinkedList<>();
        Queue<Integer> yqueue = new LinkedList<>();
        xqueue.add(sx);
        yqueue.add(sy);
        while (!xqueue.isEmpty()) {
            int x = xqueue.poll();
            int y = yqueue.poll();
            if (grid[x][y] == 'E') {
                ex = x;
                ey = y;
                break;
            }

            for (int i=0; i<4; i++) {
                int xx = x + dx[i];
                int yy = y + dy[i];

                if (isValid(R, C, xx, yy) && visited[xx][yy] == 0) {
                    visited[xx][yy] = visited[x][y] + 1;
                    if (grid[xx][yy] != '#') {
                        xqueue.add(xx);
                        yqueue.add(yy);
                    }
                }
            }
        }

        System.out.println(visited[ex][ey]);
    }
}
