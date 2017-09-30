package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

/**
 * 内容分类管理
 * @ClassName: ContentCategoryServiceImpl
 * @Description: TODO
 * @author Allen
 * @date 2017年9月23日 下午1:46:13
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		// 根据parentid查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();

		for (TbContentCategory tbContentCategory : list) {
			// 创建节点
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		// 创建pojo
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		// 状态,可选值:1(正常),2(删除)
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		// 添加记录
		contentCategoryMapper.insert(contentCategory);
		// 查看父节点的isParent是否为true,如果不是true改为true
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		// 判断是否为true
		if(parentCat.getIsParent()){
			parentCat.setIsParent(true);
			// 更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		return TaotaoResult.ok(contentCategory);
	}

	@Override
	public TaotaoResult deleteContentCategory(long parentId, long id) {
		//根据id查询对象
		TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);

		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(category.getName());
		contentCategory.setIsParent(false);
		contentCategory.setSortOrder(1);
		contentCategory.setParentId(parentId);
		contentCategory.setStatus(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		
		
		if(category.getIsParent()){
			category.setIsParent(false);
			contentCategoryMapper.updateByPrimaryKey(contentCategory);
		}else{
			contentCategoryMapper.deleteByPrimaryKey(id);
		}
		return TaotaoResult.ok(contentCategory);
	}

	@Override
	public TaotaoResult updateContentCategory(long id, String name) {
		TbContentCategory cc= contentCategoryMapper.selectByPrimaryKey(id);
		
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setId(id);
		contentCategory.setName(name);
		contentCategory.setIsParent(cc.getIsParent());
		contentCategory.setParentId(cc.getParentId());
		contentCategory.setSortOrder(cc.getSortOrder());
		contentCategory.setStatus(cc.getStatus());
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
		return TaotaoResult.ok(contentCategory);
	}

}
