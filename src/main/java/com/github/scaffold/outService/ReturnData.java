package com.github.scaffold.outService;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangzhifeng
 * @date 2018年6月19日 下午10:23:01
 */
@Setter
@Getter
public class ReturnData implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4412445138371761842L;
    private String            serviceName;
    private long              id;
    private String            content;
    private Date              createdTime;

    /**
     * 构建对象
     */
    public static ReturnData buildData() {
        ReturnData data = new ReturnData();
        data.setContent(
                "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();"
                        + "ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();ReturnData data = new ReturnData();");
        data.setCreatedTime(new Date());
        data.setId(System.currentTimeMillis());
        data.setServiceName("mockOutService");
        return data;
    }
}
