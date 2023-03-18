plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "net.nekozouneko.skript"
version = "2.2b"

repositories {
    mavenCentral()
    mavenLocal()

    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")

    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.destroystokyo.com/repository/maven-public/")
    maven("https://repo.skriptlang.org/releases/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("net.md-5:bungeecord-chat:1.19-R0.1-SNAPSHOT")

    // Lib
    implementation(platform("com.intellectualsites.bom:bom-1.18.x:1.25"))
    implementation("org.json:json:20230227")

    // Skript
    compileOnly("com.github.SkriptLang:Skript:2.7.0-beta1")

    // FastAsyncWorldEdit
    compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Core")
    compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Bukkit") {
        isTransitive = false
    }

    // PlotSquared
    compileOnly("com.plotsquared:PlotSquared-Core")
    compileOnly("com.plotsquared:PlotSquared-Bukkit")
}

tasks.withType<ProcessResources> {
    filesMatching("*") {
        expand(project.properties)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}