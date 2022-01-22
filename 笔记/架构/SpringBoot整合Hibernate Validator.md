# SpringBoot整合Hibernate Validator用于前端传递到后端校验
## 1. 引入Hibernate Validator依赖
```xml
<dependency>  
    <groupId>org.hibernate.validator</groupId>  
    <artifactId>hibernate-validator</artifactId>  
</dependency>
```

## 2. 在接收前端数据的对象上，添加注解进行校验
```java
@Data  
@TableName("pms_brand")  
public class BrandEntity implements Serializable {  
   private static final long serialVersionUID = 1L;  
  
   /**  
 * 品牌id  
 */ @TableId  
 @Null(message="品牌id必须为空!",groups = {Add.class})  
   @NotNull(message = "品牌id不能为空!",groups = {Update.class,UpdateShowStatus.class})  
   private Long brandId;  
   /**  
 * 品牌名  
 */  
 @NotBlank(message="品牌名称不能为空!",groups = {Add.class,Update.class})  
   private String name;  
   /**  
 * 品牌logo地址  
 */  
 @NotBlank(message = "logo不能为空!",groups = {Add.class})  
   @NotEmptyString(message = "logo不能为空!",groups=Update.class)  
   @URL(message="logo必须为URL格式!",groups = {Add.class,Update.class})  
   private String logo;  
   /**  
 * 介绍  
 */  
 @NotNull(message="介绍不能为空!",groups = {Add.class})  
   @NotEmptyString(message="介绍不能为空!",groups = {Update.class})  
   private String descript;  
   /**  
 * 显示状态[0-不显示；1-显示]  
 */ @NotNull(message="显示状态不能为空!",groups = {Add.class,UpdateShowStatus.class})  
   @ListValue(vals = {0,1},message = "显示状态必须为0或1!",groups = {Add.class,Update.class, UpdateShowStatus.class})  
   private Integer showStatus;  
   /**  
 * 检索首字母  
 */  
 @NotBlank(message = "检索首字母不能为空!",groups = {Add.class})  
   @Pattern(regexp = "^[a-zA-Z]$",message = "检索首字母必须为字母且只能有一位!",groups = {Add.class,Update.class})  
   private String firstLetter;  
   /**  
 * 排序  
 */  
 @NotNull(message = "排序不能为空!",groups = {Add.class})  
   @Min(value = 0,message = "排序必须为正整数")  
   private Integer sort;  
   /**  
 * 是否删除[0-未删除, 1-删除]  
 */ @TableLogic  
 private Integer isDelete;  
   /**  
 * 创建时间  
 */  
 private Date gmtCreate;  
   /**  
 * 修改时间  
 */  
 private Date gmtModified;  
   /**  
 * 更新版本  
 */  
 @Null(message="添加时，更新版本必须为空!",groups = Add.class)  
   private Integer updateVersion;  
  
}
```
- ``@NotBlank``, 不能为``null``和``""``空字符串。主要用于字符串类型数据判空
- ``@NotNull``，不能为``null``，用于除了字符串类型的其他数据判空
- ``@URL``，必须为URL
- ``@Pattern``，正则匹配，``regexp``属性存放正则表达式


## 3. 自定义校验类
声明注解
```java
@Documented  
@Constraint(validatedBy = { ListValueIntegerConstraintValidator.class})  
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })  
@Retention(RUNTIME)  
public @interface ListValue {  
  
    String message() default "{com.learn.project.common.valid.ListValid.message}";  
  
    Class<?>[] groups() default { };  
  
    Class<? extends Payload>[] payload() default { };  
  
    /**  
 * 存储校验值  
 */  
 int[] vals() default {};  
}
```
- ``@Target``，作用范围
- ``@Constraint``,validateBy属性指定自定义校验器(指定自定义校验器``ListValueIntegerConstraintValidator.class``)
- ``ListValue``注解，主要校验所校验的字段是否包含在``vals``数组中
- ``message``会读取``ValidationMessages.properties``文件中的数据(如果未指定message，会读取``com.learn.project.common.valid.ListValid.message``定义的信息，进行错误信息返回)。``ValidationMessages.properties``存放于``resource/``目录下。

自定义校验器
```java
public class ListValueIntegerConstraintValidator implements ConstraintValidator<ListValue,Integer> {  
    private HashSet<Integer> set = new HashSet<>();  
  
    @Override  
 public void initialize(ListValue constraintAnnotation) {  
        for (int val : constraintAnnotation.vals()) {  
            set.add(val);  
        }  
    }  
  
    @Override  
 public boolean isValid(Integer value, ConstraintValidatorContext context) {  
        return set.contains(value);  
    }  
}
```
- 实现``ConstraintValidator<ListValue,Integer>``接口，第一个泛型指定对应注解，第二个泛型指定处理数据类型(不同数据类型，需要定义不同的校验器)
- 实现``initialize``方法，初始化
- 实现``isValid``方法，实现校验，``true``代表通过，``false``代表不通过

## 4. 校验分组
创建接口类，标记分组。如创建``Add``、``Delete``，``Update``等接口标记分组。使每个分组有各自的校验规则。

### 使用分组?
在注解中，``groups``属性标记作用分组列表。可以将某个校验规则作用在多个分组上。

## 5. 使用校验
在``Controller``类中，针对前端传递过来的字段，标记``@Validated(分组.class)``注解，进行校验标记。当前端传递数据过来时，会对有``@Validated``注解的数据类型根据所选择的分组进行校验。当指定分组时，只有指定分组的校验规则会生效，没有通过``groups``属性指定分组的校验规则不会生效。

