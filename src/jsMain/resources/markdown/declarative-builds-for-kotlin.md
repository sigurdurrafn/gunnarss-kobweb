# Declarative builds for Kotlin

### Alternative title: Declarative Gradle? Amper? What’s going on?!

We Kotlin developers have it pretty good as it comes to configuring and executing our builds. We usually work with nice IDEs that help setting projects up for us, and a pretty robust build system in Gradle. Gradle gives us a nice way to import dependencies, compile and package the project. It also helps with running tests as a bonus! 

Not all languages have such a robust and "standardized" system. In Python, dependencies are declared by writing them into a `requirements.txt` file. Then you use `pip` to install everything into your (hopefully virtual) python environment and hope there are no dependency conflicts. Since there will be conflicts, you can write dependency versions into this `.txt` file based on the complaints from `pip` for a while before deciding maybe AI isn't for you.

Back to Kotlin! For a simple project it's pretty easy to read the Groovy based `build.gradle` build files. Since you can now instead write `build.gradle.kts` in Kotlin, build files are also easy to write!

> #### Kotlin application `build.gradle.kts`

``` kotlin
plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.someDependency) // Actually comes from a yaml file, but let's keep things simple
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8)) // Java 8 is good, right?
    }
}

application {
    mainClass.set("MainClassFile")
}

tasks.register("funTask") {
    mContext++
    compileCode()
    GlobalScope.launch {
        while (true){
            delay(100)
            println("Hi there :)")
        }
    }
}
```

As nice and flexible as it is to be able to write our build configuration in our favourite programming language, that comes at a cost. When we write custom build logic in our build files, Gradle is not able to statically analyze our build files. This limits how much our IDEs can help us, and can impact build configuration and build times.

We can help by following best practices:
 - Only put declarative software definitions into build scripts
 - Put all your custom build logic into Gradle plugins

Unfortunately, there’s nothing preventing you from writing tasks in an inefficient way. There's a large amount of projects out there where gnarly build logic is peppered around the build scripts. It's also not always obvious if your "declarative" build code really is that. Did your task correctly declare inputs and outputs? Was your code really a "software definition" or did you jst write some "build logic"? The keen eyed may have noticed some build logic in the above example.

### Enter “Declarative Gradle”

The good folks at Gradle are aware of this, and have started work on a new kind of gradle script file. One that only supports limited subset of build configuration functionality. This, in theory, will ensure that build logic is safely tucked away in .kt files in your build plugins. Although this work is still in a prototyping phase, let's look at an example of a kotlin multiplatform application that can run on the JVM and Mac. 


> #### Kotlin application `build.gradle.dcl`
``` kotlin
kotlinApplication {
    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
    }

    targets {
        jvm {
            jdkVersion = 17
            mainClass = "com.example.AppKt"
            dependencies {
                implementation("org.apache.commons:commons-lang3:3.14.0")
            }
        }
        macOsArm64 {
            entryPoint = "com.example.main"
        }
    }
}
```

Since `.dcl files are fully declarative, Gradle could easily and quickly parse them. The IDE would be able to improve things like auto completion, refactoring and documentation quick access. Think moving from Groovy build files to Kotlin, but even better!. It would even be possible to edit entire build files with a graphical interface, like with android’s xml resource files. 

### Amper

Can we go even further? In the introduction, Python’s `requirements.txt` file got some criticism. But what if our project setup was as simple as that? If build configuration was a simple text file? Can we skip the `/src/jsMain/kotlin/com/example/my/code` folder structure that scares new developers? 

This can be done. The Cargo build system for Rust is very approachable, and gives new developers very little to think about except writing code and arguing with the borrow checke

Would this work in Kotlin? That’s the question that the Amper project from JetBrains is trying to answer. 

Currently, the Amper project is seen as a build configuration tool only, and the current implementation is built upon Gradle. 

The syntax is fully original and the file . Current prototypes use yml, which not everyone loves, but whatever they land on, the goal seems to be to make things even simpler. Were they inspired by Rust Crates?

Let's look at an example of a minimal jvm only project:

> ### Kotlin jvm project
``` yaml
product: jvm/app

dependencies:
  - org.jetbrains.kotlinx:kotlinx-datetime:0.6.0

test-dependencies:
  - org.jetbrains.kotlin:kotlin-test:1.9.0

settings:
  kotlin:
    languageVersion: 1.8
  java:
    source: 17
  jvm:
    target: 17
```

#### Further reading

 - https://blog.jetbrains.com/blog/2023/11/09/amper-improving-the-build-tooling-user-experience/
 - https://github.com/JetBrains/amper
 - https://github.com/gradle/declarative-gradle/