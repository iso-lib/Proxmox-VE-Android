[English](README.md) | [简体中文](README.zh-CN.md) | [Deutsch](README.de.md) | [Español](README.es.md) | [Français](README.fr.md) | [日本語](README.ja.md) | **한국어**

# Proxmox-VE-Android

> 본 프로젝트는 업스트림 [5p00kyy/Proxmox-VE-Mobile](https://github.com/5p00kyy/Proxmox-VE-Mobile)에서 파생되었으며, 독립적인 새 프로젝트로 저장소를 새로 만들었고 더 이상 해당 업스트림의 fork이 아닙니다. 업스트림 코드를 기반으로, 본 프로젝트에는 다음이 포함됩니다: 10개 언어 번역(영어, 독일어, 스페인어, 간체 중국어, 프랑스어, 이탈리아어, 포르투갈어, 러시아어, 일본어, 한국어) 및 로그인 화면 언어 전환, 접을 수 있는 목록 카드·간결한 시스템 상태 카드·가로 퀵 액션·정리된 VM/LXC 세부 작업 등의 인터페이스 개선, LXC 게스트(xterm.js)와 VM 게스트(noVNC)를 위한 가벼운 WebView 게스트 콘솔, 다크/라이트 테마·새로 고침 간격·자동 로그인·문제 보고 등 실제로 동작하는 설정, GitHub Releases로 배포되는 정식 서명된 안정 버전 1.0.0(앞으로는 버전 태그 푸시 시 자동 릴리스), 그리고 Proxmox VE Android로의 이름 변경。

`Proxmox VE Android`은 휴대폰이나 태블릿에서 Proxmox VE 환경을 탐색하고 운영하기 위한 Android 클라이언트입니다.

현재 베타 라인은 일반적인 모바일 운영자 워크플로에 중점을 두고 있습니다. 로그인, 안전한 연결 처리, 대시보드 가시성, 노드 탐색, VM/LXC 수명 주기 작업, 작업 추적, 그리고 읽기 전용 인프라 점검이 여기에 해당합니다. 이 앱은 화면 소유 Retrofit 호출에서 저장소 기반 기능 슬라이스로 이동 중인 단일 모듈 Kotlin/Jetpack Compose 앱입니다. 이 README는 의도된 최종 상태가 아니라 현재 저장소의 모습을 설명합니다.

## 1.0.0의 새로운 기능

첫 베타 버전과 비교하면 1.0.0은 첫 안정 버전입니다: [1.0.0](https://github.com/iso-lib/Proxmox-VE-Android/releases/tag/v1.0.0)。

- **첫 안정 버전.** 버전 1.0.0(versionCode 4), GitHub Releases를 통해 배포되며 프로젝트 릴리스 키로 서명되었습니다. 앞으로 버전 태그(`v*`)를 푸시하면 자동으로 릴리스됩니다.
- **게스트 콘솔.** LXC 게스트(xterm.js)와 VM 게스트(noVNC)를 위한 가벼운 WebView 콘솔로, Proxmox 자체 콘솔 페이지를 로드합니다. 비밀번호 세션은 세션 티켓 쿠키로 인증합니다.
- **실제로 동작하는 설정.** 다크/라이트 테마, 대시보드·VM·컨테이너·작업 목록의 10~60초 새로 고침 간격, 저장된 자격 증명으로 자동 로그인, GitHub Issues 문제 보고 링크. 생체 인증과 알림 스위치는 구현이 없는 자리 표시자였으므로 제거되었습니다.
- **현지화.** 앱은 10개 언어로 번역되었습니다: 영어, 독일어, 스페인어, 간체 중국어, 프랑스어, 이탈리아어, 포르투갈어, 러시아어, 일본어, 한국어. 이 README는 7개 언어로 제공됩니다.
- **인터페이스 개선.** 접을 수 있는 목록 카드, 간결한 시스템 상태 카드(CPU, 사용 중/전체 메모리, 가동 시간), 가로 퀵 액션, 로그인 화면 언어 전환, 정리된 VM/LXC 세부 작업.
- **로그인 및 TLS.** 호스트/IP 입력의 HTTPS 정규화, 자체 서명 인증서의 최초 사용 신뢰 흐름과 SHA-256 지문 고정, 비밀번호 또는 API 토큰 로그인.
- **새 이름.** Proxmox VE Mobile에서 Proxmox VE Android로 이름이 변경되었습니다: 앱 이름, 설정 화면 헤더, 정보 대화상자.

## 릴리스

현재 안정 버전은 [1.0.0](https://github.com/iso-lib/Proxmox-VE-Android/releases/tag/v1.0.0)(versionCode 4)입니다. 첫 베타 버전 이후의 변경 사항은 위의 [1.0.0의 새로운 기능](#100의-새로운-기능)을 참조하십시오.

배포:

- GitHub Releases가 다운로드 경로이며, 각 릴리스에는 서명된 APK가 첨부됩니다.
- 앞으로 버전 태그(`v*`)를 푸시하면 자동으로 릴리스됩니다.
- Play Store와 F-Droid 패키징은 미뤄집니다.

지원 환경 목표:

- Android API 24 이상.
- Proxmox VE 8.x가 주요 스모크 테스트 대상입니다.
- 독립 실행형 노드와 소규모 클러스터가 범위에 포함됩니다.
- Proxmox Backup Server 관리는 범위에서 제외됩니다.

### APK 설치

1. `1.0.0`에 해당하는 GitHub Release를 엽니다.
2. 첨부된 `proxmox-ve-android-1.0.0.apk`를 다운로드합니다.
3. 릴리스 노트에서 해당 APK가 서명된 안정 버전 빌드임이 명시되어 있는지 확인합니다.
4. Android에서 APK를 다운로드하는 데 사용한 브라우저나 파일 관리자로부터의 설치를 허용합니다.
5. APK를 열고 Android 설치 안내를 따릅니다.

이 저장소의 GitHub Releases에 첨부된 APK만 설치하십시오. 디버그 APK와 서명되지 않은 워크플로 아티팩트는 프로젝트 검증용일 뿐이며 공개 다운로드로 취급해서는 안 됩니다.

## 스크린샷

버전 1.0.0의 스크린샷입니다.

| 로그인 | 대시보드 |
| --- | --- |
| ![로그인](docs/screenshots/login.jpg) | ![대시보드](docs/screenshots/dashboard.jpg) |
| LXC 컨테이너 | 스토리지 |
| ![LXC 컨테이너](docs/screenshots/lxc-containers.jpg) | ![스토리지](docs/screenshots/storage.jpg) |

## 현지화

이 앱은 다음 로케일에 대한 문자열 리소스를 제공합니다.

- 기본값(`values`): 영어
- `values-de`: 독일어
- `values-es`: 스페인어
- `values-zh-rCN`: 중국어 간체
- `values-fr`: 프랑스어
- `values-it`: 이탈리아어
- `values-pt`: 포르투갈어
- `values-ru`: 러시아어
- `values-ja`: 일본어
- `values-ko`: 한국어

현지화는 Android `strings.xml` 파일을 통한 리소스 기반 방식입니다. 새 로케일을 추가할 때는 문자열 이름, 플레이스홀더, 형식 지정자, 이스케이프를 유지해야 번역이 기본 리소스와 동작 면에서 동일하게 유지됩니다.

## 현재 상태

코드 점검을 기준으로 동작하는 것으로 보이는 기능:

- 사용자가 제공한 Proxmox 호스트와 포트를 대상으로 한 수동 로그인.
- 사용자가 제공한 Proxmox 호스트와 포트를 대상으로 한 비밀번호 또는 API 토큰 로그인.
- 다음 실행 시 자동 완성을 지원하는 선택적 암호화 자격 증명 저장.
- 자체 서명 HTTPS 서버를 위한 선택적 SHA-256 인증서 지문 고정.
- 저장소 기반 기능 ViewModel을 통한 대시보드 노드 목록, 기본 상태 표시, 작업 활동 요약, 폴링, 수동 새로 고침.
- 저장소 기반 기능 ViewModel을 통한 노드 상세 상태 로딩. 대시보드 노드 카드에서 노드 식별자가 유지된 채로 접근할 수 있습니다.
- 저장소 기반 기능 ViewModel을 통한 VM 목록/상세 로딩, 시작, 정상 종료, 강제 중지, 재부팅, 삭제, 반환된 작업 알림, 작업 상세 직접 링크, VMID로 필터링된 작업 기록, 읽기 전용 구성/스냅샷 표시.
- 저장소 기반 기능 ViewModel을 통한 컨테이너 목록 로딩, 시작, 정상 종료, 강제 중지, 재부팅, 삭제, 반환된 작업 알림, 작업 상세 직접 링크, VMID로 필터링된 작업 기록.
- 저장소 기반 기능 ViewModel을 통한 컨테이너 상세 상태 및 스냅샷 표시. LXC 목록에서 노드 식별자가 유지된 채로 접근할 수 있습니다.
- 저장소 기반 기능 ViewModel을 통한 작업 기록 로딩. 노드 선택, 상태/유형/VMID 필터, 상세/상태, 작업 로그 보기, 실행 중인 작업 중지 요청을 지원합니다.
- 로딩, 오류, 새로 고침, 빈 상태를 지원하는 저장소 기반 기능 ViewModel을 통한 스토리지 목록 및 읽기 전용 스토리지 콘텐츠 탐색.
- 로딩, 오류, 재시도, 새로 고침, 캐시된 노드 선택을 지원하는 저장소 기반 기능 ViewModel을 통한 네트워크 인터페이스 목록.
- 로딩, 오류, 재시도, 새로 고침, 빈 상태를 지원하는 저장소 기반 기능 ViewModel을 통한 사용자 목록.
- 노드 선택, 스토리지 필터링, 부분 스토리지 경고, 재시도, 새로 고침, 빈 상태를 지원하는 저장소 기반 기능 ViewModel을 통한 백업 목록.
- 정족수, 투표, 노드 구성원 상태를 표시하는 저장소 기반 읽기 전용 화면을 통한 클러스터 상태.

명백히 미완성이거나 부분적으로만 구현된 사항:

- 일부 화면은 계획된 워크플로가 의도적으로 구축되는 동안 플레이스홀더 또는 읽기 전용 상태로 남아 있습니다.
- 일부 설정은 로컬 UI 상태일 뿐이며, 저장되거나 런타임 동작에 연결되지 않습니다.
- 노드 재부팅/종료/셸, 백업 변경, 사용자 변경, VM 콘솔/구성 편집, 컨테이너 상세 리소스/콘솔 작업과 같은 고급 작업은 첫 베타 범위를 벗어납니다.
- 베타 범위 밖의 백업, 사용자, 설정, LXC 상세 컨트롤은 동작하는 것처럼 표시되지 않고 비활성화되거나 읽기 전용으로 표시됩니다.
- 명시적인 안전하지 않은 TLS 폴백은 디버그 전용입니다. 자체 서명 서버에는 SHA-256 인증서 지문 고정을 사용할 수 있으며 이를 우선적으로 사용해야 합니다.
- 세션/인증, 대시보드, 노드, VM, LXC, 작업, 네트워크, 스토리지, 사용자, 백업, 보안, 현지화 경계에 대한 단위 테스트 커버리지는 존재하지만, 더 넓은 단위, 계측, 스크린샷 테스트 커버리지는 아직 부족합니다.

## 알려진 제한 사항

다음 제한 사항은 `v0.1.0-beta.1`에 예상되는 사항이며 릴리스 노트에 반영되어야 합니다.

- 게스트 콘솔 접근은 첫 베타 범위를 벗어납니다.
- VM 및 LXC 구성 편집은 첫 베타 범위를 벗어납니다.
- 스냅샷 생성, 삭제, 롤백은 첫 베타 범위를 벗어납니다.
- 백업 생성, 복원, 다운로드, 삭제는 첫 베타 범위를 벗어납니다.
- 사용자 생성, 편집, 삭제 흐름은 첫 베타 범위를 벗어납니다.
- 노드 재부팅, 종료, 셸, 고급 서비스 작업은 첫 베타 범위를 벗어납니다.
- 스토리지, 네트워크, 사용자, 백업, 클러스터 영역은 주로 읽기 전용입니다.
- TFA 전용 비밀번호 로그인 처리는 완전하지 않습니다. 적절한 경우 API 토큰 로그인이 선호되는 베타 해결 방법입니다.
- 베타에 태그를 지정하기 전에 첫 베타 스모크 QA가 필요합니다. 더 넓은 검증 매트릭스는 첫 공개 베타 이후에도 계속됩니다.

## 기술 스택

- Kotlin
- Jetpack Compose
- Material 3
- AndroidX Navigation Compose
- Retrofit with Gson
- OkHttp
- Kotlin Coroutines
- 저장된 자격 증명을 위한 `EncryptedSharedPreferences`

## 아키텍처

이 프로젝트는 현재 강하게 계층화되기보다는 가볍게 구성되어 있습니다.

- `presentation/`: Compose 화면, 탐색, `MainViewModel`
- `data/api/`: Retrofit API 정의와 인증/클라이언트 설정
- `data/model/`: API 모델
- `data/security/`: 암호화 자격 증명 저장과 자격 증명 저장소 계약
- `data/session/`: 현재 인증 세션 소유권과 세션 서비스 계약
- `presentation/auth/`: 인증 UI 상태/세션 컨트롤러
- `data/dashboard/`, `data/node/`, `data/vm/`, `data/lxc/`, `data/task/`, `data/network/`, `data/storage/`, `data/user/`, `data/backup/`, `data/cluster/`: 첫 저장소 기반 수직 슬라이스

이 앱은 저장소 기반 기능 슬라이스 방향으로 이동하고 있습니다. 대시보드, 노드 상세, VM 목록/상세, LXC 목록/상세, 작업, 네트워크, 스토리지, 사용자, 백업, 클러스터 상태는 이제 전용 저장소와 기능 ViewModel을 사용합니다. VM 상세는 이제 저장소 경로를 통해 읽기 전용 구성과 스냅샷 데이터를 포함합니다. 인증 상태/API 서비스 접근은 이제 `AuthSessionController` 뒤에 있고, 저장된 로그인 데이터는 `CredentialStore` 뒤에 있으며, `MainViewModel`은 여전히 캐시된 노드와 전역 대화 상자 관련 사항을 소유합니다. 노드 상세는 이제 VM, LXC, 스토리지, 네트워크, 작업 보기를 열 때 노드 식별자를 유지합니다.

## 저장소 레이아웃

```text
.
|-- app/
|   |-- build.gradle.kts
|   `-- src/main/
|       |-- AndroidManifest.xml
|       |-- java/com/proxmoxmobile/
|       |   |-- data/
|       |   `-- presentation/
|       `-- res/
|-- gradle/
|   |-- libs.versions.toml
|   `-- wrapper/
|-- build.gradle.kts
|-- gradle.properties
`-- settings.gradle.kts
```

## 설정

전제 조건:

- JDK 17
- Android Studio 또는 로컬 Android SDK 설치

로컬 설정:

```bash
./gradlew tasks
```

로컬로 빌드하려면 직접 `local.properties`를 만들거나 Android Studio를 통해 Android SDK를 구성하십시오. 해당 파일은 의도적으로 추적되지 않습니다.

## 빌드 및 검증

실제 Android 환경에서의 일반적인 명령:

```bash
./gradlew assembleDebug
./gradlew assembleRelease
./gradlew test
./gradlew lint
./gradlew compileDebugAndroidTestKotlin
```

베타 릴리스 후보의 경우 통합 게이트를 실행합니다.

```bash
./scripts/beta-gate.sh v0.1.0-beta.1
```

최종 사전 태그 검사에서는 동일한 게이트에서 완료된 스모크 증거를 요구합니다.

```bash
REQUIRE_BETA_QA_COMPLETE=true ./scripts/beta-gate.sh v0.1.0-beta.1
```

QA가 진행되는 동안 수동 베타 스모크 증거를 요약하려면:

```bash
./scripts/beta-qa-status.sh
```

태그를 지정하기 전에 동일한 요약을 엄격한 완료 검사로 실행합니다.

```bash
./scripts/beta-qa-status.sh --require-complete
```

Android Studio/에뮬레이터 스모크 테스트는 [`app/src/androidTest`](app/src/androidTest/README.md) 아래에 있으며 다음 명령으로 실행할 수 있습니다.

```bash
./gradlew connectedDebugAndroidTest
```

이러한 테스트는 플레이스홀더 데이터를 사용하며 Proxmox 호스트에 연결하지 않습니다. 디버그 APK를 다시 설치하거나 저장된 테스트 자격 증명을 지울 수 있으므로, 수동 로그인 스모크 세션 도중에 실행하지 말고 그 전이나 후에 실행하십시오.

2026-06-06 감사는 JDK 17과 환경 변수로 구성된 Android SDK를 사용하여 `test`, `lint`, `assembleDebug`를 로컬에서 검증했습니다. 단위 커버리지는 현재 세션/인증, 노드, VM, LXC, 작업, 현지화 경계에서 시작합니다. 더 넓은 동작 커버리지가 여전히 필요합니다.

## 프로젝트 워크플로

- 로드맵 및 복구 계획: [`docs/development-cycle-2026.md`](docs/development-cycle-2026.md)
- 베타 릴리스 계획: [`docs/beta-release-plan.md`](docs/beta-release-plan.md)
- 현재 감사 및 기능 상태: [`docs/project-audit-2026-06-06.md`](docs/project-audit-2026-06-06.md)
- 기여자 가이드: [`CONTRIBUTING.md`](CONTRIBUTING.md)
- 보안 정책: [`SECURITY.md`](SECURITY.md)
- 변경 로그: [`CHANGELOG.md`](CHANGELOG.md)

브랜치 정책:

- `main`은 공개 베타 태그와 릴리스 준비가 된 핫픽스를 위한 릴리스 브랜치입니다.
- `dev`는 다음 베타 사이클을 위한 통합 브랜치입니다.
- 기능 및 수정 브랜치는 릴리스 핫픽스가 아닌 한 수명이 짧아야 하며 `dev`를 대상으로 해야 합니다.
- 공개 베타 태그는 `dev`가 승격되고 베타 게이트를 통과한 뒤 `main`에서 잘립니다.

풀 리퀘스트는 `./gradlew test`, `./gradlew lint`, `./gradlew assembleDebug`, `./gradlew assembleRelease`를 실행해야 합니다. `.github/workflows/android.yml`의 GitHub Actions 워크플로는 풀 리퀘스트와 `main` 및 `dev`에 대한 푸시에 대해 동일한 기준을 적용합니다.

## 보안 참고 사항

- 비밀번호 자격 증명과 API 토큰 구성 요소는 암호화된 shared preferences에 저장할 수 있습니다.
- HTTPS는 기본적으로 플랫폼 TLS 검증을 사용합니다.
- 자체 서명 HTTPS 서버는 Android에서 신뢰하는 가져온 CA 또는 고정된 SHA-256 인증서 지문을 사용할 수 있습니다.
- SSL 검증은 신뢰할 수 있는 랩 서버에 대해서만 디버그 빌드에서 비활성화할 수 있습니다. 릴리스 빌드는 플랫폼 TLS 검증 또는 구성된 SHA-256 인증서 지문을 요구합니다.
- 릴리스 빌드는 더 이상 앱 전체의 일반 텍스트 트래픽을 요청하지 않습니다. 디버그 빌드는 로컬 테스트를 위해 여전히 HTTP를 허용합니다.
- 권장 `openssl` 명령과 최초 신뢰(trust-on-first-use) 정책 참고 사항은 [`SECURITY.md`](SECURITY.md)를 참조하십시오.

## 개인정보 보호 참고 사항

- 이 앱에는 텔레메트리나 분석 기능이 포함되어 있지 않습니다.
- 저장된 호스트 세부 정보, 사용자 이름, 비밀번호, API 토큰 ID, API 토큰 시크릿은 자격 증명 저장이 활성화된 경우 암호화된 shared preferences를 통해 로컬에 저장됩니다.
- 이 앱은 구성된 Proxmox VE 엔드포인트 외의 다른 곳으로 자격 증명을 의도적으로 전송하지 않습니다.
- 버그 보고 시에는 비밀번호, API 토큰, 티켓, 쿠키, CSRF 토큰, 비공개 키, 공유해서는 안 되는 공개 IP 세부 정보, 민감한 호스트 이름을 제거해야 합니다.

## 알려진 공백

- 자동화된 테스트 커버리지는 좁으며 현재 세션/인증, 대시보드, 노드, VM, LXC, 작업, 네트워크, 스토리지, 사용자, 백업, 보안, 현지화 경계에 집중되어 있습니다.
- CI는 빌드, 린트, 단위 테스트를 위해 존재합니다.
- 여러 종속성과 UI의 일부는 버려졌거나 미완성인 기능 작업을 시사합니다.
- 일부 설정은 런타임 동작이 구현될 때까지 계획된 상태이거나 비활성화된 상태로 남아 있습니다.

## 권장 다음 단계

1. 실제 Android SDK/에뮬레이터에서 앱을 검증하고 어떤 화면/작업이 실제로 Proxmox 인스턴스에 대해 성공하는지 기록합니다.
2. 실제 Proxmox 어플라이언스와 리버스 프록시에 대해 자체 서명 인증서 안내를 검증합니다.
3. `v0.1.0-beta.1` 스모크 매트릭스를 실행하고 태그를 지정하기 전에 베타 차단 문제만 수정합니다.
4. QA 증거가 수집되면 변경 로그를 `Unreleased`에서 `v0.1.0-beta.1`로 승격합니다.
5. 연기된 동등성 작업을 집중된 베타 이후 릴리스 트레인으로 계속 이동합니다.

구체적인 단계별 로드맵은 [`docs/revival-plan.md`](docs/revival-plan.md)를 참조하십시오. 더 넓은 제품, 리팩터링, QA, 워크플로, 커뮤니티 개발 사이클은 [`docs/development-cycle-2026.md`](docs/development-cycle-2026.md)를 참조하십시오. 첫 공식 베타 체크리스트는 [`docs/beta-release-plan.md`](docs/beta-release-plan.md)를 참조하십시오.
