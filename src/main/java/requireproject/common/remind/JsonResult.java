package requireproject.common.remind;

/**
 * @Description: Json返回结果
 */
public abstract class JsonResult {

    String code;

    public String getCode() {
        return code;
    }

    public JsonResult setCode(String code) {
        this.code = code;
        return this;
    }

    public static SuccessResult success(Object data) {

        return new SuccessResult(data);
    }

    public static ErrorResult error(String code, String message) {

        return new ErrorResult(code, message);
    }
}
