package homework.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import homework.data.SessionData;
import homework.entity.User;
import homework.form.LoginForm;
import homework.service.UserService;

@Controller
@RequestMapping(value = "/")
public class LoginController extends AbstractController {

	@Autowired
	private LoginForm loginform;

	@Autowired
	private UserService userService;

    @Autowired
    SessionData sessionData;


	@ModelAttribute("loginData")
	public LoginForm setLoginform() {
		return this.loginform;
	}

	@GetMapping(value = "/login")
    public String index(Model model) {

        return "login/login";
    }

	@GetMapping(value = "/new")
    public String signUp(Model model) {
        return "login/create";
    }

	@PostMapping(value = "/create")
    public String create(@Validated LoginForm loginform, BindingResult result,Model model )throws Exception {
        if (result.hasErrors()) {
            return "login/create";
        }

		System.out.println(loginform.getAddress());
		System.out.println(loginform.getName());

		User user = new User();
		user.setName(loginform.getName());
		user.setAddress(loginform.getAddress());
		userService.insertUser(user);

    	model.addAttribute("loginData", loginform);

    	return "redirect:/hello";
    }

    @PostMapping(value = "/hello")
    public String hello(@Validated LoginForm loginform, BindingResult result,Model model ) {
        if (result.hasErrors()) {
            return "login/login";
        }

    	Optional<User> user = userService.searchByNameAndAddress(loginform.getName(), loginform.getAddress());
    	if(user.isPresent()) {
    		// ユーザーが存在するならセッションでユーザー名を所持
        	model.addAttribute("loginData", loginform);
        	sessionData.setName(loginform.getName());

            return "login/hello";
    	}

    	model.addAttribute("loginData", loginform);
		model.addAttribute("message", "ユーザーが見つかりませんでした");
    	return "login/login";
   }

}


