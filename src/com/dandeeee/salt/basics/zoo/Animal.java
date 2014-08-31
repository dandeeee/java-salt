package com.dandeeee.salt.basics.zoo;

import java.io.Serializable;

/**
 * Any animal
 *
 * @author dandedkov.com
* */
public abstract class Animal implements Cloneable, Serializable {
	private int age;

	public int getAge() {
		return age;
	}

	public void becomeOlder() {
		age++;
	}

	@Override
	public Animal clone() throws CloneNotSupportedException {
		try {
			return (Animal) super.clone();
		}
		catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
