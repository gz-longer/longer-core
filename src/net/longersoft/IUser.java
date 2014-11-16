package net.longersoft;

public interface IUser {
	/// userId
	public String get_UserId();
	public void set_UserId(String userId);
	/// userName
	public String get_UserName();
	public void set_UserName(String userName);
	/// department
	public IDepartment getDepartment();
}
