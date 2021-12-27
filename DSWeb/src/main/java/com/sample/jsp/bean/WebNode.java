package com.sample.jsp.bean;


import java.util.ArrayList;

public class WebNode {

	public WebNode parent;
	public ArrayList<WebNode> children;
	public String URL;
	public WebNode leftChildNode;
	public WebNode rightChildNode;
	public String title;
	public String IMG;
	public int width,height;
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getIMG() {
		return IMG;
	}
	public void setIMG(String iMG) {
		IMG = iMG;
	}
	public int score;
	public WebNode() {
		
	}
	public WebNode(String URL) {
		this.URL = URL;
		this.children = new ArrayList<WebNode>();
		
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	public WebNode getParent() {
		return parent;
	}
	public void setParent(WebNode parent) {
		this.parent = parent;
	}
	public WebNode getLeftChildNode() {
		return leftChildNode;
	}
	public void setLeftChildNode(WebNode leftChildNode) {
		this.leftChildNode = leftChildNode;
	}
	public WebNode getRightChildNode() {
		return rightChildNode;
	}
	public void setRightChildNode(WebNode rightChildNode) {
		this.rightChildNode = rightChildNode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}