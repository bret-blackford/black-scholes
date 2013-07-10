package mBret.misc;

/**
 * A basic helper class to avoid NULL String issues
 * @author mblackford M Bret Blackford (mBret)
 *
 */
public class StringUtil {

	public static String check(String in) {
		if (in != null && in.length() > 0) {

			return in;
		}
		return "0";
	}

}
