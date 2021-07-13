package hipravin.javapuzzles.markup;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CodeMarkupUtil {
    private static final Map<String, String> replacers = new LinkedHashMap<>();
    private static final Pattern wordPattern = Pattern.compile("\\w+");
    private static final Set<String> keywordsNotTypes = new LinkedHashSet<>();
    private static final Set<String> primitives = new LinkedHashSet<>();


    private static final String KEYWORD_COLOR = "#008000";
    private static final String PRIMITIVE_COLOR = "#303090";
    private static final String DIGIT_COLOR = "#0000D0";
    private static final String DOT_WORD_COLOR = "#0000C0";
    private static final String UPPERCASE_WORD_COLOR = "#B00060";
    private static final String STRING_LITERAL_COLOR = "#FFF0F0";

    static {
        replacers.put("\r\n", "<br/>");
        replacers.put("\n", "<br/>");
        replacers.put("\t", "&nbsp;");
        replacers.put("  ", "&nbsp;&nbsp;");

        //keywords
        String keywordText = "abstract continue for new switch " +
                "assert default goto package synchronized " +
                "do if private this " +
                "break implements protected throw " +
                "else import public throws " +
                "case enum instanceof return transient " +
                "catch extends try " +
                "final interface static void " +
                "class finally strictfp volatile " +
                "const native super while";

        keywordsNotTypes.addAll(Arrays.asList(keywordText.split("\\s")));

        String primitivesText = "int short double long boolean byte char";

        primitives.addAll(Arrays.asList(primitivesText.split("\\s")));

    }

    private CodeMarkupUtil() {
    }

    public static String plainToHtml(String plain) {
        String result = plain;

        //double-quoted string
        result = result.replaceAll("(\".*?\")", "<span style=\"background-color: "+ STRING_LITERAL_COLOR +"\">$1</span>");

        //numbers
        result = result.replaceAll("([\\W&&[^#]])(-?\\d+)", "$1<strong><span style=\"color: "+ DIGIT_COLOR +"\">$2</span></strong>");

        //dot words
        result = result.replaceAll("(\\.)(\\p{Alpha}\\w+)", "$1<span style=\"color: " + DOT_WORD_COLOR + "\">$2</span>");

        //start uppercase words
        result = result.replaceAll("([\\W&&[^#]])(\\p{Upper}\\w+)", "$1<strong><span style=\"color: "+ UPPERCASE_WORD_COLOR +"\">$2</span></strong>");

        //whitespaces
        for (Map.Entry<String, String> replacement : replacers.entrySet()) {
            result = result.replaceAll(replacement.getKey(), replacement.getValue());
        }
        //keywords
        Set<String> wordsFound = findWords(plain);
        for (String word : wordsFound) {
            if(keywordsNotTypes.contains(word)) {
                result = markBoldAndColored(result, word, KEYWORD_COLOR);
            } else if(primitives.contains(word)) {
                result = markBoldAndColored(result, word, PRIMITIVE_COLOR);
            }
        }

        return  result;
    }

    private static Set<String> findWords(String plain) {
        Set<String> matched = new HashSet<>();
        Matcher matcher = wordPattern.matcher(plain);
        while (matcher.find()) {
            matched.add(matcher.group());
        }

        return matched;
    }

    private static String markBoldAndColored(String text, String pattern, String color) {
        return  text.replaceAll(pattern, "<strong><span style=\"color: "+ color +"\">" + pattern + "</span></strong>");
    }
}
