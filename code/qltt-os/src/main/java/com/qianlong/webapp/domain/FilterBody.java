package com.qianlong.webapp.domain;

import java.util.List;

public class FilterBody extends PagingBean {

	private List<SourceIndex> indices;

	public List<SourceIndex> getIndices() {
		return indices;
	}

	public void setIndices(List<SourceIndex> indices) {
		this.indices = indices;
	}
}
