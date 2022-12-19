import org.jetbrains.kotlin.gradle.targets.js.KotlinJsTarget

plugins {
  id("org.jetbrains.kotlin.multiplatform")
  id("apollo.test")
  id("com.apollographql.apollo3")
}

apolloTest {
  mpp {
    withJs.set(false)
  }
}

kotlin {
  js(IR) {
    binaries.executable()
    browser {
      commonWebpackConfig {
        cssSupport.enabled = true
      }
    }
  }

  sourceSets {
    findByName("commonMain")?.apply {
      dependencies {
        implementation(golatac.lib("apollo.runtime"))
      }
    }

    findByName("jsMain")?.apply {
//      dependencies {
//        implementation(libs.kotlin.stdlib)
//        implementation(libs.kotlinx.html)
//      }
    }
  }
}

apollo {
  service("rocketreserver") {
    packageName.set("browser")
  }
}

rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
  versions.webpackCli.version = "4.10.0"
}


tasks.register("checkForJsIr") {
  doLast {
    val hasLegacyJsTarget = kotlin.targets.any { it is KotlinJsTarget && it.irTarget == null }
    println(hasLegacyJsTarget)
//    println(kotlin.targets.getByName("js") is KotlinJsIrTarget)
  }
}
