package homework.form;

import org.springframework.stereotype.Component;

@Component
public class LoginForm {
    /**
     * 名前
     */
    private String name;

    /**
     * アドレス
     */
    private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
