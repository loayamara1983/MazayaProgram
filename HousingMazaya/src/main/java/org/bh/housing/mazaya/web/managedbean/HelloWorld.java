package org.bh.housing.mazaya.web.managedbean;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import lombok.Data;

@Data
@Named
@Scope("view")
public class HelloWorld {

	private String firstName = "John";
	private String lastName = "Doe";

	public String showGreeting() {
		return "Hello " + firstName + " " + lastName + "!";
	}
}
