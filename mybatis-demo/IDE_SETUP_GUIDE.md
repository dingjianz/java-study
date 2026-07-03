# IntelliJ IDEA 设置指南 - 解决 Java 25 与 Lombok 兼容性问题

## 问题描述
错误: `java.lang.ExceptionInInitializerError: com.sun.tools.javac.code.TypeTag :: UNKNOWN`

这是因为项目 SDK 设置为 Java 25，但 Lombok 1.18.36 在 Java 25 下编译时存在兼容性问题。

## 解决方法（按顺序尝试）

### 方法 1：修改项目 SDK 为 Java 17（强烈推荐）⭐

1. 打开 **File → Project Structure** (快捷键 `Cmd + ;` 或 `Ctrl + Alt + Shift + S`)

2. 在 **Project** 标签页：
   - **SDK**: 点击下拉框，选择或添加 **Java 17**
   - 如果没有 Java 17，点击 "Add SDK" → "Download JDK" → 选择 Temurin 17
   - **Language level**: 选择 **17 - Sealed types, always-strict floating-point semantics**

3. 点击 **Apply** → **OK**

4. 在右侧 Maven 工具窗口点击 **刷新图标** (Reload All Maven Projects)

5. 执行 **Build → Rebuild Project**

---

### 方法 2：仅修改 mybatis-demo 模块的 SDK

如果你的其他模块需要 Java 25，可以单独设置 mybatis-demo：

1. **File → Project Structure** → **Modules** 标签页

2. 选择 **mybatis-demo** 模块

3. 在右侧 **Dependencies** 标签：
   - **Module SDK**: 选择 **Java 17**

4. 点击 **Apply** → **OK**

5. 刷新 Maven 并重新构建项目

---

### 方法 3：禁用 IDE 编译，仅使用 Maven 编译

1. **File → Settings** (macOS: **IntelliJ IDEA → Preferences**)

2. **Build, Execution, Deployment → Compiler**

3. 取消勾选 **Build project automatically**

4. **Build, Execution, Deployment → Build Tools → Maven → Runner**

5. 勾选 **Delegate IDE build/run actions to Maven**

6. 点击 **OK**

此后运行测试时会使用 Maven 编译，而 Maven 已正确配置为 Java 17。

---

### 方法 4：验证 Maven 导入设置

1. **File → Settings → Build, Execution, Deployment → Build Tools → Maven → Importing**

2. 确认：
   - **JDK for importer**: 选择 **Use Project JDK** 或明确选择 Java 17
   - **VM options for importer**: 添加以下内容（如果为空）：
     ```
     -Xmx1024m
     ```

3. 点击 **OK** 并重新导入 Maven 项目

---

## 验证是否成功

完成上述任何一个方法后：

1. 在 IDE 底部打开 **Terminal**
2. 运行测试：
   ```bash
   mvn clean test
   ```
3. 如果 Maven 测试通过，然后在 IDE 中右键测试类 → **Run 'MybatisDemoApplicationTests'**

---

## 快速检查当前配置

在 Terminal 中运行：
```bash
# 检查 Maven 使用的 Java 版本
mvn -v

# 应该显示 Java version: 25.0.3（这是正常的，Maven 会根据 pom.xml 使用 Java 17 编译）
```

在 IntelliJ IDEA 中：
- **Help → About** → 检查 **Runtime version**（这是 IDE 自身的 Java 版本）
- **File → Project Structure → Project → SDK**（这是项目编译使用的版本，应该是 17）
