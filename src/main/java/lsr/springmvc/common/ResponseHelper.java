package lsr.springmvc.common;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by lenovo on 2017/6/29.
 */
public class ResponseHelper {

    public static JSONObject generateResponseJSON(String rtnCode,
                                                  String rtnMsg,
                                                  Object data){
        JSONObject rtnJSON = new JSONObject();
        rtnJSON.put("rtnCode",rtnCode);
        rtnJSON.put("rtnMsg",rtnMsg);
        rtnJSON.put("data",data);
        return rtnJSON;
    }

    public static JSONObject generateResponseJSON(String rtnCode,
                                                  String rtnMsg){
        return generateResponseJSON(rtnCode,rtnMsg,null);
    }

}
