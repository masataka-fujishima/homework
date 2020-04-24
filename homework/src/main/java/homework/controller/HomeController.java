package homework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class HomeController extends AbstractController{

    @GetMapping(value = "/mypage")
    public String index() {
		if(!sessionData.isSessionId()) {
			return "redirect:/login";
		}
        return "home/myPage";
    }

    @GetMapping(value="/set")
    public String set(){
    	// セッションセットの確認
        sessionData.setStr1("hogehoge");
        sessionData.setStr2("fugafuga");
        sessionData.setStr3("piyopiyo");
        return "redirect:/home/mypage";
    }

    @GetMapping(value="/clear")
    public String crear(HttpServletRequest request){
    	// セッションクリアの確認
    	request.getSession().invalidate();
    	return "redirect:/home/mypage";
    }

}
