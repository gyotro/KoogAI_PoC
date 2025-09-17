package ai.example

import ai.koog.agents.core.agent.AIAgent
import ai.koog.agents.mcp.McpToolRegistryProvider
import ai.koog.prompt.executor.clients.openai.OpenAIClientSettings
import ai.koog.prompt.executor.clients.openai.OpenAILLMClient
import ai.koog.prompt.executor.llms.SingleLLMPromptExecutor
import ai.koog.prompt.executor.llms.all.simpleOpenAIExecutor
import ai.koog.prompt.executor.ollama.client.OllamaClient
import ai.koog.prompt.llm.*
import kotlinx.coroutines.runBlocking


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(): kotlin.Unit = runBlocking{
    println("🌤️ Weather Assistant (type 'exit' to quit)")

    // 1. Set up local model runner (Ollama or whatever local model exposing OpenAI-compatible API)
    val modelRunnerBaseUrl = "http://localhost:12434/engines/v1"// adjust to your local model's API base
    val modelName = "llama3.1:latest"  // or the model you pulled / use

/*    val llmClient = OpenAILLMClient(
        settings = OpenAIClientSettings(baseUrl = modelRunnerBaseUrl),
        apiKey = "no"  // may be ignored or required depending model runner
    )*/

    val llmClient = OllamaClient()

    val llmModel = LLModel(
        provider = LLMProvider.Ollama,
        id = modelName,
        capabilities = listOf(LLMCapability.Completion, LLMCapability.Tools),
        contextLength = 8192,  // LLaMA 3 standard context length
        maxOutputTokens = 4096  // Reasonable limit for responses
    )
/*    val executor = simpleOpenAIExecutor(
        client = llmClient, // or maybe just apiKey if using remote OpenAI
        model = llmModel
    )*/
    val executor = SingleLLMPromptExecutor(
        llmClient = llmClient,
    )


    // 2. Set up MCP tool registry to your getWeather MCP server
    // using SSE transport (if MCP is a web service)
    val transport = McpToolRegistryProvider.defaultSseTransport("http://localhost:8085/sse")  // adjust to your MCP URL
    val toolRegistry = McpToolRegistryProvider.fromTransport(
        transport = transport,
        name = "weather-client",
        version = "1.0.0"
    )

    // 3. Create the agent combining model + tool registry
    val agent = AIAgent(
        executor = executor,
        systemPrompt = "You are a helpful assistant. Use your weather tool when needed.",
        llmModel = llmModel,
        toolRegistry = toolRegistry,
        temperature = 0.2
    )

    // 4. Console chat loop
    println("🌤️ Weather Assistant – type ‘exit’ to quit")

//    val result = agent.run("What's the weather like in Via Arrigo Benedetti Roma")
//    println("🤖 $result")

    while (true) {
        print("> ")
        val input = readlnOrNull()?.trim()
        if (input == null || input.equals("exit", ignoreCase = true)) {
            println("Bye!")
            break
        }

        val result = agent.run(input)
        println("🤖 $result")
    }
}
