# 自定义@Query

## 元注解 注解的注解

### @Retention

定义了该注解的生命周期，在什么阶段丢弃，丢弃后注解无意义

- RetentionPolicy.RUNTIME: 不丢弃
- RetentionPolicy.CLASS: 在类加载的时候丢弃
- RetentionPolicy.SOURCE: 在编译阶段丢弃 

### @Target

- ElementType.TYPE:用于描述类、接口或enum声明
- ElementType.FIELD:用于描述实例变量
- ElementType.METHOD
- ElementType.PARAMETER
- ElementType.CONSTRUCTOR
- ElementType.LOCAL_VARIABLE
- ElementType.ANNOTATION_TYPE 另一个注释
- ElementType.PACKAGE 用于记录java文件的package信息

