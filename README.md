# 🏦 CityBank Stock Forage Demo

A robust Java application that fetches real-time stock market data via the Yahoo Finance API and visualizes it using a dynamic **JavaFX Line Chart**.

## ✨ Key Features

- **Real-time Data Streaming:** Automatically queries stock symbols (default: Dow Jones `^DJI`) from Yahoo Finance.
- **Live Visualization:** Features a responsive JavaFX LineChart that updates every 5 seconds.
- **Resilient Architecture:** Implements a failover mechanism that generates **Mock Data** during API rate limits or connection issues to ensure visual continuity.
- **Asynchronous Processing:** Utilizes `ScheduledExecutorService` and `Platform.runLater` to handle background data fetching without freezing the UI.

## 🛠️ Tech Stack

* **Language:** [Java 21](https://www.oracle.com/java/)
* **Build Tool:** [Gradle](https://gradle.org/)
* **UI Framework:** [JavaFX 21](https://openjfx.io/)
* **Library:** [YahooFinanceAPI](https://financequotes-api.com/)

## 🚀 Getting Started

Follow these steps to run the project locally:

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/BCemreD/CitiForageDemo.git](https://github.com/BCemreD/CitiForageDemo.git)
   cd CitiForageDemo

   Build the project:

Bash

./gradlew build
Run the application:

Bash

./gradlew run
📊 How it Works
The application starts a background worker that pings Yahoo Finance every 5 seconds. If the connection is successful, the actual price is plotted on the graph. If the connection fails, the system switches to simulation mode, generating random price points near the last known value to demonstrate the UI's charting capabilities.

📝 Implementation Details
User-Agent Spoofing: Configured to bypass basic bot detection on the Yahoo Finance API.

Main Class Wrapper: Includes a static wrapper class to resolve JavaFX runtime component issues common in modern JDKs.

Developed as a technical demonstration for CityBank Demo Task.


---

### GitHub'a Nasıl Gönderirsin?

Eğer dosyayı oluşturduysan, şu komutlarla GitHub'daki yerini almasını sağlayabilirsin:

```bash
git add README.md
git commit -m "Update README to English for professional look"
git push
