package restaurant;

import java.util.Random;

public class RandomUtil {

	public static int randomName(int max, int min) {
		Random random = new Random();
		return random.nextInt(max) % (max - min + 1) + min;
	}
	
	public static void main(String[] args) {
		System.out.println(randomName(3, 1));
		System.out.println(randomName(3, 1));
		System.out.println(randomName(3, 1));
		System.out.println(randomName(3, 1));
	}
}
