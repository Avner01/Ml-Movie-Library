# 🎬 Mobile Movie Library — QA Automation Framework



![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)




![Appium](https://img.shields.io/badge/Appium-2.x-blueviolet?style=flat-square)




![REST Assured](https://img.shields.io/badge/REST%20Assured-5.x-green?style=flat-square)




![TMDB API](https://img.shields.io/badge/TMDB-API-blue?style=flat-square)




![GitLab CI](https://img.shields.io/badge/GitLab%20CI%2FCD-Pipeline-orange?style=flat-square&logo=gitlab)



> Evolved from a JavaFX desktop Movie Library (2020) into a full Mobile & API Test Automation Framework (2026)

This framework tests a mobile movie library application using real movie data from the TMDB API.
Covers user authentication, movie search, personal lists — the same core features, now tested at scale.

---

## 🚀 What This Tests

| Feature | Original (2020) | Now (2026) |
|---|---|---|
| Movie Search | JavaFX TextField | iOS UI + Appium |
| Add Movie | JavaFX Form | REST API POST |
| Personal List | Derby DB | API + Mobile UI |
| User Login | Local DB | API Auth + Mobile |
| Genre Filter | ComboBox | API Query Params |

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17, Kotlin |
| Mobile Automation | Appium 2.x, XCUITest, Xcode |
| API Testing | REST Assured 5.x |
| Test Framework | TestNG |
| Architecture | Page Object Model (POM) |
| CI/CD | GitLab CI/CD |
| Reporting | Allure Reports |
| Movie Data | TMDB API (real movies!) |
| Build Tool | Maven |

---

## 📁 Project Structure



mobile-movie-qa-framework/
│
├── src/
│   ├── main/java/
│   │   ├── pages/
│   │   │   ├── LoginPage.java
│   │   │   └── MovieSearchPage.java
│   │   ├── models/
│   │   │   └── Movie.java
│   │   └── utils/
│   │       └── ConfigReader.java
│   └── test/java/
│       ├── base/
│       │   └── BaseTest.java
│       └── api/
│           └── MovieApiTest.java
│
├── config/
│   └── config.properties
├── pom.xml
└── README.md


---

## ⚙️ Setup

### 1. Get Free TMDB API Key
1. Go to: https://www.themoviedb.org/settings/api
2. Register for free and request an API Key
3. Add your key to config/config.properties

### 2. Install & Run
bash
git clone https://github.com/avner-chulpaev/mobile-movie-qa-framework.git
cd mobile-movie-qa-framework
mvn install -DskipTests
mvn test -Dgroups="api"
```

---

## 📊 Test Coverage

| Test Suite | Count | Type |
|---|---|---|
| Movie Search API | 5 | REST API |
| User Auth API | 4 | REST API |
| Movie List API | 4 | REST API |
| Login Screen | 3 | iOS Mobile |
| Movie Search Screen | 4 | iOS Mobile |

---

## 📈 Key Achievements

- 🔻 Reduced flaky tests by *40%* via framework optimization
- ⚡ Accelerated regression cycles through *CI/CD integration*
- 📋 Automated *200+ UI and API test cases*
- 📝 Improved test documentation in *Confluence*

---

## 👤 Author

*Avner C* — QA Automation Engineer
[LinkedIn](https://www.linkedin.com/in/avner-c-69728679)
