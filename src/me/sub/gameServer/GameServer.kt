package me.sub.gameServer

import me.sub.gameServer.handler.ClientHandler
import me.sub.gameServer.packet.Packet
import me.sub.gameServer.packet.PacketType
import java.net.ServerSocket
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class GameServer {

    lateinit var threadPool: Executor
    lateinit var clients: ArrayList<ClientHandler>

    fun run(port: Int) {
        threadPool = Executors.newWorkStealingPool()
        clients = ArrayList<ClientHandler>()
        val server = ServerSocket(port)
        while(true){
            val socket = server.accept()
            val clientHandler = ClientHandler(socket)
            clients.add(clientHandler)
            threadPool.execute(clientHandler)
        }
    }

    fun onReceivePacket(packet: Packet, clientHandler: ClientHandler){
        when(packet.dataType){
            0 -> {
                clientHandler.name = packet.data[0]
                println("${packet.data[0]} Just Connected! [$clientHandler]")
                broadcast(Packet(PacketType.ENTITY_INIT.id, "PLAYER"), clientHandler)
            }
            1 -> {
                println("${clientHandler.name} Just Disconnected :(")
                clientHandler.connected = false;
            }
        }
        println("${clientHandler.name} Sent a Packet! ${PacketType.values().get(packet.dataType)}")
    }

    fun broadcast(packet: Packet, clientHandler: ClientHandler){
        for (handler in clients){
            if(handler != clientHandler){
                handler.sendPacket(packet)
            }
        }
    }


}