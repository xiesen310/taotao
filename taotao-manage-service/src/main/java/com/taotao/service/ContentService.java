package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	EUDataGridResult getContextList(int page, int rows, Long categoryId);
	
	TaotaoResult insertContent(TbContent content);
}
