dependencies {
    implementation(project(":dermo-diagnostic-core"))
    implementation(project(":dermo-diagnostic-api"))
    implementation(project(":dermo-diagnostic-common"))
    implementation(project(":dermo-diagnostic-domain"))
    implementation(project(":dermo-diagnostic-listener"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("io.swagger:swagger-annotations:1.5.15")
    implementation("io.github.resilience4j:resilience4j-spring-boot2:1.7.1")
}
