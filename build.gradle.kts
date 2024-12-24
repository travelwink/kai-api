import org.springframework.boot.gradle.tasks.run.BootRun

println("=======================================================")
println(project.name)

plugins {
    java
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
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
    mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")
extra["springBootAdminVersion"] = "3.4.0"
extra["springCloudVersion"] = "2024.0.0"
configurations.all {
    exclude(group = "commons-collections", module = "commons-collections")
}
dependencies {
//    implementation("org.springframework.boot:spring-boot-starter-amqp")
//    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // admin-client 包含 actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator")
//    implementation("de.codecentric:spring-boot-admin-starter-client")

    /** JWT */
//	implementation("com.auth0:java-jwt:4.4.0")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

    /** Apache Shiro */
//    // springboot3 需要加 jakarta 来替换 javax
//	implementation("org.apache.shiro:shiro-core:2.0.2:jakarta")
//	implementation("org.apache.shiro:shiro-web:2.0.2:jakarta")
//	implementation("org.apache.shiro:shiro-spring:2.0.2:jakarta")
//    // 加了下面这两个会与spring-boot-starter-actuator冲突
//	implementation("org.apache.shiro:shiro-spring-boot-starter:2.0.2:jakarta")
//	implementation("org.apache.shiro:shiro-spring-boot-web-starter:2.0.2:jakarta")
//    implementation("org.apache.commons:commons-collections4:4.4")

    /** API DOC */
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    implementation("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter:4.5.0")

    /** DB */
    implementation("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.9")
    implementation("com.baomidou:mybatis-plus-jsqlparser:3.5.9")

    implementation("org.fusesource.jansi:jansi:2.4.1")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("com.mysql:mysql-connector-j")
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

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    outputs.dir(project.extra["snippetsDir"]!!)
}

tasks.asciidoctor {
    inputs.dir(project.extra["snippetsDir"]!!)
    dependsOn(tasks.test)
}

tasks.withType<BootRun> {
    systemProperty("spring.profiles.active", "prod")
}

