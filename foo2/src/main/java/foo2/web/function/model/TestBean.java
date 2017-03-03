package foo2.web.function.model;

import org.springframework.stereotype.Component;

@Component
public class TestBean {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TestBean [name=" + name + "]";
	}

}