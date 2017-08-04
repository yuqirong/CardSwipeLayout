package me.yuqirong.cardswipelayout;

/**
 * @author yuqirong
 */

class Utils {

    static <T> T checkIsNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }

    static <T> T checkIsNull(T t, String errorMsg) {
        if (t == null) {
            throw new NullPointerException(errorMsg);
        }
        return t;
    }

}
