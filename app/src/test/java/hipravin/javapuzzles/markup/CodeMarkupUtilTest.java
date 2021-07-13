package hipravin.javapuzzles.markup;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class CodeMarkupUtilTest {
    @Test
    public void testMarkup() throws IOException {
        String content = new String(Files.readAllBytes(
                Paths.get("C:\\dev\\javapuzzlesandroid\\app\\src\\main\\res\\raw\\puzzlecode1.html")), StandardCharsets.UTF_8);

        String processed = CodeMarkupUtil.plainToHtml(content);
        System.out.println(processed);
    }
}