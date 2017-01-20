
import java.util.ArrayList;
import java.util.List;

public class HangleSearchFilter {
    private List<InputKeywordDBean> words = null ;

    private final char[] CHOSUNG = {
            0xAC00, 0xAE4C, ' ', 0xB098, ' ', ' ', 0xB2E4, 0xB530, 0xB77C, ' ', ' ', ' ', ' ', ' ', ' ', ' ', 0xB9C8,
            0xBC14, 0xBE60, ' ', 0xC0AC, 0xC2F8, 0xC544, 0xC790, 0xC9DC, 0xCC28, 0xCE74, 0xD0C0, 0xD30C, 0xD558};

    public HangleSearchFilter(List<InputKeywordDBean> words) {
        this.words = words ;
    }

    public String filterSpecials(String str){ //
        String match = "[^ㄱ-ㅎㅏ-ㅣ가\uAC00-\uD7A3xfea-zA-Z\\s ]";
        str =str.replaceAll(match, "");
        return str;
    }

    public List<String> search(int limit,String searchChar) {
        int count = 0 ;

        List<String> resultWords = new ArrayList<String>() ;

        for(InputKeywordDBean word : words) {
            String toSearch;
            if(searchChar.length() != filterSpecials(searchChar).length()) toSearch = word.getOrgKeyword();
            else toSearch = filterSpecials(word.getOrgKeyword());

            if(speedHangleCheck(searchChar, toSearch) && speedHangleCheck(searchChar.substring(0, 1), word.getOrgKeyword().substring(0, 1))) {
                resultWords.add(word.getOrgKeyword());
                count++;
            }
            if( count == limit ) break ;
        }
        return resultWords;
    }

    public boolean speedHangleCheck(String searchChar, String hangle) {
        String deletedTrim = searchChar.trim();
        if (deletedTrim.length() > hangle.length()) return false;

        searchChar = searchChar.replace("^", "\\^").replace(".", "\\.").replace("[", "\\[")
                .replace("]", "\\]").replace("$", "\\$").replace("(", "\\(").replace(")", "\\)").replace("|", "\\|")
                .replace("*", "\\*").replace("+", "\\+").replace("?", "\\?").replace("{", "\\{").replace("}", "\\}");

        if (hangle.matches(".*"+searchChar.toString()+".*")) return true;

        boolean checkString = true;
        int i = 0, j = deletedTrim.length() - 1;

        int stringLength = (j % 2 == 0) ? j / 2 : j / 2 + 1;
        searchChar = searchChar.replace("\\", "");

        for (; i <= stringLength; i++, j--) {
            checkString = checkString && _getCheckCollect(searchChar.charAt(i), hangle.charAt(i))
                    && _getCheckCollect(searchChar.charAt(j), hangle.charAt(j));
            if (!checkString) return false;
        }
        return checkString;
    }

    private boolean _getCheckCollect(char searchChar, char oneChar) {
        if(searchChar == oneChar) return true;
        boolean checkText = false; // 기본 문자도아니고 초성도아닐 때
        char range;
        if (0xAC00 <= searchChar && searchChar < 0xD7A4) { // 문자
            range = _getHangleRange(searchChar);
            checkText = (range <= oneChar && oneChar < range + 0x1c);
        } else if (0x3131 <= searchChar && searchChar < 0x314F) { // 초성일 때
            range = _getChosungRange(searchChar);
            checkText = (range <= oneChar && oneChar < range + 0x24c);
        } else if (searchChar == 0x20 && searchChar == oneChar) { // 공백
            checkText = true;
        }
        return checkText;
    }

    private char _getHangleRange(char searchChar) {
        return searchChar;
    }

    private char _getChosungRange(char chosung) {
        int base = chosung - 0x3131;
        return CHOSUNG[base];
    }

}
