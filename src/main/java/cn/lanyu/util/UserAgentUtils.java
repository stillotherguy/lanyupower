/*package cn.lanyu.util;

import javax.servlet.http.HttpServletRequest;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

public class UserAgentUtils {

	*//**
	 * 是否为PC访问（true:是，false:否）
	 * 
	 * @param request
	 * @return
	 *//*
	public static boolean isComputer(HttpServletRequest request) {
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		DeviceType deviceType = userAgent.getOperatingSystem().getDeviceType();
		if (DeviceType.COMPUTER.equals(deviceType)) {
			return true;
		}
		return false;
	}

	*//**
	 * 判断是否是低版本的IE（ie8以下）
	 * 
	 * @param request
	 * @return
	 *//*
	public static boolean isOldIE(HttpServletRequest request) {
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		Browser browser = userAgent.getBrowser();
		Version version = userAgent.getBrowserVersion();
		return (browser.getGroup().equals(Browser.IE) && (Integer.parseInt(version.getMajorVersion()) < 9));
	}

}
*/