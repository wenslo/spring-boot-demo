import org.springframework.boot.gradle.tasks.bundling.BootJar

buildscript {
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
}
plugins {
    val springBootVersion = "2.1.0.RELEASE"
    java
    idea
    `java-library`
    id("base")
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
    id("org.springframework.boot") version springBootVersion apply false
    id ("org.jetbrains.kotlin.kapt") version "1.3.31"
    id ("org.jetbrains.kotlin.jvm") version "1.3.31"
    `maven-publish`
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val repos:List<String> by extra
val querydslVersion = "4.2.1"
allprojects{
    repositories {
        mavenLocal()
        for (u in repos) { maven(u) }
    }
}
subprojects{
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "java-library")
    apply(plugin ="base")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin  = "org.springframework.boot")
    apply(plugin  = "maven-publish")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies{
        kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")
    }

    tasks{
        "jar"(Jar::class){
            enabled = true
        }
        "bootJar"(BootJar::class){
            enabled = false
        }
        create("sourcesJar",Jar::class){
            classifier = "sources"
            from(sourceSets.main.get().allJava)
        }

        withType(PublishToMavenRepository::class){
            onlyIf {
                (repository == publishing.repositories["hhkj"] &&
                        publication == publishing.publications["binary"])
//                    ||
//                    (repository == publishing.repositories["internal"] &&
//                            publication == publishing.publications["binaryAndSources"])
            }
        }

        withType(PublishToMavenLocal::class){
            onlyIf {
                publication == publishing.publications["binaryAndSources"]
            }
        }
    }

    publishing {
        publications {
            create<MavenPublication>("binary") {
                version = "${project.version}"
                from(components["java"])
            }
            create<MavenPublication>("binaryAndSources") {
                from(components["java"])
                artifact(tasks["sourcesJar"])
            }
        }
        repositories {
//            maven {
//                name = "self"
//                url = uri("$buildDir/repos/internal")
//            }
        }
    }
}

project(":core"){
    dependencies{
        compile("org.springframework.boot:spring-boot-starter-data-jpa")
        compile("org.springframework.boot:spring-boot-starter-security")
        compile("org.springframework.boot:spring-boot-starter-web")
        compile("com.github.springtestdbunit:spring-test-dbunit:1.3.0")
        compile("org.dbunit:dbunit:2.5.0")
        compile("com.google.code.gson:gson:2.8.5")
        compile("com.google.guava:guava:27.0-jre")
        compile("org.apache.commons:commons-lang3:3.8.1")
        compile("org.modelmapper:modelmapper:2.3.2")
        runtimeOnly("com.h2database:h2")
        compile("com.querydsl:querydsl-jpa:4.2.1")
        compile("com.querydsl:querydsl-apt:4.2.1")
        compile("org.hibernate.validator:hibernate-validator:6.0.13.Final")
        compile("org.jxls:jxls:2.5.1")
        compile("org.jxls:jxls-poi:1.1.0")
        compile("org.jxls:jxls-jexcel:1.0.8")
        compile("mysql:mysql-connector-java:5.1.44")
        testCompile("mysql:mysql-connector-java:5.1.44")
        testCompile("org.springframework.boot:spring-boot-starter-test")
        testCompile("org.springframework.security:spring-security-test")
    }
}

project(":api") {
    dependencies {
        compile(project(":core"))
        testCompile("org.springframework.boot:spring-boot-starter-test")
        testCompile("org.springframework.security:spring-security-test")
    }
}