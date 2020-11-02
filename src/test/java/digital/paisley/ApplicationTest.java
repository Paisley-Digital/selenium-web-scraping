package digital.paisley;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void findLinkId() {
        String link="https://www.upwork.com/jobs/Java-Junit-need-someone-with-junit-mockito-knoledge-for-two_%7E01e336b86fe1303613?source=rss";
        Pattern pattern = Pattern.compile("_%7E(.*?)\\?source=", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(link);
        while (matcher.find()) {
            assertEquals("01e336b86fe1303613",matcher.group(1));
        }
    }
}