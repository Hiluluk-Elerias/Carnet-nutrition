# Carnet nutrition

Application web personnelle de suivi alimentaire, dans l'esprit de MacroFactor :

- journal des repas : recherche Open Food Facts, scan de code-barres, photo analysée par IA, saisie manuelle ;
- calories, protéines, lipides, glucides et fibres par rapport aux objectifs du jour ;
- poids tendance et dépense énergétique adaptative (calée sur l'historique MacroFactor) ;
- import de l'export MacroFactor (totaux journaliers, pesées, dépense, détail des repas) ;
- fonctionne hors ligne ; existe en version web et en appli Android (APK).

Les données ne sont pas dans ce dépôt public : elles sont enregistrées dans le dépôt privé
`Carnet-nutrition-data` (`jours/AAAA-MM.json` et `profil.json`) grâce à un jeton GitHub saisi dans l'appli.
La clé API de la photo IA reste sur chaque appareil et n'est jamais envoyée sur GitHub.

## Mise en ligne

Settings → Pages → Source : « Deploy from a branch », branche `main`, dossier `/ (root)`.
L'appli est ensuite disponible sur `https://hiluluk-elerias.github.io/Carnet-nutrition/`.

## Appli Android (APK)

Le dossier `apk/` contient une coque Android (Capacitor) qui ouvre la version en ligne de l'appli,
avec l'accès à l'appareil photo pour le scan et la photo IA.

L'APK est compilé par GitHub Actions (`.github/workflows/apk.yml`) à chaque modification du dossier `apk/`,
ou à la main depuis l'onglet Actions (« Run workflow »). Il est publié dans la release `android`.
Les améliorations de l'appli passent par GitHub Pages : pas besoin de réinstaller l'APK.

Pour signer toujours avec la même clé (et pouvoir mettre à jour l'APK sans le désinstaller),
ajouter les secrets `KEYSTORE_BASE64` et `KEYSTORE_PASSWORD` (alias `carnet`), comme pour le carnet de musculation.
