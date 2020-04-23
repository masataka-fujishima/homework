package homework.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class LoginForm implements Serializable {
    /**
     * 名前
     */
	@NotNull
	@Size(max = 20)
    private String name;

    /**
     * アドレス
     */
	@NotNull
	@Size(max = 20)
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
