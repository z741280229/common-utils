import com.zeno.entity.User;
import com.zeno.utils.ParameterUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @program: common-utils
 * @description:
 * @author: Zeno
 * @create: 2019-12-21 16:44
 **/
public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.setName("zeno");
        user.setGender("male");
        user.setAge(18);
        Integer num = new Integer(1);

        System.out.println(ParameterUtil.parameterIsNull(user));
        System.out.println(ParameterUtil.parameterIsNull(user,"name","age"));
        System.out.println(Arrays.toString(ParameterUtil.findNullParam(user).toArray()));
        System.out.println(Arrays.toString(ParameterUtil.findNullParam(user,"name","age").toArray()));
        System.out.println(ParameterUtil.parameterIsNull("zeno",num,user));
    }
}
