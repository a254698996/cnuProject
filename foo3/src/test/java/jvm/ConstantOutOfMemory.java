package jvm;

import java.util.ArrayList;
import java.util.List;

public class ConstantOutOfMemory {

	/**
	 * 
	 * @param args
	 * 
	 * @throws Exception
	 * 
	 * @Author YHJ create at 2011-10-30 下午04:28:25
	 * 
	 */

	public static void main(String[] args) throws Exception {

		try {
			StringBuffer sbf;
			StringBuilder sbd;
			List<String> strings = new ArrayList<String>();

			int i = 0;

			while (true) {

				strings.add(String.valueOf(i++).intern());

			}

		} catch (Exception e) {

			e.printStackTrace();

			throw e;

		}

	}

}