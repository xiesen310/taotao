package com.taotao.common.pojo;

import java.util.List;
/**
 * 封装分页对象
* @ClassName: EUDataGridResult
* @Description: TODO
* @author Allen
* @date 2017年9月16日 上午11:44:30
*
 */
public class EUDataGridResult {
	private long total;
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
