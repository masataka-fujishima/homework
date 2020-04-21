package homework;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping(value = "/")
    public String index(Model model) {
		model.addAttribute("message", "Hello Thymeleaf!!");
        return "login";
    }

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam("name") String name,Model model ) {
    	model.addAttribute("name", name);
       return "hello";
   }

}
