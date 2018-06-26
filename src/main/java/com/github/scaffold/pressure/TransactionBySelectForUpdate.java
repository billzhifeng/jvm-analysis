package com.github.scaffold.pressure;

import org.springframework.stereotype.Service;

/**
 * * 交易:A B两账户，A付款 B收款，保存交易记录，保存A 、B的账户变化记录，在一个事务内完成 <br>
 * 并发时候，账户不安全。
 * 
 * @author wangzhifeng
 * @date 2018年6月16日 下午5:38:16
 */
@Service
public class TransactionBySelectForUpdate {

}
