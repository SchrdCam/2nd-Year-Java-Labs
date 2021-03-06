package labExam1;

/**
 * Represents a time slot: a day of the week, and a starting time and 
 * ending time.
 */
public class TimeSlot {

	/**
	 * Creates a new TimeSlot based on the specification.
	 * 
	 * @param spec The TimeSlot specification: a day, a starting time, and an ending time,
	 * separated by space characters
	 */
	public String day;
	public Time strtTime;
	public Time endTime;
	
	public TimeSlot(String spec) {
		day = spec.split(" ")[0];
		strtTime = new Time(spec.split(" ")[1]);
		endTime = new Time(spec.split(" ")[2]);
	}

	public String getDay() {
		return day;
	}

	public Time getStrtTime() {
		return strtTime;
	}

	public Time getEndTime() {
		return endTime;
	}
	// *** !!! ALL BELOW METHODS ARE AUTO GENERATED BY ECLIPSE !!! ***
	// 	hashCode must be generated but is never used (tutor said its just a good coding practice to have)
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((strtTime == null) ? 0 : strtTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeSlot other = (TimeSlot) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (strtTime == null) {
			if (other.strtTime != null)
				return false;
		} else if (!strtTime.equals(other.strtTime))
			return false;
		return true;
	}
	
	// minor edit to toString
	@Override
	public String toString() {
		return day + " " + strtTime.toString() + " " + endTime.toString() ;
	}	
}