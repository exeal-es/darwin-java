plugins {
    id("java")
}

group = "com.exeal.darwin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.auth0:java-jwt:4.0.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("io.rest-assured:rest-assured:5.5.0")
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}