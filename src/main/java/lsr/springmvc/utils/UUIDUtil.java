package lsr.springmvc.utils;

import java.util.UUID;

/**
 * Created by lsr on 2017/6/28.
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUUID().length());
    }
}
