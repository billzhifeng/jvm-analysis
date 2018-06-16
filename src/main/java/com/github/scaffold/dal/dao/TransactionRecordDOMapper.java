package com.github.scaffold.dal.dao;

import com.github.scaffold.dal.dataobject.TransactionRecordDO;
import com.github.scaffold.dal.dataobject.TransactionRecordDOCondition;
import java.util.List;

public interface TransactionRecordDOMapper {
    long countByExample(TransactionRecordDOCondition example);

    int deleteByPrimaryKey(Long id);

    int insert(TransactionRecordDO record);

    int insertSelective(TransactionRecordDO record);

    List<TransactionRecordDO> selectByExample(TransactionRecordDOCondition example);

    TransactionRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransactionRecordDO record);

    int updateByPrimaryKey(TransactionRecordDO record);
}