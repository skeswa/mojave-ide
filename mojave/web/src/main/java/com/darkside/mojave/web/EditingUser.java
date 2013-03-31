package com.darkside.mojave.web;

public class EditingUser implements Comparable<EditingUser>{
	
	private String name;
	private int editIndex;
	
	public EditingUser(String name) {
		this.name = name;
		this.editIndex = 0;
	}
	
	public EditingUser(String name, int editIndex) {
		this.name = name;
		this.editIndex = editIndex;
	}
    
	public String getName() {
		return name;
	}
    
	public void setName(String name) {
		this.name = name;
	}
    
	public int getEditIndex() {
		return editIndex;
	}
    
	public void setEditIndex(int editIndex) {
		this.editIndex = editIndex;
	}

	@Override
	public int compareTo(EditingUser o) {
		if(name.equalsIgnoreCase(o.getName())) return 0;
		return -1;
	}
}
