package com.swapnil.constant;




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
