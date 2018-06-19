package com.github.scaffold.outService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangzhifeng
 * @date 2018年6月19日 下午10:23:01
 */
@Setter
@Getter
public class ReturnBigData implements Serializable {

    /**
     * 
     */
    private static final long       serialVersionUID = 4412445138371761842L;
    private String                  serviceName;
    private long                    id;
    private List<ReturnData>        content;
    private Date                    createdTime;

    private static List<ReturnData> list;
    static {
        list = new ArrayList<ReturnData>();
        for (int i = 0; i < 200; i++) {
            list.add(ReturnData.buildData());
        }
    }

    /**
     * 构建对象
     */
    public static ReturnBigData buildBigData() {
        ReturnBigData data = new ReturnBigData();
        data.setContent(list);
        data.setCreatedTime(new Date());
        data.setId(System.currentTimeMillis());
        data.setServiceName("mockOutServiceWithBigData");
        return data;
    }
}
