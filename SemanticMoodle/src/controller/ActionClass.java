package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Role;

import model.Course;
import model.CourseCategory;
import model.DB;
import model.PropertyN;
import model.User;



public class ActionClass {
	
	ArrayList <User> user= new ArrayList<User>();
	ArrayList <Course> course= new ArrayList<Course>();
	ArrayList <Role> role= new ArrayList<Role>();
	ArrayList <CourseCategory> category= new ArrayList<CourseCategory>();
	ArrayList <PropertyN> properties=new ArrayList<PropertyN>();
	String queryname;
	
	public String getQueryname() {
		
		return queryname;
	}
	public void setQueryname(String queryname) {
	
		this.queryname = queryname;
	}
	public ArrayList<User> getUser() {
		return user;
	}
	public void setUser(ArrayList<User> user) {
		this.user = user;
	}
	public ArrayList<PropertyN> getProperties() {
		System.out.println("here213");
		return properties;
	}
	public void setProperties(ArrayList<PropertyN> properties) {
		this.properties = properties;
	}
	public ArrayList<Course> getCourse() {
		return course;
	}
	public void setCourse(ArrayList<Course> course) {
		this.course = course;
	}
	public ArrayList<Role> getRole() {
		return role;
	}
	public void setRole(ArrayList<Role> role) {
		this.role = role;
	}
	public ArrayList<CourseCategory> getCategory() {
		return category;
	}
	public void setCategory(ArrayList<CourseCategory> category) {
		this.category = category;
	}
	
	public String fetchData()
	{
		
		return "success";
	}
	
	
	public String categoryData() throws ClassCastException, NullPointerException, SQLException, IOException
	{	
		
		System.out.println("Fetching data");
		DB.getCategory(category);
		DB.getProperties(properties,"mdl_course_categories");
		System.out.println(properties.size());
		return "success";
	}

	public String listpropertiesData() throws ClassCastException, NullPointerException, SQLException, IOException
	{	
		
		System.out.println("Test5");
		DB.listProperties(properties);
		System.out.println("Fetched data");
		System.out.println(properties.size());
		return "success";
	}

	
	public String propertiesData() throws ClassCastException, NullPointerException, SQLException, IOException
	{	
		
		System.out.println("Test5");
		
		System.out.println("Fetched data");
		System.out.println(properties.size());
		return "success";
	}
	
	public String roleData() throws ClassCastException, NullPointerException, SQLException, IOException
	{	
		
		System.out.println("Fetching data");
		DB.getRole(role);
		DB.getProperties(properties,"mdl_role");		
		return "success";
	}
	
	public String userData() throws ClassCastException, NullPointerException, SQLException, IOException
	{	
		
		System.out.println("Fetching data");
		DB.getUser(user);
		DB.getProperties(properties,"mdl_user");
		return "success";
	}
	
	
	public String courseData() throws ClassCastException, NullPointerException, SQLException, IOException
	{	
		
		System.out.println("Fetching data");
		DB.getCourse(course);
		DB.getProperties(properties,"mdl_course");
		return "success";
	}
	public String userAction() throws ClassCastException, NullPointerException, SQLException, IOException
	{
		System.out.println("User:::"+queryname);
		DB.getUserProperty(user,course,role,category,queryname);
		DB.getProperties(properties,"mdl_user");
		return "success";
	}
	public String courseAction() throws ClassCastException, NullPointerException, SQLException, IOException
	{	
		System.out.println("Course:::"+queryname);
		DB.getCourseProperty(user,course,role,category,queryname);
		DB.getProperties(properties,"mdl_course");
		return "success";
	}
	public String roleAction() throws ClassCastException, NullPointerException, SQLException, IOException
	
	{
		System.out.println("Role:::"+queryname);
		DB.getRoleProperty(user,course,role,category,queryname);
		DB.getProperties(properties,"mdl_role");
		return "success";
	}
public String categoryAction() throws ClassCastException, NullPointerException, SQLException, IOException
	
	{
	System.out.println("Category:::"+queryname);
	DB.getCategoryProperty(user,course,role,category,queryname);
	DB.getProperties(properties,"mdl_course_categories");
		return "success";
	}
	
	
}
