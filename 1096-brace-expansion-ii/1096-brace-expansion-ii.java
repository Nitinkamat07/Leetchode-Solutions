
class Solution {

    public List<String> braceExpansionII(String expression) {
        Set<String> result = solve(expression, 0, expression.length() - 1);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> solve(String s, int l, int r) {

        Set<String> result = new HashSet<>();

        // 1. Look for a top-level comma
        int level = 0;
        int start = l;

        for (int i = l; i <= r; i++) {

            if (s.charAt(i) == '{') {
                level++;
            } 
            else if (s.charAt(i) == '}') {
                level--;
            } 
            else if (s.charAt(i) == ',' && level == 0) {

                result.addAll(solve(s, start, i - 1));
                start = i + 1;
            }
        }

        // If we found a comma, process the remaining part
        if (start != l) {
            result.addAll(solve(s, start, r));
            return result;
        }

        // 2. Check if the entire expression is wrapped
        //    by one matching pair of braces
        if (s.charAt(l) == '{') {

            int level2 = 0;

            for (int i = l; i <= r; i++) {

                if (s.charAt(i) == '{') {
                    level2++;
                } 
                else if (s.charAt(i) == '}') {
                    level2--;

                    // The opening brace at l matches here
                    if (level2 == 0) {

                        if (i == r) {
                            return solve(s, l + 1, r - 1);
                        }

                        break;
                    }
                }
            }
        }

        // 3. Concatenation
        Set<String> current = new HashSet<>();
        current.add("");

        int i = l;

        while (i <= r) {

            Set<String> part;

            // Expression starts with {
            if (s.charAt(i) == '{') {

                int level3 = 0;
                int j = i;

                for (; j <= r; j++) {

                    if (s.charAt(j) == '{') {
                        level3++;
                    } 
                    else if (s.charAt(j) == '}') {
                        level3--;

                        if (level3 == 0) {
                            break;
                        }
                    }
                }

                part = solve(s, i + 1, j - 1);
                i = j + 1;

            } 
            // Single character
            else {

                part = new HashSet<>();
                part.add(String.valueOf(s.charAt(i)));

                i++;
            }

            // Cartesian product / concatenation
            Set<String> next = new HashSet<>();

            for (String a : current) {
                for (String b : part) {
                    next.add(a + b);
                }
            }

            current = next;
        }

        return current;
    }
}