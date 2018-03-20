package com.qawhcb.shadow;

import org.mockito.internal.configuration.GlobalConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * SWAGGER 标题配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket buildDocket() {


        Docket docket = new Docket(DocumentationType.SWAGGER_2)
               .apiInfo(buildApiInf())
                                .select()
                                //要扫描的API(Controller)基础包
                                .apis(RequestHandlerSelectors.basePackage("com.qawhcb.shadow.controller"))
                                .paths(PathSelectors.any())
                                .build();

        ResponseMessage error33 = new ResponseMessageBuilder()
                .code(-33)
                .message("token 验证失败！")
                .responseModel(new ModelRef("Error"))
                .build();

        ResponseMessage error500 = new ResponseMessageBuilder()
                .code(500)
                .message("服务器内部错误！")
                .responseModel(new ModelRef("Error"))
                .build();
        ResponseMessage error502 = new ResponseMessageBuilder()
                .code(502 )
                .message("服务器尝试执行请求时，从上游服务器接收到无效的响应！")
                .responseModel(new ModelRef("Error"))
                .build();
        ResponseMessage error504 = new ResponseMessageBuilder()
                .code(504)
                .message("服务器尝试执行请求时！")
                .responseModel(new ModelRef("Error"))
                .build();

        ResponseMessage error400 = new ResponseMessageBuilder()
                .code(400)
                .message("请求参数有误，当前请求无法被服务器理解!")
                .responseModel(new ModelRef("Error"))
                .build();
        ResponseMessage error403 = new ResponseMessageBuilder()
                .code(403)
                .message("非法请求，服务器拒绝执行!")
                .responseModel(new ModelRef("Error"))
                .build();
        ResponseMessage error404 = new ResponseMessageBuilder()
                .code(404)
                .message("请求资源未找到！")
                .responseModel(new ModelRef("Error"))
                .build();

        ArrayList<ResponseMessage> arrayList = new ArrayList();

        arrayList.add(error33);
        arrayList.add(error400);
        arrayList.add(error403);
        arrayList.add(error404);
        arrayList.add(error500);
        arrayList.add(error502);
        arrayList.add(error504);

        docket.useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET,arrayList);
        docket.useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.POST,arrayList);
        docket.useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.PUT,arrayList);
        docket.useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.DELETE,arrayList);

        return docket;
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("shadow 接口文档")
                .contact("shadow team")
                .version("1.0")
                .build();

    }

}
