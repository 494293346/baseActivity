package basea.asheng.cn.baseactivity.Utils;

/**
 * Created by Administrator on 2016/12/1.
 */

public class StringUtil {
    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0 || s.equals("null");
    }
}
