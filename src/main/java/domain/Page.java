package domain;

import java.util.List;

/**
 * 分页数据
 * @param <T>具体要分页的类
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 10;
    //当前页页码
    private Integer pageNo;
    //总页数
    private Integer pageTotal;
    //当前页大小
    private Integer pageSize = PAGE_SIZE;
    //要分页数据记录总数
    private Integer total;
    //当前页数据详细信息
    private List<T> items;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo <= 1) {
            this.pageNo = 1;
        } else if (pageNo > pageTotal) {
            this.pageNo = pageTotal;
        } else {
            this.pageNo = pageNo;
        }
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}