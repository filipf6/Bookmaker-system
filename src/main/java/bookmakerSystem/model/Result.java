package bookmakerSystem.model;

public enum Result {
	DEFEAT("Przegrana gospodarza"), DRAW("Remis"), WIN("Wygrana gospodarza");
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
