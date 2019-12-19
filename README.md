# itoak

## 简介
整理收集平时工作中常用实用的工具

## 快速安装
使用Maven依赖即可：
```
<dependency>
    <groupId>cn.itoak</groupId>
    <artifactId>itoak</artifactId>
    <version>0.0.2</version>
</dependency>
```

## 功能表

|序号|名称|说明|对应版本|时间|
|:----:|:----|:----|:----|:----|
|1|[函数耗时统计](https://github.com/OakWang/itoak#1%E5%87%BD%E6%95%B0%E8%80%97%E6%97%B6%E7%BB%9F%E8%AE%A1)|新增注解@TimeConsuming，在指定函数上加上该注解，即可打印该函数耗时|0.0.1+|2019-11-23|
|2|[对象属性拷贝工具类](https://github.com/OakWang/itoak#2%E5%AF%B9%E8%B1%A1%E5%B1%9E%E6%80%A7%E6%8B%B7%E8%B4%9D%E5%B7%A5%E5%85%B7%E7%B1%BB)|新增CopierUtil工具类，封装对象属性拷贝工具BeanCopier，支持单个对象属性拷贝，列表拷贝等|0.0.2+|2019-11-26|
|3|[枚举校验器](https://github.com/OakWang/itoak#3%E6%9E%9A%E4%B8%BE%E6%A0%A1%E9%AA%8C%E5%99%A8)|新增枚举校验器EnumValidator，可对枚举成员变量的值做合法性校验；可对枚举项进行合法性校验|0.0.2+|2019-11-26|

## 使用示例

### 1.函数耗时统计

- 说明

函数耗时统计功能默认是开启状态，使用注解可直接使用。如若要关闭该功能，可在配置文件中增加以下配置(0.0.2+)：
```
cn.itoak.time.consuming.enabled=false
```

- 使用
```java
/**
* 普通类函数上使用示例
*/
@Service
public class PerformanceImpl implements Performance {
    @Override
    @TimeConsuming
    public void perform() {
        System.out.println("演员正在表演...");
    }
}

/**
* DAO层接口函数使用示例
*/
@Mapper
public interface PerDAO {
    @TimeConsuming
    Per selectById(Long id);
}
```

- 结果
```
Method completed in 17 ms [cn.itoak.tranquility.service.impl.PerformanceImpl.perform]
```

### 2.对象属性拷贝工具类

- 使用
```java
public class CopierUtilTest {
    public static void main(String[] args) {
        
        //单个对象属性拷贝
        OriginBean originBean = new OriginBean();
        TargetBean t = CopierUtil.copyProperties(originBean, TargetBean.class);

        //列表对象拷贝
        List<OriginBean> list = new ArrayList<>();
        list.add(originBean);
        List<TargetBean> list1 = CopierUtil.copyObjects(list, TargetBean.class);
    }
}
```

### 3.枚举校验器

- 应用场景

客户端传过来的枚举信息（成员变量值、枚举项）不一定是合法的，在严谨编程的思路下，应该对这些信息进行合法性校验，故而需要使用枚举校验器。

- 示例枚举
```java
public enum ColorEnum {
    RED(1, "红色"),
    WHITE(2, "白色"),
    BLACK(3, "黑色");
    
    private Integer code;
    private String desc;

    ColorEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
```

- 使用
```java
public class EnumValidatorTest {
    public static void main(String[] args) {
        //校验变量值是否合法
        Boolean aboolean = EnumValidator.checkFieldValue("code", 1, ColorEnum.class);//true

        //校验枚举项是否合法
        //0.0.2 版本
        Boolean cboolean = EnumValidator.checkField("RED", ColorEnum.class);//true
        //0.0.3 版本
        Boolean dboolean = EnumValidator.checkItem("RED", ColorEnum.class);//true
    }
}
```
