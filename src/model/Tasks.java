package model;

import java.util.Date;

public class Tasks {
	private int id;
	private int idProject;
	private String name;
	private String description;
	private boolean isComplete;
	private String notes;
	private Date deadLine;
	private Date createdAt;
	private Date updatedAt;
	
	public Tasks(int id, int idProject, String name, String description, boolean isComplete, String notes,
			Date deadLine, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.idProject = idProject;
		this.name = name;
		this.description = description;
		this.isComplete = isComplete;
		this.notes = notes;
		this.deadLine = deadLine;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Tasks(int idProject, String name, String description, boolean isComplete, String notes,
			Date deadLine, Date createdAt, Date updatedAt) {
		super();
		this.idProject = idProject;
		this.name = name;
		this.description = description;
		this.isComplete = isComplete;
		this.notes = notes;
		this.deadLine = deadLine;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Tasks(int idProject, String name, String description, String notes, Date deadLine) {
		super();
		this.idProject = idProject;
		this.name = name;
		this.description = description;
		this.isComplete = false;
		this.notes = notes;
		this.deadLine = deadLine;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Tasks [id=" + id + ", idProject=" + idProject + ", name=" + name + ", description=" + description
				+ ", isComplete=" + isComplete + ", notes=" + notes + ", deadLine=" + deadLine + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
