[English](README.md) | [简体中文](README.zh-CN.md) | [Deutsch](README.de.md) | [Español](README.es.md) | **Français** | [日本語](README.ja.md) | [한국어](README.ko.md)

# Proxmox-VE-Android

> Ce projet est issu de l'amont [5p00kyy/Proxmox-VE-Mobile](https://github.com/5p00kyy/Proxmox-VE-Mobile), recréé en tant que nouveau projet indépendant, et non plus comme un fork de cet amont. Par rapport au code amont, ce projet comprend : la traduction complète en chinois simplifié, le changement de langue sur la page de connexion, le repli des cartes de liste et des ajustements d'interface, la console LXC/VM (WebView), ainsi que d'autres modifications.

`Proxmox VE Android` est un client Android permettant de consulter et de piloter un environnement Proxmox VE depuis un téléphone ou une tablette.

La ligne bêta actuelle se concentre sur les flux de travail courants d'un opérateur mobile : connexion, gestion des connexions sécurisées, visibilité du tableau de bord, navigation entre les nœuds, actions de cycle de vie des VM/LXC, suivi des tâches et inspection en lecture seule de l'infrastructure. Il s'agit d'une application Kotlin/Jetpack Compose à module unique, qui évolue d'appels Retrofit appartenant aux écrans vers des tranches fonctionnelles adossées à des dépôts. Ce README décrit le dépôt tel qu'il existe aujourd'hui, et non l'état final visé.

## Nouveautés de la version 1.0.0

Par rapport à la première bêta, la 1.0.0 est la première version stable : [1.0.0](https://github.com/iso-lib/Proxmox-VE-Android/releases/tag/v1.0.0).

- **Première version stable.** Version 1.0.0 (versionCode 4), distribuée via GitHub Releases et signée avec la clé de publication du projet. Les versions futures seront publiées automatiquement dès qu'une étiquette de version (`v*`) sera poussée.
- **Console des invités.** Une console WebView légère pour les invités LXC (xterm.js) et VM (noVNC), qui charge les pages de console propres à Proxmox ; les sessions par mot de passe s'authentifient avec un cookie de ticket de session.
- **Paramètres qui fonctionnent.** Thème sombre/clair, intervalle d'actualisation de 10 à 60 secondes pour les listes du tableau de bord, des VM, des conteneurs et des tâches, connexion automatique avec les identifiants enregistrés et un lien « Signaler un problème » vers GitHub Issues. Les interrupteurs biométrique et de notifications n'étaient que des espaces réservés sans implémentation, ils ont donc été supprimés.
- **Localisation.** L'application est traduite en 10 langues : anglais, allemand, espagnol, chinois simplifié, français, italien, portugais, russe, japonais et coréen. Ce README est disponible en 7 langues.
- **Améliorations de l'interface.** Cartes de liste repliables, carte d'état système compacte (CPU, mémoire utilisée/totale, durée de fonctionnement), actions rapides horizontales, sélecteur de langue sur l'écran de connexion et actions de détail VM/LXC plus ordonnées.
- **Connexion et TLS.** Normalisation HTTPS pour la saisie hôte/IP, flux de confiance à la première utilisation pour les certificats auto-signés avec épinglage d'empreinte SHA-256, et connexion par mot de passe ou jeton d'API.
- **Nouveau nom.** Renommé de Proxmox VE Mobile en Proxmox VE Android : nom de l'application, en-tête des paramètres et boîte de dialogue À propos.

## Version bêta

La première bêta officielle prévue est `v0.1.0-beta.1`.

Plan de distribution :

- GitHub Releases sera le premier chemin de téléchargement de la bêta.
- L'APK bêta sera joint à la version une fois les contrôles automatisés et les tests de fumée de la première bêta réussis.
- L'empaquetage pour le Play Store et F-Droid est reporté après la première bêta GitHub.

Cibles d'environnement prises en charge pour la première bêta :

- Android API 24 ou ultérieure.
- Proxmox VE 8.x est la cible principale des tests de fumée.
- Les nœuds autonomes et les petits clusters sont dans le périmètre.
- La gestion de Proxmox Backup Server n'est pas dans le périmètre de la première bêta.

### Installer un APK bêta

Lorsque `v0.1.0-beta.1` est publiée :

1. Ouvrez la GitHub Release correspondant au tag de la bêta.
2. Téléchargez l'`proxmox-ve-mobile-v0.1.0-beta.1-release.apk` joint.
3. Vérifiez que les notes de version indiquent que l'APK est une compilation bêta signée.
4. Sur Android, autorisez l'installation depuis le navigateur ou le gestionnaire de fichiers utilisé pour télécharger l'APK.
5. Ouvrez l'APK et suivez l'invite d'installation d'Android.

Installez uniquement les APK joints aux GitHub Releases de ce dépôt. Les APK de débogage et les artefacts de flux de travail non signés servent uniquement à la validation du projet et ne doivent pas être considérés comme des téléchargements bêta publics.

## Captures d'écran

Les captures d'écran et les courts enregistrements d'écran sont utiles pour `v0.1.0-beta.1`, mais ils doivent être omis si aucune capture sûre pour la publication n'est disponible. Parmi les bonnes captures figurent :

- La connexion avec les contrôles TLS/empreinte visibles, en utilisant un texte d'hôte d'exemple non sensible.
- Le tableau de bord avec uniquement des ressources de laboratoire génériques ou jetables.
- Les listes/détails de VM et les listes/détails de LXC avec des noms d'invités jetables.
- Les détails/journaux de tâches avec des UPID, des noms d'utilisateur, des noms de nœuds, des IP et des valeurs de journal sensibles expurgés, ou capturés depuis un laboratoire jetable.
- Une tranche d'administration en lecture seule, comme le stockage, le réseau, les utilisateurs, les sauvegardes ou le cluster, en utilisant des données d'exemple assainies.

Ne publiez pas de captures d'écran révélant des noms d'hôtes privés, des adresses IP publiques ou privées, des noms d'utilisateur, des notes de sauvegarde, des journaux de tâches contenant des détails d'environnement, des jetons d'API, des tickets, des cookies, des empreintes liées à un serveur privé, ou des chemins propres à un bureau ou à un utilisateur. Si aucune capture réelle assainie n'est disponible, utilisez un laboratoire Proxmox jetable ou omettez les captures d'écran jusqu'à ce que des médias sûrs pour la publication puissent être capturés.

Parmi les exemples sûrs figurent un texte d'hôte fictif, des noms de nœuds génériques tels que `pve-demo`, des noms d'invités jetables tels que `demo-vm-101`, des UPID expurgés, ainsi que des vues de stockage ou de cluster issues d'un laboratoire jetable.

Avant de joindre des captures d'écran ou des enregistrements à une GitHub Release, consignez un manifeste de médias publics dans les notes de version ou dans [`docs/beta-smoke-qa.md`](docs/beta-smoke-qa.md). Le manifeste doit énumérer chaque nom de fichier, l'écran ou le flux de travail, le type de source, les identifiants assainis utilisés, la légende ou le texte alternatif, et l'état de la QA. Parmi les noms de fichiers candidats figurent `login-tls.png`, `dashboard.png`, `vm-detail.png`, `lxc-detail.png`, `task-detail.png` et `read-only-admin.png`.

## Localisation

L'application fournit des ressources de chaînes pour les paramètres régionaux suivants :

- Par défaut (`values`) : anglais
- `values-de` : allemand
- `values-es` : espagnol
- `values-zh-rCN` : chinois simplifié
- `values-fr` : français
- `values-it` : italien
- `values-pt` : portugais
- `values-ru` : russe
- `values-ja` : japonais
- `values-ko` : coréen

La localisation repose sur les ressources via les fichiers Android `strings.xml`. Les nouveaux ajouts de paramètres régionaux doivent préserver les noms de chaînes, les espaces réservés, les spécificateurs de format et l'échappement, afin que les traductions restent identiques sur le plan comportemental aux ressources par défaut.

## État actuel

Ce qui semble fonctionner, d'après l'inspection du code :

- Connexion manuelle à un hôte et à un port Proxmox fournis par l'utilisateur.
- Connexion par mot de passe ou par jeton d'API à un hôte et à un port Proxmox fournis par l'utilisateur.
- Stockage chiffré facultatif des identifiants, avec remplissage automatique au lancement suivant.
- Épinglage facultatif de l'empreinte de certificat SHA-256 pour les serveurs HTTPS auto-signés.
- Liste des nœuds du tableau de bord, affichage de l'état de base, résumé de l'activité des tâches, interrogation périodique et actualisation manuelle via un ViewModel fonctionnel adossé à un dépôt.
- Chargement de l'état détaillé d'un nœud via un ViewModel fonctionnel adossé à un dépôt, accessible depuis les cartes de nœuds du tableau de bord avec l'identité du nœud préservée.
- Chargement des listes/détails de VM, avec démarrage, arrêt gracieux, arrêt forcé, redémarrage, suppression, avis de tâches renvoyées, liens directs vers les détails de tâches, historique des tâches filtré par VMID, et visibilité en lecture seule de la configuration et des instantanés, via des ViewModels fonctionnels adossés à des dépôts.
- Chargement des listes de conteneurs, avec démarrage, arrêt gracieux, arrêt forcé, redémarrage, suppression, avis de tâches renvoyées, liens directs vers les détails de tâches, et historique des tâches filtré par VMID, via un ViewModel fonctionnel adossé à un dépôt.
- Visibilité de l'état détaillé et des instantanés d'un conteneur via un ViewModel fonctionnel adossé à un dépôt, accessible depuis la liste LXC avec l'identité du nœud préservée.
- Chargement de l'historique des tâches via un ViewModel fonctionnel adossé à un dépôt, avec sélection du nœud, filtres d'état/type/VMID, détail/état, consultation des journaux de tâches et demandes d'arrêt des tâches en cours.
- Liste du stockage et consultation en lecture seule du contenu du stockage via un ViewModel fonctionnel adossé à un dépôt, avec états de chargement, d'erreur, d'actualisation et de vide.
- Liste des interfaces réseau via un ViewModel fonctionnel adossé à un dépôt, avec états de chargement, d'erreur, de nouvel essai, d'actualisation et de sélection du nœud mis en cache.
- Liste des utilisateurs via un ViewModel fonctionnel adossé à un dépôt, avec états de chargement, d'erreur, de nouvel essai, d'actualisation et de vide.
- Liste des sauvegardes via un ViewModel fonctionnel adossé à un dépôt, avec sélection du nœud, filtrage par stockage, avertissements de stockage partiel, nouvel essai, actualisation et états de vide.
- État du cluster via un écran en lecture seule adossé à un dépôt, avec l'état du quorum, des votes et des membres des nœuds.

Ce qui est manifestement incomplet ou seulement partiellement implémenté :

- Certains écrans restent des espaces réservés ou en lecture seule pendant que les flux de travail prévus sont construits délibérément.
- Certains paramètres ne sont qu'un état d'interface local et ne sont ni persistés ni reliés au comportement d'exécution.
- Les actions avancées telles que le redémarrage/l'arrêt/l'interpréteur de commandes d'un nœud, la mutation des sauvegardes, la mutation des utilisateurs, l'édition de la console/configuration des VM et les opérations de ressources/console des détails de conteneurs sont hors du périmètre de la première bêta.
- Les contrôles de sauvegarde, d'utilisateur, de paramètres et de détails LXC qui sont hors du périmètre de la bêta sont désactivés ou étiquetés en lecture seule plutôt que présentés comme fonctionnels.
- La solution de repli TLS non sécurisée explicite est réservée au débogage ; l'épinglage de l'empreinte de certificat SHA-256 est disponible pour les serveurs auto-signés et doit être préféré.
- La couverture de tests unitaires existe pour les jointures session/authentification, tableau de bord, nœud, VM, LXC, tâche, réseau, stockage, utilisateur, sauvegarde, sécurité et localisation, mais une couverture plus large des tests unitaires, d'instrumentation et de captures d'écran fait encore défaut.

## Limitations connues

Ces limitations sont attendues pour `v0.1.0-beta.1` et doivent être reflétées dans les notes de version :

- L'accès à la console des invités est hors du périmètre de la première bêta.
- L'édition de la configuration des VM et des LXC est hors du périmètre de la première bêta.
- La création, la suppression et la restauration des instantanés sont hors du périmètre de la première bêta.
- La création, la restauration, le téléchargement et la suppression des sauvegardes sont hors du périmètre de la première bêta.
- Les flux de création, d'édition et de suppression des utilisateurs sont hors du périmètre de la première bêta.
- Le redémarrage, l'arrêt, l'interpréteur de commandes et les actions de service avancées d'un nœud sont hors du périmètre de la première bêta.
- Les domaines du stockage, du réseau, des utilisateurs, des sauvegardes et du cluster sont principalement en lecture seule.
- La gestion de la connexion par mot de passe propre à l'authentification à deux facteurs (TFA) n'est pas complète ; la connexion par jeton d'API est la solution de contournement bêta préférée, le cas échéant.
- Les tests de fumée de la première bêta sont requis avant l'étiquetage de la bêta ; la matrice de validation plus large se poursuit après la première bêta publique.

## Pile technologique

- Kotlin
- Jetpack Compose
- Material 3
- AndroidX Navigation Compose
- Retrofit avec Gson
- OkHttp
- Kotlin Coroutines
- `EncryptedSharedPreferences` pour les identifiants enregistrés

## Architecture

Le projet est actuellement léger plutôt que fortement structuré en couches :

- `presentation/` : écrans Compose, navigation et `MainViewModel`
- `data/api/` : définitions d'API Retrofit ainsi que configuration de l'authentification/du client
- `data/model/` : modèles d'API
- `data/security/` : stockage chiffré des identifiants ainsi que contrats du magasin d'identifiants
- `data/session/` : propriété de la session d'authentification actuelle et contrat du service de session
- `presentation/auth/` : état de l'interface d'authentification/contrôleur de session
- `data/dashboard/`, `data/node/`, `data/vm/`, `data/lxc/`, `data/task/`, `data/network/`, `data/storage/`, `data/user/`, `data/backup/`, `data/cluster/` : premières tranches verticales adossées à des dépôts

L'application évolue vers des tranches fonctionnelles adossées à des dépôts. Le tableau de bord, le détail des nœuds, les listes/détails de VM, les listes/détails de LXC, les tâches, le réseau, le stockage, les utilisateurs, les sauvegardes et l'état du cluster utilisent désormais des dépôts dédiés et des ViewModels fonctionnels. Le détail des VM comprend désormais des données de configuration et d'instantanés en lecture seule via le chemin du dépôt. L'état d'authentification et l'accès au service d'API se trouvent désormais derrière `AuthSessionController`, les données de connexion enregistrées se trouvent derrière `CredentialStore`, et `MainViewModel` conserve la propriété des préoccupations liées au nœud mis en cache et aux dialogues globaux. Le détail d'un nœud préserve désormais l'identité du nœud lors de l'ouverture des vues de VM, de LXC, de stockage, de réseau et de tâches.

## Arborescence du dépôt

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

## Configuration

Prérequis :

- JDK 17
- Android Studio ou une installation locale du SDK Android

Configuration locale :

```bash
./gradlew tasks
```

Si vous souhaitez compiler localement, créez votre propre `local.properties` ou configurez le SDK Android via Android Studio. Ce fichier n'est volontairement pas suivi.

## Compilation et vérification

Commandes typiques dans un environnement Android réel :

```bash
./gradlew assembleDebug
./gradlew assembleRelease
./gradlew test
./gradlew lint
./gradlew compileDebugAndroidTestKotlin
```

Pour les candidats à la version bêta, exécutez le contrôle consolidé :

```bash
./scripts/beta-gate.sh v0.1.0-beta.1
```

Pour la vérification finale avant l'étiquetage, exigez des preuves de fumée terminées dans le même contrôle :

```bash
REQUIRE_BETA_QA_COMPLETE=true ./scripts/beta-gate.sh v0.1.0-beta.1
```

Pour résumer les preuves de fumée bêta manuelles pendant que la QA est en cours :

```bash
./scripts/beta-qa-status.sh
```

Avant l'étiquetage, exécutez le même résumé comme vérification stricte d'achèvement :

```bash
./scripts/beta-qa-status.sh --require-complete
```

Les tests de fumée Android Studio/émulateur se trouvent sous [`app/src/androidTest`](app/src/androidTest/README.md) et peuvent être exécutés avec :

```bash
./gradlew connectedDebugAndroidTest
```

Ces tests utilisent des données fictives et ne se connectent pas à un hôte Proxmox. Ils peuvent réinstaller l'APK de débogage ou effacer les identifiants de test enregistrés, donc exécutez-les avant ou après les sessions de fumée manuelles avec connexion, plutôt que pendant l'une d'elles.

L'audit du 2026-06-06 a vérifié `test`, `lint` et `assembleDebug` localement avec JDK 17 et un SDK Android configuré via des variables d'environnement. La couverture unitaire commence actuellement avec les jointures session/authentification, nœud, VM, LXC, tâche et localisation ; une couverture comportementale plus large est encore nécessaire.

## Flux de travail du projet

- Feuille de route et plan de reprise : [`docs/development-cycle-2026.md`](docs/development-cycle-2026.md)
- Plan de version bêta : [`docs/beta-release-plan.md`](docs/beta-release-plan.md)
- Audit actuel et état des fonctionnalités : [`docs/project-audit-2026-06-06.md`](docs/project-audit-2026-06-06.md)
- Guide du contributeur : [`CONTRIBUTING.md`](CONTRIBUTING.md)
- Politique de sécurité : [`SECURITY.md`](SECURITY.md)
- Journal des modifications : [`CHANGELOG.md`](CHANGELOG.md)

Politique des branches :

- `main` est la branche de version pour les tags bêta publics et les correctifs prêts pour la version.
- `dev` est la branche d'intégration pour le cycle bêta suivant.
- Les branches de fonctionnalités et de correctifs doivent être de courte durée et cibler `dev`, sauf s'il s'agit de correctifs de version.
- Les tags bêta publics sont créés depuis `main` après la promotion de `dev` et la réussite du contrôle bêta.

Les pull requests doivent exécuter `./gradlew test`, `./gradlew lint`, `./gradlew assembleDebug` et `./gradlew assembleRelease`. Le flux de travail GitHub Actions dans `.github/workflows/android.yml` impose la même base pour les pull requests et les poussées vers `main` et `dev`.

## Notes de sécurité

- Les identifiants par mot de passe et les éléments de jeton d'API peuvent être stockés dans des préférences partagées chiffrées.
- HTTPS utilise par défaut la validation TLS de la plateforme.
- Les serveurs HTTPS auto-signés peuvent utiliser une AC importée de confiance Android ou une empreinte de certificat SHA-256 épinglée.
- La vérification SSL ne peut être désactivée que dans les compilations de débogage pour les serveurs de laboratoire de confiance. Les compilations de version exigent la validation TLS de la plateforme ou une empreinte de certificat SHA-256 configurée.
- Les compilations de version ne demandent plus de trafic en clair à l'échelle de l'application. Les compilations de débogage autorisent toujours HTTP pour les tests locaux.
- Voir [`SECURITY.md`](SECURITY.md) pour la commande `openssl` recommandée et les notes de politique de confiance à la première utilisation.

## Notes de confidentialité

- L'application ne comprend ni télémétrie ni analytique.
- Les détails d'hôte enregistrés, les noms d'utilisateur, les mots de passe, les identifiants de jeton d'API et les secrets de jeton d'API sont stockés localement via des préférences partagées chiffrées lorsque l'enregistrement des identifiants est activé.
- L'application n'envoie intentionnellement les identifiants nulle part ailleurs que vers le point de terminaison Proxmox VE configuré.
- Les rapports de bogues doivent supprimer les mots de passe, les jetons d'API, les tickets, les cookies, les jetons CSRF, les clés privées, les détails d'IP publiques qui ne doivent pas être partagés, et les noms d'hôtes sensibles.

## Lacunes connues

- La couverture de tests automatisés est étroite et se concentre actuellement sur les jointures session/authentification, tableau de bord, nœud, VM, LXC, tâche, réseau, stockage, utilisateur, sauvegarde, sécurité et localisation.
- L'IC est présente pour la compilation, le lint et les tests unitaires.
- Plusieurs dépendances et parties de l'interface suggèrent un travail de fonctionnalité abandonné ou inachevé.
- Certains paramètres restent prévus ou désactivés jusqu'à ce que leur comportement d'exécution soit implémenté.

## Prochaines étapes recommandées

1. Vérifiez l'application sur un SDK Android/émulateur réel et consignez quels écrans/actions réussissent réellement face à une instance Proxmox.
2. Validez les conseils relatifs aux certificats auto-signés face à de véritables appliances Proxmox et proxys inverses.
3. Exécutez la matrice de fumée `v0.1.0-beta.1` et corrigez uniquement les bloqueurs de la bêta avant l'étiquetage.
4. Promouvez le journal des modifications de `Unreleased` à `v0.1.0-beta.1` une fois les preuves de QA collectées.
5. Continuez à déplacer le travail de parité différé vers des trains de versions ciblés après la bêta.

Pour une feuille de route échelonnée concrète, voir [`docs/revival-plan.md`](docs/revival-plan.md). Pour le cycle de développement plus large du produit, de la refactorisation, de la QA, du flux de travail et de la communauté, voir [`docs/development-cycle-2026.md`](docs/development-cycle-2026.md). Pour la liste de vérification de la première bêta officielle, voir [`docs/beta-release-plan.md`](docs/beta-release-plan.md).
