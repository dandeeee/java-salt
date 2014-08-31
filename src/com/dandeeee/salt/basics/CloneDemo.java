package com.dandeeee.salt.basics;

import com.dandeeee.salt.basics.zoo.Animal;
import com.dandeeee.salt.basics.zoo.Dog;

/**
 * @author dandedkov.com
 * */
public class CloneDemo {
	public static void main(String[] args) throws CloneNotSupportedException {
        Animal animal = new Dog("Dolly");
		Animal reference = animal;
		Animal clone = animal.clone();

		System.out.println(animal == reference);
		System.out.println(animal == clone);
		System.out.println(animal.equals(clone));
		System.out.println(clone);
	}
}
