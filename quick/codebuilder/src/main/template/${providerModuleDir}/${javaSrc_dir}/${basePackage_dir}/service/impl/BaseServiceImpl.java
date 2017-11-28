package ${basePackage}.service.impl;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public class BaseServiceImpl implements BaseMapper<T extends Serializable> {

    protected BaseMapper<T> mapper;

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    public int insert(T model) {
        return this.mapper.insert(model);
    }

    /**
     * 添加
     *
     * @param models
     * @return
     */
    @Override
    public int insertList(List<T> models) {
        return this.mapper.insertList(models);
    }

    /**
     * 根据主键删除
     *
     * @param pk
     * @return
     */
    @Override
    public int deleteByPk(Object pk) {
        return this.mapper.deleteByPk(pk);
    }

    /**
     * 根据条件删除
     *
     * @param condition
     * @return
     */
    @Override
    int deleteByCondition(T condition) {
        return this.mapper.deleteByCondition(condition);
    }

    /**
     * 根据主键更新
     *
     * @param model
     * @return
     */
    @Override
    int updateByPk(T model) {
        return this.mapper.updateByPk(model);
    }

    /**
     * 根据条件更新
     *
     * @param model
     * @param condition
     * @return
     */
    @Override
    int updateByCondition(T model, T condition) {
        return this.mapper.updateByCondition(model, condition);
    }

    /**
     * 根据主键获取
     *
     * @param pk
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    T getByPk(Object pk) {
        return this.mapper.getByPk(pk);
    }

    /**
     * 查询
     *
     * @param condition
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    List<T> findByCondition(T condition) {
        return this.mapper.findByCondition(condition);
    }

}
