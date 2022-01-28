# GitHubRepo

## Overview
This app provides an overview about a GitHub's public repository.

## Features
* Dashboard screen with watchers, subscribers, forks and opened issues count
* List screen of recent issues with issue title and status
* Issue details screen with user and created at date
* About screen with title, description and link of the repo

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:wellingtonrib/github_repo.git
```

## Configuration
You can customize the repo in AppConfig class.
If you reach the GitHub Api limits per IP, so you need to a ACCESS_TOKEN
Create a new one in: https://github.com/settings/tokens/new
Create `keystore.properties` with the following info:
```gradle
ACCESS_TOKEN='YOUR_ACCESS_TOKEN'
```

## Next steps
* Improve state view management
* Increase test coverage and report
* Make all offline-first
* Use new android components (Paginate, Hilt and Compose)

## Maintainer
This project is mantained by:
* [Wellington Ribeiro](http://github.com/wellingtonrib)
