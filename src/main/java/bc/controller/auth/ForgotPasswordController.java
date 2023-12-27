package bc.controller.auth;

import bc.bean.Mailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/forgot")
public class ForgotPasswordController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    Mailer mailer;

    @GetMapping
    public String showForgortForm(@RequestParam("username")String username,
                                  @RequestParam("email")String email,
                                  @RequestParam("valiCode")String valiCode){
        return "auth/forgot";
    }

}
