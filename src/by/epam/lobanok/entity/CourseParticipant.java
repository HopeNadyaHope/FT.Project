package by.epam.lobanok.entity;

public class CourseParticipant {///////////rename to StudentCourse?
	private int id;
	private User student;
	private RunningCourse course;
	private Result result;
	
	public CourseParticipant() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public RunningCourse getCourse() {
		return course;
	}

	public void setCourse(RunningCourse course) {
		this.course = course;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + id;
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		CourseParticipant other = (CourseParticipant) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (id != other.id)
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseParticipant [id=" + id + ", student=" + student + ", course=" + course + ", result=" + result
				+ "]";
	}	
}