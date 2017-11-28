package org.xi.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/28 19:23
 */
public class Pagination<T extends Serializable> implements Serializable {

    public Pagination() {
        this(1, 10, 0, 0, null);
    }

    public Pagination(int page, int pageSize, int totalPage, long dataNumber, List<T> content) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.dataNumber = dataNumber;
        this.content = content;
    }

    /**
     * 页号
     */
    private int page;
    /**
     * 每页显示记录数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 数据的总数量
     */
    private long dataNumber;
    /**
     * 数据列表
     */
    private List<T> content;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getDataNumber() {
        return dataNumber;
    }

    public void setDataNumber(long dataNumber) {
        this.dataNumber = dataNumber;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
