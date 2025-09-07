module cn.ivfzhou.moduleA {
    // 声明该模块依赖的其他模块。
    requires java.base;  // 默认已经包含，通常不需要写

    // 导出该模块中提供的包。
    exports cn.ivfzhou.moduleA;

    // 开放给特定的模块访问内部包。
    opens cn.ivfzhou.moduleA to cn.ivfzhou.moduleB;
}
