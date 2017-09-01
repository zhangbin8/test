package com.alipay;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;

@Controller
public class Test {
	
	public static void main(String[] args) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date =new Date();
		System.out.println(sf.format(date));
		System.out.println(sf.format(new Date(date.getTime()-5000)));
		System.out.println(3/4.0);
		
	}
	
	//http://localhoust:8080/alipay/pay
	private static final String APP_ID = "2016080500170461";
	private static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDgmc4VbjMRGqKrITFw9XCodF/0LtXdzKu4OyvjZJjzVAw8HqYoi2kXjar/VZFBwq3u9bnL5c9Uj0Eww/KJyU8SinkuQALZq+qjGFSseBeDzf2U8qVcWr64vJR+Ar+O78Oo0TTXOiK4b1vNF7DnAwv9wvD9GIkJUdrsTiIqgYSzTA/DDarYXSTuheW4tSgSbcZYR7ZjjjheXuB+i2l4B6iwgqs2s+neqb6qZC4divvFBAuoFcq3UD1GXr1BRNmKo61buiSdqTDZj6G1Syoql/GeLeoJabfmZuoedWyUszG9z7UT0TbeDVZWEnpOdBHeUoItqN03rYtkan3MfVEko9JJAgMBAAECggEAIuiE2VvZYA0tvz9XeeEL84Ky7zbgUqre/bFkruERzLABfs6csKyKVvjT3P+lpfzmH7/dcs1zHgdt8HBkH/pD6fPPxdp5Fu/bdiHv9yZrMTcw9JeKEAKipf/ZbIRs1YB6fqCVf+YvQ087i3LlMeKNWJGV9yB7HBG23wd90/FxeJRNS3pxtsyw9Qj0C0c4+IkuHojBq6CcRdw4RBJXXQQWlHu3ls7CyP34wHgldXcumSBJSwFOkVfWqjGxu5e2AWrEeI8IAI2njLa90SsLyqz35DBs1k4pGljvgEpJhU4qdopnldi7NAMKJToRZ1Lpw+W2FOMtiHJrzHgQnWDZNDBIKQKBgQDy4pA2hRvDEaxEO95PMmQ5ZE/TdX0wOtMSXV0NwfYAZgz5SzYEw/qRBduDoM6GI7JJmYIaRpiUHqFnkOuexv/y3zGJ/i9TvjNgfSwlf+Yg02aAo67dwN8Ac3tmZUeTUQt4f0IF+tzAEJ+xwDcO89Nr9iw922bCv5UQnTC+k0gLMwKBgQDsun8IbNSDpc/nDHcJIrw5+PkJkvqqlW8zgcZ5e/nxQumavAhirC7B6eCH9wPVlBPSKNm4HVFFRxiRUjdPi6zUShR6Qz+NuqSfhM3Q5pdphMBzB6iryhkIJeFManCAGOcRMftI5T6bZEFoQ1Xdf5iFfRKAm4+2UzTnJE33H0UMkwKBgDCvy7qyCfObSk3PNmvnrQF4Iyw/I94UHJVU079bPwWrbzRVqDliU4rYgr0q8dsfsrBgLh04Q4OFdFM/UIveVhYIOXTw1+nQeZoA3WWS8r1nwudMaFQZQlpjy7ul16gnGcaIs+Jg+/kgG/LUlfTQWXQNv6aSoVGud6IB8HBzM4DTAoGBAJuCd+jk9UX2Kvkw6xGJS/UZZR+w+hINUJuZaPRtSOgAeM05MJkBW0XviZxH3LD1MYAhbcTRHY5RAgaU59jNfJ+IeqNGgf1s2rAf4cSVdmnO1025fkg73u3HVJWKZpT/xus41kYwskwKNB11/nYfV+Z5Hyp1BpdTWbFt2/m3BPkZAoGAaWfwvMa5td8BoHfBWCEZlcBIg8Vj9hC9yuTCJz59IXxdpmC0wg/3JDhHFf22bdmDr73dIlmgjW4w+n4mzMnskANxx0o69Qy1t9sRGsFUQ4HSCbQnZMmBIo4a0L+RGgpXT5zKPCTTDRRAOtwLvS+r43LOwv1HYIAttiEHO9rKWUc=";
	private static final String CHARSET = "UTF-8";
	private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3kdP9bkCpEMHgGSoVN2iHWIyvfNMEJyroGvfq/runCaTr/rxh3VFemyUvmC4ZK2GKsHms4SNpFqxRN+WGyAvi7lgdPPWnKdc0lq++gTYGOD6FhYBmEkuVc2qTxuWpdA+cn1XveV/5gboxpXZTLO85LYJNY0F8vuXs39OvumiN8bna6dVNNYamog5hpQ4EFthaih/whVVaOHppPjzndiRHU4Jt0i+Uqt+qaXacIl+knQ6BjVm/u1/P2Hy69kwdB7o8B+e9W971ubBCgXWRsyzpGJp/koTAj7bKwZedHkswNCbaV7eoUeAo03VKVacGggyCM7eFqXjI+u3+jbNpD/HWwIDAQAB";
	
	@RequestMapping("/pay")
	public void main(HttpServletRequest httpRequest,HttpServletResponse httpResponse) throws AlipayApiException, IOException {
		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify 
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
	    alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
	    alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
	    alipayRequest.setBizContent("{" +
	        "    \"out_trade_no\":\"20150320010101002\"," +
	        "    \"total_amount\":\"88.88\"," +
	        "    \"subject\":\"Iphone6 16G\"," +
	        "    \"product_code\":\"QUICK_WAP_PAY\"" +
	        "  }");//填充业务参数
	    String form="";
	    try {
	        form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
	    } catch (AlipayApiException e) {
	        e.printStackTrace();
	    }
	    httpResponse.setContentType("text/html;charset=" + CHARSET);
	    httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
	    httpResponse.getWriter().flush();
	    httpResponse.getWriter().close();
	}
}
