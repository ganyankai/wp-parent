
package cn.dante.jk.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController
{

	public HomeController()
	{
	}
	@RequestMapping(value = {"/home.action"})
	public String login()
	{
		System.out.println("abc");
		return "/index.jsp";
	}

	@RequestMapping(value = {"/fmain.action"})
	public String fmain()
	{
		return "/home/fmain.jsp";
	}

	@RequestMapping(value = {"/title.action"})
	public String title()
	{
		return "/home/title.jsp";
	}

	@RequestMapping(value = {"/left.action"})
	public String left()
	{
		return "/home/left.jsp";
	}

	@RequestMapping(value = {"/olmsgList.action"})
	public String main()
	{
		return "/home/olmsgList.jsp";
	}

//	@RequestMapping(value = {"/main.action"})
	public String sysadminMain()
	{
		return "/sysadmin/main.jsp";
	}
	//	@RequestMapping(value = {"/main.action"})
	public String sysadminLeft()
	{
		return "/sysadmin/left.jsp";
	}

	//	@RequestMapping(value = {"/main.action"})
	public String baseinfoMain()
	{
		return "/baseinfo/main.jsp";
	}

	//	@RequestMapping(value = {"/main.action"})
	public String baseinfoLeft()
	{
		return "/baseinfo/left.jsp";
	}

	//	@RequestMapping(value = {"/main.action"})
	public String cargoMain()
	{
		return "/cargo/main.jsp";
	}

	//	@RequestMapping(value = {"/main.action"})
	public String cargoLeft()
	{
		return "/cargo/left.jsp";
	}
}