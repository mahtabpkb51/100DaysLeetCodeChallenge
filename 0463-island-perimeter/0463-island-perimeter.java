class Solution {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 1) {
                    // Har land cell ke 4 sides
                    perimeter += 4;

                    // Upar land hai → 2 shared sides remove
                    if (i > 0 && grid[i - 1][j] == 1) {
                        perimeter -= 2;
                    }

                    // Left me land hai → 2 shared sides remove
                    if (j > 0 && grid[i][j - 1] == 1) {
                        perimeter -= 2;
                    }
                }
            }
        }

        return perimeter;
    }
}