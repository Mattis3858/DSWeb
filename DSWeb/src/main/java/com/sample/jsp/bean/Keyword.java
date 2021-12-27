package com.sample.jsp.bean;
public class Keyword {
	 public String name;//name��雿輻�撓������摮�
	 
	 public Keyword(String name){
	  this.name= name;
	  
	 }
	 public Keyword() {
	  
	 }
	 public void setName(String name) {
	  this.name = name;
	 }
	 public String getName() {
	  return name;
	 }
	 @Override
	 public String toString(){
	  return "["+name+","+"]";
	 }
	}