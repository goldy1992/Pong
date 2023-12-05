plugins {
    application
}

application {
    mainClass.set("com.github.goldy1992.Main")
}

dependencies {
    // https://mvnrepository.com/artifact/org.jogamp.jogl/jogl-all
    implementation("org.jogamp.jogl:jogl-all:2.3.2")
// https://mvnrepository.com/artifact/org.jogamp.gluegen/gluegen-rt-main
    implementation("org.jogamp.gluegen:gluegen-rt-main:2.3.2")
//// https://mvnrepository.com/artifact/org.jogamp.gluegen/gluegen-rt-natives-windows-amd64
//    // https://mvnrepository.com/artifact/org.jogamp.gluegen/gluegen-rt-natives-windows-amd64
//    implementation("org.jogamp.gluegen:gluegen-rt-natives-windows-amd64:v2.4.0-rc4")
//
//}
// https://mvnrepository.com/artifact/org.jogamp.jogl/jogl-all-natives-windows-amd64
    implementation("org.jogamp.jogl:jogl-all-natives-windows-amd64:2.4.0-rc-20210111")



}

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.jzy3d.org/releases")
    }
    maven {
        url = uri("https://maven.scijava.org/content/repositories/public")
    }
}
