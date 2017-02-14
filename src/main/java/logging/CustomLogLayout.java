package logging;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.spi.LoggingEvent;

public class CustomLogLayout extends PatternLayout implements SubjectLayoutable {
    private String subjectPattern = "%m%n";
    private PatternConverter topic = this.createPatternParser(subjectPattern).parse();
    private StringBuffer sbuf = new StringBuffer(256);

    public String formatSubject(LoggingEvent event) {
        if(sbuf.capacity() > MAX_CAPACITY) {
            sbuf = new StringBuffer(BUF_SIZE);
        } else {
            sbuf.setLength(0);
        }

        PatternConverter c = topic;

        while(c != null) {
            c.format(sbuf, event);
            c = c.next;
        }
        return sbuf.toString();
    }

    public String getSubjectPattern() {
        return this.subjectPattern;
    }

    public void setSubjectPattern(String subjectPattern) {
        this.subjectPattern = subjectPattern;
        this.topic = this.createPatternParser(subjectPattern).parse();
    }
}
