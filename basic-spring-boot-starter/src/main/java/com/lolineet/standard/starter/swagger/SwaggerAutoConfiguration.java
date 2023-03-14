package com.lolineet.standard.starter.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

@EnableSwagger2
@EnableKnife4j
@Configuration //表示这个类为配置类
@EnableConfigurationProperties({SwaggerProperties.class})
@ConditionalOnProperty(prefix = "ghwl.swagger",value = "enabled",matchIfMissing = true,havingValue = "true")
public class SwaggerAutoConfiguration {

    private final SwaggerProperties swaggerProperties;

    public SwaggerAutoConfiguration(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket createDocket(ServletContext servletContext)
    {
        System.out.println("****************swagger api 已加载*******************");
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getPackageName()))
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title(swaggerProperties.getServiceName() + "接口文档")
                        .version("1.0")
                        .contact(new Contact("共好未来研发部后台组","","tj.zhanghongwei@163.com"))
                        .license("共好未来(天津)教育科技有限公司")
                        .build())
                // 如果为生产环境，则不创建swagger
                .enable(true);

        // 在knife4j前端页面的地址路径中添加gateway网关的项目名，解决在调试接口、发送请求出现404的问题
        docket.pathProvider(new RelativePathProvider(servletContext)
        {
            @Override
            public String getApplicationBasePath()
            {
                return "/" + swaggerProperties.getServiceName() + super.getApplicationBasePath();
            }
        });

        return docket;
    }
}
