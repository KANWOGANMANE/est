package cn.gdrj.est.dto;

import lombok.Data;

import java.util.List;

/**
 * 分页结果对象
 *
 * @author jun
 * @date 2021/11/10
 */
@Data
public class PageResult<T> {
    private Integer pageNo;
    private Integer pageSize;
    // private Integer pages;
    private Integer total;
    private List<T> list;

    public PageResult(Integer pageSize,Integer total){
        this.total = total;
        this.pageSize = pageSize;
    }

    /**
     * 获取总页数
     * @return
     */
    public Integer getPages(){
        int total = getTotal();
        int pageSize = getPageSize();

        // if(total % pageSize == 0){
        //     return total /pageSize;
        // }
        // return total /pageSize + 1;

        // size: 5 total: 10  ==> (10-1)/5 +1 = 2
        // size: 5 total: 13  ==> (13-1)/5 +1 = 3
        return (total -1) / pageSize + 1;
    }

    // public static void main(String[] args) {
    //     PageResult<Object> result = new PageResult<>(5,22);
    //     System.out.println(result.getPages());
    // }
}
