package DynamicProgramming;

/**
 * Problem: Identify the least number of coins from a given denomination that add up to a given sum.
 * Time complexity: O(sum*denominations)
 * Space complexity: O(sum)
 * Reference: https://www.byte-by-byte.com
 */
public class CoinChangeProblem {
    private int[] denominations;
    private int[] cache;

    private void initCache(int n) {
        cache = new int[n];
    }

    public CoinChangeProblem(int[] coins) {
        denominations = coins;
    }

    public int getLeastCoinsCount(int sum) {
        if(sum < 1) return 0;
        if(cache[sum] > 0) return cache[sum];

        int numCoins = Integer.MAX_VALUE;
        for (int coin : denominations) {
            if(sum - coin >= 0) {
                int temp = 1 + getLeastCoinsCount(sum - coin);
                if (temp < numCoins) {
                    numCoins = temp;
                }
            }
        }
        cache[sum] = numCoins;
        return numCoins;
    }

    public static void main(String[] args) {
        CoinChangeProblem obj = new CoinChangeProblem(new int[]{10, 6, 1});
        int sum = 12;
        obj.initCache(sum+1);
        System.out.print(obj.getLeastCoinsCount(sum));
    }
}
