package br.com.stecnology.math;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleMath {

	public Double sum(Double firstNumber, Double secondNumber) {
		return firstNumber + secondNumber;
	}

	public Double subTraction(Double firstNumber, Double secondNumber) {
		return firstNumber - secondNumber;
	}
	public Double multiplication(Double firstNumber, Double secondNumber) {
		return firstNumber * secondNumber;
	}

	public Double division(Double firstNumber, Double secondNumber) {
		return firstNumber / secondNumber;
	}

	public Double media(Double firstNumber, Double secondNumber) {
		return (firstNumber + secondNumber)/2;
	}
	public Double square(Double uniqueNumber) {
		return (Double) Math.sqrt(uniqueNumber);
	}

}
