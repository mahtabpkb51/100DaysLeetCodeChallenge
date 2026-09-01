class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int startX = 0;
        int startY = 0;
        int litterCount = 0;

        // Find S and give every L an ID
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startX = i;
                    startY = j;
                } 
                else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        int totalMasks = 1 << litterCount;
        int allCollected = totalMasks - 1;

        /*
         * visited[x][y][energy][mask]
         *
         * mask = collected litter
         */
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][totalMasks];

        Queue<int[]> queue = new LinkedList<>();

        // x, y, currentEnergy, mask
        queue.offer(new int[] {
            startX,
            startY,
            energy,
            0
        });

        visited[startX][startY][energy][0] = true;

        int moves = 0;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int size = queue.size();

            // BFS level
            for (int q = 0; q < size; q++) {

                int[] state = queue.poll();

                int x = state[0];
                int y = state[1];
                int currEnergy = state[2];
                int mask = state[3];

                // All litter collected
                if (mask == allCollected) {
                    return moves;
                }

                // Energy finished
                if (currEnergy == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {

                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    // Outside grid
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nx].charAt(ny) == 'X') {
                        continue;
                    }

                    char cell = classroom[nx].charAt(ny);

                    // Moving costs 1 energy
                    int nextEnergy = currEnergy - 1;

                    // R resets energy
                    if (cell == 'R') {
                        nextEnergy = energy;
                    }

                    int nextMask = mask;

                    // Collect litter
                    if (cell == 'L') {
                        int id = litterId[nx][ny];
                        nextMask = mask | (1 << id);
                    }

                    if (!visited[nx][ny][nextEnergy][nextMask]) {

                        visited[nx][ny][nextEnergy][nextMask] = true;

                        queue.offer(new int[] {
                            nx,
                            ny,
                            nextEnergy,
                            nextMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}