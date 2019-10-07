
package main.java.cn.dante.jk.controller;


public class HomeController
{

	public HomeController()
	{
	}

	public String login()
	{
		return "/index.jsp";
	}

	public String fmain()
	{
		return "/home/fmain.jsp";
	}

	public String title()
	{
		return "/home/title.jsp";
	}

	public String left()
	{
		return "/home/left.jsp";
	}

	public String main()
	{
		return "/home/olmsgList.jsp";
	}

	public String sysadminMain()
	{
		return "/sysadmin/main.jsp";
	}

	public String sysadminLeft()
	{
		return "/sysadmin/left.jsp";
	}

	public String baseinfoMain()
	{
		return "/baseinfo/main.jsp";
	}

	public String baseinfoLeft()
	{
		return "/baseinfo/left.jsp";
	}

	public String cargoMain()
	{
		return "/cargo/main.jsp";
	}

	public String cargoLeft()
	{
		return "/cargo/left.jsp";
	}
}