 class Solution {
    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);

        // Remove coins that are multiples of smaller coins
        ArrayList<Integer> list = new ArrayList<>();

        for (int coin : coins) {
            boolean useful = true;

            for (int x : list) {
                if (coin % x == 0) {
                    useful = false;
                    break;
                }
            }

            if (useful) {
                list.add(coin);
            }
        }

        int n = list.size();
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = list.get(i);
        }

        long low = 1;
        long high = (long) arr[0] * k;

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (count(mid, arr) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long x, long[] coins) {
        long total = 0;
        int n = coins.length;

        // Inclusion-Exclusion
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;

                    long g = gcd(lcm, coins[i]);
                    lcm = lcm / g * coins[i];

                    if (lcm > x) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (!overflow) {
                long add = x / lcm;

                if (bits % 2 == 1) {
                    total += add;
                } else {
                    total -= add;
                }
            }
        }

        return total;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}