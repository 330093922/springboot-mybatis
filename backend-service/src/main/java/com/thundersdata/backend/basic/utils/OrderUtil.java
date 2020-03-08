package com.thundersdata.backend.basic.utils;

import cn.hutool.core.date.DateUtil;
import com.thundersdata.backend.basic.dao.WaybillConsignMapper;
import com.thundersdata.backend.basic.dao.WaybillOrderMapper;
import com.thundersdata.backend.basic.model.WaybillConsign;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

import static cn.hutool.core.date.DatePattern.PURE_DATE_PATTERN;

@Component
public class OrderUtil {

    @Autowired
    private WaybillOrderMapper waybillOrderMapper;

    @Autowired
    private WaybillConsignMapper waybillConsignMapper;

    private static OrderUtil orderUtil;

    @PostConstruct
    public void init() {
        orderUtil = this;
        orderUtil.waybillOrderMapper = this.waybillOrderMapper;
    }


    public static String getLikeStr(String str) {
        if (str == null || "".equalsIgnoreCase(str)) {
            return null;
        }

        return "%" + str + "%";
    }

    public static String getLeftLikeStr(String str) {
        if (str == null || "".equalsIgnoreCase(str)) {
            return null;
        }

        return str + "%";
    }

    /**
     * 根据许可证编号(企业统一征信号码),生成运单编号
     *
     * @param creditCode
     * @return
     */
    public static String generateOrderNo(String creditCode) {
        String day = DateUtil.format(new Date(), PURE_DATE_PATTERN);
        creditCode = "C" + creditCode;
        String orderCode = orderUtil.waybillOrderMapper.selectByOrderCode(getLeftLikeStr(creditCode + day));
        if (StringUtils.isBlank(orderCode)) {
            orderCode = creditCode + day + "000";
        } else {
            String substring = orderCode.substring(orderCode.length() - 3);
            int macCode = Integer.parseInt(substring) + 1;
            orderCode = creditCode + day + String.format("%03d", macCode);
        }

        return orderCode;
    }

    /**
     * 根据许可证编号(企业统一征信号码),生成托运单编号
     *
     * @param creditCode
     * @return
     */
    public static String generateConsignNo(String creditCode) {
        String day = DateUtil.format(new Date(), PURE_DATE_PATTERN);
        creditCode = "T" + creditCode;
        String consignCode = orderUtil.waybillConsignMapper.selectByConsignCode(getLeftLikeStr(creditCode + day));

        if (StringUtils.isBlank(consignCode)) {
            consignCode = creditCode + day + "000";
        } else {
            String substring = consignCode.substring(consignCode.length() - 3);
            int macCode = Integer.parseInt(substring) + 1;
            consignCode = creditCode + day + String.format("%03d", macCode);
        }

        return consignCode;
    }
}
