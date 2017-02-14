package logging;

import org.apache.log4j.spi.LoggingEvent;

public interface SubjectLayoutable {
    String formatSubject(LoggingEvent event);
}
