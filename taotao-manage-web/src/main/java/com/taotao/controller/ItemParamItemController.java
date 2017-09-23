package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.service.ItemParamItemService;

/**
* 展示商品规格参数 Controller 
* @ClassName: ItemParamItemController
* @Description: TODO
* @author Allen
* @date 2017年9月20日 下午11:33:52
*/
@Controller
public class ItemParamItemController {

	@Autowired 
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/showitem/{itemId}")
	public String showItemParam(@PathVariable Long itemId,Model model){
		String string = itemParamItemService.getIntemParamItemById(itemId);
		model.addAttribute("itemParam", string);
		return "item";
	}
	
	
}
