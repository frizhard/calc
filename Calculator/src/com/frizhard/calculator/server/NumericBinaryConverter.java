package com.frizhard.calculator.server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.frizhard.calculator.client.Constants;

public class NumericBinaryConverter implements BinaryConverter {

	@Override
	public String convertNumber(String number) {
		
		if(number == null || number.equals(Constants.Digit0)) {
			return Constants.Digit0;
		}
		
		String kNumberRegex = "^(-?)([0-9]+)(,([0-9]*))?$";
		//                       1   2       3 4
		Pattern numberRegex = Pattern.compile(kNumberRegex);
		Matcher matcher = numberRegex.matcher(number);
		
		if(matcher.find()) {
			String signum = matcher.group(1);
			String integral = matcher.group(2);
			//String comma = matcher.group(3);
			String fractional = matcher.group(4);
			
			boolean isNegative = (signum.length() > 0);
			boolean hasFractional = (fractional != null && fractional.length() > 0);
			
			if(isNegative && hasFractional && !integral.equals(Constants.Digit0)) {
				// Must adjust integral part
				integral = "" + (Integer.parseInt(integral) + 1);
			}
			
			String binaryIntegral = binarizeIntegral(integral);
			if(isNegative) {
				binaryIntegral = invertBinarySign(binaryIntegral);
			}
			String binaryFractional = "";
			if(hasFractional) {
				double fraction = Double.parseDouble(Constants.AritmeticDot + fractional);
				binaryFractional = binarizeFractional(isNegative ? (1.0 - fraction) : fraction);
			}
			
			return signum + binaryIntegral + (hasFractional ? Constants.ModifierDot : "") + binaryFractional;
		} else {
			return Constants.Error;
		}
	}
	
	private String binarizeIntegral(String integral) {
		if(integral == null || integral.equals(Constants.Digit0)) {
			return Constants.Digit0;
		} else {
			StringBuffer result = new StringBuffer();
			int value = Integer.parseInt(integral);
			while(value > 0) {
				int reminder = value & 0x01; 
				result.append((reminder == 1) ? Constants.Digit1 : Constants.Digit0);
				value >>= 1;
			}
			
			return result.reverse().toString();
		}
	}
	
	private String invertBinarySign(String binaryString) {
		if(binaryString == null || binaryString.equals(Constants.Digit0)) {
			return Constants.Digit1;
		} else {
			StringBuffer reversed = new StringBuffer(binaryString).reverse();
			StringBuffer inverted = new StringBuffer();
			
			boolean hasFound1 = false;
			int reversedLength = reversed.length();
			for(int i=0;i<reversedLength;i++) {
				String digit = Character.toString(reversed.charAt(i));
				if (digit.equals(Constants.Digit0)) {
					inverted.append(hasFound1 ? Constants.Digit1 : Constants.Digit0);
				} else {
					inverted.append(hasFound1 ? Constants.Digit0 : Constants.Digit1);
					hasFound1 = true;
				}
			}
			inverted.append(Constants.Digit1);
			
			return inverted.reverse().toString(); 
		}
	}
	
	private String binarizeFractional(double fractional) {
		StringBuffer binary = new StringBuffer();
		int length = 0;
		int kMaxConvertedDigits = 20;
		
		while(fractional != 0.0 && length < kMaxConvertedDigits) {
			double dup = fractional * 2.0;
			binary.append((dup >= 1.0) ? Constants.Digit1 : Constants.Digit0);
			
			fractional = dup - Math.floor(dup);
			++length;
		}
		
		return binary.toString();
	}
	
}
