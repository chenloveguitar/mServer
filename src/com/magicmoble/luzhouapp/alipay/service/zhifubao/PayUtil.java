package com.magicmoble.luzhouapp.alipay.service.zhifubao;

import static com.magicmoble.luzhouapp.alipay.service.zhifubao.OrderConfig.RSA_PRIVATE;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by mangues on 16/6/20.
 */
public class PayUtil {
    public static String  ZhiFuBaoPay(String orderName, String orderContent, String price,String orderId,String url){
        String orderInfo = OrderUtil.getOrderInfo(orderName,orderContent,price,orderId,url);
        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        /**
         * 仅需对sign 做URL编码
         */
        try {
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
        return payInfo;
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    private static String getSignType() {
        return "sign_type=\"RSA\"";
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    private static String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }


    public static void main(String[] args){
//        System.out.print(ZhiFuBaoPay("毛笔","毛笔字","0.01","1213rrr223b232www43",recharge_url));

    }

}
