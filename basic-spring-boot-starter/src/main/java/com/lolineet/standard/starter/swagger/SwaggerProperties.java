package com.lolineet.standard.starter.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ghwl.swagger")
public class SwaggerProperties {
    public boolean enabled = true;
    public String packageName = "com.ghwl.standard";
    public String serviceName = "共好未来服务";
}
