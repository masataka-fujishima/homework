package homework.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import homework.entity.User;
import homework.form.LoginForm;
import homework.service.UserService;

@Controller
@RequestMapping(value = "/")
public class LoginController extends AbstractController{

	@Autowired
	private LoginForm loginform;

	@Autowired
	private UserService userService;

	@ModelAttribute("loginData")
	public LoginForm setLoginform() {
		return this.loginform;
	}

	@GetMapping(value = "/login")
    public String index(Model model) {
		if(sessionData.isSessionId()) {
			return "redirect:/home/mypage";
		}
        return "login/login";
    }

	@GetMapping(value = "/new")
    public String signUp(Model model) {
		if(sessionData.isSessionId()) {
			return "redirect:/home/mypage";
		}
        return "login/create";
    }

	@PostMapping(value = "/create")
    public String create(@Validated LoginForm loginform, BindingResult result,Model model )throws Exception {
		if(sessionData.isSessionId()) {
			return "redirect:/home/mypage";
		}

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
    	model.addAttribute("message", "ユーザーを登録しました");

    	return "login/login";
    }

    @PostMapping(value = "/hello")
    public String hello(HttpServletRequest request, HttpServletResponse response,
    		@Validated LoginForm loginform, BindingResult result,Model model ) throws Exception{
		if(sessionData.isSessionId()) {
			return "redirect:/home/mypage";
		}

        if (result.hasErrors()) {
            return "login/login";
        }


    	Optional<User> user = userService.searchByNameAndAddress(loginform.getName(), loginform.getAddress());
    	if(user.isPresent()) {

            String session = request.getSession().getId().toString();
            userService.login(session,user.get(),model);

            return "login/hello";
    	}
    	model.addAttribute("loginData", loginform);
		model.addAttribute("message", "ユーザーが見つかりませんでした");
    	return "login/login";
   }

}


