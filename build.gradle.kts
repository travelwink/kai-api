import org.springframework.boot.gradle.tasks.run.BootRun
import org.springframework.boot.gradle.tasks.bundling.BootJar

println("=======================================================")
println(project.name)

plugins {
    java
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.asciidoctor.jvm.convert") version "4.0.5"
}

group = "com.travelwink"
version = "0.0.1-SNAPSHOT"
println(project.version)
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    // 自定义私有仓库
    maven("http://localhost:8081/repository/maven-public/") {
        name = "local nexus"
        isAllowInsecureProtocol = true
//        credentials {
//            username = "user"
//            password = "password"
//        }
    }
    mavenCentral()
    mavenLocal()
}

extra["snippetsDir"] = file("build/generated-snippets")
extra["springBootAdminVersion"] = "3.4.5"
extra["springCloudVersion"] = "2024.0.0"
configurations.all {
    exclude(group = "commons-collections", module = "commons-collections")
}
dependencies {
//    implementation("org.springframework.boot:spring-boot-starter-amqp")
//    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-data-neo4j")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mail")
//    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // admin-client 包含 actuator
//    implementation("de.codecentric:spring-boot-admin-starter-client")

    /** Nacos */
//    implementation("com.alibaba.cloud:spring-cloud-alibaba-dependencies:2023.0.3.2")
//    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:2023.0.3.2") // 服务发现
//    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:2023.0.3.2") // 配置中心

    /** JWT */
//	implementation("com.auth0:java-jwt:4.4.0")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

    /** Apache Shiro */
    // spring-boot3 需要加 jakarta 来替换 javax
	implementation("org.apache.shiro:shiro-core:2.0.6:jakarta")
	implementation("org.apache.shiro:shiro-web:2.0.6:jakarta")
	implementation("org.apache.shiro:shiro-spring:2.0.6:jakarta")
    // 加了下面这两个会与spring-boot-starter-actuator冲突
//	implementation("org.apache.shiro:shiro-spring-boot-starter:2.0.4:jakarta")
//	implementation("org.apache.shiro:shiro-spring-boot-web-starter:2.0.4:jakarta")
    implementation("org.apache.shiro:shiro-spring-boot-starter:2.0.6:jakarta")
    implementation("org.apache.shiro:shiro-spring-boot-web-starter:2.0.6:jakarta")

    implementation("org.apache.commons:commons-collections4:4.4")

    /** 流程引擎 Camunda **/
//    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter:7.24.0")
//    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter-rest:7.24.0")
    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter-webapp:7.24.0")

    /** API DOC */
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.8")
    implementation("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter:4.5.0")

    /** DB */
    implementation("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.16")
    implementation("com.baomidou:dynamic-datasource-spring-boot3-starter:4.5.0")
    implementation("com.baomidou:mybatis-plus-jsqlparser:3.5.16")

    implementation("org.fusesource.jansi:jansi:2.4.1")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    developmentOnly("org.springframework.boot:spring-boot-docker-compose")

    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.3")
//    testImplementation("org.springframework.amqp:spring-rabbit-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("de.codecentric:spring-boot-admin-dependencies:${property("springBootAdminVersion")}")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.test {
    useJUnitPlatform()
    outputs.dir(project.extra["snippetsDir"]!!)
}

tasks.asciidoctor {
    inputs.dir(project.extra["snippetsDir"]!!)
    dependsOn(tasks.test)
}

tasks.withType<BootRun> {
    systemProperty("spring.profiles.active", "prod")
}

tasks.named<BootJar>("bootJar") {
    // 重复文件处理策略：保留第一个，忽略后续重复的（推荐，避免包冲突）
    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    // 可选策略（按需选择）：
    // 1. DuplicatesStrategy.EXCLUDE：忽略所有重复文件（仅保留最后一个，不推荐）
    // 2. DuplicatesStrategy.FAIL：默认策略，遇到重复直接失败（当前问题的诱因）
    // 3. DuplicatesStrategy.WARN：仅打印警告，保留第一个，不中断打包（适合调试）
}

// 禁用普通JAR生成
tasks.jar {
    enabled = false
}



