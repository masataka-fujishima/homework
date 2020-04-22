package homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import homework.form.LoginForm;
import homework.service.UserService;

@Controller
@RequestMapping(value = "/")
public class LoginController {

	@Autowired
	private LoginForm loginform;

	@Autowired
	private UserService userService;


	@ModelAttribute("loginData")
	public LoginForm loginform() {
		return this.loginform;
	}

	@RequestMapping(value = "/login")
    public String index(Model model) {
		model.addAttribute("message", "Hello Thymeleaf!!");
        return "login";
    }

	@RequestMapping(value = "/new")
    public String signUp(Model model) {
		model.addAttribute("message", "ユーザ登録");
        return "create";
    }

	@RequestMapping(value = "/create")
    public String create(@ModelAttribute("loginData") LoginForm loginform,Model model )throws Exception {

		System.out.println(loginform.getAddress());
		System.out.println(loginform.getName());
		userService.insertUser(loginform);
		System.out.println("登録しました");
    	model.addAttribute("loginData", loginform);

    	return "redirect:/hello";
    }

    @RequestMapping(value = "/hello")
    public String hello(@ModelAttribute("loginData") LoginForm loginform,Model model ) {

    	model.addAttribute("loginData", loginform);

       return "hello";
   }

}


