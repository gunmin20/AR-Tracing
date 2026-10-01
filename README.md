<p align="center">
  <img src="app/src/main/ic_launcher-playstore.png" width="112" height="112" alt="AR Tracing 앱 아이콘 — 연필과 십자선">
</p>

<h1 align="center">AR Tracing</h1>

<p align="center">
  <strong>참고 이미지를 카메라 위에, 그림은 내 손으로.</strong><br>
  카메라 화면에 이미지를 겹쳐 종이에 따라 그리는 Android 트레이싱 앱
</p>

<p align="center">
  <a href="https://github.com/gunmin20/AR-Tracing/releases/latest"><img src="https://img.shields.io/github/v/release/gunmin20/AR-Tracing?color=16877a&amp;label=release" alt="최신 릴리스"></a>
  <img src="https://img.shields.io/badge/Android-7.0%2B-3DDC84?logo=android&amp;logoColor=white" alt="Android 7.0 이상">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&amp;logoColor=white" alt="Kotlin">
</p>

<p align="center">
  <a href="https://github.com/gunmin20/AR-Tracing/releases/latest"><strong>APK 다운로드</strong></a> ·
  <a href="#사용법">사용법</a> ·
  <a href="https://github.com/gunmin20/AR-Tracing/issues/new?template=bug_report.md">문제 제보</a> ·
  <a href="docs/DEVELOPMENT.md">개발 가이드</a>
</p>

---

## 소개

그림 연습에 사용할 간단한 도구를 만들고 싶어서 시작한 개인 프로젝트입니다.
휴대폰을 거치대에 고정하고, 종이를 비추는 카메라 화면 위에 참고 이미지를 올려 윤곽과 비율을 맞춰 보세요.
이미지의 위치·크기·각도·투명도를 조절한 뒤 잠그면 손으로 따라 그리는 데 집중할 수 있습니다.

*An Android camera-overlay app for tracing reference images onto paper. Adjust, lock, and draw by hand.*

## 주요 기능

| 기능 | 할 수 있는 일 |
| --- | --- |
| 카메라 오버레이 | 후면 카메라 미리보기 위에 선택한 이미지 표시 |
| 이미지 배치 | 불러온 이미지를 화면 중앙에 맞추고 드래그로 이동 |
| 크기와 각도 조절 | 두 손가락으로 확대·축소 및 회전 |
| 투명도 조절 | 슬라이더로 참고 이미지와 종이를 함께 보기 |
| 위치 잠금 | 이미지 이동·확대·회전 터치 조작 잠금 |
| 중앙 십자선 | 화면 중심을 확인하는 가이드 표시 |

## 다운로드 및 설치

**[최신 APK 받기 →](https://github.com/gunmin20/AR-Tracing/releases/latest)**

Android **7.0 이상**과 **후면 카메라**가 필요합니다. 설치 파일은 GitHub Releases에서 배포합니다.

1. 릴리스 페이지의 **Assets**에서 `AR-Tracing-v…apk`를 받습니다.
2. 휴대폰에서 APK를 열어 설치합니다. 설치가 차단되면 다운로드에 사용한 앱의 **알 수 없는 앱 설치** 설정을 확인하세요.
3. **AR Tracing**을 실행하고 카메라 권한을 허용합니다.

| 릴리스 파일 | 용도 |
| --- | --- |
| `AR-Tracing-v….apk` | 휴대폰에 설치할 앱 |
| `SHA256SUMS.txt` | APK가 원본과 같은지 확인하는 체크섬. 설치에는 필요하지 않습니다. |
| `Source code (zip / tar.gz)` | 개발자를 위한 해당 버전의 소스 코드 |

## 사용법

1. **고정하기** — 거치대에 휴대폰을 놓고 카메라가 종이를 비추도록 맞춥니다.
2. **불러오기** — 하단의 **이미지 선택**으로 참고 이미지를 고릅니다.
3. **맞추기** — 한 손가락으로 이동하고 두 손가락으로 크기와 각도를 조절합니다.
4. **겹쳐 보기** — 하단 슬라이더로 이미지 투명도를 조절합니다.
5. **잠그고 그리기** — 우측 상단 **≡ → 🔒 잠금**을 누른 뒤 화면을 보며 종이에 그립니다.

**≡ → 🎯 십자선**을 누르면 중앙 가이드가 켜집니다. 잠금과 십자선은 같은 메뉴를 다시 눌러 해제할 수 있습니다.

> 잠금은 이미지에 대한 터치 조작을 막는 기능입니다. 작업 중 휴대폰과 종이의 위치를 유지해 주세요.

## 자주 묻는 질문

<details>
<summary><strong>종이에 이미지를 실제로 투사하나요?</strong></summary>

카메라 화면 위에 2D 이미지를 겹쳐 보여 주는 방식입니다. 화면을 보며 종이에 그립니다.
빛으로 이미지를 투사하거나, ARCore로 공간을 추적해 이미지 위치를 고정하는 기능은 없습니다.

</details>

<details>
<summary><strong>카메라 화면이 나오지 않아요.</strong></summary>

휴대폰 설정에서 AR Tracing의 카메라 권한을 허용한 뒤 앱을 다시 열어 주세요.
계속 문제가 있으면 기기명, Android 버전, 앱 버전과 재현 순서를 [문제 제보](https://github.com/gunmin20/AR-Tracing/issues/new?template=bug_report.md)에 남겨 주세요.

</details>

<details>
<summary><strong>이미지를 움직일 수 없어요.</strong></summary>

메뉴의 **🔒 잠금**이 켜져 있다면 다시 눌러 해제하세요. 잠금 상태에서도 투명도는 조절할 수 있습니다.

</details>

<details>
<summary><strong>기존 앱 위에 APK가 설치되지 않아요.</strong></summary>

이전에 설치한 개발용 APK와 정식 APK의 서명이 다르면 덮어쓰기 설치가 되지 않습니다.
이 경우 기존 앱을 제거하고 정식 APK를 설치해야 하며, 기존 앱 데이터가 삭제될 수 있습니다.
Android 7.0 이상인지도 확인해 주세요.

</details>

<details>
<summary><strong>SHA256SUMS.txt는 어떻게 확인하나요?</strong></summary>

Windows PowerShell에서 다운로드한 APK의 경로를 지정해 아래 명령을 실행합니다.
출력되는 `Hash`를 같은 릴리스의 `SHA256SUMS.txt`에 적힌 값과 비교하세요. 대소문자는 상관없습니다.

```powershell
Get-FileHash .\AR-Tracing-v1.0.0.apk -Algorithm SHA256
```

두 값이 같으면 배포된 APK와 동일한 파일입니다. 이 파일에는 서명키나 비밀번호가 들어 있지 않습니다.

</details>

## 권한과 데이터

- **카메라:** 실시간 미리보기에 사용합니다.
- **참고 이미지:** 사용자가 선택한 이미지를 앱 화면에 표시합니다.
- 현재 앱 코드에는 사진 촬영·저장·업로드, 로그인, 광고, 분석 서비스가 없습니다.
- 배포 APK에 인터넷 권한이 없습니다. 기기에 저장된 이미지를 이용한 트레이싱은 인터넷 연결 없이 사용할 수 있습니다.

## 현재 버전의 범위

화면 회전이나 앱 재시작 시 이미지와 조정 상태를 복원하지 않습니다.
촬영·그림 저장·실행 취소·자동 윤곽선 추출은 아직 지원하지 않습니다.

`v1.0.0`은 빌드와 APK 서명 검증을 통과했으며 Android Lint 결과는 오류 0개, 경고 33개입니다.
기존 예제 단위 테스트는 통과했지만 카메라·제스처 동작을 검증하는 테스트는 아닙니다.
**해당 배포 APK의 실기기 테스트는 아직 수행하지 않았습니다.**

## 개발 및 피드백

- [개발 가이드](docs/DEVELOPMENT.md) — 환경 설정, 빌드 명령, 소스 구조
- [배포 가이드](docs/RELEASING.md) — 정식 서명 및 APK 배포
- [변경 기록](CHANGELOG.md) — 버전별 변경사항
- [문제 제보 및 기능 제안](https://github.com/gunmin20/AR-Tracing/issues) — 기기 정보와 재현 순서를 함께 알려 주세요.

## 제작 배경

ChatGPT의 도움을 받아 Android Studio에서 개발한 개인 프로젝트입니다.
앱 아이콘을 직접 제작하고 이름과 아이콘을 적용해 동작을 확인한 뒤, GitHub에 소스와 APK를 공개했습니다.

## 라이선스

별도의 오픈소스 라이선스는 아직 지정하지 않았습니다.
