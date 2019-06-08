package demo.spring.boot.docker.util;


import java.util.UUID;

/**
 * UUIDUtil
 */
public class UUIDUtils {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
