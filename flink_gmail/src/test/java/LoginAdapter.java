/**
 * @ClassName LoginAdapter
 * @Author yy
 * @Description
 * @Date 2021/9/17 11:48
 * @Version 1.0
 **/
public class LoginAdapter extends UserLogin implements Ilogin{
    @Override
    public void loginQQ(String username, String password) {
        System.out.println("QQ");
        super.login(username,password);
    }

    @Override
    public void loginWechat(String username, String password) {

        System.out.println("WeChat");
        super.login(username,password);
    }
}
