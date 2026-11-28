import java.util.*;

class Solution {
public int maximumSaleItems(int[][] items, int budget){
        int n = items.length;

        // Step 1: Count frequency of each factor
        int[] freq = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Step 2: Count divisible items for each factor
        int[] count = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                count[i] += freq[j];
            }
        }

        // Step 3: Store bonus copies by price
        long[][] bonus = new long[n][2];

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price = items[i][1];

            // Exclude the item itself
            int free = count[factor] - 1;

            bonus[i][0] = price;
            bonus[i][1] = free;
        }

        // Step 4: Sort by price
        Arrays.sort(bonus, (a, b) ->
            Long.compare(a[0], b[0])
        );

        // Step 5: Greedily purchase beneficial copies
        long remaining = budget;
        long answer = 0;

        for (int i = 0; i < n; i++) {
            long price = bonus[i][0];
            long available = bonus[i][1];

            // Bonus copies are beneficial only below 2 * minPrice
            if (price >= 2L * minPrice) {
                break;
            }

            long buy = Math.min(available, remaining / price);

            remaining -= buy * price;

            // Each purchased copy gives one additional free item
            answer += 2L * buy;
        }

        // Step 6: Spend remaining budget on cheapest item
        answer += remaining / minPrice;

return (int) answer;
    }
}