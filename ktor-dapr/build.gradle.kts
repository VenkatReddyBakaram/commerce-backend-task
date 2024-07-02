
val kotlin_version: String by project
val logback_version: String by project
val koinVersion : String by project
val ktor_version: String by project
val daprVersion: String by project

plugins {
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "2.3.12"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"
}

group = "com.tdl"
version = "0.0.1"

application {
    mainClass.set("com.tdl.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.dapr:dapr-sdk:$daprVersion")

    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-status-pages-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    implementation("redis.clients:jedis:3.6.3")
    implementation("io.ktor:ktor-server-cio:$ktor_version")
    implementation("org.litote.kmongo:kmongo-coroutine:4.6.0")
    implementation("io.insert-koin:koin-ktor:$koinVersion")
    implementation("io.ktor:ktor-serialization-gson-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    testImplementation("io.mockk:mockk:1.12.0")
}
