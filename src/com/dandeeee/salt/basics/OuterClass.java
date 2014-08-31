package com.dandeeee.salt.basics;

/**
 * @author dandedkov.com
 * */
public class OuterClass {
	int someField;
	static int someStaticField;

	class InnerClass {
		int someField;

		@Override
		public String toString() {
			return "My outer class has " + OuterClass.this.someField;
		}
	}

	static class StaticInnerClass {
		static int someStaticField;

		@Override
		public String toString() {
			return "" + OuterClass.someStaticField;
		}
	}
}
