import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("plugin.jpa") version "1.3.72"
	id("org.jetbrains.kotlin.plugin.noarg") version "1.6.20"
	id("org.flywaydb.flyway") version "8.5.8"
}

group = "br.com"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	// https://mvnrepository.com/artifact/org.testcontainers/testcontainers-bom
	implementation("org.testcontainers:testcontainers-bom:1.17.1")
	// https://mvnrepository.com/artifact/org.testcontainers/postgresql
	testImplementation("org.testcontainers:postgresql:1.17.1")
	// https://mvnrepository.com/artifact/org.testcontainers/junit-jupiter
	testImplementation("org.testcontainers:junit-jupiter:1.17.1")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-noarg")
	implementation("org.flywaydb:flyway-core:8.5.7")
	runtimeOnly("org.flywaydb:flyway-gradle-plugin:8.5.7")
	implementation("org.springframework.boot:spring-boot-starter-cache:2.6.6")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.jsonwebtoken:jjwt-api:0.11.2")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
	runtimeOnly("io.jsonwebtoken:jjwt-orgjson:0.11.2")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// https://mvnrepository.com/artifact/io.mockk/mockk
	testImplementation("io.mockk:mockk:1.12.3")
	// https://mvnrepository.com/artifact/org.assertj/assertj-core
	testImplementation("org.assertj:assertj-core:3.22.0")
	// https://mvnrepository.com/artifact/org.springframework.security/spring-security-test
	testImplementation("org.springframework.security:spring-security-test")


}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}


