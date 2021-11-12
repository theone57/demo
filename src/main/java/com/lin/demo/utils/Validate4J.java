package com.lin.demo.utils;

import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Datebase Field is Validate for JAVA（数据库字段的合法性验证 增删改的操作时，验证字段的格式）
 *
 * @version 0.1
 * @Author jinjian.feng
 */
public class Validate4J {

	/**
	 * 验证邮箱地址是否正确
	 *
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 *
	 * @param mobiles
	 * @return
	 */
	public static boolean checkMobileNO(String mobiles) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证密码 长度为 6-16的数据或字母
	 *
	 * @param password
	 * @return
	 */
	public static boolean checkPassword(String password) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("[0-9a-zA-Z,.~!@#$%^&*()_-]{6,16}");
			Matcher m = p.matcher(password);
			flag = m.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证商户ID只能是数字和英文字母，且15位
	 *
	 * @param mchtId
	 * @return
	 */
	public static boolean checkMchtId(String mchtId) {
		boolean flag = false;
		try {
			String check = "^[A-Za-z0-9]*$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(mchtId);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证商户ID只能是数字和英文字母
	 *
	 * @param str
	 * @return
	 */
	public static boolean checkEnAndNum(String str) {
		boolean flag = false;
		try {
			String check = "^[A-Za-z0-9]+$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(str);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断是否为正整数
	 *
	 * @param str
	 * @return
	 */
	public static boolean checkPositiveInteger(String str) {
		boolean flag = false;
		try {
			String check = "^[0-9]*[1-9][0-9]*$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(str);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断是否为非负整数
	 *
	 * @param str
	 * @return
	 */
	public static boolean checkInteger(String str) {
		boolean flag = false;
		try {
			String check = "^\\d+$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(str);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 检查日期 YYYYMMDD
	 *
	 * @param str
	 * @return
	 */
	public static boolean checkDate(String str) {
		boolean flag = false;
		try {
			String check = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)(([0-1][0-9])|2[0-3])[0-5][0-9]";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(str);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证日期和时间YYYYMMDDHHmm
	 *
	 * @param str
	 * @return
	 */
	public static boolean checkDateTime(String str) {
		boolean flag = false;
		try {
			String check = "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))(([0-1][0-9])|2[0-3])[0-5][0-9]";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(str);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证ID只能是数字和英文字母，且10位
	 *
	 * @param mchtId
	 * @return
	 */
	public static boolean checkTenId(String mchtId) {
		boolean flag = false;
		try {
			String check = "^[A-Za-z0-9]{10}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(mchtId);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号、带区域的座机号（有无“-”皆可）、不带区域的座机号、类似110这类座机号 此验证不包含类似400、800等方式的座机号验证
	 *
	 * @param phone
	 * @return
	 */
	public static boolean checkTelPhone(String phone) {
		boolean flag = false;
		try {
			String check = "^([0][1-9]{2,3}-[0-9]{5,10}$)|([1-9]{1}[0-9]{2,8}$)|([1][3,4,5,8][0-9]{9}$)|([0][1-9]{2,3}[0-9]{5,10}$)";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(phone);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证用户名可包含中文、英文、数字和空格，数字和空格不能在第一位
	 *
	 * @param name
	 * @return
	 */
	public static boolean checkName(String name) {
		boolean flag = false;
		try {
			String check = "[a-zA-Z\u4e00-\u9fa5]+[a-zA-Z0-9 \u4e00-\u9fa5]*$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(name);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证真实姓名 （1-12位中文）
	 *
	 * @param name
	 * @return
	 */
	public static boolean checkRealName(String name) {
		boolean flag = false;
		try {
			String check = "^[\u4e00-\u9fa5 a-zA-Z]{1,12}";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(name);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证身份证号
	 *
	 * @param cerNo
	 * @return
	 */
	public static boolean checkCerNo(String cerNo) {
		boolean flag = false;
		try {
			String check = "^([1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$)|([1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(cerNo);
			flag = matcher.matches();
			if (flag == true && cerNo.length() == 18) {
				int sum = 0;
				for (int i = 0; i < 18; i++) {
					sum += (getOneNum(cerNo.substring(i, i + 1)) * getW(18 - i));
				}
				flag = sum % 11 == 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 18位身份证校验 根据字符获得数字
	 *
	 * @param str
	 * @return
	 */
	private static Integer getOneNum(String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		if ("x".equals(str.toLowerCase())) {
			return 10;
		}
		Integer n = null;
		try {
			n = Integer.parseInt(str.substring(0, 1));
		} catch (Exception e) {
			return null;
		}
		return n;
	}

	/**
	 * 身份证校验码
	 *
	 * @param i 从右向左第几位
	 */
	private static Integer getW(int i) {
		i = i < 1 ? 0 : (i - 1) % 18;
		return (int) (Math.pow(2, i) % 11);
	}

	/**
	 * 验证请求IP是否处于白名单中
	 *
	 * @param ipList
	 * @param ip
	 * @return
	 */
	public static boolean validateIp(List<String> ipList, String ip) {
		if (StringUtils.hasText(ip) && ipList != null && ipList.size() > 0) {
			for (String ipVar : ipList) {
				if (ipVar.equals(ip)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkNumber(String checkValue) {
		String check = "^[-+]?[0-9]+(\\.[0-9]+)?$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(checkValue);
		return matcher.matches();
	}
}