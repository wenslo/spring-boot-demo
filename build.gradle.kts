import org.springframework.boot.gradle.tasks.bundling.BootJar

buildscript {
    repositories {
        val repos by extra {
            listOf(
                    "http://maven.aliyun.com/nexus/content/groups/public",
                    "https://jcenter.bintray.com/",
                    "https://plugins.gradle.org/m2/",
                    "https://repo.spring.io/milestone"
            )
        }
        repositories {
            for (u in repos) {
                maven(u)
            }
            google()
            mavenCentral()
            mavenLocal()
        }
//        maven("http://127.0.0.1:8081/repository/maven-public/") {
//            credentials(HttpHeaderCredentials::class.java) {
//                name = "wenslo-user"
//                value = "123456"
//            }
//            authentication {
//                register("header", HttpHeaderAuthentication::class)
//            }
//        }
    }
}

//configurations{
//    all{
//        resolutionStrategy{
//            cacheChangingModulesFor(0,"seconds")
//        }
//    }
//}
plugins {
    val springBootVersion = "2.1.0.RELEASE"
    java
    idea
    `java-library`
    id("base")
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
    id("org.springframework.boot") version springBootVersion apply false
    id("org.jetbrains.kotlin.kapt") version "1.3.31"
    id("org.jetbrains.kotlin.jvm") version "1.3.31"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val repos: List<String> by extra
val querydslVersion = "4.2.1"
allprojects {
    repositories {
        maven("http://127.0.0.1:8081/repository/maven-public/") {
            credentials(HttpHeaderCredentials::class.java) {
                name = "admin"
                value = "admin"
            }
            authentication {
                register("header", HttpHeaderAuthentication::class)
            }
        }
    }
}
subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "java-library")
    apply(plugin = "base")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "maven-publish")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")
    }

    tasks {
        "jar"(Jar::class) {
            enabled = true
        }
        "bootJar"(BootJar::class) {
            enabled = false
        }
        create("sourcesJar", Jar::class) {
            classifier = "sources"
            from(sourceSets.main.get().allJava)
        }

    }
}

project(":core") {
    dependencies {
        kapt("com.querydsl:querydsl-apt:${querydslVersion}:jpa")
        api("com.github.wenslo.fluent:fluent-security:1.0.5-RELEASE")
        api("com.github.wenslo.fluent:fluent-data-jpa:1.0.5-RELEASE")
        api("com.h2database:h2")
        api("org.jxls:jxls:2.5.1")
        api("org.jxls:jxls-poi:1.1.0")
        api("org.jxls:jxls-jexcel:1.0.8")
        api("mysql:mysql-connector-java:5.1.44")
        testApi("mysql:mysql-connector-java:5.1.44")
        testApi("org.springframework.boot:spring-boot-starter-test")
//        testApi("org.springframework.security:spring-security-test")
    }
}

project(":api") {
    dependencies {
        api(project(":core"))
        api("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5")
        testApi("org.springframework.boot:spring-boot-starter-test")
//        testApi("org.springframework.security:spring-security-test")
    }
}