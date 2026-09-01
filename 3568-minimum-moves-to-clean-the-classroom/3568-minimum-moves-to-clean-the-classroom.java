class Solution {
    public int minMoves(String[] classroom, int energy) {




        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        int litterCount = 0;

        // Assign an index to every litter cell.
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        int allCollected = (1 << litterCount) - 1;

        // State = [row, col, energyLeft, mask]
        Queue<int[]> queue = new ArrayDeque<>();

        /*
         * visited[r][c][energy][mask]
         *
         * energy ranges from 0 to energy.
         * mask ranges from 0 to 2^litterCount - 1.
         */
        boolean[][][][] visited =
                new boolean[m][n][energy + 1][1 << litterCount];

        queue.offer(new int[]{sr, sc, energy, 0});
        visited[sr][sc][energy][0] = true;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process one BFS level.
            while (size-- > 0) {
                int[] state = queue.poll();

                int r = state[0];
                int c = state[1];
                int currEnergy = state[2];
                int mask = state[3];

                if (mask == allCollected) {
                    return moves;
                }

                // Cannot move if no energy is left.
                if (currEnergy == 0) {
                    continue;
                }

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Outside grid
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int newEnergy = currEnergy - 1;
                    int newMask = mask;

                    char cell = classroom[nr].charAt(nc);

                    // Collect litter.
                    if (cell == 'L') {
                        int id = litterId[nr][nc];
                        newMask |= (1 << id);
                    }

                    // Reset energy.
                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    if (!visited[nr][nc][newEnergy][newMask]) {
                        visited[nr][nc][newEnergy][newMask] = true;
                        queue.offer(new int[]{
                            nr, nc, newEnergy, newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}
    
