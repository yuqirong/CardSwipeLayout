package me.yuqirong.cardswipelayout;

/**
 * @author maomao
 * @email qirong.yu@jubaobar.com
 * @date 2017-8-3
 */

public class Utils {

    public static <T> T checkIsNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }

    public static <T> T checkIsNull(T t, String errorMsg) {
        if (t == null) {
            throw new NullPointerException(errorMsg);
        }
        return t;
    }

}
