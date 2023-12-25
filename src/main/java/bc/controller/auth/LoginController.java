package bc.controller.auth;



import bc.bean.User;
import bc.entity.UsersEntity;
import bc.util.HibernateUtil;
import bc.util.PasswordHashing;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
//public class LoginController {
//    @Transactional
//    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
//    public String login(@ModelAttribute("user")User user) {
//        return "auth/login";
//    }
//
//    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
//    public String showMain(ModelMap modelMap, @ModelAttribute("user") User user) {
//        String username = user.getUsername();
//        String password = PasswordHashing.toSha1(user.getPassword());
//        if (getUser(username, password) != null){
//            return "main/main";
//        } else {
//
//        }
//        return "auth/login";
//    }
//
//    public UserEntity getUser(String username, String password) {
//        Session session = HibernateConfig.getSessionFactory().openSession();
//        UserEntity userEntity = null;
//        try {
//            String hql = "FROM UserEntity u WHERE u.username = :username AND u.password = :password";
//            Query query = session.createQuery(hql);
//            query.setParameter("username", username);
//            query.setParameter("password", password);
//            userEntity = (UserEntity) query.uniqueResult();
//            return userEntity;
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }
//        return userEntity;
//    }
//}
@Controller
public class LoginController {
//    private static SessionFactory factory;

    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("user")User user) {
        return "auth/login";
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public String showMain(ModelMap modelMap, @ModelAttribute("user") User user) {
        String username = user.getUsername();
        String hashedPassword = PasswordHashing.toSha1(user.getPassword()); // Sử dụng thuật toán mã hóa mạnh mẽ hơn
        System.out.println(username + " " + hashedPassword);
        UsersEntity userEntity = getUser(username, hashedPassword);

        if (userEntity != null) {
            return "main/main";
        } else {
            // Xử lý trường hợp người dùng không hợp lệ
//            modelMap.addAttribute("error", "Invalid username or password");
            return "auth/login";
        }
    }

    public UsersEntity getUser(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM UsersEntity u WHERE u.username = :username AND u.password = :password";
            Query<UsersEntity> query = session.createQuery(hql, UsersEntity.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.uniqueResult();
        } catch (HibernateException e) {
            // Xử lý lỗi nếu cần
            e.printStackTrace();
            return null;
        }
    }
}

