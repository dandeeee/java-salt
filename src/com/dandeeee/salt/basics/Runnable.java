package com.dandeeee.salt.basics;

/**
 * @author dandedkov.com
 * */
public class Runnable {

	public static void time(java.lang.Runnable code) {
		long start = System.nanoTime();
		code.run();
		System.out.println("it took " + (System.nanoTime() - start) + " ns");
	}

	public static void main(String[] args) {
		String[] s = new String[] {"a", "b", "c"};

		java.lang.Runnable code = new java.lang.Runnable() {
			public void run() {
				System.out.println("Hello World!");
			}
		};
		System.out.println(code.toString());

		time(code);
	}
}
