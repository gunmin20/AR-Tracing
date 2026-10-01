# APK 서명 및 배포

APK는 소스 저장소에 커밋하지 않고 GitHub Releases의 첨부 파일로 배포합니다.
서명키, 비밀번호, `local.properties`는 Git에 추가하지 않습니다.

## 서명키

기존 사용자에게 업데이트를 배포하려면 같은 applicationId와 같은 서명키를 사용하고 `versionCode`를 증가시켜야 합니다.
현재 applicationId는 `com.example.myapplication`입니다.
서명키와 비밀번호는 저장소 밖의 안전한 장소에 별도 백업하세요.

이 프로젝트의 release 서명은 다음 환경변수가 모두 설정되어 있을 때 활성화됩니다.

| 환경변수 | 내용 |
| --- | --- |
| `AR_TRACING_KEYSTORE` | 키스토어 절대 경로 |
| `AR_TRACING_STORE_PASSWORD` | 키스토어 비밀번호 |
| `AR_TRACING_KEY_ALIAS` | 키 별칭 |
| `AR_TRACING_KEY_PASSWORD` | 키 비밀번호 |

비밀번호를 명령 기록이나 저장소 파일에 직접 입력하지 말고 비밀 관리 도구 또는 로컬 보안 입력으로 전달하세요.
`AR_TRACING_KEYSTORE`가 없으면 release 빌드는 서명되지 않습니다. unsigned APK는 배포하지 마세요.

## 빌드와 검증

1. `app/build.gradle.kts`의 `versionCode`, `versionName`을 확인합니다.
2. 서명 환경변수를 설정하고 실행합니다.

```powershell
.\gradlew.bat :app:assembleRelease :app:testDebugUnitTest :app:lintRelease
```

3. Android SDK Build Tools의 `apksigner verify --verbose --print-certs`로 `app/build/outputs/apk/release/app-release.apk`를 검사합니다.
4. 실제 Android 기기에서 최초 카메라 권한 허용/거부, 이미지 선택, 이동·확대·회전, 투명도, 잠금, 십자선을 확인합니다.
5. APK 이름을 `AR-Tracing-v<version>.apk`로 정하고 SHA-256 체크섬 파일을 만듭니다.
6. 소스 커밋에 버전 태그를 붙이고 GitHub Release에 APK와 `SHA256SUMS.txt`를 첨부합니다.

```powershell
Get-FileHash .\AR-Tracing-v1.0.0.apk -Algorithm SHA256
```

릴리스 설명에는 지원 Android 버전, 변경사항, 알려진 제한사항, 실제 기기 테스트 여부를 기재합니다.
테스트용 debug APK와 정식 APK는 서명이 달라 덮어쓰기 설치가 안 될 수 있습니다.
