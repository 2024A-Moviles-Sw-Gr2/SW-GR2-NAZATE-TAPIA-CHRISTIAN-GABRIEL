buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.3")
        classpath("com.google.gms:google-services:4.4.1")
        classpath("com.google.firebase:firebase-appdistribution-gradle:4.2.0")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.firebase.appdistribution") version "5.0.0" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
}