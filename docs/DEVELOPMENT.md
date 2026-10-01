# 개발 가이드

- Kotlin / Android Views(XML), CameraX
- JDK 21, Gradle 9.3.1, Android Gradle Plugin 9.1.0
- Android SDK Platform 36.1, `minSdk 24`, `targetSdk 36`

```bash
git clone https://github.com/gunmin20/AR-Tracing.git
cd AR-Tracing
```

Android Studio에서 프로젝트를 열고 SDK를 설치한 뒤 Gradle 동기화를 실행하세요.
SDK 경로는 로컬 `local.properties`의 `sdk.dir` 또는 `ANDROID_HOME`으로 지정합니다.

```powershell
# Windows
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest :app:lintDebug
```

```bash
# macOS / Linux
bash ./gradlew :app:assembleDebug :app:testDebugUnitTest :app:lintDebug
```

테스트용 APK: `app/build/outputs/apk/debug/app-debug.apk`

정식 APK의 서명과 배포 절차는 [배포 가이드](RELEASING.md)를 참고하세요.

## 프로젝트 구성

```text
app/src/main/
├── java/com/example/myapplication/MainActivity.kt  # 카메라·이미지·제스처
├── res/layout/activity_main.xml                   # 메인 화면
├── res/menu/menu_main.xml                         # 잠금·십자선 메뉴
└── AndroidManifest.xml                            # 앱·권한 설정
docs/RELEASING.md                                  # 서명·배포 가이드
```

