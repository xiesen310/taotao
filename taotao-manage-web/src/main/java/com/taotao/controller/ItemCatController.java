package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
* 商品管理 Controller 
* @ClassName: ItemCatController
* @Description: TODO
* @author Allen
* @date 2017年9月16日 上午11:34:09
*
*/
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ItemCatService;
@Controller
@RequestMapping("/item/cat/")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getCatList(@RequestParam(value="id",defaultValue="0") long parentId){
		List<EUTreeNode> list = itemCatService.getCatList(parentId);
		return list;
	}
}
