package com.magicmoble.luzhouapp.action.weixin_pay.RechargeInq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Created by mangues on 16/7/3.
 */
@Service()
public class AppPaymentManager {



    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *  支付成功执行
    * @param id
     * @return
     */
    public  boolean payOrderFinish(String id) {//订单id
        String ids[] = id.split("_");
        if (ids.length==2){
            id = ids[0];
        }


        /**
         * 处理订单id
         */



        logger.info("支付成功");
        return true;
    }





}
