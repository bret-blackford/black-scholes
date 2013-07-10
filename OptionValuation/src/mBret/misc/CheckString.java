package mBret.misc;

/**
 * A basic helper class to avaoid NULL String issues
 * @author mblackford M Bret Blackford (mBret)
 * @deprecated
 */
public class CheckString {

	public static String check(String in) {
		String out = in;

		if (in == null) {
			out = "";
		}

		return out;
	}
}
