package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContentService;

/**
 * 调用服务层服务,查询内容列表
 * 
 * @ClassName: ContentServiceImpl
 * @Description: TODO
 * @author Allen
 * @date 2017年9月24日 下午3:47:31
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;

	@Override
	public String getContentList() {
		// 调用服务层的服务
		// HttpClientUtil.doGet("http://localhost:8081/rest/content/list/89");
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		System.out.println("result: " + result);
		// 将字符串转换为TaotaoResult
		try {
			TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);
			System.out.println("taotaoResult: " + taotaoResult);
			// 取出内容列表
			List<TbContent> list = (List<TbContent>) taotaoResult.getData();
			// 创建一个jsp要求的pojo列表
			List<Map> resultList = new ArrayList<>();
			for (TbContent tbContent : list) {
				Map map = new HashMap<>();
				map.put("src", tbContent.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", tbContent.getPic2());
				map.put("widthB", 550);
				map.put("heigthB", 240);
				map.put("href", tbContent.getUrl());
				map.put("alt", tbContent.getSubTitle());
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
