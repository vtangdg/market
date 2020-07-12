package com.example.market.dal.dao;

import java.util.List;

public interface BaseDao<Do, Query> {
    /**
     * 新增记录。
     *
     * @param record 记录数据
     * @return <code>0</code>：更新失败，<code>1</code>：更新成功
     */
    int insert(Do record);

    /**
     * 修改记录。
     *
     * @param record 记录数据
     * @return <code>0</code>：更新失败，<code>1</code>：更新成功
     */
    int update(Do record);

    /**
     * 根据实体id查询实体对象
     *
     * @param id 主键
     * @return {@link Do}
     */
    Do selectOneById(long id);

    /**
     * 查询
     *
     * @param condition fetch condition
     * @return {@code List<TaskQuery>}
     */
    List<Do> fetch(Query condition);

    /**
     * 统计
     *
     * @param condition fetch condition
     * @return row count
     */
    Integer count(Query condition);
}