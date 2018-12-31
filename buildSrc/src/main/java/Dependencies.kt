import Versions.androidSupportRulesVersion
import Versions.androidSupportRunnerVersion
import Versions.androidxAnnotationsVersion
import Versions.annotationProcessorVersion
import Versions.assertJVersion
import Versions.constraintLayoutVersion
import Versions.daggerVersion
import Versions.espressoVersion
import Versions.firebaseAuthVersion
import Versions.firebaseCoreVersion
import Versions.glassfishAnnotationVersion
import Versions.glideVersion
import Versions.gmsLocationVersion
import Versions.googleMapsVersion
import Versions.googlePlacesVersion
import Versions.gsonVersion
import Versions.hdodenhofVersion
import Versions.jUnitVersion
import Versions.javaxAnnotationVersion
import Versions.javaxInjectVersion
import Versions.kotlinVersion
import Versions.kotlinktxVersion
import Versions.legacySupportVersion
import Versions.lifecycleVersion
import Versions.lottieVersion
import Versions.luhnVersion
import Versions.materialDesignVersion
import Versions.mockitoAndroidVersion
import Versions.mockitoKotlinVersion
import Versions.multidexVersion
import Versions.okHttpVersion
import Versions.parcelerVersion
import Versions.retrofitVersion
import Versions.robolectricVersion
import Versions.roomVersion
import Versions.rxAndroidVersion
import Versions.rxJavaVersion
import Versions.rxKotlinVersion
import Versions.rxRelayVersion
import Versions.timberVersion

object Versions {

    const val versionCode = 1
    const val versionName = "1.0.0"
    const val androidBuildToolsVersion = "28.0.3"
    const val androidMinSdkVersion = 19
    const val androidTargetSdkVersion = 28
    const val androidCompileSdkVersion = 28
    const val kotlinVersion = "1.3.11"

    //Versions
    const val legacySupportVersion = "1.0.0"
    const val materialDesignVersion = "1.0.0-rc01"
    const val rxJavaVersion = "2.0.2"
    const val javaxAnnotationVersion = "1.0"
    const val javaxInjectVersion = '1'
    const val rxKotlinVersion = "2.3.0"
    const val rxAndroidVersion = "2.1.0"
    const val androidxAnnotationsVersion = "1.0.0"
    const val daggerVersion = "2.15"
    const val gsonVersion = "2.8.1"
    const val okHttpVersion = "3.11.0"
    const val retrofitVersion = "2.4.0"
    const val timberVersion = "4.7.1"
    const val glideVersion = "4.8.0"
    const val glassfishAnnotationVersion = "10.0-b28"
    const val archCompVersion = "2.0.0"
    const val roomVersion = "2.1.0-alpha02"
    const val lifecycleVersion = "2.0.0"
    const val roomRxJavaVersion = "1.0.0"
    const val constraintLayoutVersion = "1.1.3"
    const val annotationProcessorVersion = "1.1"
    const val lottieVersion = "2.5.0-rc1"
    const val hdodenhofVersion = "2.2.0"
    const val kotlinktxVersion = "1.0.0"
    const val luhnVersion = "2.1.3"
    const val googleMapsVersion = "16.0.0"
    const val firebaseCoreVersion = "16.0.6"
    const val firebaseAuthVersion = "16.1.0"
    const val multidexVersion = "1.0.3"
    const val gmsLocationVersion = "16.0.0"
    const val parcelerVersion = "1.1.11"
    const val googlePlacesVersion = "16.0.0"
    const val rxRelayVersion = "2.1.0"

    //Testing Versions
    const val jUnitVersion = "4.12"
    const val assertJVersion = "3.8.0"
    const val mockitoKotlinVersion = "2.0.0-RC3"
    const val espressoVersion = "3.1.0-alpha3"
    const val robolectricVersion = "3.8"
    const val mockitoVersion = "2.21.0"
    const val mockitoAndroidVersion = "2.8.9"
    const val androidSupportRunnerVersion = "1.1.0-alpha3"
    const val androidSupportRulesVersion = "1.1.0-alpha3"
    const val dexmakerMockitoversion = "2.2.0"
    const val runnerVersion = "1.1.0-alpha3"
}


object Libraries {
    const val javaxAnnotation = "javax.annotation:jsr250-api:$javaxAnnotationVersion"
    const val javaxInject = "javax.inject:javax.inject:$javaxInjectVersion"
    const val rxJava = "io.reactivex.rxjava2:rxkotlin:$rxJavaVersion"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:$rxKotlinVersion"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    const val dagger = "com.google.dagger:dagger:$daggerVersion"
    const val gson = "com.google.code.gson:gson:$gsonVersion"
    const val androidAnnotations = "androidx.annotation:annotation:$androidxAnnotationsVersion"
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomRxJava = "androidx.room:room-rxjava2:$roomVersion"
    const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
    const val okHttpLogger = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:$rxAndroidVersion"
    const val archRuntime = "androidx.lifecycle:lifecycle-runtime:$lifecycleVersion"
    const val archExtensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
    const val archCompiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
    const val androidSupportV4 = "androidx.legacy:legacy-support-v4:$legacySupportVersion"
    const val androidSupportV13 = "androidx.legacy:legacy-support-v13:$legacySupportVersion"
    const val appCompatV7 = "androidx.appcompat:appcompat:$legacySupportVersion"
    const val supportRecyclerView = "androidx.recyclerview:recyclerview:$legacySupportVersion"
    const val supportDesign = "com.google.android.material:material:$materialDesignVersion"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    const val timber = "com.jakewharton.timber:timber:$timberVersion"
    const val daggerAndroid = "com.google.dagger:dagger-android:$daggerVersion"
    const val daggerSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"
    const val glassfishAnnotation = "org.glassfish:javax.annotation:$glassfishAnnotationVersion"
    const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"
    const val annotationProcessor = "com.google.auto.value:auto-value:$annotationProcessorVersion"
    const val lottie = "com.airbnb.android:lottie:$lottieVersion"
    const val junit = "junit:junit:$jUnitVersion"
    const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion"
    const val assertj = "org.assertj:assertj-core:$assertJVersion"
    const val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
    const val robolectric = "org.robolectric:robolectric:$robolectricVersion"
    const val roomTesting = "androidx.room:room-testing:$roomVersion"
    const val archTesting = "androidx.arch.core:core-testing:$lifecycleVersion"
    const val supportRunner = "androidx.test:runner:$androidSupportRunnerVersion"
    const val supportRules = "androidx.test:rules:$androidSupportRulesVersion"
    const val mockitoAndroid = "org.mockito:mockito-android:$mockitoAndroidVersion"
    const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:$espressoVersion"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:$espressoVersion"
    const val hdodenhofCirleImage = "de.hdodenhof:circleimageview:$hdodenhofVersion"
    const val kotlinktx = "androidx.core:core-ktx:$kotlinktxVersion"
    const val luhnCardEntry = "xyz.belvi.validator:luhn:$luhnVersion"
    const val googleMaps = "com.google.android.gms:play-services-maps:$googleMapsVersion"
    const val firebaseCore = "com.google.firebase:firebase-core:$firebaseCoreVersion"
    const val firebaseAuth = "com.google.firebase:firebase-auth:$firebaseAuthVersion"
    const val multidex = "com.android.support:multidex:$multidexVersion"
    const val gmsLocation = "com.google.android.gms:play-services-location:$gmsLocationVersion"
    const val parceler = "org.parceler:parceler-api:$parcelerVersion"
    const val googlePlaces = "com.google.android.gms:play-services-places:$googlePlacesVersion"
    const val rxRelay = "com.jakewharton.rxrelay2:rxrelay:$rxRelayVersion"
}


object DomainDependencies {
    const val javaxAnnotation = Libraries.javaxAnnotation
    const val javaxInject = Libraries.javaxInject
    const val rxJava = Libraries.rxJava
    const val parceler = Libraries.parceler

}

object DomainTestDependencies {
    const val junit = Libraries.junit
    const val mockito = Libraries.mockito
    const val assertj = Libraries.assertj
}


object DataDependencies {
    const val rxKotlin = Libraries.rxKotlin
    const val kotlin = Libraries.kotlin
    const val javaxAnnotation = Libraries.javaxAnnotation
    const val javaxInject = Libraries.javaxInject
    const val firebaseAuth = Libraries.firebaseAuth
    const val firebaseCore = Libraries.firebaseCore

}

object DataTestDependencies {
    const val junit = Libraries.junit
    const val kotlinJUnit = Libraries.kotlinJUnit
    const val assertj = Libraries.assertj
    const val mockito = Libraries.mockito
    const val robolectric = Libraries.robolectric
}

object CacheDependencies {
    const val daggerCompiler = Libraries.daggerCompiler
    const val dagger = Libraries.dagger
    const val gson = Libraries.gson
    const val rxKotlin = Libraries.rxKotlin
    const val kotlin = Libraries.kotlin
    const val javaxAnnotation = Libraries.javaxAnnotation
    const val javaxInject = Libraries.javaxInject
    const val androidAnnotations = Libraries.androidAnnotations
    const val roomRuntime = Libraries.roomRuntime
    const val roomCompiler = Libraries.roomCompiler
    const val roomRxJava = Libraries.roomRxJava
}

object CacheTestDependencies {
    const val junit = Libraries.junit
    const val kotlinJUnit = Libraries.kotlinJUnit
    const val robolectric = Libraries.robolectric
    const val assertj = Libraries.assertj
    const val mockito = Libraries.mockito
    const val roomTesting = Libraries.roomTesting
    const val archTesting = Libraries.archTesting
    const val supportRunner = Libraries.supportRunner
    const val supportRules = Libraries.supportRules
}

object RemoteDependencies {
    const val gson = Libraries.gson
    const val rxKotlin = Libraries.rxKotlin
    const val kotlin = Libraries.kotlin
    const val javaxAnnotation = Libraries.javaxAnnotation
    const val javaxInject = Libraries.javaxInject
    const val androidAnnotations = Libraries.androidAnnotations
    const val okHttp = Libraries.okHttp
    const val okHttpLogger = Libraries.okHttpLogger
    const val retrofit = Libraries.retrofit
    const val retrofitConverter = Libraries.retrofitConverter
    const val retrofitAdapter = Libraries.retrofitAdapter
    const val firebaseAuth = Libraries.firebaseAuth
    const val firebaseCore = Libraries.firebaseCore

}

object RemoteTestDependencies {
    const val junit = Libraries.junit
    const val kotlinJUnit = Libraries.kotlinJUnit
    const val assertj = Libraries.assertj
    const val mockito = Libraries.mockito
}

object PresentationDependencies {
    const val daggerCompiler = Libraries.daggerCompiler
    const val dagger = Libraries.dagger
    const val rxKotlin = Libraries.rxKotlin
    const val kotlin = Libraries.kotlin
    const val rxAndroid = Libraries.rxAndroid
    const val javaxAnnotation = Libraries.javaxAnnotation
    const val javaxInject = Libraries.javaxInject
    const val androidAnnotations = Libraries.androidAnnotations
    const val archRuntime = Libraries.archRuntime
    const val archExtensions = Libraries.archExtensions
    const val archCompiler = Libraries.archCompiler
    const val roomRxJava = Libraries.roomRxJava
    const val retrofit = Libraries.retrofit
    const val firebaseAuth = Libraries.firebaseAuth
    const val firebaseCore = Libraries.firebaseCore

}

object PresentationTestDependencies {
    const val junit = Libraries.junit
    const val kotlinJUnit = Libraries.kotlinJUnit
    const val assertj = Libraries.assertj
    const val mockito = Libraries.mockito
    const val robolectric = Libraries.robolectric
    const val archTesting = Libraries.archTesting
}

object MobileUiDependencies {
    const val daggerCompiler = Libraries.daggerCompiler
    const val dagger = Libraries.dagger
    const val rxKotlin = Libraries.rxKotlin
    const val rxAndroid = Libraries.rxAndroid
    const val glide = Libraries.glide
    const val glideCompiler = Libraries.glideCompiler
    const val kotlin = Libraries.kotlin
    const val javaxAnnotation = Libraries.javaxAnnotation
    const val javaxInject = Libraries.javaxInject
    const val androidAnnotations = Libraries.androidAnnotations
    const val androidSupportV4 = Libraries.androidSupportV4
    const val androidSupportV13 = Libraries.androidSupportV13
    const val appCompatV7 = Libraries.appCompatV7
    const val supportRecyclerView = Libraries.supportRecyclerView
    const val supportDesign = Libraries.supportDesign
    const val constraintLayout = Libraries.constraintLayout
    const val timber = Libraries.timber
    const val daggerSupport = Libraries.daggerSupport
    const val daggerProcessor = Libraries.daggerProcessor
    const val glassfishAnnotation = Libraries.glassfishAnnotation
    const val roomRuntime = Libraries.roomRuntime
    const val roomCompiler = Libraries.roomCompiler
    const val roomRxJava = Libraries.roomRxJava
    const val annotationProcessor = Libraries.annotationProcessor
    const val lottie = Libraries.lottie
    const val hdodenhofCirleImage = Libraries.hdodenhofCirleImage
    const val kotlinKtx = Libraries.kotlinktx
    const val daggerAndroid = Libraries.daggerAndroid
    const val luhnCardEntry = Libraries.luhnCardEntry
    const val googleMaps = Libraries.googleMaps
    const val gson = Libraries.gson
    const val firebaseCore = Libraries.firebaseCore
    const val firebaseAuth = Libraries.firebaseAuth
    const val multidex = Libraries.multidex
    const val gmsLocation = Libraries.gmsLocation
    const val parceler = Libraries.parceler
    const val googlePlaces = Libraries.googlePlaces
    const val rxRelay = Libraries.rxRelay
}

object MobileUiTestDependencies {
    const val junit = Libraries.junit
    const val kotlinJUnit = Libraries.kotlinJUnit
    const val assertj = Libraries.assertj
    const val mockito = Libraries.mockito
    const val supportRunner = Libraries.supportRunner
    const val supportRules = Libraries.supportRules
    const val mockitoAndroid = Libraries.mockitoAndroid
    const val espressoCore = Libraries.espressoCore
    const val espressoIntents = Libraries.espressoIntents
    const val espressoContrib = Libraries.espressoContrib
}