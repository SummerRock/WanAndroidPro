package com.demo.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginImpl implements Plugin<Project> {
    // 操作符 << 在Gradle 4.x中被弃用（deprecated），并且在Gradle 5.0 被移除（removed）
    void apply(Project project) {
        project.task('testTask')  {
            println "Hello gradle plugin"
        }
    }
}
