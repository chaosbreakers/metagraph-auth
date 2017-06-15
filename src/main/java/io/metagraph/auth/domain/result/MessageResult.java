package io.metagraph.auth.domain.result;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class MessageResult {

    public MessageResult() {
    }

    public MessageResult(String code, String message, String description, List<Object> result) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.result = result;
    }

    //error code;
    @JSONField(ordinal = 1)
    protected String code = "200";

    //error message
    @JSONField(ordinal = 2)
    protected String message = "success";

    //description
    @JSONField(ordinal = 3)
    protected String description = "请求成功";

    //result
    @JSONField(ordinal = 4)
    private Object result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
