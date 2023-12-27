package bc.controller.auth;


import bc.bean.User;
import bc.dao.UserDAO;
import bc.entity.UsersEntity;
import bc.util.HibernateUtil;
import bc.util.PasswordHashing;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
//    private static SessionFactory factory;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("user") User user) {
        return "auth/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showMain( @ModelAttribute("user") User user, BindingResult result) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (username.isBlank()){
            result.rejectValue("username", "user", "Vui lòng nhập tên tài khoản.");
        } else if (password.isBlank()){
            result.rejectValue("password", "user", "Vui lòng nhập mật khẩu.");
        } else {
            UsersEntity userEntity = UserDAO.getInstance().searchUserByUsername(username.trim());
            String hashedPassword = PasswordHashing.toSha1(user.getPassword().trim());
            if (userEntity == null){
                result.rejectValue("username", "user", "Tên tài khoản không tồn tại trên hệ thống, vui lòng kiểm tra lại");
            } else if (!userEntity.getPassword().equals(hashedPassword)) {
                result.rejectValue("password", "user", "Mật khẩu không chính xác, vui lòng kiểm tra lại");
            } else {
                return "redirect:/main";
            }
        }
        return "auth/login";
    }
}

