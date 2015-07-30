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
		createLayout();
	}
	
	private void createLayout() {
		final TextBox screenText = new TextBox();
		screenText.setEnabled(false);
		final Button clearButton = new Button("C");	// IMPROVE: constants
		final Button clearEntryButton = new Button("CE");
		final Button digit7Button = new Button("7");
		final Button digit8Button = new Button("8");
		final Button digit9Button = new Button("9");
		final Button signumButton = new Button("+/-");
		final Button percentButton = new Button("%");
		final Button digit4Button = new Button("4");
		final Button digit5Button = new Button("5");
		final Button digit6Button = new Button("6");
		final Button operatorSumButton = new Button("+");
		final Button operatorSubButton = new Button("-");
		final Button digit1Button = new Button("1");
		final Button digit2Button = new Button("2");
		final Button digit3Button = new Button("3");
		final Button operatorMulButton = new Button("*");
		final Button operatorDivButton = new Button("/");
		final Button digit0Button = new Button("0");
		final Button dotButton = new Button(",");
		final Button equalButton = new Button("=");

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
}
