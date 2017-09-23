package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
public interface ItemService {

	TbItem getItemById(long itemId);
	
	EUDataGridResult getItemList(int page,int rows);
	
	TaotaoResult createItem(TbItem item,String desc,String itemParam) throws Exception;
}
