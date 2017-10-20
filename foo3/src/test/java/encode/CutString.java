package encode;

import java.io.UnsupportedEncodingException;

public class CutString {
	/**
	 * 判断是否是一个中文汉字
	 * 
	 * @param c
	 *            字符
	 * @return true表示是中文汉字，false表示是英文字母
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	private static boolean isChineseChar(char c) throws UnsupportedEncodingException {
		// 如果字节数大于1，是汉字
		// 以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了
		return String.valueOf(c).getBytes("UTF-8").length > 1;
	}

	/**
	 * 按字节截取字符串
	 * 
	 * @param orignal
	 *            原始字符串
	 * @param count
	 *            截取位数
	 * @return 截取后的字符串
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	private static String substring(String orignal, int count) throws UnsupportedEncodingException {
		// 原始字符不为null，也不是空字符串
		if (orignal != null && !"".equals(orignal)) {
			// 将原始字符串转换为GBK编码格式
			orignal = new String(orignal.getBytes(), "UTF-8");//
			// System.out.println(orignal);
			// System.out.println(orignal.getBytes().length);
			// 要截取的字节数大于0，且小于原始字符串的字节数
			if (count > 0 && count < orignal.getBytes("UTF-8").length) {
				StringBuffer buff = new StringBuffer();
				char c;
				for (int i = 0; i < count; i++) {
					System.out.println(count);
					c = orignal.charAt(i);
					buff.append(c);
					if (CutString.isChineseChar(c)) {
						// 遇到中文汉字，截取字节总数减1
						--count;
					}
				}
				// System.out.println(new
				// String(buff.toString().getBytes("GBK"),"UTF-8"));
				return new String(buff.toString().getBytes(), "UTF-8");
			}
		}
		return orignal;
	}

	/**
	 * 按字节截取字符串
	 * 
	 * @param orignal
	 *            原始字符串
	 * @param count
	 *            截取位数
	 * @return 截取后的字符串
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	private static String gsubstring(String orignal, int count) throws UnsupportedEncodingException {
		// 原始字符不为null，也不是空字符串
		if (orignal != null && !"".equals(orignal)) {
			// 将原始字符串转换为GBK编码格式
			orignal = new String(orignal.getBytes(), "GBK");
			// 要截取的字节数大于0，且小于原始字符串的字节数
			if (count > 0 && count < orignal.getBytes("GBK").length) {
				StringBuffer buff = new StringBuffer();
				char c;
				for (int i = 0; i < count; i++) {
					c = orignal.charAt(i);
					buff.append(c);
					if (CutString.isChineseChar(c)) {
						// 遇到中文汉字，截取字节总数减1
						--count;
					}
				}
				return buff.toString();
			}
		}
		return orignal;
	}

	/**
	 * 判断传进来的字符串，是否 大于指定的字节，如果大于递归调用 直到小于指定字节数
	 * 
	 * @param s
	 *            原始字符串
	 * @param num
	 *            传进来指定字节数
	 * @return String 截取后的字符串
	 * @throws UnsupportedEncodingException 
	 */
	public static String idgui(String s, int num) throws UnsupportedEncodingException {
		int changdu = s.getBytes("GBK").length;
		if (changdu > num) {
			System.out.println(" 0000 ");
			s = s.substring(0, s.length() - 1);
			s = idgui(s, num);
		}
		return s;
	}

	public static void main(String[] args) throws Exception {
		// 原始字符串
		String s = "我ZWR爱你们JAVA";
		System.out.println("原始字符串：" + s + " : 字节数是: " + s.getBytes("GBK").length);

//		System.out.println("截取前1位：" + CutString.substring(s, 1));
//		System.out.println("截取前2位：" + CutString.substring(s, 2));
//		System.out.println("截取前4位：" + CutString.substring(s, 4));
		
		System.out.println("截取前1位：" + CutString.gsubstring(s, 1));
		System.out.println("截取前2位：" + CutString.gsubstring(s, 2));
		System.out.println("截取前4位：" + CutString.gsubstring(s, 4));

		// System.out.println("截取前12位：" + CutString.substring(s, 12));
//		 System.out.println("截取前12字节：" + CutString.idgui(s, 11));

	}
}