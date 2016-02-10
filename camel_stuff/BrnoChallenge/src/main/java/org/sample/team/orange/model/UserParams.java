package org.sample.team.orange.model;

import java.util.List;

public class UserParams {

	String email;
	List <String> urls;
	List <BuzzWord> buzzWords;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getUrls() {
		return urls;
	}
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	public List<BuzzWord> getBuzzWords() {
		return buzzWords;
	}
	public void setBuzzWords(List<BuzzWord> buzzWords) {
		this.buzzWords = buzzWords;
	}
	
	
}
