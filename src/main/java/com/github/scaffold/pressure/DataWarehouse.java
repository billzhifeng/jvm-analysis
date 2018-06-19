package com.github.scaffold.pressure;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.scaffold.dal.dao.AcctDOMapper;
import com.github.scaffold.dal.dataobject.AcctDO;
import com.github.scaffold.dal.dataobject.AcctDOCondition;
import com.github.scaffold.pressure.enums.AcctTypeEnum;

/**
 * @author wangzhifeng
 * @date 2018年6月14日 上午11:24:05
 */
@Service
public class DataWarehouse {

    @Autowired
    AcctDOMapper             acctDOMapper;

    //付款方
    public static List<Long> payerAcctIds = new ArrayList<Long>();

    //收款方
    public static List<Long> payeeAcctIds = new ArrayList<Long>();

    /**
     * 启动后加载数据
     */
    @PostConstruct
    public void initData() {
        AcctDOCondition con = new AcctDOCondition();
        con.setLength(2048);
        con.setOffSet(0);
        AcctDOCondition.Criteria cri = con.createCriteria();
        cri.andAcctTypeEqualTo(AcctTypeEnum.PAYER.name());
        List<AcctDO> instances = acctDOMapper.selectByExample(con);
        for (AcctDO acc : instances) {
            payerAcctIds.add(acc.getId());
        }

        AcctDOCondition con2 = new AcctDOCondition();
        con2.setLength(100);
        con2.setOffSet(0);
        AcctDOCondition.Criteria cri2 = con2.createCriteria();
        cri2.andAcctTypeEqualTo(AcctTypeEnum.PAYEE.name());
        List<AcctDO> instances2 = acctDOMapper.selectByExample(con2);
        for (AcctDO acc2 : instances2) {
            payeeAcctIds.add(acc2.getId());
        }
    }

}
