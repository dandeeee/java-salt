package com.dandeeee.salt.basics.zoo;


/**
 * A lovely pet
 *
 * @author dandedkov.com
 * */
public class Pet extends Animal {
	String name;

	public Pet(String name) {
		this.name = name;
	}

	public Pet rename(String newName) {
		return new Dog(newName);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Dog)) return false;

		Dog dog = (Dog) o;
		return name.equals(dog.name) && getAge() == dog.getAge();
	}

	@Override
	public int hashCode() {
		return 31 * name.hashCode() + getAge();
	}

	public String getName() {
		return name;
	}
}
