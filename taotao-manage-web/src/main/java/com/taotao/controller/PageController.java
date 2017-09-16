package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* 页面跳转Controller
* @ClassName: PageController
* @Description: TODO
* @author Allen
* @date 2017年9月16日 上午8:56:37
*
*/
@Controller
public class PageController {
	
	/**
	 * 打开首页
	 */
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	/**
	* 展示其他页面
	* @Title: showPage
	* @Description: TODO
	* @param @param page
	* @param @return    设定文件
	* @return String   返回类型
	* @throws
	*/
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
	
}
