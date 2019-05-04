/**
 * Palindrome Number
 */
public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        
        if (x >= 0 && x < 10) {
            return true;
        }
        
        long rev = 0;
        long temp = x;
        while (x != 0) {
            rev = rev * 10 + (x % 10);
            x = x / 10;
        }
        
        if (rev == temp) {
            return true;
        }
        
        return false;
    }
}
