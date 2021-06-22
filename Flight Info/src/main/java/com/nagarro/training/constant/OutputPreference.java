/**
 * @enum OutputPreference
 * @author chahat
 * @description OutputPreference enum contains preference for the ordering the result.
 *
 */
package com.nagarro.training.constant;

public enum OutputPreference {
	A("fare"), B("Duration");
	public String value;
	private OutputPreference(String str) {
		value = str;
	}
}
