package com.itheima.SoftwareTest;

public class Test {
    /*
    测试：测试是一种用来促进鉴定软件的正确性、完整性、安全性和质量的过程。

    测试阶段划分：单元测试、集成测试、系统测试、验收测试。
        单元测试：对软件的基本组成单位进行测试，最小测试单位。目的：检测软件基本组成单位的正确性。测试人员：开发人员。
        集成测试：将已分别通过测试的单元，按设计要求组成系统或者子系统，再进行的测试。目的：检查单元之间的协作是否正确。测试人员：开发人员。
        系统测试：对已经集成好的软件系统进行彻底的测试。目的：验证软件系统的正确性、性能是否满足指定的要求。测试人员：专业测试。
        验收测试：交付测试，是针对用户需求、业务流程进行的正式的测试。目的：验证软件系统是否满足验收标准。测试人员：客户/需求方。

    测试方法：白盒测试、黑盒测试、灰盒测试。
        白盒测试：清楚软件内部结构、代码逻辑。用于验证代码、逻辑正确性。
        黑盒测试：不清楚软件内部结构、代码逻辑。用于验证软件的功能、兼容性等方面。
        灰盒测试：结合了白盒测试和黑盒测试的特点，即关注软件的内部结构又考虑外部表现（功能）。

   结合起来，单元测试就是白盒测试，集成测试是灰盒测试，系统测试和验收测试是黑盒测试。

   单元测试：就是针对最小的功能单元（方法），编写测试代码对其正确性进行测试。
   JUnit：最流行的Java测试框架之一，提供了一些功能，方便程序进行单元测试（第三方公司提供）。

   为什么不在main方法中测试？
       1.测试代码与源代码未分开，难维护；
       2.一个方法测试失败，影响后面方法的测试；
       3.无法自动化测试，得到测试报告。

   JUnit单元测试：
       1.测试代码与源代码分开，便于维护；
       2.可根据需要进行自动化测试；
       3.可自动分析测试结果，产出测试报告。

   案例：使用Junit，对UserService中业务方法进行单元测试
       1.在pom.xml文件中添加JUnit依赖
       2.在text/java目录下，创建测试类，并编写对应的测试方法，并在方法上生命@Test注解
       3.运行单元测试（测试通过：绿色✅；测试失败：红色❌）
       ⚠️：JUnit单元测试类命名规范为:XxxxTest【规范】.Junit单元测试的方法，必须声明为 public void【规定】
       <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>6.1.0</version>
            <scope>test</scope>
        </dependency>

        @Test
        public void testGetAge() {
            Integer age = new UserService().getAge("110101199001011234");
            sout(age);
        }

     JUnit提供了一些辅助方法，用来帮我们确定被测是的方法是否按照预期的效果正常工作，这种方式称为断言。
     Assertions类：
        assertEquals(Object exp, Object act, String msg) 检查两个值是否相等，不相等就报错。
        assertnotEquals(Object unexp, Object act, String msg) 检查两个值是否不相等，相等就报错。
        assertNull(Object act, String msg) 检查对象是否为null，不为null就报错。
        assertNotNull(Object act, String msg) 检查对象是否不为null，为null就报错。
        assertTrue(boolean condition, String msg) 检查条件是否为true，为false就报错。
        assertFalse(boolean condition, String msg) 检查条件是否为false，为true就报错。
        assertThrows(Class expType, Excuteable exec, String msg) 检查两个对象引用是否相等，不相等就报错

        上述方法形参中的最后一个参数msg，表示错误提示信息，可以不指定（有对应的重载方法）。
        JNnit单元测试中，为什么要使用断言？
        1.单元测试方法运行不报错，不代表业务方法没问题；
        2.通过断言可以检测方法运行结果是否和预期一致，从而判断业务方法的正确性。

      JUnit常见注解：在JUnit在还提供了一些注解，还增强其功能，常见的注解有以下几个：
      @Test：测试类中的方法用它修饰才能成为测试方法，才能启动执行。备注：单元测试。
      @ParameterizedTest：参数化测试的注解（可以让单个测试运行多次，每次运行时仅参数不同）。备注：用了该注解，就不需要@Test注解了。
      @ValueSource：参数化测试的参数来源，赋予测试方法参数。注解：与参数化测试注解配合使用。
      @DisplayName:指定测试类、测试方法现实的名称（默认为类名、方法名）
      @BeforeEach：用来修饰一个实例方法，该方法会在每一个测试方法执行之前执行一次。备注：初始化资源（准备工作）
      @AfferEach：用来修饰一个实例方法，该方法会在每一个测试方法执行之后执行一次。备注：释放资源（清理工作）
      @BeforeAll：用来修饰一个静态方法，该方法会在所有测试方法之前只执行一次。备注：初始化资源（准备工作）
      @AfterAll：用来修饰一个静态方法，该方法会在所有测试方法之后只执行一次。备注：释放资源（清理工作）
     */
}
