plugins {
  id("org.jetbrains.kotlin.multiplatform")
  id("com.apollographql.apollo3")
}

apolloTest()

kotlin {
  sourceSets {
    findByName("commonMain")?.apply {
      dependencies {
        implementation(libs.apollo.runtime)
        implementation(libs.apollo.normalizedcache)
        implementation(libs.apollo.adapters)
      }
    }

    findByName("commonTest")?.apply {
      dependencies {
        implementation(libs.apollo.testingsupport)
        implementation(libs.apollo.mockserver)
      }
    }
  }
}

apollo {
  service("service") {
    srcDir(file("../models-fixtures/graphql"))
    packageName.set("codegen.models")
    generateFragmentImplementations.set(true)
    mapScalar("Date", "kotlin.Long")
    codegenModels.set("responseBased")
    sealedClassesForEnumsMatching.set(setOf("StarshipType"))
    languageVersion.set("1.5")
  }
}
