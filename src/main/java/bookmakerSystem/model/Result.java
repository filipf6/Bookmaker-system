package bookmakerSystem.model;

public enum Result {
	DEFEAT("Host defeat"), DRAW("Draw"), WIN("Host win");
	String type;
	Result(String type) {
		this.type = type;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
}
