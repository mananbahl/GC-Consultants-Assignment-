package application;

public class Person {
	String name,email;
	Person(String email,String name)
	{
		this.name=name;
		this.email=email;
	}
public void setName(String name)
{
	this.name=name;
}
public void setEmail(String email)
{
	this.email=email;
}
public String getEmail()
{
	return this.email;
}
public String getName()
{
	return this.name;
}
}
