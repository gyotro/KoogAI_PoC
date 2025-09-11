# KoogAI PoC

A Kotlin-based Proof of Concept (PoC) application demonstrating the integration of Koog Agents with OpenAI-compatible language models to create an interactive Weather Assistant.

## Features

- Integration with Koog Agents framework
- Support for local LLM models through OpenAI-compatible APIs (e.g., Ollama with LLaMA 3)
- MCP (Model Control Protocol) tool registry integration
- Asynchronous processing with Kotlin Coroutines

## Prerequisites

- Java 21 or later
- Kotlin 2.2.0 or later
- Gradle 8.0 or later
- Access to an OpenAI-compatible LLM API (local or remote)

## Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd KoogAI_PoC
```

### 2. Configure Environment Variables

Create a `.env` file in the project root with the following variables:

```
OPENAI_API_KEY=your_api_key_here
```

### 3. Run the Application

```bash
./gradlew run
```

## Configuration

The application is configured to connect to a local LLM server by default (e.g., Ollama). You can modify the following settings in `src/main/kotlin/Main.kt`:

- `modelRunnerBaseUrl`: URL of your local LLM server (default: `http://localhost:11434`)
- `modelName`: Name of the model to use (default: `llama3`)
- MCP server URL (default: `http://localhost:8080`)

## Project Structure

```
KoogAI_PoC/
├── src/
│   ├── main/
│   │   └── kotlin/
│   │       └── Main.kt         # Main application entry point
│   └── test/                   # Test files
├── build.gradle.kts            # Project build configuration
└── README.md                   # This file
```

## Dependencies

- Koog Agents: `ai.koog:koog-agents:0.4.1`
- Kotlinx Serialization: `org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3`
- Kotlin Coroutines: `org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0`

## Development

### Building the Project

```bash
./gradlew build
```

### Running Tests

```bash
./gradlew test
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- [Koog AI](https://koog.ai/) for the agents framework
- [OpenAI](https://openai.com/) for the API compatibility
- [Kotlin](https://kotlinlang.org/) for the awesome language
