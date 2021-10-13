package util;

import java.util.List;
import java.util.TreeMap;

public class LogRecords extends TreeMap<String, TimeslotMap> {
	private static final long serialVersionUID = 1L;
	private String allLines;
	private String[] giftLines = { "[A-Z ]+! .+ gift with (\\[[A-Z\\+]+\\] )?[0-9A-Za-z_]+!" };
	private String skills = "Alchemy|Combat|Enchanting|Farming|Fishing|Foraging|Mining";
	private String armorPieces = "Helmet|Chestplate|Leggings|Boots";

	public LogRecords() {
		allLines = giftLines[0];
	}

	/**
	 * Starts parsing a recorded activity like a dungeon run that starts at a
	 * specific log line and returns the line after the record ends and the record
	 * was added to a list
	 * 
	 * @param recordStartLine - start line
	 * @param logLines        - the list with all the MinecraftLogLine objects
	 * @return - the line where the parsing should continue
	 */
	public int add(int recordStartLine, List<MCLogLine> logLines) {
		int lineIndex = recordStartLine;
		MCLogLine line = logLines.get(lineIndex);
		if (line.matches(giftLines)) {
			String item = line.getText().split("! ")[1].split(" gift with ")[0];
			item = item.replaceAll(skills, "Skill").replaceAll(armorPieces, "Pieces");
			get(item).add(line.getCreationTime(), 1);
		}
		return lineIndex;
	}

	@Override
	public TimeslotMap get(Object key) {
		if (super.get(key.toString()) == null)
			put(key.toString(), new TimeslotMap(365000, 0));
		return super.get(key.toString());
	}

	public String getDefaultLoglineFilterRegex() {
		return allLines;
	}

	public String getNameDependentLoglineFilterRegex(String userName) {
		return allLines;
	}
}
