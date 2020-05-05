package POJO;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.validation.constraints.NotNull;

public class API {

    @Excel(name = "接口编号")
    @NotNull
    private String apiId;
    @Excel(name = "接口名称")
    private String apiName;
    @Excel(name = "接口地址")
    private String url;
    @Excel(name = "接口请求方式")
    private String contentType;
    @Excel(name = "参数格式")
    private String type;

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "API{" +
                "apiId='" + apiId + '\'' +
                ", apiName='" + apiName + '\'' +
                ", url='" + url + '\'' +
                ", contentType='" + contentType + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
