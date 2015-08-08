package com.frizhard.calculator.client;

/**
 * Interface que define una máquina de estados finitos (FSM).
 * 
 * Es genérica, para que se le pueda pasar cualquier tipo como entrada.
 * 
 * @author jose
 *
 * @param <Input>	Tipo de la entrada que se le va a pasar a la FSM
 */
public interface FSM<Input> {
	void reset();
	void feedInput(Input input);
}
