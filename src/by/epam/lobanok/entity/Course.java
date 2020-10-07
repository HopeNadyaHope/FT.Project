package by.epam.lobanok.entity;

import java.io.Serializable;


public class Course implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String courseName;
	private String description;
	
	public Course(){}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		Course other = (Course) obj;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [courseName=" + courseName + ", description=" + description + "]";
	}
}