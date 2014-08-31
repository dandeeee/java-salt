package com.dandeeee.salt.basics.zoo;

/**
 * This class represents any <b>dog</b> in the world.
 *
 * @author dandedkov.com
 */
public class Dog extends Pet {

	/**
	 * Creates a dog with a name
	 * @param name specifies the new name
	 */
	public Dog(String name) {
		super(name);
	}

	public String toString() {
        return "Dog named " + name;
    }
}
