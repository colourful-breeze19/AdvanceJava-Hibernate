/**
 * @class  Main
 * @author chahat
 * @description Main class which creates a UserInputManger object that handles
 * all the required operations.
 *
 */
package com.nagarro.training;

import java.util.logging.Level;

import com.nagarro.training.input.UserInput;

public class Main {
	public static void main(String[] args) {
		// Turning off hibernate console logging.
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		UserInput object = new UserInput();
		object.operations();
	}
}
