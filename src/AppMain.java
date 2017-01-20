import java.util.ArrayList;
import java.util.List;

public class AppMain {
    public static void main(String[] args){
        List<InputKeywordDBean> words = new ArrayList<InputKeywordDBean>();

        /**
         * Arbitrary Data
         * */
        words.add(new InputKeywordDBean("1단어(숫자로시작하는글자)"));
        words.add(new InputKeywordDBean("단어(★가나다)"));
        words.add(new InputKeywordDBean("a도쿄"));
        words.add(new InputKeywordDBean("a돔b쿄"));
        words.add(new InputKeywordDBean("동영상"));
        words.add(new InputKeywordDBean("다양성"));
        words.add(new InputKeywordDBean("대나무"));
        words.add(new InputKeywordDBean("라면"));
        words.add(new InputKeywordDBean("고속도로"));
        words.add(new InputKeywordDBean("라디오"));
        words.add(new InputKeywordDBean("다리미"));
        words.add(new InputKeywordDBean("*동영상+시청"));

        /**
         * Constructing
         * */
        HangleSearchFilter hFilter = new HangleSearchFilter(words);
        String searchChar = "aㄷ";

        for(String s : hFilter.search(10, searchChar)) System.out.println(s);
    }
}
