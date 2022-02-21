package io.renren.utils;

import io.renren.config.MongoManager;
import io.renren.entity.ColumnEntity;
import io.renren.entity.TableEntity;
import io.renren.entity.mongo.MongoDefinition;
import io.renren.entity.mongo.MongoGeneratorEntity;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午11:40:24
 */
public class GenUtils {

    private static String currentTableName;

    private static final String[] COMMON_FIELD = {"is_delete", "gmt_create", "gmt_modified", "update_version"};

    private static final String TEMPLATE_PATH = "template/";

    private static final String TEMPLATE_JAVA_SUFFIX = ".java.vm";

    private static final String MYBATIS_FILE_PATH = "infrastructure" + File.separator + "persistence" + File.separator + "mybatis" + File.separator;

    private static final String APPLICATION_FILE_PATH = "application" + File.separator;

    private static final String DOMAIN_FILE_PATH = "domain" + File.separator;

    private static String addJavaTemplate(String fileName) {
        return TEMPLATE_PATH + fileName + TEMPLATE_JAVA_SUFFIX;
    }

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        //TODO 添加模板
        String templateStr = "template/";
        String javaStr = ".java.vm";
        //java
        //api
        templates.add(addJavaTemplate("Controller"));

        //application
        templates.add(addJavaTemplate("Assembler"));
        templates.add(addJavaTemplate("Command"));
        templates.add(addJavaTemplate("Dto"));
        templates.add(addJavaTemplate("CommandService"));
        templates.add(addJavaTemplate("CommandServiceImpl"));
        templates.add(addJavaTemplate("QueryService"));
        templates.add(addJavaTemplate("QueryServiceImpl"));

        //domain
        templates.add(addJavaTemplate("DoMain"));
        templates.add(addJavaTemplate("Id"));
        templates.add(addJavaTemplate("CreateSpecification"));
        templates.add(addJavaTemplate("Repository"));

        //infrastructure
        templates.add(addJavaTemplate("Mapper"));
        templates.add(addJavaTemplate("Do"));
        templates.add(addJavaTemplate("RepositoryImpl"));
        templates.add(addJavaTemplate("Converter"));

        //Dao.xml
        templates.add("template/Dao.xml.vm");

        //sql
        templates.add("template/menu.sql.vm");

        //vue
        templates.add("template/index.vue.vm");
        templates.add("template/add-or-update.vue.vm");
        if (MongoManager.isMongo()) {
            // mongo不需要mapper、sql   实体类需要替换
            templates.remove(0);
            templates.remove(1);
            templates.remove(2);
            templates.add("template/MongoEntity.java.vm");
        }
        return templates;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, String moduleName,String classname) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }

        if (template.contains("Assembler"+ TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + APPLICATION_FILE_PATH + "assembler" + File.separator + className + "Assembler.java";
        }

        if (template.contains("Command" + TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + APPLICATION_FILE_PATH + "command" + File.separator + className + "Command.java";
        }

        if (template.contains("Dto" + TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + APPLICATION_FILE_PATH + "dto" + File.separator + className + "Dto.java";
        }

        if (template.contains("QueryServiceImpl" + TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + APPLICATION_FILE_PATH + "impl" + File.separator + className + "QueryServiceImpl.java";
        }

        if (template.contains("CommandServiceImpl" + TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + APPLICATION_FILE_PATH + "impl" + File.separator + className + "CommandServiceImpl.java";
        }

        if (template.contains("QueryService" + TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + APPLICATION_FILE_PATH + className + "QueryService.java";
        }

        if (template.contains("CommandService" + TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + APPLICATION_FILE_PATH + className + "CommandService.java";
        }

        if (template.contains("DoMain" + TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + DOMAIN_FILE_PATH + "model" + File.separator + classname + File.separator + className + ".java";
        }

        if (template.contains("Id" + TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + DOMAIN_FILE_PATH + "model" + File.separator + classname + File.separator + className + "Id.java";
        }

        if (template.contains("Repository" + TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + DOMAIN_FILE_PATH + "model" + File.separator +classname + File.separator + className + "Repository.java";
        }

        if (template.contains("CreateSpecification" + TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + DOMAIN_FILE_PATH + "specification" + File.separator + className + "CreateSpecification.java";
        }

        if (template.contains("Do" + TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + MYBATIS_FILE_PATH + "entity" + File.separator + className + "Do.java";
        }

        if (template.contains("Converter" + TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + MYBATIS_FILE_PATH + "converter" + File.separator + className + "Converter.java";
        }

        if (template.contains("RepositoryImpl" + TEMPLATE_JAVA_SUFFIX)) {
            return packagePath + MYBATIS_FILE_PATH + "repository" + File.separator + className + "RepositoryImpl.java";
        }

        if (template.contains("Mapper.java.vm")) {
            return packagePath + MYBATIS_FILE_PATH + "mapper" + File.separator + className + "Mapper.java";
        }



        if (template.contains("MongoChildrenEntity.java.vm")) {
            return packagePath + "entity" + File.separator + "inner" + File.separator + currentTableName + File.separator + splitInnerName(className) + "InnerEntity.java";
        }
        if (template.contains("Entity.java.vm") || template.contains("MongoEntity.java.vm")) {
            return packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if (template.contains("Dao.java.vm")) {
            return packagePath + "dao" + File.separator + className + "Dao.java";
        }

        if (template.contains("Service.java.vm")) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm")) {
            return packagePath + "api" + File.separator + className + "Controller.java";
        }

        if (template.contains("Dao.xml.vm")) {
            return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Dao.xml";
        }

        if (template.contains("menu.sql.vm")) {
            return className.toLowerCase() + "_menu.sql";
        }

        if (template.contains("index.vue.vm")) {
            return "main" + File.separator + "resources" + File.separator + "src" + File.separator + "views" + File.separator + "modules" +
                    File.separator + moduleName + File.separator + className.toLowerCase() + ".vue";
        }

        if (template.contains("add-or-update.vue.vm")) {
            return "main" + File.separator + "resources" + File.separator + "src" + File.separator + "views" + File.separator + "modules" +
                    File.separator + moduleName + File.separator + className.toLowerCase() + "-add-or-update.vue";
        }

        //TODO 调整包结构
        return null;
    }

    public static List<String> getMongoChildTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("template/MongoChildrenEntity.java.vm");
        return templates;
    }

    /**
     * 生成代码
     */
    public static void generatorCode(Map<String, String> table,
                                     List<Map<String, String>> columns, ZipOutputStream zip) {
        //配置信息
        Configuration config = getConfig();
        boolean hasBigDecimal = false;
        boolean hasList = false;
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), config.getStringArray("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));
        boolean hasDate = false;
        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName"));
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setComments(column.get("columnComment"));
            columnEntity.setExtra(column.get("extra"));


            // 设置是否是公共字段
            columnEntity.setCommonField(isCommonField(column.get("columnName")));


            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), columnToJava(columnEntity.getDataType()));
            //判断是否含有Date类型数据
            if ("Date".equals(attrType)) {
                hasDate = true;
            }
            columnEntity.setAttrType(attrType);


            if (!hasBigDecimal && attrType.equals("BigDecimal")) {
                hasBigDecimal = true;
            }
            if (!hasList && "array".equals(columnEntity.getExtra())) {
                hasList = true;
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        String mainPath = config.getString("mainPath");
        mainPath = StringUtils.isBlank(mainPath) ? "io.renren" : mainPath;
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("hasList", hasList);
        map.put("mainPath", mainPath);
        map.put("package", config.getString("package"));
        map.put("moduleName", config.getString("moduleName"));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        map.put("hasDate", hasDate);
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getString("package"), config.getString("moduleName"),tableEntity.getClassname())));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RRException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }
    }

    private static boolean isCommonField(String columName) {
        for (String field : COMMON_FIELD) {
            if (field.equals(columName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 生成mongo其他实体类的代码
     */
    public static void generatorMongoCode(String[] tableNames, ZipOutputStream zip) {
        for (String tableName : tableNames) {
            MongoDefinition info = MongoManager.getInfo(tableName);
            currentTableName = tableName;
            List<MongoGeneratorEntity> childrenInfo = info.getChildrenInfo(tableName);
            childrenInfo.remove(0);
            for (MongoGeneratorEntity mongoGeneratorEntity : childrenInfo) {
                generatorChildrenBeanCode(mongoGeneratorEntity, zip);
            }
        }
    }

    private static void generatorChildrenBeanCode(MongoGeneratorEntity mongoGeneratorEntity, ZipOutputStream zip) {
        //配置信息
        Configuration config = getConfig();
        boolean hasList = false;
        //表信息
        TableEntity tableEntity = mongoGeneratorEntity.toTableEntity();
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), config.getStringArray("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));
        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        for (Map<String, String> column : mongoGeneratorEntity.getColumns()) {
            ColumnEntity columnEntity = new ColumnEntity();
            String columnName = column.get("columnName");
            if (columnName.contains(".")) {
                columnName = columnName.substring(columnName.lastIndexOf(".") + 1);
            }
            columnEntity.setColumnName(columnName);
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setExtra(column.get("extra"));

            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), columnToJava(columnEntity.getDataType()));
            columnEntity.setAttrType(attrType);

            if (!hasList && "array".equals(columnEntity.getExtra())) {
                hasList = true;
            }
            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        String mainPath = config.getString("mainPath");
        mainPath = StringUtils.isBlank(mainPath) ? "io.renren" : mainPath;
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("hasList", hasList);
        map.put("mainPath", mainPath);
        map.put("package", config.getString("package"));
        map.put("moduleName", config.getString("moduleName"));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getMongoChildTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getString("package"), config.getString("moduleName"),tableEntity.getClassname())));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RRException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }

    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String[] tablePrefixArray) {
        if (null != tablePrefixArray && tablePrefixArray.length > 0) {
            for (String tablePrefix : tablePrefixArray) {
                if (tableName.startsWith(tablePrefix)) {
                    tableName = tableName.replaceFirst(tablePrefix, "");
                }
            }
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RRException("获取配置文件失败，", e);
        }
    }



    private static String splitInnerName(String name) {
        name = name.replaceAll("\\.", "_");
        return name;
    }
}
