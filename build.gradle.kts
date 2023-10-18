plugins {
    id("java")
    //javafx
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    //javafx
    implementation("org.openjfx:javafx-controls:17")
    implementation("org.openjfx:javafx-graphics:17")
    implementation("org.openjfx:javafx-fxml:17")
    implementation("org.openjfx:javafx-base:17")
    implementation("org.openjfx:javafx-media:17")
    implementation("org.openjfx:javafx-web:17")
}

tasks.withType<JavaExec> {
    jvmArgs(
            "--module-path", System.getenv("PATH_TO_FX") ?: "",
            "--add-modules", "javafx.controls,javafx.fxml"
    )
}

tasks.withType(JavaCompile::class.java) {
    options.encoding = "UTF-8"
}