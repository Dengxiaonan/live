package requireproject.common.remind;

/**
 * @Description: 错误结果
 */
public class ErrorResult extends JsonResult {

    private String message;

    public String getMessage() {
        return message;
    }

    public ErrorResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorResult() {
    }

    public ErrorResult(String code, String message) {

        super.code = code;
        this.message = message;
    }

}
