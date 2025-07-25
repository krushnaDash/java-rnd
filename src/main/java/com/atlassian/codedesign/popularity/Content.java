package com.atlassian.codedesign.popularity;

import java.util.Objects;

public class Content implements Comparable<Content> {
	
	int contentId;
	int populality;
	
	
	public Content(int contentId, int populality) {
		super();
		this.contentId = contentId;
		this.populality = populality;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getPopulality() {
		return populality;
	}
	public void setPopulality(int populality) {
		this.populality = populality;
	}
	@Override
	public int hashCode() {
		return Objects.hash(contentId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		return contentId == other.contentId ;
	}
	@Override
	public int compareTo(Content o) {
		// TODO Auto-generated method stub
		return Integer.compare(o.populality, this.populality);
	}
	@Override
	public String toString() {
		return "Content [contentId=" + contentId + ", populality=" + populality + "]";
	}
	
	
	
	
	

}
