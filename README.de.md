[English](README.md) | [简体中文](README.zh-CN.md) | **Deutsch** | [Español](README.es.md) | [Français](README.fr.md) | [日本語](README.ja.md) | [한국어](README.ko.md)

# Proxmox-VE-Android

> Dieses Projekt stammt vom Upstream-Projekt [5p00kyy/Proxmox-VE-Mobile](https://github.com/5p00kyy/Proxmox-VE-Mobile), wurde aber als eigenständiges neues Projekt neu angelegt und ist kein Fork dieses Upstream-Projekts mehr. Auf Basis des Upstream-Codes enthält dieses Projekt: Übersetzungen in 10 Sprachen (Englisch, Deutsch, Spanisch, Vereinfachtes Chinesisch, Französisch, Italienisch, Portugiesisch, Russisch, Japanisch und Koreanisch) mit einer Sprachauswahl auf der Anmeldeseite; Oberflächenverbesserungen wie einklappbare Listenkarten, eine kompakte Systemstatus-Karte, horizontale Schnellaktionen und aufgeräumte VM-/LXC-Detailaktionen; eine schlanke WebView-Gastkonsole für LXC-Gäste (xterm.js) und VM-Gäste (noVNC); funktionierende Einstellungen für dunkles/helles Design, Aktualisierungsintervall, automatische Anmeldung und Problemmeldungen; eine formal signierte stabile Version 1.0.0, die über GitHub Releases verteilt wird und deren Nachfolger bei Push eines Versions-Tags automatisch veröffentlicht werden; sowie die Umbenennung der App in Proxmox VE Android.

`Proxmox VE Android` ist ein Android-Client, mit dem eine Proxmox-VE-Umgebung von einem Smartphone oder Tablet aus durchsucht und bedient werden kann.

Die aktuelle Beta-Linie konzentriert sich auf gängige mobile Arbeitsabläufe für Betreiber: Anmeldung, sichere Verbindungsverwaltung, Dashboard-Übersicht, Knoten-Navigation, VM-/LXC-Lebenszyklusaktionen, Aufgabenverfolgung und schreibgeschützte Infrastrukturansicht. Es handelt sich um eine einmodulige Kotlin/Jetpack-Compose-App, die von bildschirmgebundenen Retrofit-Aufrufen zu repositorygestützten Funktionsbereichen übergeht. Diese README beschreibt das Repository in seinem heutigen Zustand und nicht den angestrebten Endzustand.

## Neu in 1.0.0

Im Vergleich zur ersten Beta ist 1.0.0 die erste stabile Version: [1.0.0](https://github.com/iso-lib/Proxmox-VE-Android/releases/tag/v1.0.0).

- **Erste stabile Version.** Version 1.0.0 (versionCode 4), verteilt über GitHub Releases und mit dem Release-Schlüssel des Projekts signiert. Künftige Versionen werden automatisch veröffentlicht, sobald ein Versions-Tag (`v*`) gepusht wird.
- **Gastkonsole.** Eine schlanke WebView-Konsole für LXC-Gäste (xterm.js) und VM-Gäste (noVNC), die die eigenen Konsolenseiten von Proxmox lädt; Passwort-Sitzungen authentifizieren sich mit einem Sitzungs-Ticket-Cookie.
- **Funktionierende Einstellungen.** Dunkles/helles Design, ein Aktualisierungsintervall von 10–60 Sekunden für Dashboard-, VM-, Container- und Aufgabenlisten, automatische Anmeldung mit gespeicherten Zugangsdaten sowie ein Link „Problem melden“ zu GitHub Issues. Die Schalter für Biometrie und Benachrichtigungen waren Platzhalter ohne Implementierung und wurden entfernt.
- **Lokalisierung.** Die App ist in 10 Sprachen übersetzt: Englisch, Deutsch, Spanisch, Vereinfachtes Chinesisch, Französisch, Italienisch, Portugiesisch, Russisch, Japanisch und Koreanisch. Diese README ist in 7 Sprachen verfügbar.
- **Oberflächenverbesserungen.** Einklappbare Listenkarten, eine kompakte Systemstatus-Karte (CPU, belegter/gesamter Speicher, Laufzeit), horizontale Schnellaktionen, ein Sprachumschalter auf der Anmeldeseite und aufgeräumte VM-/LXC-Detailaktionen.
- **Anmeldung und TLS.** HTTPS-Normalisierung für Host-/IP-Eingaben, ein Vertrauensdialog bei erster Verwendung für selbstsignierte Zertifikate mit SHA-256-Fingerprint-Pinning sowie Anmeldung per Passwort oder API-Token.
- **Neuer Name.** Umbenannt von Proxmox VE Mobile in Proxmox VE Android: App-Name, Kopfzeile der Einstellungen und Info-Dialog.

## Veröffentlichung

Die aktuelle stabile Version ist [1.0.0](https://github.com/iso-lib/Proxmox-VE-Android/releases/tag/v1.0.0) (versionCode 4). Die Änderungen seit der ersten Beta stehen oben unter [Neu in 1.0.0](#neu-in-100).

Vertrieb:

- GitHub Releases ist der Download-Weg; an jede Version ist die signierte APK angehängt.
- Künftige Versionen werden automatisch veröffentlicht, sobald ein Versions-Tag (`v*`) gepusht wird.
- Die Paketierung für den Play Store und F-Droid wird zurückgestellt.

Unterstützte Umgebungsziele:

- Android API 24 oder neuer.
- Proxmox VE 8.x ist das primäre Smoke-Test-Ziel.
- Eigenständige Knoten und kleine Cluster sind im Umfang enthalten.
- Die Verwaltung von Proxmox Backup Server ist nicht im Umfang enthalten.

### Installieren der APK

1. Öffnen Sie das GitHub Release für `1.0.0`.
2. Laden Sie die angehängte `proxmox-ve-android-1.0.0.apk` herunter.
3. Bestätigen Sie, dass die Versionshinweise die APK als signierten stabilen Build ausweisen.
4. Erlauben Sie unter Android die Installation aus dem Browser oder Dateimanager, mit dem Sie die APK heruntergeladen haben.
5. Öffnen Sie die APK und folgen Sie der Android-Installationsaufforderung.

Installieren Sie nur APKs, die an die GitHub Releases dieses Repositorys angehängt sind. Debug-APKs und unsignierte Workflow-Artefakte dienen nur der Projektvalidierung und sollten nicht als öffentliche Downloads behandelt werden.

## Screenshots

Screenshots aus Version 1.0.0.

| Anmeldung | Dashboard |
| --- | --- |
| ![Anmeldung](docs/screenshots/login.jpg) | ![Dashboard](docs/screenshots/dashboard.jpg) |
| LXC-Container | Speicher |
| ![LXC-Container](docs/screenshots/lxc-containers.jpg) | ![Speicher](docs/screenshots/storage.jpg) |

## Lokalisierung

Die App wird mit String-Ressourcen für diese Gebietsschemata ausgeliefert:

- Standard (`values`): Englisch
- `values-de`: Deutsch
- `values-es`: Spanisch
- `values-zh-rCN`: Vereinfachtes Chinesisch
- `values-fr`: Französisch
- `values-it`: Italienisch
- `values-pt`: Portugiesisch
- `values-ru`: Russisch
- `values-ja`: Japanisch
- `values-ko`: Koreanisch

Die Lokalisierung erfolgt ressourcenbasiert über Android-`strings.xml`-Dateien. Neue Gebietsschemata sollten Stringnamen, Platzhalter, Formatspezifizierer und Escaping beibehalten, damit Übersetzungen verhaltensgleich zu den Standardressourcen bleiben.

## Aktueller Status

Was nach Codeprüfung zu funktionieren scheint:

- Manuelle Anmeldung an einem vom Benutzer angegebenen Proxmox-Host und -Port.
- Anmeldung mit Passwort oder API-Token an einem vom Benutzer angegebenen Proxmox-Host und -Port.
- Optionale verschlüsselte Speicherung von Anmeldedaten mit automatischem Ausfüllen beim nächsten Start.
- Optionales Pinning eines SHA-256-Zertifikatsfingerabdrucks für selbstsignierte HTTPS-Server.
- Dashboard-Knotenliste, grundlegende Statusanzeige, Aufgabenaktivitätsübersicht, Abfragen in Abständen und manuelle Aktualisierung über ein repositorygestütztes Feature-ViewModel.
- Laden des Knotendetailstatus über ein repositorygestütztes Feature-ViewModel, erreichbar über Dashboard-Knotenkarten unter Beibehaltung der Knotenidentität.
- Laden von VM-Liste/-Details sowie Start, ordnungsgemäßes Herunterfahren, erzwungenes Stoppen, Neustart, Löschen, zurückgegebene Aufgabenhinweise, direkte Links zu Aufgabendetails, nach VMID gefilterte Aufgabenhistorie und schreibgeschützte Konfigurations-/Snapshot-Ansicht über repositorygestützte Feature-ViewModels.
- Laden von Containerlisten sowie Start, ordnungsgemäßes Herunterfahren, erzwungenes Stoppen, Neustart, Löschen, zurückgegebene Aufgabenhinweise, direkte Links zu Aufgabendetails und nach VMID gefilterte Aufgabenhistorie über ein repositorygestütztes Feature-ViewModel.
- Containerdetailstatus und Snapshot-Ansicht über ein repositorygestütztes Feature-ViewModel, erreichbar über die LXC-Liste unter Beibehaltung der Knotenidentität.
- Laden der Aufgabenhistorie über ein repositorygestütztes Feature-ViewModel mit Knotenauswahl, Status-/Typ-/VMID-Filtern, Detail/Status, Anzeige von Aufgabenprotokollen und Stopp-Anfragen für laufende Aufgaben.
- Speicherliste und schreibgeschütztes Durchsuchen von Speicherinhalten über ein repositorygestütztes Feature-ViewModel mit Lade-, Fehler-, Aktualisierungs- und Leerzuständen.
- Auflistung von Netzwerkschnittstellen über ein repositorygestütztes Feature-ViewModel mit Lade-, Fehler-, Wiederholungs-, Aktualisierungs- und zwischengespeicherter Knotenauswahl.
- Benutzerliste über ein repositorygestütztes Feature-ViewModel mit Lade-, Fehler-, Wiederholungs-, Aktualisierungs- und Leerzuständen.
- Backupliste über ein repositorygestütztes Feature-ViewModel mit Knotenauswahl, Speicherfilterung, Warnungen bei Teilspeichern, Wiederholung, Aktualisierung und Leerzuständen.
- Clusterstatus über einen schreibgeschützten Bildschirm mit Quorum-, Stimm- und Knotenmitgliedschaftsstatus.

Was eindeutig unvollständig oder nur teilweise umgesetzt ist:

- Einige Bildschirme bleiben Platzhalter oder schreibgeschützt, während geplante Arbeitsabläufe bewusst schrittweise aufgebaut werden.
- Einige Einstellungen sind nur lokaler UI-Zustand und werden nicht gespeichert oder in Laufzeitverhalten eingebunden.
- Erweiterte Aktionen wie Knoten-Neustart/-Herunterfahren/-Shell, Backup-Änderungen, Benutzeränderungen, VM-Konsolen-/Konfigurationsbearbeitung sowie Containerdetail-Ressourcen-/Konsolenoperationen liegen außerhalb des Umfangs der ersten Beta.
- Backup-, Benutzer-, Einstellungs- und LXC-Detail-Steuerelemente, die außerhalb des Beta-Umfangs liegen, sind deaktiviert oder als schreibgeschützt gekennzeichnet, statt als funktionierend dargestellt zu werden.
- Der explizite unsichere TLS-Fallback ist nur für Debug-Builds vorgesehen; das Pinning eines SHA-256-Zertifikatsfingerabdrucks steht für selbstsignierte Server zur Verfügung und sollte bevorzugt werden.
- Unit-Test-Abdeckung existiert für Session/Auth, Dashboard, Knoten, VM, LXC, Aufgaben, Netzwerk, Speicher, Benutzer, Backup, Sicherheit und Lokalisierungs-Schnittstellen, doch eine breitere Unit-, Instrumentierungs- und Screenshot-Testabdeckung fehlt noch.

## Bekannte Einschränkungen

Diese Einschränkungen werden für `v0.1.0-beta.1` erwartet und sollten in den Versionshinweisen berücksichtigt werden:

- Der Gastkonsolenzugriff liegt außerhalb des Umfangs der ersten Beta.
- Die Bearbeitung von VM- und LXC-Konfigurationen liegt außerhalb des Umfangs der ersten Beta.
- Das Erstellen, Löschen und Zurücksetzen von Snapshots liegt außerhalb des Umfangs der ersten Beta.
- Das Erstellen, Wiederherstellen, Herunterladen und Löschen von Backups liegt außerhalb des Umfangs der ersten Beta.
- Abläufe zum Erstellen, Bearbeiten und Löschen von Benutzern liegen außerhalb des Umfangs der ersten Beta.
- Knoten-Neustart, -Herunterfahren, -Shell und erweiterte Dienstaktionen liegen außerhalb des Umfangs der ersten Beta.
- Die Bereiche Speicher, Netzwerk, Benutzer, Backup und Cluster sind in erster Linie schreibgeschützt.
- Die TFA-spezifische Passwort-Anmeldebehandlung ist nicht vollständig; die API-Token-Anmeldung ist gegebenenfalls die bevorzugte Beta-Alternative.
- Die Smoke-QA der ersten Beta ist erforderlich, bevor die Beta getaggt wird; die breitere Validierungsmatrix wird nach der ersten öffentlichen Beta fortgesetzt.

## Tech-Stack

- Kotlin
- Jetpack Compose
- Material 3
- AndroidX Navigation Compose
- Retrofit mit Gson
- OkHttp
- Kotlin Coroutines
- `EncryptedSharedPreferences` für gespeicherte Anmeldedaten

## Architektur

Das Projekt ist derzeit eher schlank als streng geschichtet:

- `presentation/`: Compose-Bildschirme, Navigation und `MainViewModel`
- `data/api/`: Retrofit-API-Definitionen sowie Authentifizierungs-/Client-Einrichtung
- `data/model/`: API-Modelle
- `data/security/`: verschlüsselte Speicherung von Anmeldedaten sowie Credential-Store-Verträge
- `data/session/`: Besitz der aktuellen Authentifizierungssitzung und Dienstvertrag für Sitzungen
- `presentation/auth/`: UI-Zustand der Authentifizierung / Sitzungssteuerung
- `data/dashboard/`, `data/node/`, `data/vm/`, `data/lxc/`, `data/task/`, `data/network/`, `data/storage/`, `data/user/`, `data/backup/`, `data/cluster/`: erste repositorygestützte vertikale Bereiche

Die App bewegt sich hin zu repositorygestützten Funktionsbereichen. Dashboard, Knotendetails, VM-Liste/-Details, LXC-Liste/-Details, Aufgaben, Netzwerk, Speicher, Benutzer, Backups und Clusterstatus verwenden mittlerweile eigene Repositories und Feature-ViewModels. VM-Details enthalten nun schreibgeschützte Konfigurations- und Snapshot-Daten über den Repository-Pfad. Authentifizierungsstatus und API-Dienstzugriff liegen nun hinter `AuthSessionController`, gespeicherte Anmeldedaten hinter `CredentialStore`, und `MainViewModel` besitzt weiterhin zwischengespeicherte Knoten und globale Dialogbelange. Knotendetails bewahren nun die Knotenidentität beim Öffnen von VM-, LXC-, Speicher-, Netzwerk- und Aufgabenansichten.

## Repository-Layout

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

## Einrichtung

Voraussetzungen:

- JDK 17
- Android Studio oder eine lokale Android-SDK-Installation

Lokale Einrichtung:

```bash
./gradlew tasks
```

Wenn Sie lokal bauen möchten, erstellen Sie eine eigene `local.properties` oder konfigurieren Sie das Android SDK über Android Studio. Diese Datei wird bewusst nicht versioniert.

## Build und Verifizierung

Typische Befehle in einer echten Android-Umgebung:

```bash
./gradlew assembleDebug
./gradlew assembleRelease
./gradlew test
./gradlew lint
./gradlew compileDebugAndroidTestKotlin
```

Führen Sie für Beta-Release-Kandidaten das konsolidierte Gate aus:

```bash
./scripts/beta-gate.sh v0.1.0-beta.1
```

Verlangen Sie für die letzte Prüfung vor dem Taggen abgeschlossene Smoke-Nachweise im selben Gate:

```bash
REQUIRE_BETA_QA_COMPLETE=true ./scripts/beta-gate.sh v0.1.0-beta.1
```

Um manuelle Beta-Smoke-Nachweise zusammenzufassen, während die QA läuft:

```bash
./scripts/beta-qa-status.sh
```

Führen Sie vor dem Taggen dieselbe Zusammenfassung als harte Abschlussprüfung aus:

```bash
./scripts/beta-qa-status.sh --require-complete
```

Smoke-Tests für Android Studio/Emulator befinden sich unter [`app/src/androidTest`](app/src/androidTest/README.md) und können ausgeführt werden mit:

```bash
./gradlew connectedDebugAndroidTest
```

Diese Tests verwenden Platzhalterdaten und verbinden sich nicht mit einem Proxmox-Host. Sie können die Debug-APK neu installieren oder gespeicherte Test-Anmeldedaten löschen; führen Sie sie daher vor oder nach manuellen angemeldeten Smoke-Sitzungen aus und nicht während einer solchen Sitzung.

Die Prüfung vom 2026-06-06 hat `test`, `lint` und `assembleDebug` lokal mit JDK 17 und einem über Umgebungsvariablen konfigurierten Android SDK verifiziert. Die Unit-Abdeckung beginnt derzeit bei den Session-/Auth-, Knoten-, VM-, LXC-, Aufgaben- und Lokalisierungs-Schnittstellen; eine breitere verhaltensbezogene Abdeckung ist noch erforderlich.

## Projektablauf

- Roadmap- und Wiederherstellungsplan: [`docs/development-cycle-2026.md`](docs/development-cycle-2026.md)
- Beta-Release-Plan: [`docs/beta-release-plan.md`](docs/beta-release-plan.md)
- Aktuelle Prüfung und Funktionsstatus: [`docs/project-audit-2026-06-06.md`](docs/project-audit-2026-06-06.md)
- Leitfaden für Mitwirkende: [`CONTRIBUTING.md`](CONTRIBUTING.md)
- Sicherheitsrichtlinie: [`SECURITY.md`](SECURITY.md)
- Änderungsprotokoll: [`CHANGELOG.md`](CHANGELOG.md)

Branch-Richtlinie:

- `main` ist der Release-Branch für öffentliche Beta-Tags und releasefertige Hotfixes.
- `dev` ist der Integrations-Branch für den nächsten Beta-Zyklus.
- Feature- und Fix-Branches sollten kurzlebig sein und auf `dev` abzielen, es sei denn, es handelt sich um Release-Hotfixes.
- Öffentliche Beta-Tags werden von `main` geschnitten, nachdem `dev` befördert wurde und das Beta-Gate bestanden ist.

Von Pull Requests wird erwartet, dass sie `./gradlew test`, `./gradlew lint`, `./gradlew assembleDebug` und `./gradlew assembleRelease` ausführen. Der GitHub-Actions-Workflow in `.github/workflows/android.yml` erzwingt dieselbe Baseline für Pull Requests und Pushes auf `main` und `dev`.

## Sicherheitshinweise

- Passwort-Anmeldedaten und API-Token-Bestandteile können in verschlüsselten Shared Preferences gespeichert werden.
- HTTPS verwendet standardmäßig die TLS-Validierung der Plattform.
- Selbstsignierte HTTPS-Server können eine von Android vertraute importierte CA oder einen gepinnten SHA-256-Zertifikatsfingerabdruck verwenden.
- Die SSL-Verifizierung kann nur in Debug-Builds für vertrauenswürdige Laborserver deaktiviert werden. Release-Builds erfordern die TLS-Validierung der Plattform oder einen konfigurierten SHA-256-Zertifikatsfingerabdruck.
- Release-Builds fordern keinen appweiten Klartextverkehr mehr an. Debug-Builds erlauben weiterhin HTTP für lokale Tests.
- Siehe [`SECURITY.md`](SECURITY.md) für den empfohlenen `openssl`-Befehl und Hinweise zur Trust-on-First-Use-Richtlinie.

## Datenschutzhinweise

- Die App enthält keine Telemetrie oder Analysen.
- Gespeicherte Hostdetails, Benutzernamen, Passwörter, API-Token-IDs und API-Token-Secrets werden bei aktivierter Speicherung von Anmeldedaten lokal über verschlüsselte Shared Preferences gespeichert.
- Die App sendet Anmeldedaten absichtlich nirgendwohin außer an den konfigurierten Proxmox-VE-Endpunkt.
- Fehlerberichte sollten Passwörter, API-Token, Tickets, Cookies, CSRF-Token, private Schlüssel, öffentliche IP-Details, die nicht geteilt werden sollten, und sensible Hostnamen entfernen.

## Bekannte Lücken

- Die automatisierte Testabdeckung ist schmal und konzentriert sich derzeit auf Session-/Auth-, Dashboard-, Knoten-, VM-, LXC-, Aufgaben-, Netzwerk-, Speicher-, Benutzer-, Backup-, Sicherheits- und Lokalisierungs-Schnittstellen.
- CI ist für Build, Lint und Unit-Tests vorhanden.
- Mehrere Abhängigkeiten und Teile der UI deuten auf aufgegebene oder unfertige Funktionsarbeiten hin.
- Einige Einstellungen bleiben geplant oder deaktiviert, bis ihr Laufzeitverhalten umgesetzt ist.

## Empfohlene nächste Schritte

1. Verifizieren Sie die App auf einem echten Android SDK/Emulator und erfassen Sie, welche Bildschirme/Aktionen gegen eine Proxmox-Instanz tatsächlich erfolgreich sind.
2. Validieren Sie die Anleitung für selbstsignierte Zertifikate an echten Proxmox-Appliances und Reverse Proxys.
3. Führen Sie die `v0.1.0-beta.1`-Smoke-Matrix aus und beheben Sie vor dem Taggen nur Beta-Blocker.
4. Befördern Sie das Änderungsprotokoll von `Unreleased` zu `v0.1.0-beta.1`, sobald QA-Nachweise gesammelt sind.
5. Verlagern Sie aufgeschobene Paritätsarbeiten weiterhin in fokussierte Post-Beta-Release-Züge.

Eine konkrete stufenweise Roadmap finden Sie unter [`docs/revival-plan.md`](docs/revival-plan.md). Den breiteren Produkt-, Refaktorierungs-, QA-, Workflow- und Community-Entwicklungszyklus finden Sie unter [`docs/development-cycle-2026.md`](docs/development-cycle-2026.md). Die Checkliste für die erste offizielle Beta finden Sie unter [`docs/beta-release-plan.md`](docs/beta-release-plan.md).
