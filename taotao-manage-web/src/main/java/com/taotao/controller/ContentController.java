package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
/**
* 内容管理 Controller
* @ClassName: ContentController
* @Description: TODO
* @author Allen
* @date 2017年9月24日 上午11:16:36
*/

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getContentList(int page, int rows, Long categoryId){
		EUDataGridResult result = contentService.getContextList(page, rows, categoryId);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content){
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}
}
