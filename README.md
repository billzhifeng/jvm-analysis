# jvm-analysis
- jvm 分析，压力测试，cpu、内存占用高分析

# 项目规范
- 字符集 UTF-8
- 启动查看 http://localhost:9999/ok
- 访问数据源管理 http://localhost:9999/druid/index.html
- MySQL默认字符集全局使用utf8mb4 + utf8mb4_general_ci，以兼容现在常用的emoji等超宽字符

# 功能
- 压力测试 http://localhost:9999/showView 
- stackOverFlow 栈溢出
- OutOfMemoryError 内存溢出
- high memory 内存占用高
- high CPU cpu占用高
- system block 系统阻塞或系统慢