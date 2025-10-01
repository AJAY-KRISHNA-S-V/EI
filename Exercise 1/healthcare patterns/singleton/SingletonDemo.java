package singleton;



import util.LoggingUtil;
import java.util.logging.Logger;

public class SingletonDemo {
    private static final Logger logger = LoggingUtil.getLogger(SingletonDemo.class);

    public static void run() {
        logger.info("--- Singleton Pattern Demo ---");

        HospitalDatabase db1 = HospitalDatabase.getInstance();
        HospitalDatabase db2 = HospitalDatabase.getInstance();
        logger.info("Are both DB instances same? " + (db1 == db2));

        db1.addRecord("P001", "Alice - BP Normal");
        logger.info("Retrieve P001: " + db2.getRecord("P001"));
    }
}

