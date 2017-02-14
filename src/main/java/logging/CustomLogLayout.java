package logging;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

public class CustomLogLayout extends PatternLayout {
    @Override
    public String format(LoggingEvent event) {
        Object obj = event.getMessage();

        StringBuffer sb = new StringBuffer();

        sb.append(event.getLevel());
        sb.append(" - ");
        sb.append(event.getTimeStamp());
        sb.append(" - ");
        sb.append(obj.toString());

        return sb.toString();
    }
}
