package leader.bean;

import org.springframework.stereotype.Component;

@Component
public class MyServiceLocalStorage {
	private String storagePath;

	public String getStoragePath() {
	return storagePath;
}

	public void setStoragePath(String storagePath) {
	this.storagePath = storagePath;
}

}
