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
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Calculator implements EntryPoint {
	
	private final TextField screenText = new TextField();
	
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
		CalculatorFSM fsm = new CalculatorFSM(new CalculatorOutput() {
			
			@Override
			public void outputDidChangeTo(String output) {
				// TODO Auto-generated method stub
				screenText.setText(output);
			}
		});
		
		createLayout(fsm);
	}
	
	private void createLayout(final CalculatorFSM fsm) {
		//final TextBox screenText = new TextBox();
		screenText.setEnabled(false);
		final TextButton clearButton = new TextButton(Constants.CommandClear);
		final TextButton clearEntryButton = new TextButton(Constants.CommandClearEntry);
		final TextButton digit7Button = new TextButton(Constants.Digit7);
		final TextButton digit8Button = new TextButton(Constants.Digit8);
		final TextButton digit9Button = new TextButton(Constants.Digit9);
		final TextButton signumButton = new TextButton(Constants.ModifierSignum);
		final TextButton percentButton = new TextButton(Constants.ModifierPercent);
		final TextButton digit4Button = new TextButton(Constants.Digit4);
		final TextButton digit5Button = new TextButton(Constants.Digit5);
		final TextButton digit6Button = new TextButton(Constants.Digit6);
		final TextButton operatorSumButton = new TextButton(Constants.OperatorSum);
		final TextButton operatorSubButton = new TextButton(Constants.OperatorSubtract);
		final TextButton digit1Button = new TextButton(Constants.Digit1);
		final TextButton digit2Button = new TextButton(Constants.Digit2);
		final TextButton digit3Button = new TextButton(Constants.Digit3);
		final TextButton operatorMulButton = new TextButton(Constants.OperatorMultiply);
		final TextButton operatorDivButton = new TextButton(Constants.OperatorDivision);
		final TextButton digit0Button = new TextButton(Constants.Digit0);
		final TextButton dotButton = new TextButton(Constants.ModifierDot);
		final TextButton equalButton = new TextButton(Constants.CommandEqual);
		
		clearButton.addSelectHandler(eventHandlerWithInput(Constants.CommandClear, fsm));
		clearEntryButton.addSelectHandler(eventHandlerWithInput(Constants.CommandClearEntry, fsm));
		equalButton.addSelectHandler(eventHandlerWithInput(Constants.CommandEqual, fsm));
		
		digit0Button.addSelectHandler(eventHandlerWithInput(Constants.Digit0, fsm));
		digit1Button.addSelectHandler(eventHandlerWithInput(Constants.Digit1, fsm));
		digit2Button.addSelectHandler(eventHandlerWithInput(Constants.Digit2, fsm));
		digit3Button.addSelectHandler(eventHandlerWithInput(Constants.Digit3, fsm));
		digit4Button.addSelectHandler(eventHandlerWithInput(Constants.Digit4, fsm));
		digit5Button.addSelectHandler(eventHandlerWithInput(Constants.Digit5, fsm));
		digit6Button.addSelectHandler(eventHandlerWithInput(Constants.Digit6, fsm));
		digit7Button.addSelectHandler(eventHandlerWithInput(Constants.Digit7, fsm));
		digit8Button.addSelectHandler(eventHandlerWithInput(Constants.Digit8, fsm));
		digit9Button.addSelectHandler(eventHandlerWithInput(Constants.Digit9, fsm));
		
		signumButton.addSelectHandler(eventHandlerWithInput(Constants.ModifierSignum, fsm));
		percentButton.addSelectHandler(eventHandlerWithInput(Constants.ModifierPercent, fsm));
		dotButton.addSelectHandler(eventHandlerWithInput(Constants.ModifierDot, fsm));
		
		operatorSumButton.addSelectHandler(eventHandlerWithInput(Constants.OperatorSum, fsm));
		operatorSubButton.addSelectHandler(eventHandlerWithInput(Constants.OperatorSubtract, fsm));
		operatorMulButton.addSelectHandler(eventHandlerWithInput(Constants.OperatorMultiply, fsm));
		operatorDivButton.addSelectHandler(eventHandlerWithInput(Constants.OperatorDivision, fsm));

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
	
	private SelectEvent.SelectHandler eventHandlerWithInput(final String input, final CalculatorFSM fsm) {
		return new SelectEvent.SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				fsm.feedInput(input);
			}
		};
	}
}
