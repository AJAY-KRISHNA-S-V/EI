package singleton;

import util.LoggingUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class HospitalDatabase {
    private static final Logger logger = LoggingUtil.getLogger(HospitalDatabase.class);
    private static volatile HospitalDatabase instance;
    private final Map<String, String> records = new ConcurrentHashMap<>();

    private HospitalDatabase() {
        logger.info("HospitalDatabase initialized.");
    }

    public static HospitalDatabase getInstance() {
        if (instance == null) {
            synchronized (HospitalDatabase.class) {
                if (instance == null) {
                    instance = new HospitalDatabase();
                }
            }
        }
        return instance;
    }

    public void addRecord(String id, String details) {
        records.put(id, details);
        logger.info("Record added: " + id + " -> " + details);
    }

    public String getRecord(String id) {
        return records.getOrDefault(id, "❌ Record not found");
    }

    // ✅ New: list all patient records
    public Map<String, String> getAllRecords() {
        return records;
    }
}
