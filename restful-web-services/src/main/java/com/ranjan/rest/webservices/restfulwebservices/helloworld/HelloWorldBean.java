package com.ranjan.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {

	private String messagae;

	public HelloWorldBean(String message) {
		this.messagae = message;
	}

	public String getMessagae() {
		return messagae;
	}

	public void setMessagae(String messagae) {
		this.messagae = messagae;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [messagae=" + messagae + "]";
	}
	
}
