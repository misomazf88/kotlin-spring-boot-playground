dependencies {
    implementation(project(":dermo-diagnostic-domain"))
    implementation(project(":dermo-diagnostic-common"))
    implementation(project(":dermo-diagnostic-listener"))
    implementation("org.springframework:spring-context")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("io.github.resilience4j:resilience4j-spring-boot2:1.7.1")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation(project(":dermo-diagnostic-app"))
}
