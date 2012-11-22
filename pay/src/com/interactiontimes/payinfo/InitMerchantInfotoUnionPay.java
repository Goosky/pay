package com.interactiontimes.payinfo;

import com.unionpay.unionpayinfo.*;

public class InitMerchantInfotoUnionPay {
	public static void merchantUnionInfoSetting(){
		//merchant info setting
		UnionPayInfo.setMerchantId("898000000000002");//merchantId
		UnionPayInfo.setMerchantName("联通华建");//merchantName
		UnionPayInfo.setMerchantPassword("898000000000002");//merchant password
		UnionPayInfo.setOrderIdLength(20);//orderIdlength
		UnionPayInfo.setTransTimeout("");//order transtime out
		UnionPayInfo.setMerchantPublicCer("MIIDuDCCAyGgAwIBAgIQaOXUUCzukC6m5EAlw0LdZjANBgkqhkiG9w0BAQUFADAkMQswCQYDVQQGEwJDTjEVMBMGA1UEChMMQ0ZDQSBURVNUIENBMB4XDTExMDgxNzAyNDU1M1oXDTEyMDgxNzAyNDU1M1owfjELMAkGA1UEBhMCQ04xFTATBgNVBAoTDENGQ0EgVEVTVCBDQTERMA8GA1UECxMITG9jYWwgUkExFDASBgNVBAsTC0VudGVycHJpc2VzMS8wLQYDVQQDFCYwNDFAWjIwMTEwODE3QDg5ODAwMDAwMDAwMDAwMkAwMDAwMDAwMzCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAzD7Xy03ptoXR7jx3BxGD5GN2Fsivu/QprnYZF+Axby8LjVNGs97tHn8CHfXzvFMqAvsd4dkKzKrTG+dOmrlunYLGFrntIHl8Mx3liFkGLYFuJUy1+HF/hIRAMPIkDux6AAhbbCZlawdx5faHkM5OQg2KGeBcD+8NUJA6IYOunIUCAwEAAaOCAY8wggGLMB8GA1UdIwQYMBaAFEZy3CVynwJOVYO1gPkL2+mTs/RFMB0GA1UdDgQWBBSrHyJHSuW1lYnmxBBh6ulilF4xSTALBgNVHQ8EBAMCBPAwDAYDVR0TBAUwAwEBADA7BgNVHSUENDAyBggrBgEFBQcDAQYIKwYBBQUHAwIGCCsGAQUFBwMDBggrBgEFBQcDBAYIKwYBBQUHAwgwgfAGA1UdHwSB6DCB5TBPoE2gS6RJMEcxCzAJBgNVBAYTAkNOMRUwEwYDVQQKEwxDRkNBIFRFU1QgQ0ExDDAKBgNVBAsTA0NSTDETMBEGA1UEAxMKY3JsMTI3XzE1MzCBkaCBjqCBi4aBiGxkYXA6Ly90ZXN0bGRhcC5jZmNhLmNvbS5jbjozODkvQ049Y3JsMTI3XzE1MyxPVT1DUkwsTz1DRkNBIFRFU1QgQ0EsQz1DTj9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0P2Jhc2U/b2JqZWN0Y2xhc3M9Y1JMRGlzdHJpYnV0aW9uUG9pbnQwDQYJKoZIhvcNAQEFBQADgYEARfg4YNGNETrJx+gy74UmPJ326T7H2hIE/lRcTyonq4NFpXmssau+TDV7btLUuhDxBGF1JysknFjeNAKMl9ZFGjKbOGGpQ7nfnEC8HIM7cp2n+gSlADRZbc8PHrqxLbjxsKoSUUFfh3PhfNXtWLfTxi5TT+hm6coV1K/EUX4t0AY=");//public cer
		UnionPayInfo.setMerchantPublicCerPath("d:\\key\\898000000000001.cer");
		UnionPayInfo.setMerchantPrivateKeyAlias("889ce7a52067a87f905c91f502c69644_d1cba47d-cbb1-4e29-9d77-8d1fe1b0dccd");//private key alias
		UnionPayInfo.setPrivateKeyPath("d:\\key\\898000000000002.p12");//private key path
		UnionPayInfo.setBackEndUrl("http://124.127.89.56:8985/pay/unionBack.action");//backEndUrl
		// union server setting
		UnionPayInfo.setUnionServerUrl("http://211.154.166.219/qzjy/MerOrderAction/deal.action");
		//test
		//UnionPayInfo.setUnionServerUrl("http://localhost:8082/SimulateUnionServer/orderdeal.action");
		UnionPayInfo.setUnionServerTimeOut("");
		}
}
