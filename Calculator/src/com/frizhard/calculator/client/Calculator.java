package com.frizhard.calculator.client;

import com.frizhard.calculator.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Calculator implements EntryPoint {
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		CalculatorFSM fsm = new CalculatorFSM();
		
		createLayout(fsm);
	}
	
	private void createLayout(final CalculatorFSM fsm) {
		final TextBox screenText = new TextBox();
		screenText.setEnabled(false);
		final Button clearButton = new Button(Constants.CommandClear);
		final Button clearEntryButton = new Button(Constants.CommandClearEntry);
		final Button digit7Button = new Button(Constants.Digit7);
		final Button digit8Button = new Button(Constants.Digit8);
		final Button digit9Button = new Button(Constants.Digit9);
		final Button signumButton = new Button(Constants.ModifierSignum);
		final Button percentButton = new Button(Constants.ModifierPercent);
		final Button digit4Button = new Button(Constants.Digit4);
		final Button digit5Button = new Button(Constants.Digit5);
		final Button digit6Button = new Button(Constants.Digit6);
		final Button operatorSumButton = new Button(Constants.OperatorSum);
		final Button operatorSubButton = new Button(Constants.OperatorSubtract);
		final Button digit1Button = new Button(Constants.Digit1);
		final Button digit2Button = new Button(Constants.Digit2);
		final Button digit3Button = new Button(Constants.Digit3);
		final Button operatorMulButton = new Button(Constants.OperatorMultiply);
		final Button operatorDivButton = new Button(Constants.OperatorDivision);
		final Button digit0Button = new Button(Constants.Digit0);
		final Button dotButton = new Button(Constants.ModifierDot);
		final Button equalButton = new Button(Constants.CommandEqual);
		
		clearButton.addClickHandler(clickHandlerWithInput(Constants.CommandClear, fsm));
		clearEntryButton.addClickHandler(clickHandlerWithInput(Constants.CommandClearEntry, fsm));
		equalButton.addClickHandler(clickHandlerWithInput(Constants.CommandEqual, fsm));
		
		digit0Button.addClickHandler(clickHandlerWithInput(Constants.Digit0, fsm));
		digit1Button.addClickHandler(clickHandlerWithInput(Constants.Digit1, fsm));
		digit2Button.addClickHandler(clickHandlerWithInput(Constants.Digit2, fsm));
		digit3Button.addClickHandler(clickHandlerWithInput(Constants.Digit3, fsm));
		digit4Button.addClickHandler(clickHandlerWithInput(Constants.Digit4, fsm));
		digit5Button.addClickHandler(clickHandlerWithInput(Constants.Digit5, fsm));
		digit6Button.addClickHandler(clickHandlerWithInput(Constants.Digit6, fsm));
		digit7Button.addClickHandler(clickHandlerWithInput(Constants.Digit7, fsm));
		digit8Button.addClickHandler(clickHandlerWithInput(Constants.Digit8, fsm));
		digit9Button.addClickHandler(clickHandlerWithInput(Constants.Digit9, fsm));
		
		signumButton.addClickHandler(clickHandlerWithInput(Constants.ModifierSignum, fsm));
		percentButton.addClickHandler(clickHandlerWithInput(Constants.ModifierPercent, fsm));
		dotButton.addClickHandler(clickHandlerWithInput(Constants.ModifierDot, fsm));
		
		operatorSumButton.addClickHandler(clickHandlerWithInput(Constants.OperatorSum, fsm));
		operatorSubButton.addClickHandler(clickHandlerWithInput(Constants.OperatorSubtract, fsm));
		operatorMulButton.addClickHandler(clickHandlerWithInput(Constants.OperatorMultiply, fsm));
		operatorDivButton.addClickHandler(clickHandlerWithInput(Constants.OperatorDivision, fsm));

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("calcScreenContainer").add(screenText);
		RootPanel.get("calcCContainer").add(clearButton);
		RootPanel.get("calcCEContainer").add(clearEntryButton);
		RootPanel.get("calc7Container").add(digit7Button);
		RootPanel.get("calc8Container").add(digit8Button);
		RootPanel.get("calc9Container").add(digit9Button);
		RootPanel.get("calcSignumContainer").add(signumButton);
		RootPanel.get("calcPercentContainer").add(percentButton);
		RootPanel.get("calc4Container").add(digit4Button);
		RootPanel.get("calc5Container").add(digit5Button);
		RootPanel.get("calc6Container").add(digit6Button);
		RootPanel.get("calcSumContainer").add(operatorSumButton);
		RootPanel.get("calcSubContainer").add(operatorSubButton);
		RootPanel.get("calc1Container").add(digit1Button);
		RootPanel.get("calc2Container").add(digit2Button);
		RootPanel.get("calc3Container").add(digit3Button);
		RootPanel.get("calcMulContainer").add(operatorMulButton);
		RootPanel.get("calcDivContainer").add(operatorDivButton);
		RootPanel.get("calc0Container").add(digit0Button);
		RootPanel.get("calcDotContainer").add(dotButton);
		RootPanel.get("calcEqualContainer").add(equalButton);
	}
	
	private ClickHandler clickHandlerWithInput(final String input, final CalculatorFSM fsm) {
		return new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				fsm.feedInput(input);				
			}
		};
	}
}
