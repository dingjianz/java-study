package com.itheima;

import lombok.*;

import java.util.List;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString(exclude = {"address"})
//@RequiredArgsConstructor
@Data
@Builder                 // 生成建造者：User.builder().xxx().build()
@NoArgsConstructor       // @Builder 默认只生成全参构造器；若还需无参构造器需显式加上
@AllArgsConstructor      // 与 @Builder 搭配，避免和 @NoArgsConstructor 冲突
public class User
{
    // @NonNull
    private String name;
    private int age;
    private String address;

    // @Builder.Default：为字段指定默认值，不设置时会使用该默认值
    // 不加 @Builder.Default 时，即使字段有初始值，builder 也会把它覆盖为 null / 0 / false
    @Builder.Default
    private String country = "中国";

    // @Singular：为集合类字段生成"逐个添加"的方法（如 .hobby("篮球").hobby("篮球")）
    // 生成的集合是不可变的（Collections.unmodifiableList）
    @Singular("hobby")
    private List<String> hobbies;
}
