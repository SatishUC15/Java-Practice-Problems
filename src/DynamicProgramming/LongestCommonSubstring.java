package DynamicProgramming;

/**
 * Program to compute the longest common substring from two given input strings str1 and str2.
 * Run-time complexity: O(n*m)
 * Space complexity: O(n*m)
 * where m,n are the lengths of str1 and str2 respectively.
 * Reference: https://www.byte-by-byte.com
 */
public class LongestCommonSubstring {

    /*
        Example:
            IP: ABABA, DBADB

            memo:
                A B A B A
              D 0 0 0 0 0
              B 0 1 0 1 0
              A 1 0 2 0 2
              D 0 0 0 0 0
              B 0 1 0 1 0

            OP: Diagonal from 1 to 2 in memo - "BA".
     */
    public String getLCS(String str1, String str2) {
        if(str1 == null || str2 == null) return null;
        String result = "";
        int[][] memo = new int[str1.length()][str2.length()];
        for(int i=0; i<str1.length(); i++) {
            for(int j=0; j<str2.length(); j++) {
                if(str1.charAt(i) == str2.charAt(j)) {
                    if(i == 0 || j == 0) {
                        memo[i][j] = 1;
                    } else {
                        memo[i][j] = memo[i-1][j-1] + 1;
                        if(memo[i][j] > result.length()) {
                            result = str1.substring(i-memo[i][j]+1, i+1);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LongestCommonSubstring obj = new LongestCommonSubstring();
        System.out.println(obj.getLCS("ABABA", "DBAB"));
    }
}
