package com.swapnil.reflection_gym.constant;

/**
 * @author Swapnil Bhosale
 * This is Enum for DI types
 */
public enum DIType {

	DIByConstructore("constructore"),
	DIBySetter("setter");

	private String value;

	DIType(String value){
		this.value = value;
	}

	@Override
	public String toString(){
		return value;
	}
}
