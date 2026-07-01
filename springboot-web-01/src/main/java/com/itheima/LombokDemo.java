package com.itheima;

/**
 * Lombok 演示类
 *
 * === Lombok 简介 ===
 * Lombok 是一个 Java 库，通过注解的方式，在编译时自动生成常用的样板代码（如 getter/setter、构造器、toString 等）
 * 可以大大简化 POJO 类的编写，提高开发效率，让代码更加简洁
 *
 * === 常用注解 ===
 * 1. @Getter / @Setter
 *    - 作用：自动生成 getter/setter 方法
 *    - 位置：可以放在类上（为所有字段生成）或字段上（为单个字段生成）
 *    - 示例：@Getter @Setter private String name;
 *
 * 2. @ToString
 *    - 作用：自动生成 toString() 方法
 *    - 参数：exclude = {"field1"} 排除某些字段
 *    - 示例：@ToString(exclude = "password")
 *
 * 3. @EqualsAndHashCode
 *    - 作用：自动生成 equals() 和 hashCode() 方法
 *    - 参数：exclude = {"field1"} 排除某些字段
 *
 * 4. @NoArgsConstructor
 *    - 作用：生成无参构造器
 *
 * 5. @AllArgsConstructor
 *    - 作用：生成包含所有字段的构造器
 *
 * 6. @RequiredArgsConstructor
 *    - 作用：为标记了 @NonNull 的字段和 final 字段生成构造器
 *
 * 7. @Data
 *    - 作用：组合注解，相当于 @Getter + @Setter + @ToString + @EqualsAndHashCode + @RequiredArgsConstructor
 *    - 这是最常用的注解，一般用于实体类/DTO
 *
 * 8. @Builder（建造者模式）
 *    - 作用：为类生成建造者，链式调用创建对象，特别适合参数较多的场景
 *    - 基本使用：
 *        User user = User.builder()
 *                        .name("张三")
 *                        .age(18)
 *                        .address("北京")
 *                        .build();
 *
 *    - 生成内容：
 *        a) 一个静态内部类 UserBuilder
 *        b) 类上的静态方法 builder()，返回 UserBuilder 实例
 *        c) UserBuilder 中每个字段对应的 setter 方法（返回 this，支持链式调用）
 *        d) UserBuilder 的 build() 方法，返回构造好的目标对象
 *        e) 目标类上的 全参构造器（用于 build() 内部调用）
 *
 *    - 常见搭配：
 *        @Builder + @NoArgsConstructor + @AllArgsConstructor
 *        因为 @Builder 只会生成全参构造器；若还想保留无参构造器（如 JPA、Jackson 反序列化需要），
 *        需显式加上 @NoArgsConstructor 和 @AllArgsConstructor。
 *
 *    - 进阶注解：
 *        @Builder.Default   为字段指定默认值。不加时字段的初始值会被 builder 覆盖为默认零值（null/0/false）。
 *        @Singular          用于集合字段（List/Set/Map 等），生成 add 单个元素的方法。
 *                           如 @Singular("hobby") List<String> hobbies;
 *                           调用：.hobby("篮球").hobby("足球")，生成的集合是不可变的。
 *
 *    - toBuilder：
 *        @Builder(toBuilder = true) 会额外生成 toBuilder() 方法，
 *        可以基于现有对象创建一个 builder，修改部分字段后重新 build，适合"不可变对象的修改"场景。
 *        User u2 = u1.toBuilder().age(20).build();
 *
 *    - 方法级 @Builder：
 *        @Builder 也可以标注在构造器或静态方法上，用于为特定构造器/工厂方法生成 builder。
 *
 *    - 注意事项：
 *        1) @Builder 与 @Data 一起用时，通常还需要 @NoArgsConstructor + @AllArgsConstructor
 *        2) 继承场景使用 @SuperBuilder 代替 @Builder
 *        3) build() 不会做 @NonNull 校验之外的业务校验，需要自行处理
 *
 * 9. @Slf4j / @Log4j / @Log
 *    - 作用：自动生成日志对象
 *    - 使用：log.info("message");
 *
 * 10. @NonNull
 *     - 作用：在方法参数或字段上使用，自动生成 null 检查
 *
 * 11. @Cleanup
 *     - 作用：自动调用资源的 close() 方法
 *     - 示例：@Cleanup InputStream in = new FileInputStream("file.txt");
 *
 * 12. @Value
 *     - 作用：用于不可变类，所有字段都是 private final，只有 getter，没有 setter
 *
 * === 使用要求 ===
 * 1. 添加 Maven 依赖：
 *    <dependency>
 *        <groupId>org.projectlombok</groupId>
 *        <artifactId>lombok</artifactId>
 *    </dependency>
 *
 * 2. IDE 安装 Lombok 插件：
 *    - IntelliJ IDEA: Settings -> Plugins -> 搜索 "Lombok" 并安装
 *    - Eclipse: 下载 lombok.jar 并运行安装程序
 *
 * 3. 开启注解处理器：
 *    - IntelliJ IDEA: Settings -> Build -> Compiler -> Annotation Processors -> Enable annotation processing
 *
 * === 优势 ===
 * - 减少样板代码，提高开发效率
 * - 代码更简洁易读
 * - 统一代码风格
 * - 减少人为错误（如忘记更新 equals/hashCode）
 *
 * === 注意事项 ===
 * - Lombok 是编译时生成代码，不影响运行时性能
 * - 团队成员都需要安装 Lombok 插件
 * - 过度使用可能降低代码可读性
 * - 某些场景下需要自定义逻辑时，不适合使用 Lombok
 */
public class LombokDemo {
    public static void main(String[] args) {
        // ============ 传统方式 ============
        // 通过 setter 方法逐个设置属性
        User user = new User();
        user.setAge(18);
        user.setName("张三");
        user.setAddress("北京");
        System.out.println(user);
        System.out.println(user.getName() + " " + user.getAge() + " " + user.getAddress());

        // ============ @Builder 建造者模式 ============
        // 优点：链式调用，可读性强，参数顺序无关，适合多参数场景
        User user2 = User.builder()
                .name("李四")
                .age(25)
                .address("上海")
                .hobby("篮球")           // @Singular 生成的单个添加方法
                .hobby("编程")
                .build();
        // country 未设置，使用 @Builder.Default 定义的默认值 "中国"
        System.out.println(user2);

        // ============ 部分字段构造 ============
        // builder 允许只设置部分字段，其余使用默认值（对象类型为 null，基本类型为 0/false）
        User user3 = User.builder()
                .name("王五")
                .build();
        System.out.println(user3);   // age=0, address=null, country=中国
    }
}
