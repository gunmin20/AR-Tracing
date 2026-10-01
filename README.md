# AR Tracing

카메라 화면 위에 참고 이미지를 겹쳐 놓고 그림을 따라 그릴 수 있는 Android 앱입니다.
종이와 휴대폰의 위치를 고정한 뒤 이미지의 크기·각도·투명도를 조절해 사용하세요.

[APK 다운로드](https://github.com/gunmin20/AR-Tracing/releases/latest) · [문제 제보](https://github.com/gunmin20/AR-Tracing/issues)

## 주요 기능

- 후면 카메라 실시간 미리보기
- 기기에서 참고 이미지 선택 및 화면 중앙 배치
- 한 손가락으로 이미지 이동
- 두 손가락으로 확대·축소 및 회전
- 슬라이더로 이미지 투명도 조절
- 메뉴에서 이미지 위치 잠금 및 중앙 십자선 표시

이 앱은 카메라 미리보기 위에 2D 이미지를 표시합니다. ARCore 기반의 공간 추적이나 벽·종이에 이미지를 고정하는 기능은 없습니다.

## 설치

1. [최신 릴리스](https://github.com/gunmin20/AR-Tracing/releases/latest)의 **Assets**에서 `.apk` 파일을 다운로드합니다.
2. Android 기기에서 APK를 열고, 필요하면 다운로드에 사용한 앱의 ‘알 수 없는 앱 설치’를 허용합니다.
3. 앱을 실행하고 카메라 권한을 허용합니다.

**지원 환경:** Android 7.0(API 24) 이상, 후면 카메라가 있는 기기.
APK는 Google Play가 아닌 이 저장소의 GitHub Releases에서 배포합니다.
릴리스의 `SHA256SUMS.txt`로 다운로드 파일의 체크섬을 확인할 수 있습니다.

## 사용 방법

1. 휴대폰을 거치대에 고정하고 카메라가 종이를 비추도록 배치합니다.
2. **이미지 선택**을 눌러 참고 이미지를 불러옵니다.
3. 드래그와 두 손가락 제스처로 이미지의 위치·크기·각도를 맞춥니다.
4. 하단 슬라이더로 종이와 이미지가 함께 보이도록 투명도를 조절합니다.
5. 우측 상단 **≡ → 🔒 잠금**으로 이미지 이동을 막고 따라 그립니다. 다시 누르면 해제됩니다.
6. **≡ → 🎯 십자선**으로 중앙 정렬선을 켜거나 끕니다.

잠금은 이미지 터치 조작만 막습니다. 휴대폰이 움직이면 카메라 구도도 바뀝니다.

## 권한 및 데이터

- 카메라 권한은 실시간 미리보기에 사용합니다.
- 선택한 이미지를 화면에 표시합니다. 현재 앱 코드에는 사진 촬영·저장·업로드 기능이 없습니다.
- 앱 매니페스트에는 인터넷 권한이 없으며, 현재 앱 코드에 로그인·광고·분석 서비스가 없습니다.

## 개발 환경과 빌드

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

정식 APK의 서명과 배포 절차는 [배포 가이드](docs/RELEASING.md)를 참고하세요.

## 프로젝트 구성

```text
app/src/main/
├── java/com/example/myapplication/MainActivity.kt  # 카메라·이미지·제스처
├── res/layout/activity_main.xml                   # 메인 화면
├── res/menu/menu_main.xml                         # 잠금·십자선 메뉴
└── AndroidManifest.xml                            # 앱·권한 설정
docs/RELEASING.md                                  # 서명·배포 가이드
```

## 현재 제한사항

- 화면 회전이나 앱 재시작 시 선택 이미지와 조정 상태를 복원하지 않습니다.
- 촬영, 그림 저장, 실행 취소, 공간 추적 기능은 없습니다.
- 실제 기기별 카메라·갤러리 동작은 차이가 있을 수 있습니다. 문제 제보 시 기기명, Android 버전, 재현 순서를 함께 남겨 주세요.
- 별도의 오픈소스 라이선스는 아직 지정하지 않았습니다.
