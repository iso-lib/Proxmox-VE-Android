[English](README.md) | [简体中文](README.zh-CN.md) | [Deutsch](README.de.md) | **Español** | [Français](README.fr.md) | [日本語](README.ja.md) | [한국어](README.ko.md)

# Proxmox-VE-Android

> Este proyecto procede del proyecto original [5p00kyy/Proxmox-VE-Mobile](https://github.com/5p00kyy/Proxmox-VE-Mobile), recreado como un proyecto independiente nuevo, y ya no es un fork de dicho proyecto original. Sobre la base del código original, este proyecto incluye: traducción completa al chino simplificado, selector de idioma en la pantalla de inicio de sesión, tarjetas de lista plegables y ajustes de la interfaz, consola LXC/VM (WebView), entre otros cambios.

`Proxmox VE Mobile` es un cliente Android para explorar y operar un entorno Proxmox VE desde un teléfono o una tableta.

La línea beta actual se centra en los flujos de trabajo habituales de un operador móvil: inicio de sesión, gestión de conexiones seguras, visibilidad del panel de control, navegación por nodos, acciones del ciclo de vida de VM/LXC, seguimiento de tareas e inspección de infraestructura de solo lectura. Es una aplicación de un solo módulo en Kotlin/Jetpack Compose que está pasando de llamadas Retrofit propias de cada pantalla a secciones de funcionalidades respaldadas por repositorios. Este README describe el repositorio tal como existe hoy, no el estado final previsto.

## Versión beta

La primera beta oficial prevista es `v0.1.0-beta.1`.

Plan de distribución:

- GitHub Releases será la primera vía de descarga de la beta.
- El APK de la beta se adjuntará a la versión una vez que superen las comprobaciones automatizadas y las pruebas de humo de la primera beta.
- El empaquetado para Play Store y F-Droid se pospone hasta después de la primera beta de GitHub.

Objetivos de entorno admitidos para la primera beta:

- Android API 24 o superior.
- Proxmox VE 8.x es el objetivo principal de las pruebas de humo.
- Los nodos independientes y los clústeres pequeños están dentro del alcance.
- La gestión de Proxmox Backup Server no está dentro del alcance de la primera beta.

### Instalar un APK beta

Cuando se publique `v0.1.0-beta.1`:

1. Abre la versión de GitHub Release correspondiente a la etiqueta beta.
2. Descarga el archivo adjunto `proxmox-ve-mobile-v0.1.0-beta.1-release.apk`.
3. Confirma que las notas de la versión identifican el APK como una compilación beta firmada.
4. En Android, permite la instalación desde el navegador o el administrador de archivos que hayas usado para descargar el APK.
5. Abre el APK y sigue las indicaciones de instalación de Android.

Instala únicamente APK adjuntos a las GitHub Releases de este repositorio. Los APK de depuración y los artefactos de flujo de trabajo sin firmar son solo para la validación del proyecto y no deben tratarse como descargas beta públicas.

## Capturas de pantalla

Las capturas de pantalla y las grabaciones cortas de pantalla son útiles para `v0.1.0-beta.1`, pero deben omitirse si no se dispone de capturas seguras para publicar. Buenas capturas incluyen:

- Inicio de sesión con los controles TLS/de huella visibles, usando texto de host de ejemplo no sensible.
- Panel de control solo con recursos de laboratorio genéricos o desechables.
- Lista/detalle de VM y lista/detalle de LXC con nombres de invitados desechables.
- Detalle/registros de tareas con UPID, nombres de usuario, nombres de nodo, IP y valores sensibles de registros redactados o capturados desde un laboratorio desechable.
- Una sección de administración de solo lectura, como almacenamiento, red, usuarios, copias de seguridad o clúster, usando datos de ejemplo depurados.

No publiques capturas que revelen nombres de host privados, direcciones IP públicas o privadas, nombres de usuario, notas de copias de seguridad, registros de tareas con detalles del entorno, tokens de API, tickets, cookies, huellas vinculadas a un servidor privado ni rutas de escritorio o específicas de un usuario. Si no se dispone de capturas reales depuradas, usa un laboratorio Proxmox desechable u omite las capturas hasta que se puedan obtener medios seguros para publicar.

Ejemplos seguros incluyen texto de host de marcador de posición, nombres de nodo genéricos como `pve-demo`, nombres de invitados desechables como `demo-vm-101`, UPID redactados y vistas de almacenamiento o clúster procedentes de un laboratorio desechable.

Antes de adjuntar capturas o grabaciones a una GitHub Release, registra un manifiesto de medios público en las notas de la versión o en [`docs/beta-smoke-qa.md`](docs/beta-smoke-qa.md). El manifiesto debe enumerar cada nombre de archivo, pantalla o flujo de trabajo, tipo de origen, identificadores depurados utilizados, texto de título o alternativo, y estado de control de calidad. Buenos nombres de archivo candidatos son `login-tls.png`, `dashboard.png`, `vm-detail.png`, `lxc-detail.png`, `task-detail.png` y `read-only-admin.png`.

## Localización

La aplicación incluye recursos de texto para estas configuraciones regionales:

- Predeterminada (`values`): inglés
- `values-de`: alemán
- `values-es`: español
- `values-zh-rCN`: chino simplificado
- `values-fr`: francés
- `values-it`: italiano
- `values-pt`: portugués
- `values-ru`: ruso
- `values-ja`: japonés
- `values-ko`: coreano

La localización se basa en recursos a través de archivos `strings.xml` de Android. Las nuevas incorporaciones de configuraciones regionales deben conservar los nombres de texto, los marcadores de posición, los especificadores de formato y el escapado, de modo que las traducciones sigan siendo idénticas en comportamiento a los recursos predeterminados.

## Estado actual

Lo que parece funcionar según la inspección del código:

- Inicio de sesión manual con un host y un puerto de Proxmox proporcionados por el usuario.
- Inicio de sesión con contraseña o token de API con un host y un puerto de Proxmox proporcionados por el usuario.
- Almacenamiento cifrado opcional de credenciales con autocompletado en el siguiente inicio.
- Fijación opcional de huella de certificado SHA-256 para servidores HTTPS autofirmados.
- Listado de nodos del panel de control, visualización básica de estado, resumen de actividad de tareas, sondeo y actualización manual a través de un ViewModel de funcionalidad respaldado por repositorio.
- Carga del estado de detalle de nodo a través de un ViewModel de funcionalidad respaldado por repositorio, accesible desde las tarjetas de nodo del panel de control con la identidad del nodo conservada.
- Carga de lista/detalle de VM, además de inicio, apagado ordenado, detención forzada, reinicio, eliminación, avisos de tareas devueltas, enlaces directos a detalles de tareas, historial de tareas filtrado por VMID y visibilidad de configuración/instantáneas de solo lectura a través de ViewModels de funcionalidad respaldados por repositorios.
- Carga de lista de contenedores, además de inicio, apagado ordenado, detención forzada, reinicio, eliminación, avisos de tareas devueltas, enlaces directos a detalles de tareas e historial de tareas filtrado por VMID a través de un ViewModel de funcionalidad respaldado por repositorio.
- Visibilidad del estado de detalle de contenedor e instantáneas a través de un ViewModel de funcionalidad respaldado por repositorio, accesible desde la lista de LXC con la identidad del nodo conservada.
- Carga del historial de tareas a través de un ViewModel de funcionalidad respaldado por repositorio, con selección de nodo, filtros de estado/tipo/VMID, detalle/estado, visualización de registros de tareas y solicitudes de detención de tareas en ejecución.
- Listado de almacenamiento y exploración de contenido de almacenamiento de solo lectura a través de un ViewModel de funcionalidad respaldado por repositorio con estados de carga, error, actualización y vacío.
- Listado de interfaces de red a través de un ViewModel de funcionalidad respaldado por repositorio con estados de carga, error, reintento, actualización y selección de nodo en caché.
- Listado de usuarios a través de un ViewModel de funcionalidad respaldado por repositorio con estados de carga, error, reintento, actualización y vacío.
- Listado de copias de seguridad a través de un ViewModel de funcionalidad respaldado por repositorio con selección de nodo, filtrado por almacenamiento, advertencias de almacenamiento parcial, reintento, actualización y estados vacíos.
- Estado del clúster a través de una pantalla de solo lectura respaldada por repositorio con estado de quórum, votos y pertenencia de nodos.

Lo que está claramente incompleto o solo parcialmente implementado:

- Algunas pantallas siguen siendo marcadores de posición o de solo lectura mientras los flujos de trabajo previstos se construyen deliberadamente.
- Algunos ajustes son solo estado de interfaz local y no se conservan ni se conectan con el comportamiento en tiempo de ejecución.
- Las acciones avanzadas como reinicio/apagado/terminal de nodo, mutación de copias de seguridad, mutación de usuarios, edición de consola/configuración de VM y operaciones de recursos/consola de detalle de contenedor están fuera del alcance de la primera beta.
- Los controles de copias de seguridad, usuarios, ajustes y detalle de LXC que están fuera del alcance de la beta están deshabilitados o etiquetados como de solo lectura, en lugar de presentarse como funcionales.
- La alternativa explícita de TLS inseguro es solo para depuración; la fijación de huella de certificado SHA-256 está disponible para servidores autofirmados y debe preferirse.
- Existe cobertura de pruebas unitarias para las uniones de sesión/autenticación, panel de control, nodo, VM, LXC, tareas, red, almacenamiento, usuarios, copias de seguridad, seguridad y localización, pero aún falta una cobertura más amplia de pruebas unitarias, de instrumentación y de capturas de pantalla.

## Limitaciones conocidas

Se espera que estas limitaciones existan en `v0.1.0-beta.1` y deben reflejarse en las notas de la versión:

- El acceso a la consola de invitados está fuera del alcance de la primera beta.
- La edición de configuración de VM y LXC está fuera del alcance de la primera beta.
- La creación, eliminación y reversión de instantáneas están fuera del alcance de la primera beta.
- La creación, restauración, descarga y eliminación de copias de seguridad están fuera del alcance de la primera beta.
- Los flujos de creación, edición y eliminación de usuarios están fuera del alcance de la primera beta.
- Las acciones de reinicio, apagado, terminal y servicios avanzados de nodo están fuera del alcance de la primera beta.
- Las áreas de almacenamiento, red, usuarios, copias de seguridad y clúster son principalmente de solo lectura.
- La gestión de inicio de sesión con contraseña específica de TFA no está completa; el inicio de sesión con token de API es la solución alternativa preferida para la beta cuando procede.
- Se requieren pruebas de humo de la primera beta antes de etiquetar la beta; la matriz de validación más amplia continúa después de la primera beta pública.

## Pila tecnológica

- Kotlin
- Jetpack Compose
- Material 3
- AndroidX Navigation Compose
- Retrofit with Gson
- OkHttp
- Kotlin Coroutines
- `EncryptedSharedPreferences` para credenciales guardadas

## Arquitectura

El proyecto es actualmente ligero en lugar de estar fuertemente estructurado por capas:

- `presentation/`: pantallas Compose, navegación y `MainViewModel`
- `data/api/`: definiciones de API Retrofit más configuración de autenticación/cliente
- `data/model/`: modelos de API
- `data/security/`: almacenamiento cifrado de credenciales más contratos de almacén de credenciales
- `data/session/`: propiedad de la sesión de autenticación actual y contrato de servicio de sesión
- `presentation/auth/`: estado de interfaz de autenticación/controlador de sesión
- `data/dashboard/`, `data/node/`, `data/vm/`, `data/lxc/`, `data/task/`, `data/network/`, `data/storage/`, `data/user/`, `data/backup/`, `data/cluster/`: primeras secciones verticales respaldadas por repositorios

La aplicación está avanzando hacia secciones de funcionalidades respaldadas por repositorios. El panel de control, el detalle de nodo, la lista/detalle de VM, la lista/detalle de LXC, las tareas, la red, el almacenamiento, los usuarios, las copias de seguridad y el estado del clúster ahora usan repositorios dedicados y ViewModels de funcionalidades. El detalle de VM ahora incluye datos de configuración e instantáneas de solo lectura a través de la ruta de repositorio. El estado de autenticación/acceso al servicio de API ahora se encuentra detrás de `AuthSessionController`, los datos de inicio de sesión guardados se encuentran detrás de `CredentialStore`, y `MainViewModel` sigue siendo propietario de las preocupaciones de nodo en caché y diálogos globales. El detalle de nodo ahora conserva la identidad del nodo al abrir vistas de VM, LXC, almacenamiento, red y tareas.

## Distribución del repositorio

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

## Configuración

Requisitos previos:

- JDK 17
- Android Studio o una instalación local de Android SDK

Configuración local:

```bash
./gradlew tasks
```

Si quieres compilar localmente, crea tu propio `local.properties` o configura el Android SDK a través de Android Studio. Ese archivo no se rastrea intencionadamente.

## Compilación y verificación

Comandos habituales en un entorno Android real:

```bash
./gradlew assembleDebug
./gradlew assembleRelease
./gradlew test
./gradlew lint
./gradlew compileDebugAndroidTestKotlin
```

Para candidatos a versión beta, ejecuta la comprobación consolidada:

```bash
./scripts/beta-gate.sh v0.1.0-beta.1
```

Para la comprobación final previa a la etiqueta, exige evidencia de humo completada en la misma comprobación:

```bash
REQUIRE_BETA_QA_COMPLETE=true ./scripts/beta-gate.sh v0.1.0-beta.1
```

Para resumir la evidencia manual de humo de la beta mientras el control de calidad está en curso:

```bash
./scripts/beta-qa-status.sh
```

Antes de etiquetar, ejecuta el mismo resumen como comprobación estricta de finalización:

```bash
./scripts/beta-qa-status.sh --require-complete
```

Las pruebas de humo de Android Studio/emulador se encuentran en [`app/src/androidTest`](app/src/androidTest/README.md) y se pueden ejecutar con:

```bash
./gradlew connectedDebugAndroidTest
```

Estas pruebas usan datos de marcador de posición y no se conectan a un host Proxmox. Pueden reinstalar el APK de depuración o borrar las credenciales de prueba guardadas, así que ejecútalas antes o después de las sesiones de humo manuales con sesión iniciada, en lugar de durante una de ellas.

La auditoría del 2026-06-06 verificó `test`, `lint` y `assembleDebug` localmente con JDK 17 y un Android SDK configurado a través de variables de entorno. La cobertura unitaria actualmente comienza con las uniones de sesión/autenticación, nodo, VM, LXC, tareas y localización; aún se necesita una cobertura conductual más amplia.

## Flujo de trabajo del proyecto

- Hoja de ruta y plan de recuperación: [`docs/development-cycle-2026.md`](docs/development-cycle-2026.md)
- Plan de versión beta: [`docs/beta-release-plan.md`](docs/beta-release-plan.md)
- Auditoría actual y estado de funcionalidades: [`docs/project-audit-2026-06-06.md`](docs/project-audit-2026-06-06.md)
- Guía de contribución: [`CONTRIBUTING.md`](CONTRIBUTING.md)
- Política de seguridad: [`SECURITY.md`](SECURITY.md)
- Registro de cambios: [`CHANGELOG.md`](CHANGELOG.md)

Política de ramas:

- `main` es la rama de lanzamiento para etiquetas beta públicas y correcciones urgentes listas para lanzamiento.
- `dev` es la rama de integración para el siguiente ciclo beta.
- Las ramas de funcionalidades y correcciones deben ser de corta duración y apuntar a `dev`, salvo que sean correcciones urgentes de lanzamiento.
- Las etiquetas beta públicas se cortan desde `main` después de que `dev` se promocione y supere la comprobación beta.

Se espera que las solicitudes de incorporación de cambios ejecuten `./gradlew test`, `./gradlew lint`, `./gradlew assembleDebug` y `./gradlew assembleRelease`. El flujo de trabajo de GitHub Actions en `.github/workflows/android.yml` aplica la misma base para solicitudes de incorporación de cambios y envíos a `main` y `dev`.

## Notas de seguridad

- Las credenciales de contraseña y las partes de tokens de API pueden almacenarse en preferencias compartidas cifradas.
- HTTPS usa la validación TLS de la plataforma de forma predeterminada.
- Los servidores HTTPS autofirmados pueden usar una CA importada de confianza de Android o una huella de certificado SHA-256 fijada.
- La verificación SSL solo se puede deshabilitar en compilaciones de depuración para servidores de laboratorio de confianza. Las compilaciones de lanzamiento requieren validación TLS de la plataforma o una huella de certificado SHA-256 configurada.
- Las compilaciones de lanzamiento ya no solicitan tráfico de texto claro en toda la aplicación. Las compilaciones de depuración aún permiten HTTP para pruebas locales.
- Consulta [`SECURITY.md`](SECURITY.md) para el comando `openssl` recomendado y las notas de política de confianza en el primer uso.

## Notas de privacidad

- La aplicación no incluye telemetría ni análisis.
- Los detalles de host guardados, nombres de usuario, contraseñas, ID de tokens de API y secretos de tokens de API se almacenan localmente a través de preferencias compartidas cifradas cuando el guardado de credenciales está habilitado.
- La aplicación no envía credenciales intencionadamente a ningún lugar excepto al punto de conexión de Proxmox VE configurado.
- Los informes de errores deben eliminar contraseñas, tokens de API, tickets, cookies, tokens CSRF, claves privadas, detalles de IP públicas que no deban compartirse y nombres de host sensibles.

## Carencias conocidas

- La cobertura automatizada de pruebas es reducida y actualmente se centra en las uniones de sesión/autenticación, panel de control, nodo, VM, LXC, tareas, red, almacenamiento, usuarios, copias de seguridad, seguridad y localización.
- La CI está presente para compilación, lint y pruebas unitarias.
- Varias dependencias y partes de la interfaz sugieren trabajo de funcionalidades abandonado o sin terminar.
- Algunos ajustes siguen estando planificados o deshabilitados hasta que se implemente su comportamiento en tiempo de ejecución.

## Próximos pasos recomendados

1. Verifica la aplicación en un SDK/emulador Android real y registra qué pantallas/acciones realmente tienen éxito con una instancia de Proxmox.
2. Valida la orientación sobre certificados autofirmados con dispositivos Proxmox reales y proxies inversos.
3. Ejecuta la matriz de humo de `v0.1.0-beta.1` y corrige solo los bloqueadores de la beta antes de etiquetar.
4. Promueve el registro de cambios de `Unreleased` a `v0.1.0-beta.1` una vez recopilada la evidencia de control de calidad.
5. Continúa trasladando el trabajo de paridad aplazado a trenes de lanzamiento centrados posteriores a la beta.

Para una hoja de ruta concreta por etapas, consulta [`docs/revival-plan.md`](docs/revival-plan.md). Para el ciclo de desarrollo más amplio de producto, refactorización, control de calidad, flujo de trabajo y comunidad, consulta [`docs/development-cycle-2026.md`](docs/development-cycle-2026.md). Para la lista de comprobación de la primera beta oficial, consulta [`docs/beta-release-plan.md`](docs/beta-release-plan.md).
