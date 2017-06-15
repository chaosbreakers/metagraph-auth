package io.metagraph.auth.enmu;

/**
 * @author ZhaoPeng
 */
public enum ErrorCode {

    USER_MANAGE_ERROR("10000", "user operation error", "用户操作出错"),

    ADD_USER_ERROR(USER_MANAGE_ERROR, "10001", "add user error", "新增用户出错"),

    UPDATE_USER_ERROR(USER_MANAGE_ERROR, "10002", "update user error", "更新用户出错"),

    DELETE_USER_ERROR(USER_MANAGE_ERROR, "10003", "delete user error", "删除用户出错"),

    GET_USER_ERROR(USER_MANAGE_ERROR, "10004", "get user error", "查询用户出错"),

    ;

    ErrorCode(ErrorCode parent, String code, String message, String description) {
        this.parent = parent;
        this.code = code;
        this.message = message;
        this.description = description;
    }

    ErrorCode(String code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }


    protected String code;//error code;

    protected String message;//error message

    protected String description;//description

    protected ErrorCode parent;

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

    public ErrorCode getParent() {
        return parent;
    }

    public void setParent(ErrorCode parent) {
        this.parent = parent;
    }
}

