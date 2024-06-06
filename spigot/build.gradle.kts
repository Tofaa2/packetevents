plugins {
    packetevents.`library-conventions`
    packetevents.`shadow-conventions`
    alias(libs.plugins.run.paper)
}

repositories {
    maven("https://jitpack.io")
    maven("https://repo.viaversion.com/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly(libs.netty)
    api(project(":api", "shadow"))
    api(project(":netty-common"))

    compileOnly(libs.spigot)
    compileOnly(libs.via.version)
    compileOnly(libs.protocol.support)
}

tasks {
    runServer {
        minecraftVersion("1.19.4")
        outputs.upToDateWhen { false }
    }
}
