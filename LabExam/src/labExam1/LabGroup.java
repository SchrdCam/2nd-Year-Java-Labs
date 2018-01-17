package labExam1;


/**
 * Represents a single lab group, consisting of a group label and a time slot.
 */
public class LabGroup {

	/**
	 * Creates a new LabGroup based on the given specification: a single-character label,
	 * a space, and then a time-slot specification.
	 * 
	 * @param line The lab group specification in the above format
	 */
	private String group;
	private TimeSlot tSlot;
	
	public LabGroup(String line) {
		group = line.split(" ")[0];
		tSlot = (new TimeSlot(line.substring(2)));
	}
	
	public String getGroup() {
		return group;
	}

	public TimeSlot gettSlot() {
		return tSlot;
	}
}