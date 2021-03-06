package util;

/**
 * 
 * @author doej1367
 */
public class MCLogLine implements Comparable<MCLogLine> {
	private long creationTime;
	private String playerName;
	private String text;

	public MCLogLine(long creationTime, String playerName, String text) {
		this.creationTime = creationTime;
		this.playerName = playerName;
		this.text = text;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean matches(String[] regexArray) {
		for (int i = 0; i < regexArray.length; i++)
			if (getText().matches(regexArray[i]))
				return true;
		return false;
	}

	@Override
	public int compareTo(MCLogLine arg0) {
		long tmp = getCreationTime() - arg0.getCreationTime();
		return tmp < 0 ? -1 : tmp > 0 ? 1 : 0;
	}
}
