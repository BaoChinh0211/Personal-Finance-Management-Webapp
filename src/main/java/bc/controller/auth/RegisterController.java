package bc.controller.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/auth/register")
public class RegisterController {
    @RequestMapping(method = RequestMethod.GET)
    public String showRegisterForm(){
        return "auth/register";
    }
    @RequestMapping( method = RequestMethod.POST)
    public String success(ModelMap modelMap, HttpServletRequest request){
        return "auth/login";
    }
}
