package com.thucpham.shopweb.common;

import org.springframework.stereotype.Component;

@Component
public class GenOtp {

	public String genRandomCode()
    {
        long decimalNumber = System.nanoTime() + 69;
        String strBaseDigits = "0123456789";
        String strTempVal = "";
        int mod = 0;
        while (decimalNumber != 0)
        {
            mod = (int) (decimalNumber % 10);
            strTempVal = strBaseDigits.substring(mod, mod + 1) + strTempVal;
            decimalNumber = decimalNumber / 10;
        }
        return strTempVal.substring(strTempVal.length() - 6, strTempVal.length());
    }
	
//	public static void main(String[] args) {
//		GenOtp genOtp = new GenOtp();
//		String otp = genOtp.genRandomCode();
//		System.out.println(otp);
//	}
}
