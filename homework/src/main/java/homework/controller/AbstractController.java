package homework.controller;

import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes
public abstract class AbstractController {

	// TODO 共通処理として、セッションをみてユーザー情報がなければログインページへ
	// ユーザー情報があればマイページへ遷移させる。

}
