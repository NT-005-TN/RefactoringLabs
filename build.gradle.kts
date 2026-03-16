plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.anasttruh"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot starters
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Gson для ручного парсинга JSON (как в вашем коде)
    implementation("com.google.code.gson:gson:2.10.1")

    // Jakarta annotations (уже в spring-boot-starter-web, но можно явно)
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")

    // Тестирование
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

// Опционально: отключение devtools, если не нужен
tasks.bootRun {
    classpath = files(tasks.named<Jar>("bootJar").map { it.archiveFile })
}