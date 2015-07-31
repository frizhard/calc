package com.frizhard.calculator.client;

public interface FSM<Input> {
	void reset();
	void feedInput(Input input);
}
