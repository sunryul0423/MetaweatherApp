# MetaweatherApp
MetaWeather의 Open_API를 활용한 날씨검색 App
-
- 지역 이름에 "se"가 포함된 지역을 대상으로 오늘, 내일 날씨를 보여줍니다.
- 날씨 정보는 지역 이름, 날씨 아이콘, 날씨 요약, 현재 온도, 습도로 구성됩니다.

### [1] Build 환경 및 개발 **( MVVM 패턴 )**
* 안드로이드 스튜디오 3.4.1 
* Gradle 5.1.1
* Kotlin 1.3.40
* JDK 1.8
* target, compileSdkVersion 28
* minSdkVersion 21

### [2] 사용라이브러리
* DataBinding
* AAC
* RectiveX
* Koin
* Glide 4
* Retrofit2
<pre>
<code>   
    // android_X
    implementation 'androidx.appcompat:appcompat:1.0.2'
    implementation 'androidx.core:core-ktx:1.0.2'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.recyclerview:recyclerview:1.0.0'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0-alpha01'
    androidTestImplementation 'androidx.test:runner:1.2.0'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    testImplementation 'junit:junit:4.12'

    // Rx
    implementation 'io.reactivex.rxjava2:rxjava:2.2.10'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    implementation 'com.jakewharton.rxbinding2:rxbinding:2.2.0'

    // Koin
    implementation 'org.koin:koin-android:2.0.1'
    implementation 'org.koin:koin-androidx-scope:2.0.1'
    implementation 'org.koin:koin-androidx-viewmodel:2.0.1'
    testImplementation 'org.koin:koin-test:2.0.1'

    // Glide
    implementation 'com.github.bumptech.glide:glide:4.9.0'
    kapt 'com.github.bumptech.glide:compiler:4.9.0'
    implementation 'com.github.bumptech.glide:okhttp3-integration:4.9.0'

    // Http
    implementation 'com.google.code.gson:gson:2.8.5'
    implementation 'com.squareup.retrofit2:retrofit:2.6.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.6.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.0.0'
</code>
</pre>
