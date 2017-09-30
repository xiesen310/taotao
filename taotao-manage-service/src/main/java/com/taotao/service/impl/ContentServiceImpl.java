package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;

/**
* 内容管理 Service
* @ClassName: ContentServiceImpl
* @Description: TODO
* @author Allen
* @date 2017年9月24日 上午10:44:26
*/
@Service
public class ContentServiceImpl implements ContentService{
	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public EUDataGridResult getContextList(int page, int rows, Long categoryId) {
		// 创建查询条件
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		// 分页处理
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExample(example);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 获取记录总条数
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult insertContent(TbContent content) {
		// 补全pojo
		content.setCreated(new Date());
		content.setUpdated(new Date());
		
		contentMapper.insert(content);
		
		return TaotaoResult.ok();
	}

}
