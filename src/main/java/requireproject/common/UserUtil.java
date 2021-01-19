package requireproject.common;


import javax.servlet.http.HttpServletRequest;

/**
 * @author tyd
 * @version 1.0
 * @className UserUtil
 * @date 2019/1/14 19:34
 * @description TODO
 **/

public class UserUtil {

    public static UseDetailsDTO getUserDTO(HttpServletRequest request){
        Object o = request.getAttribute("useDetailsDTO");
        if(null == o){
            return null;
        }
        UseDetailsDTO useDetailsDTO = (UseDetailsDTO)o ;
        return useDetailsDTO;
    }
}
