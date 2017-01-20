import java.util.ArrayList;
import java.util.List;

public class AppMain {
    public static void main(String[] args){

        List<InputKeywordDBean> words = new ArrayList<InputKeywordDBean>();

        /**
         * Arbitrary Data
         * */
        words.add(new InputKeywordDBean("1다이마루(★가나다)"));
        words.add(new InputKeywordDBean("담이마루(★가나다)"));
        words.add(new InputKeywordDBean("a도쿄"));
        words.add(new InputKeywordDBean("a돔b쿄"));
        words.add(new InputKeywordDBean("동영상"));
        words.add(new InputKeywordDBean("도레미파솔"));
        words.add(new InputKeywordDBean("대나무"));
        words.add(new InputKeywordDBean("라면"));
        words.add(new InputKeywordDBean("도로로로롱"));
        words.add(new InputKeywordDBean("라디오"));
        words.add(new InputKeywordDBean("다리미"));
        words.add(new InputKeywordDBean("*다이+마루가나다라"));

        /**
         * Constructing
         * */
        HangleSearchFilter hFilter = new HangleSearchFilter(words);
        String searchChar = "담이마루(★";

        for(String s : hFilter.search(10, searchChar)) System.out.println(s);
    }
}
