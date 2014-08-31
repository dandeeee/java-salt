package com.dandeeee.salt.basics;

import java.util.Properties;

/**
 * @author dandedkov.com
 * */
public class SystemProperties {
	public static void main(String[] args) {
		System.out.println("Running on " + System.getProperty("os.name"));

		Properties properties = System.getProperties();
		for (Object name : properties.keySet()) {
			System.out.println(name + "=" + properties.get(name));
		}
	}
}
