package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.common.pojo.EUDataGridResult;
public interface ItemService {

	TbItem getItemById(long itemId);
	
	EUDataGridResult getItemList(int page,int rows);
}
