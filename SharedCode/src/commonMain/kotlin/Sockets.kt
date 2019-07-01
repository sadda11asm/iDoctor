package org.kotlin.mpp.mobile

import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.ws
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText

class Sockets() {

    suspend fun startChat() {
        val client = HttpClient{
            install(WebSockets)
        }
        client.ws(
            method = HttpMethod.Get,
            host = "localhost",
            port = 5333
        ) { // this: DefaultClientWebSocketSession

            // Send text frame.
//            send("Hello, Text frame")

            // Send text frame.
            send(Frame.Text("Hello World"))

            // Send binary frame.
//            send(Frame.Binary(...))

            // Receive frame.
            val frame = incoming.receive()
            when (frame) {
                is Frame.Text -> println(frame.readText())
//                is Frame.Binary -> println(frame.readBytes())
            }
        }
    }




}