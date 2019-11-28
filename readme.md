# itoak

## 简介
整理收集平时工作中常用实用的工具

## 功能表

|序号|名称|说明|对应版本|时间|
|:----:|:----|:----|:----|:----|
|1|函数耗时统计|新增注解@TimeConsuming，在指定函数上加上该注解，即可打印该函数耗时|0.0.1|2019-11-23|
|2|对象属性拷贝工具类|新增CopierUtil工具类，封装对象属性拷贝工具BeanCopier，支持单个对象属性拷贝，列表拷贝等|0.0.2|2019-11-26|
|3|枚举校验器|新增枚举校验器EnumValidator，可对枚举成员变量的值做合法性校验；可对枚举项进行合法性校验|0.0.2|2019-11-26|

## 使用示例

### 1.函数耗时统计

- 使用
```java
@Service
public class PerformanceImpl implements Performance {
    @Override
    @TimeConsuming
    public void perform() {
        System.out.println("演员正在表演...");
    }
}
```

- 结果
```
892  INFO 59499 --- [nio-8088-exec-1] cn.itoak.aspect.TimeConsumingAspect      : Method completed in 17 ms [cn.itoak.tranquility.service.impl.PerformanceImpl.perform]
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
        Boolean cboolean = EnumValidator.checkField("RED", ColorEnum.class);//true
    }
}
```