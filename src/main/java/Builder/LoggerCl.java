package Builder;

import java.util.logging.Logger;

public class LoggerCl {

    private static final Logger logger = Logger.getLogger(LoggerCl.class.getName());

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.util.logging.config.file"));
        logger.info("message");
    }
}
