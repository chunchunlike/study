package org.xi.quick.filemanager.mapper.condition;

public class BaseCondition {

    public static final Integer DEFAULT_PAGE_SIZE = 10;

    private Integer page = 1;
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    private String orderBy;
    
    
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public String getOrderBy() {
        return orderBy;
    }
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    
    
}
