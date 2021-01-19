package requireproject.common.remind;

import java.io.Serializable;

/**
 * @Description: 成功结果
 */
public class SuccessResult extends JsonResult implements Serializable {

    private static final long serialVersionUID = 4060499997854428644L;

    private Object data;

    public Object getData() {
        return data;
    }

    public SuccessResult setData(Object data) {
        this.data = data;
        return this;
    }

    public SuccessResult() {
    }

    public SuccessResult(Object data) {

        super.code = "0";
        this.data = data;
    }

}
