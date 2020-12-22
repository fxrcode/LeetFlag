package lint;

// Your submission beats 8.40% Submissions!
public class FirstUniqueCharacterInaString_209 {
    /**
     * @param str: str: the given string
     * @return: char: the first unique character in a given string
     */
    public char firstUniqChar(String str) {
        // Write your code here
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (isUniq(str, i)) {
                return ch;
            }
        }
        return '!';
    }

    private boolean isUniq(String str, int idx) {
        char ch = str.charAt(idx);
        for (int i = 0; i < str.length(); i++) {
            if (i != idx && str.charAt(i) == ch) {
                return false;
            }
        }
        return true;
    }
}
