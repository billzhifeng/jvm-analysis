package com.github.scaffold.dal.dao;

import com.github.scaffold.dal.dataobject.AcctRecordDO;
import com.github.scaffold.dal.dataobject.AcctRecordDOCondition;
import java.util.List;

public interface AcctRecordDOMapper {
    long countByExample(AcctRecordDOCondition example);

    int deleteByPrimaryKey(Long id);

    int insert(AcctRecordDO record);

    int insertSelective(AcctRecordDO record);

    List<AcctRecordDO> selectByExample(AcctRecordDOCondition example);

    AcctRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AcctRecordDO record);

    int updateByPrimaryKey(AcctRecordDO record);
}