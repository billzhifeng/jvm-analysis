package com.github.scaffold.dal.dao;

import com.github.scaffold.dal.dataobject.AcctDO;
import com.github.scaffold.dal.dataobject.AcctDOCondition;
import java.util.List;

public interface AcctDOMapper {
    long countByExample(AcctDOCondition example);

    int deleteByPrimaryKey(Long id);

    int insert(AcctDO record);

    int insertSelective(AcctDO record);

    List<AcctDO> selectByExample(AcctDOCondition example);

    AcctDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AcctDO record);

    int updateByPrimaryKey(AcctDO record);
}