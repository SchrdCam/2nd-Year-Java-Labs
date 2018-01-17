package labExam1;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student, based on their student number and a list of time slots
 * where they have a commitment.
 */
public class Student {

	/**
	 * Creates a new Student object based on the input parameters.
	 * 
	 * @param number
	 *            The student number
	 * @param slotSpecs
	 *            A list of time-slot specifications
	 */
	private String studentNumber;
	public List<TimeSlot> slotSpecs = new ArrayList<TimeSlot>();

	/*
	 * The constructor here defines the student number string and a list of times
	 * the student cannot attend labs due to prior commitments
	 */
	public Student(String number, List<String> slotSpecs) {
		this.studentNumber = number;
		for (String slot : slotSpecs) {
			this.slotSpecs.add(new TimeSlot(slot));
		}
	}
	//general getters
	public String getStudentNumber() {
		return studentNumber;
	}

	public List<TimeSlot> getSlotSpecs() {
		return slotSpecs;
	}

	//custom getter for the lab groups the student can attend
	public List<String> getLabGroups(Course course) {
		List<LabGroup> groups = course.getLabGroups();
		List<String> eligibles = new ArrayList<String>();
		for (LabGroup lab : groups) {
			// stops pointless checking after a lab is added to the eligible list
			if (eligibles.contains(lab.getGroup())) {
				continue;
			}
			boolean noCommit = true;
			/* checks if the day of the current lab group has any commitments. if
			/ the student has no commitments for the day at all, they are immediately
			/ eligible for the lab*/
			for (int d = 0; d < slotSpecs.size(); d++) {
				if (slotSpecs.get(d).getDay().equals(lab.gettSlot().getDay())) {
					noCommit = false;
					break;
				}
			}
			if (noCommit) {
				eligibles.add(lab.getGroup());
				continue;
			}
			// the main checking loop
			for (TimeSlot tSlot : slotSpecs) {
				//if the days of the current lab and commitment are equal
				if (lab.gettSlot().getDay().equals(tSlot.getDay())) {
					//if one starts when the other ends, add the lab to the list of eligible labs
					if (endToEnd(lab.gettSlot(),tSlot)) {
						eligibles.add(lab.getGroup());
					/* if that is false, check if the day and times of the current lab and commitment match 
					/ and then if the lab and commitment times intersect (12-14 and 13-15 intersect)
					/ or envelop (12-17 envelops 13-15) by calling the method defined below. If both of these
					 * conditions are false, then the lab can be added to the list of eligible labs */
					} else if (!lab.gettSlot().equals(tSlot) && !intersectOrEnvelop(lab.gettSlot(), tSlot)) {
						eligibles.add(lab.getGroup());
					}
				}
			}
		}
		return eligibles;
	}
	/* This takes two TimeSlots and checks if they overlap by returning the big ugly boolean
	 * condition at the bottom of the method. the 4 parts are:
	 *  1 - if the activity starts after the lab starts but before it ends
	 *  2 - if the activity ends after the lab starts but before it ends
	 *  3 - if the activity starts before the lab and ends after it
	 *  4 - if the lab starts before the activity and ends after it*/
	private boolean intersectOrEnvelop(TimeSlot lab, TimeSlot act) {
		int labStart = lab.getStrtTime().getHour();
		int labEnd = lab.getEndTime().getHour();
		int actStart = act.getStrtTime().getHour();
		int actEnd = act.getEndTime().getHour();
		return ((actStart >= labStart && actStart <= labEnd) || (actEnd >= labStart && actEnd <= labEnd)
				|| (actStart < labStart && actEnd > labEnd) || (labStart < actStart && labEnd > actEnd));

	}
	private boolean endToEnd(TimeSlot lab, TimeSlot act) {
		return (lab.getStrtTime().equals(act.getEndTime())|| (lab.getStrtTime().equals(act.getEndTime())));
	}
}