package com.itheima.mySQL;

/**
 * SQL成绩等级分类示例
 * 演示使用CASE WHEN语句根据分数进行等级划分
 */
public class ScoreGradeDemo {
    public static void main(String[] args) {
        System.out.println("=== SQL成绩等级分类示例 ===\n");

        // 方法1: 使用CASE WHEN进行简单分类
        String sql1 = """
                SELECT
                    student_name,
                    score,
                    CASE
                        WHEN score >= 90 THEN '优秀'
                        WHEN score >= 80 THEN '良好'
                        WHEN score >= 70 THEN '中等'
                        WHEN score >= 60 THEN '及格'
                        ELSE '不及格'
                    END AS grade
                FROM student_scores;
                """;

        System.out.println("【方法1】基础CASE WHEN分类:");
        System.out.println(sql1);

        // 方法2: 使用CASE WHEN带区间范围
        String sql2 = """
                SELECT
                    student_name,
                    score,
                    CASE
                        WHEN score BETWEEN 90 AND 100 THEN '优秀(A)'
                        WHEN score BETWEEN 80 AND 89 THEN '良好(B)'
                        WHEN score BETWEEN 70 AND 79 THEN '中等(C)'
                        WHEN score BETWEEN 60 AND 69 THEN '及格(D)'
                        WHEN score BETWEEN 0 AND 59 THEN '不及格(F)'
                        ELSE '成绩异常'
                    END AS grade_level
                FROM student_scores
                ORDER BY score DESC;
                """;

        System.out.println("【方法2】使用BETWEEN指定区间:");
        System.out.println(sql2);

        // 方法3: 统计各等级人数
        String sql3 = """
                SELECT
                    CASE
                        WHEN score >= 90 THEN '优秀'
                        WHEN score >= 80 THEN '良好'
                        WHEN score >= 70 THEN '中等'
                        WHEN score >= 60 THEN '及格'
                        ELSE '不及格'
                    END AS grade,
                    COUNT(*) AS student_count,
                    CONCAT(ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM student_scores), 2), '%') AS percentage
                FROM student_scores
                GROUP BY grade
                ORDER BY
                    CASE grade
                        WHEN '优秀' THEN 1
                        WHEN '良好' THEN 2
                        WHEN '中等' THEN 3
                        WHEN '及格' THEN 4
                        WHEN '不及格' THEN 5
                    END;
                """;

        System.out.println("【方法3】统计各等级人数和百分比:");
        System.out.println(sql3);

        // 方法4: 多科目成绩分类
        String sql4 = """
                SELECT
                    student_name,
                    math_score,
                    CASE
                        WHEN math_score >= 90 THEN '优秀'
                        WHEN math_score >= 60 THEN '及格'
                        ELSE '不及格'
                    END AS math_grade,
                    english_score,
                    CASE
                        WHEN english_score >= 90 THEN '优秀'
                        WHEN english_score >= 60 THEN '及格'
                        ELSE '不及格'
                    END AS english_grade,
                    (math_score + english_score) / 2 AS avg_score,
                    CASE
                        WHEN (math_score + english_score) / 2 >= 90 THEN '优秀'
                        WHEN (math_score + english_score) / 2 >= 80 THEN '良好'
                        WHEN (math_score + english_score) / 2 >= 70 THEN '中等'
                        WHEN (math_score + english_score) / 2 >= 60 THEN '及格'
                        ELSE '不及格'
                    END AS overall_grade
                FROM student_scores;
                """;

        System.out.println("【方法4】多科目成绩分类:");
        System.out.println(sql4);

        // 方法5: 使用WHERE子句筛选特定等级
        String sql5 = """
                -- 查询优秀学生
                SELECT student_name, score
                FROM student_scores
                WHERE score >= 90;

                -- 查询不及格学生
                SELECT student_name, score
                FROM student_scores
                WHERE score < 60;

                -- 查询及格及以上的学生
                SELECT student_name, score,
                    CASE
                        WHEN score >= 90 THEN '优秀'
                        WHEN score >= 80 THEN '良好'
                        WHEN score >= 70 THEN '中等'
                        ELSE '及格'
                    END AS grade
                FROM student_scores
                WHERE score >= 60
                ORDER BY score DESC;
                """;

        System.out.println("【方法5】筛选特定等级学生:");
        System.out.println(sql5);

        // 方法6: 创建测试表和插入数据的完整示例
        String fullExample = """
                -- 创建学生成绩表
                CREATE TABLE IF NOT EXISTS student_scores (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    student_name VARCHAR(50) NOT NULL,
                    score DECIMAL(5,2) NOT NULL CHECK (score >= 0 AND score <= 100),
                    subject VARCHAR(20) DEFAULT '数学'
                );

                -- 插入测试数据
                INSERT INTO student_scores (student_name, score, subject) VALUES
                ('张三', 95.5, '数学'),
                ('李四', 87.0, '数学'),
                ('王五', 76.5, '数学'),
                ('赵六', 62.0, '数学'),
                ('钱七', 45.5, '数学'),
                ('孙八', 90.0, '数学'),
                ('周九', 58.5, '数学'),
                ('吴十', 82.0, '数学');

                -- 查询成绩并分类
                SELECT
                    student_name AS 姓名,
                    score AS 分数,
                    CASE
                        WHEN score >= 90 THEN '优秀 ⭐⭐⭐⭐⭐'
                        WHEN score >= 80 THEN '良好 ⭐⭐⭐⭐'
                        WHEN score >= 70 THEN '中等 ⭐⭐⭐'
                        WHEN score >= 60 THEN '及格 ⭐⭐'
                        ELSE '不及格 ⭐'
                    END AS 等级评定
                FROM student_scores
                ORDER BY score DESC;
                """;

        System.out.println("【完整示例】创建表、插入数据并查询:");
        System.out.println(fullExample);

        // 常用成绩分类标准说明
        System.out.println("\n=== 常用成绩分类标准 ===");
        System.out.println("90-100分: 优秀(A)");
        System.out.println("80-89分:  良好(B)");
        System.out.println("70-79分:  中等(C)");
        System.out.println("60-69分:  及格(D)");
        System.out.println("0-59分:   不及格(F)");

        System.out.println("\n=== CASE WHEN语法要点 ===");
        System.out.println("1. 条件从上到下匹配，匹配到第一个true就返回");
        System.out.println("2. 使用 >= 降序判断，避免重复条件");
        System.out.println("3. ELSE子句处理所有未匹配的情况");
        System.out.println("4. 可以在SELECT、WHERE、ORDER BY中使用");
        System.out.println("5. 支持嵌套和复杂表达式");
    }
}
