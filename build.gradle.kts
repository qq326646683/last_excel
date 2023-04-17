import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("org.apache.poi:poi:4.0.1")
                implementation("org.apache.poi:poi-ooxml:4.0.1")
                implementation("com.alibaba:easyexcel:3.2.1")
                implementation("com.google.code.gson:gson:2.8.5")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "LXN私人助手"
            packageVersion = "1.0.0"
            macOS {
                iconFile.set(project.file("launcher/icon.icns"))
            }
            windows {
                iconFile.set(project.file("launcher/icon.ico"))
            }
        }
    }
}
