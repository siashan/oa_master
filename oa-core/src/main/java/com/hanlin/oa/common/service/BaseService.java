package com.hanlin.oa.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanlin.oa.common.support.orm.Page;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2018/4/4 14:39
 * @Modified By:
 */
public class BaseService<T> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected Mapper<T> mapper;

    /**
     * Description:按主键查找  <br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2019/5/15 10:02
     */
    public T selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    /**
     * Description: 查询一条记录 <br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2019/5/15 10:02
     */
    public T selectOne(T record) {
        return mapper.selectOne(record);
    }

    /**
     * Description:查询全部  <br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2019/5/15 10:03
     */
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * Description:分页查询全部  <br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2019/5/15 10:22
     */
    public PageInfo selectAllOfPage(Page page, String orderbyArgs) {
        PageHelper.startPage(page.getPageNum(), page.getLength(), true);
        if (StringUtil.isNotBlank(orderbyArgs)) {
            PageHelper.orderBy(orderbyArgs);
        }
        List<T> tList = selectAll();
        return new PageInfo(tList);
    }

    /**
     * Description:条件查询  <br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2019/5/15 10:06
     */
    public List<T> select(T record) {
        return mapper.select(record);
    }

    /**
     * Description:分页条件查询  <br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2019/5/15 10:26
     */
    public PageInfo selectOfPage(T record, Page page, String orderbyArgs) {
        PageHelper.startPage(page.getPageNum(), page.getLength(), true);
        if (StringUtil.isNotBlank(orderbyArgs)) {
            PageHelper.orderBy(orderbyArgs);
        }
        List<T> tList = select(record);
        return new PageInfo(tList);
    }

    /**
     * Description:统计条数  <br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2019/5/15 10:07
     */
    public int selectCount(T record) {
        return mapper.selectCount(record);
    }

    /**
     * Description:插入数据  <br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2019/5/15 10:09
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    /**
     * Description:更新  <br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2019/5/15 10:09
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * Description:条件删除  <br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2019/5/15 10:10
     */
    @Transactional(rollbackFor = Exception.class)
    public int delete(T record) {
        return mapper.delete(record);
    }

    /**
     * Description:根据id删除  <br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2019/5/15 10:11
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

}
