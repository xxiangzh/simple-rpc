import com.xzh.rpc.handler.RpcUserServiceImpl;
import com.xzh.service.UserService;

/**
 * @author 向振华
 * @date 2021/03/10 15:00
 */
public class ConsumerTest {

    public static void main(String[] args) {
        UserService userService = new RpcUserServiceImpl();
        System.out.println(userService.getUserNameById(1L));
        System.out.println(userService.getUserNameById(-1L));
    }
}
