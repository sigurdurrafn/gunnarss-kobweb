plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
}

group = "com.gunnarss"
version = "1.0-SNAPSHOT"

kotlin {
    jvm {
        tasks.named("jvmJar", Jar::class.java).configure {
            archiveFileName.set("websitekoweb.jar")
        }
    }
    js(IR) {
        moduleName = "websitekoweb"
        browser {
            testTask {
                useKarma {
                    useFirefox()
                }
            }
            commonWebpackConfig {
                outputFileName = "websitekoweb.js"
            }
        }
        binaries.executable()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk.core)
                implementation(libs.kobweb.silk.icons.fa)
             }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.kobweb.api)
             }
        }
    }
}