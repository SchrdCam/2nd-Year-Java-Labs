package labExam1;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a course, including information on lab groups of the
 * course
 */
public class Course {

	/**
	 * Creates a new Course based on the specification. The first element of the
	 * input list will represent the course name, and the rest will represent the
	 * lab groups.
	 * 
	 * @param lines
	 *            The lines read from the courses file.
	 */
	public ArrayList<LabGroup> LabGroups = new ArrayList<LabGroup>();
	public String Name;
	
	public Course(List<String> lines) {
		Name = lines.get(0);
		for (int l = 1; l < lines.size(); l++) {
			LabGroup currentGroup = new LabGroup(lines.get(l));
			LabGroups.add(currentGroup);
		}
	}
	
	public String getName() {
		return Name;
	}

	public ArrayList<LabGroup> getLabGroups() {
		return LabGroups;
	}
	
	// A custom toString method. Nothing fancy, just a minor edit of the auto generated one
	@Override
	public String toString() {
		String returnVal = "";
		for (LabGroup lab:LabGroups) {
			returnVal = returnVal +" Group " + lab.getGroup() +" "+ lab.gettSlot() +"\n";
		}
		return Name + "\n" + returnVal;
	}
}