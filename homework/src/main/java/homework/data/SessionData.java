package homework.data;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;



@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SessionData implements Serializable{
    private static final long serialVersionUID = 1L;
    String id;
    String name;
    String str1;
    String str2;
    String str3;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	public String getStr3() {
		return str3;
	}
	public void setStr3(String str3) {
		this.str3 = str3;
	}

	public boolean isSessionId() {
		String sessionId = getId();
		if(StringUtils.isEmpty(sessionId)) {
			return false;
		}
		return true;
	}

}