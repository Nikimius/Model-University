package Builder;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerCl {

    private static final Logger log = LoggerFactory.getLogger(ClassNameUtil.getCurrentClassName());

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.util.logging.config.file"));
        log.info("message");
    }
}
