package domain;

import java.util.List;

/**
 * 分页数据
 * @param <T>具体要分页的类
 */
public class Page<T> {
    public static final Long PAGE_SIZE = 10L;
    //当前页页码
    private Long pageNo;
    //总页数
    private Long pageTotal;
    //当前页大小
    private Long pageSize = PAGE_SIZE;
    //要分页数据记录总数
    private Long total;
    //当前页数据详细信息
    private List<T> items;

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        if(pageNo <= 1) {
            this.pageNo = 1L;
        } else if (pageNo > pageTotal) {
            this.pageNo = pageTotal;
        } else {
            this.pageNo = pageNo;
        }
    }

    public Long getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Long pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}