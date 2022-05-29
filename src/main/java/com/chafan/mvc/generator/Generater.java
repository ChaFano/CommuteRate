package com.chafan.mvc.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 茶凡
 * @ClassName Generater
 * @Description TODO
 * @date 2022/5/15 17:49
 * @Version 1.0
 */
public class Generater {

    /**
     * 与数据库相关的都改成自己的
     */
    private static String url = "jdbc:mysql://127.0.0.1:3306/message_push?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
    private static String user = "root";
    private static String password = "3306";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String author = "Chafan";
    private static String outputDir = "/src/main/java/";
    private static String packageName = "com.chafan.mvc.project";//生成的东西放在这个包里
    private static String tablePrefix = ""; //表前缀
    private static String projectPath = System.getProperty("user.dir");

    public static void main(String[] args) {

        //数据库连接
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(url)
                .setUsername(user)
                .setPassword(password)
                .setDriverName(driverName);

        //全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)   //// 开启 activeRecord 模式
                .setEnableCache(false)  //二级缓存
                .setSwagger2(true) //实体属性 Swagger2 注解
                .setAuthor(author)  //作者
                .setOutputDir(projectPath + outputDir)  //输出目录
                .setFileOverride(false)  //覆盖文件
                .setServiceName("I%sService"); // 自定义文件命名，注意 %s 会自动填充表实体属性！
//                .setServiceImplName("%sServiceImpl")
//                .setMapperName("%sMapper")
//                .setXmlName("%sMapper");

        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {   //"/mybatis/mapper.xml.ftl"
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mybatis/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)   // 全局大写命名
//                .setDbColumnUnderline(true)   // 全局下划线命名
                .setEntityLombokModel(true)     //Lombok
                .setNaming(NamingStrategy.underline_to_camel)   // 表名生成策略
                .setColumnNaming(NamingStrategy.underline_to_camel)     //字段名生成策略
                .setRestControllerStyle(true) //RestController注解
                .setControllerMappingHyphenStyle(true)          //驼峰转连字符
                .setTablePrefix(tablePrefix);  //表前缀
//                .setInclude("line_meters") // 需要生成的表,默认全部
//                //.setExclude(new String[]{"test"}) // 排除生成的表
//                .setSuperEntityClass("com.baomidou.demo.base.BsBaseEntity") //自定义实体父类
//                .setSuperMapperClass("com.baomidou.demo.base.BsBaseMapper")   // 自定义 mapper 父类
//                .setSuperServiceClass("com.baomidou.demo.base.BsBaseService") // 自定义 service 父类
//                .setSuperServiceImplClass("com.baomidou.demo.base.BsBaseServiceImpl") // 自定义 service 实现类父类
//                .setSuperControllerClass("com.baomidou.demo.TestController")// 自定义 controller 父类
//                .setEntityColumnConstant(true)    //是否生成字段常量（默认 false）
//                .setEntityBooleanColumnRemoveIsPrefix(true);  // Boolean类型字段是否移除is前缀处理

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setCfg(new InjectionConfig() {
                            @Override
                            public void initMap() {
                            }
                        }.setFileOutConfigList(focList)
                )
                .setStrategy(strategyConfig)
                .setPackageInfo(new PackageConfig()
                        .setParent(packageName)
                        .setController("controller")
                        .setEntity("entity")
                )
                //.setTemplate(new TemplateConfig().setXml(null)) //关闭默认的XML生成
                .setTemplateEngine(new FreemarkerTemplateEngine())  //模板引擎
                .execute(); //执行
    }


}
