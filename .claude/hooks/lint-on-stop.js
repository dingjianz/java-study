#!/usr/bin/env node
/**
 * Claude Hook: Lint modified files before session ends
 * 在会话结束前自动检查修改的文件及其引用
 */

const { execSync } = require('child_process');
const fs = require('fs');
const path = require('path');

// 配置
const CONFIG = {
  enableJavaLint: true,
  enableFrontendLint: true,
  enableDependencyCheck: true,
  maxFilesToCheck: 50,
  timeout: 30000, // 30 seconds
};

/**
 * 执行命令并返回结果
 */
function runCommand(cmd, cwd = process.cwd()) {
  try {
    const output = execSync(cmd, {
      cwd,
      encoding: 'utf-8',
      timeout: CONFIG.timeout,
      stdio: 'pipe',
    });
    return { success: true, output: output.trim() };
  } catch (error) {
    return {
      success: false,
      output: error.stdout?.toString() || '',
      error: error.stderr?.toString() || error.message
    };
  }
}

/**
 * 获取 git 修改的文件列表
 */
function getModifiedFiles() {
  const result = runCommand('git status --porcelain');
  if (!result.success) {
    console.log('⚠️  无法获取 git 状态');
    return [];
  }

  const files = result.output
    .split('\n')
    .filter(line => line.trim())
    .map(line => {
      // 格式: "M  file.java" 或 "?? file.ts"
      const match = line.match(/^.{3}(.+)$/);
      return match ? match[1].trim() : null;
    })
    .filter(Boolean)
    .slice(0, CONFIG.maxFilesToCheck);

  return files;
}

/**
 * 查找依赖于指定文件的其他文件
 */
function findDependencies(filePath, projectRoot) {
  const dependencies = [];
  const fileName = path.basename(filePath, path.extname(filePath));

  // 对于 Java 文件，搜索 import 语句
  if (filePath.endsWith('.java')) {
    const result = runCommand(
      `grep -r "import.*${fileName}" --include="*.java" .`,
      projectRoot
    );
    if (result.success && result.output) {
      const matches = result.output.split('\n')
        .map(line => line.split(':')[0])
        .filter(file => file !== filePath);
      dependencies.push(...matches);
    }
  }

  // 对于前端文件，搜索 import/require 语句
  if (filePath.match(/\.(ts|tsx|js|jsx)$/)) {
    const fileNameWithoutExt = path.basename(filePath).replace(/\.[^.]+$/, '');
    const result = runCommand(
      `grep -r "from.*['\"].*${fileNameWithoutExt}" --include="*.ts" --include="*.tsx" --include="*.js" --include="*.jsx" .`,
      projectRoot
    );
    if (result.success && result.output) {
      const matches = result.output.split('\n')
        .map(line => line.split(':')[0])
        .filter(file => file !== filePath);
      dependencies.push(...matches);
    }
  }

  return [...new Set(dependencies)].slice(0, 10);
}

/**
 * Lint Java 文件
 */
function lintJavaFiles(files, projectRoot) {
  const javaFiles = files.filter(f => f.endsWith('.java'));
  if (javaFiles.length === 0) return { success: true, message: '无 Java 文件需要检查' };

  console.log(`\n🔍 检查 ${javaFiles.length} 个 Java 文件...`);

  // 尝试使用 Maven checkstyle
  const pomPath = path.join(projectRoot, 'pom.xml');
  if (fs.existsSync(pomPath)) {
    // 检查 mvn 是否可用
    const mvnCheck = runCommand('which mvn');
    if (!mvnCheck.success || !mvnCheck.output) {
      console.log('⚠️  Maven 未安装或不在 PATH 中，跳过 Java 检查');
      console.log('   提示: 如需检查，请安装 Maven 或将其添加到 PATH');
      return { success: true, files: javaFiles, skipped: true };
    }

    console.log('   使用 Maven 编译检查...');
    const result = runCommand('mvn compile -q', projectRoot);
    if (!result.success) {
      // 检查是否是真正的编译错误还是环境问题
      if (result.error && result.error.includes('command not found')) {
        console.log('⚠️  Maven 命令执行失败，跳过检查');
        return { success: true, files: javaFiles, skipped: true };
      }
      console.log('❌ Maven 编译失败:');
      console.log(result.error || result.output);
      return { success: false, files: javaFiles };
    }
    console.log('✅ Java 文件编译通过');
  } else {
    console.log('⚠️  未找到 pom.xml，跳过 Java 检查');
  }

  return { success: true, files: javaFiles };
}

/**
 * Lint 前端文件
 */
function lintFrontendFiles(files, projectRoot) {
  const frontendFiles = files.filter(f => f.match(/\.(ts|tsx|js|jsx)$/));
  if (frontendFiles.length === 0) return { success: true, message: '无前端文件需要检查' };

  console.log(`\n🔍 检查 ${frontendFiles.length} 个前端文件...`);

  // 检查是否存在 package.json
  const packageJsonPath = path.join(projectRoot, 'package.json');
  if (!fs.existsSync(packageJsonPath)) {
    console.log('⚠️  未找到 package.json，跳过前端检查');
    return { success: true, files: frontendFiles };
  }

  const packageJson = JSON.parse(fs.readFileSync(packageJsonPath, 'utf-8'));

  // 使用 oxlint 或其他可用的 linter
  if (packageJson.scripts?.lint) {
    console.log('   运行 lint 脚本...');
    const fileArgs = frontendFiles.map(f => `"${f}"`).join(' ');
    const result = runCommand(`npm run lint -- ${fileArgs}`, projectRoot);

    if (!result.success) {
      console.log('❌ Lint 发现问题:');
      console.log(result.output || result.error);
      return { success: false, files: frontendFiles };
    }
    console.log('✅ 前端文件 lint 通过');
  } else if (packageJson.devDependencies?.oxlint || packageJson.dependencies?.oxlint) {
    console.log('   使用 oxlint 检查...');
    const fileArgs = frontendFiles.join(' ');
    const result = runCommand(`npx oxlint ${fileArgs}`, projectRoot);

    if (!result.success && result.output) {
      console.log('❌ Oxlint 发现问题:');
      console.log(result.output);
      return { success: false, files: frontendFiles };
    }
    console.log('✅ 前端文件 lint 通过');
  } else {
    console.log('⚠️  未找到 lint 工具，尝试 TypeScript 编译检查...');
    const result = runCommand('npx tsc --noEmit', projectRoot);
    if (!result.success && result.error) {
      console.log('❌ TypeScript 编译错误:');
      console.log(result.error);
      return { success: false, files: frontendFiles };
    }
    console.log('✅ TypeScript 编译通过');
  }

  return { success: true, files: frontendFiles };
}

/**
 * 主函数
 */
async function main() {
  console.log('\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━');
  console.log('🔧 Claude Hook: 会话结束前代码检查');
  console.log('━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n');

  const projectRoot = process.cwd();
  console.log(`📁 项目目录: ${projectRoot}`);

  // 获取修改的文件
  const modifiedFiles = getModifiedFiles();

  if (modifiedFiles.length === 0) {
    console.log('✅ 没有修改的文件，无需检查\n');
    return;
  }

  console.log(`\n📝 发现 ${modifiedFiles.length} 个修改的文件:`);
  modifiedFiles.forEach(file => console.log(`   - ${file}`));

  let hasErrors = false;

  // Lint Java 文件
  if (CONFIG.enableJavaLint) {
    const result = lintJavaFiles(modifiedFiles, projectRoot);
    if (!result.success) hasErrors = true;
  }

  // Lint 前端文件
  if (CONFIG.enableFrontendLint) {
    const result = lintFrontendFiles(modifiedFiles, projectRoot);
    if (!result.success) hasErrors = true;
  }

  // 检查依赖文件
  if (CONFIG.enableDependencyCheck && modifiedFiles.length > 0) {
    console.log('\n🔗 检查依赖关系...');
    const allDependencies = [];

    for (const file of modifiedFiles.slice(0, 5)) {
      const deps = findDependencies(file, projectRoot);
      if (deps.length > 0) {
        console.log(`\n   ${file} 被以下文件引用:`);
        deps.forEach(dep => console.log(`   - ${dep}`));
        allDependencies.push(...deps);
      }
    }

    if (allDependencies.length > 0) {
      console.log(`\n   建议检查这 ${allDependencies.length} 个依赖文件`);
    }
  }

  // 总结
  console.log('\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━');
  if (hasErrors) {
    console.log('❌ 检查完成，发现问题需要修复');
  } else {
    console.log('✅ 检查完成，所有文件通过');
  }
  console.log('━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n');

  process.exit(hasErrors ? 1 : 0);
}

// 运行主函数
main().catch(error => {
  console.error('❌ Hook 执行失败:', error.message);
  process.exit(1);
});
