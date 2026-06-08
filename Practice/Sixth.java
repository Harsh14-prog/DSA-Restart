package Practice;

import java.util.Stack;

public class Sixth {
    public static void main(String[] args) {
        
        String num = "1432219" ;
        int n = num.length() ;
        int k = 3 ;

        Stack <Character> st = new Stack<>() ;
         
        for(int i = 0 ; i < n ; i++){

            char ch = num.charAt(i) ;

            while(!st.isEmpty() && st.peek() > ch && k > 0){
                st.pop() ;
                k-- ;
            }

            st.push(ch) ;
        }

        while(!st.isEmpty() && k > 0){
            st.pop() ;
            k-- ;
        }

        StringBuilder sb = new StringBuilder() ;

        while (!st.isEmpty()){
            sb.append(st.peek()) ;
            st.pop() ;
        }

        String s = sb.reverse().toString() ;

        int idx = 0 ;
        while(idx < s.length() && s.charAt(idx) == '0'){
            idx++;
        }

        s = s.substring(idx) ;

        System.out.println(s.length() == 0 ? "0" : s);
    }
}
