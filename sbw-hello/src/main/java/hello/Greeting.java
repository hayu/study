package hello;

// outgoing message to client
public class Greeting {
	private final String content;
	public Greeting(String content) {
		this.content = content;
	}
	public String getContent() {
		return this.content;
	}
}
