package com.myAmway.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtils {

	public static String htmlEscape(String str) {

		String result = org.springframework.web.util.HtmlUtils.htmlEscape(str);

		String reg = "[\n-\r]";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(result);
		result = m.replaceAll("<br/>");

		return result;
	}
	
}
