package com.lightning.portal.util;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author gyf
 * @Date 2021-02-25 16:46
 * @ClassName Results
 * @Description
 */
public class Results {
    public static String myResult(String boo){
        Map<String, Object> ssm = new HashMap<>();
        Gson gson = new Gson();
        if ("true".equals(boo)) {
            ssm.put("code", 200);
            ssm.put("msg", "success");
        } else {
            ssm.put("code", 500);
            ssm.put("msg", "error");
        }
        String s = gson.toJson(ssm);
        return s;
    }
}
