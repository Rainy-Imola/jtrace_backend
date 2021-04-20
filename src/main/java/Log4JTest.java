import org.apache.log4j.*;

public class Log4JTest {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Log4JTest.class);
        logger.debug("This is debug");
        logger.info("This is info");
        logger.warn("This is warn");
        logger.error("This is error");
        logger.fatal("This is fatal");
    }
}
