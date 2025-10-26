plugins {
    id("java")
}

group = "com.alihaine.testpacket"
version = "1.0"

repositories {
    mavenCentral()
    maven {
        name = "aikar"
        url = uri("https://repo.aikar.co/content/groups/aikar/")
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")

}