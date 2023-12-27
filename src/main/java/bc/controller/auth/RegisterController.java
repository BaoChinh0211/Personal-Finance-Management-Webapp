package bc.controller.auth;

import bc.bean.Mailer;
import bc.bean.User;
import bc.dao.UserDAO;
import bc.entity.UsersEntity;
import bc.util.HibernateUtil;
import bc.util.PasswordHashing;
import bc.util.ValidationUtilities;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/register")
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    Mailer mailer;

    @GetMapping()
    public String showRegisterForm(@ModelAttribute("user") User user){
        return "auth/register";
    }
    @PostMapping()
    public String success(ModelMap modelMap,
                          @ModelAttribute("user") User user,
                          BindingResult result){
        String fullname = user.getFullname().trim();
        String username = user.getUsername().trim();
        String password = user.getPassword().trim();
        String confirm = user.getConfirm().trim();
        String email = user.getEmail().trim();
        String validation = user.getValidationCode().trim();
        if (fullname.isEmpty()){
            result.rejectValue("fullname", "user", "Please enter your full name");
        } else if (username.isEmpty()){
            result.rejectValue("username", "user", "Please enter username");
        } else if (UserDAO.getInstance().searchUserByUsername(username) != null) {
            result.rejectValue("username", "user", "The username already exists on the system, please try another username");
        } else if (password.isEmpty()){
            result.rejectValue("password", "user", "Please enter password");
        } else if (confirm.isEmpty()) {
            result.rejectValue("confirm", "user", "Please enter confirm password");
        } else if (email.isEmpty()) {
            result.rejectValue("email", "user", "Please enter register email");
        } else if (validation.isEmpty()) {
            result.rejectValue("validationCode", "user", "Please enter validation code in register email");
        } else if (!ValidationUtilities.checkNameOfUserValid(fullname)) {
            result.rejectValue("fullname", "user", "Please enter valid name");
        } else if (!ValidationUtilities.checkUsernameValid(username)) {
            result.rejectValue("username", "user", "Please enter valid username");
        } else if (!ValidationUtilities.checkPasswordValid(password)) {
            result.rejectValue("password", "user", "Please enter a password that includes at least 1 lowercase letter, " +
                    "1 uppercase letter, 1 numeric character, 1 special character and is between 6 and 20 characters long.");
        } else if (!confirm.equals(password)) {
            result.rejectValue("confirm", "user", "Please enter correct confirm password");
        } else if (ValidationUtilities.checkEmailValid(email) == 2){
            result.rejectValue("email", "user", "Please enter valid email");
        } else if (ValidationUtilities.checkEmailValid(email) == 1){
            result.rejectValue("email", "user", "Please enter an email address between 6 and 30");
        } else if (email.length() > 255) {
            result.rejectValue("email", "user", "Please enter an email address with a maximum length of 255 characters.");
        } else if (!validation.equals(Mailer.getCode())) {
            result.rejectValue("validationCode", "user", "Verification code is incorrect, please try again.");
        } else {
            String hashPassword = PasswordHashing.toSha1(password);
            boolean status = UserDAO.getInstance().insert(new UsersEntity(fullname, username,hashPassword , email));
            if (status)
                return "auth/login";
        }
        return "auth/register";
    }

    @PostMapping("/checkEmailExistence")
    @ResponseBody
    public String checkEmailExistence(@RequestParam("email") String email) {
        if (UserDAO.getInstance().checkEmailExistence(email))
            return "true";
        return "false";
    }


    @PostMapping("/sendCode")
    @ResponseBody
    public String sendCode(@RequestParam("sendCodeEmail") String sendCodeEmail) {
        if (UserDAO.getInstance().checkEmailExistence(sendCodeEmail))
            return "Email is already in use, please try another email.";
        try {
            mailer.getRandomCode();
            boolean result = mailer.sendMail("baochinh000210@gmail.com", sendCodeEmail, "Yêu cầu đăng ký tài khoản", "Mã đăng ký: " + Mailer.getCode());
            if(result) {
                return "Code sent successfully!";
            } else {
                return "Error sending code: ";
            }
        } catch (Exception e) {
            return "Error sending code: " + e.getMessage();
        }
    }
}
