package bmmf.turzimProje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static java.util.Objects.nonNull;

@Controller
public class LoginController {

    private static final String MY_LOGIN_VIEW = "login";

    @GetMapping("/login")
    public ModelAndView index(@RequestParam(value = "error", required = false) final String error,
                              @RequestParam(value = "logout", required = false) final String logout){
        ModelAndView modelAndView = new ModelAndView(MY_LOGIN_VIEW);
        if (nonNull(error)) {
            modelAndView.addObject("error", "Kullanıcı adı veya şifre hatalı");
        }
        if (nonNull(logout)) {
            modelAndView.addObject("msg", "Başarıyla çıkış yaptın");
        }
        return modelAndView;
    }
}
