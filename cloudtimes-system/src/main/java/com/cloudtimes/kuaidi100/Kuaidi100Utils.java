package com.cloudtimes.kuaidi100;

import com.google.gson.Gson;
import com.kuaidi100.sdk.api.QueryTrack;
import com.kuaidi100.sdk.contant.CompanyConstant;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.QueryTrackParam;
import com.kuaidi100.sdk.request.QueryTrackReq;
import com.kuaidi100.sdk.utils.SignUtils;
import com.cloudtimes.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kuaidi100Utils {

    @Autowired
    private Kuaidi100Config config;

    private static Logger log = LoggerFactory.getLogger(Kuaidi100Utils.class);


    public String query(String company, String num, String mobile) {

        QueryTrackReq queryTrackReq = new QueryTrackReq();
        QueryTrackParam queryTrackParam = new QueryTrackParam();
        queryTrackParam.setCom(company);
        queryTrackParam.setNum(num);
        queryTrackParam.setPhone(mobile);
        String param = new Gson().toJson(queryTrackParam);

        queryTrackReq.setParam(param);
        queryTrackReq.setCustomer(config.getCustomer());
        queryTrackReq.setSign(SignUtils.querySign(param, config.getKey(), config.getCustomer()));

        IBaseClient baseClient = new QueryTrack();
        try {
            HttpResult result = baseClient.execute(queryTrackReq);
            log.info("快递查询结果：" + result.getBody());
            return result.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("");
        }
    }
}
