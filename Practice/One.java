package Practice ;

public class One {
    public static void main(String[] args) {

        String s1 = "abcdaf";
        String s2 = "acbcf";

        int n = s1.length();
        int m = s2.length() ;

        int[][] dp = new int[n+1][m+1] ;

        for(int i = 1 ; i <= n ; i++){

            for(int j = 1 ; j <= m ; j++){  

                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i][j-1] , dp[i-1][j]);
                }
            }
        }

        int i = n ;
        int j = m ;

        StringBuilder sb = new StringBuilder() ;

        while (i > 0 && j > 0){

            if(s1.charAt(i-1) == s2.charAt(j-1)){
               sb.append(s1.charAt(i-1));
               i--;
               j--;
            }
            else{
                if(dp[i][j-1] > dp[i-1][j]){
                    j--;
                }
                else{
                    i--;
                }
            }
        }

        System.out.println(sb.reverse().toString());
    }
}
