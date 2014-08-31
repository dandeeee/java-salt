package com.dandeeee.salt.basics.zoo;

/**
 * @author dandedkov.com
 * */
public class Bulldog extends Dog {
	public Bulldog(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Bulldog named " + name;
	}
}
