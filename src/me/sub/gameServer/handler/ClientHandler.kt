package me.sub.gameServer.handler

import me.sub.gameServer.GameServer
import me.sub.gameServer.packet.Packet
import me.sub.gameServer.packet.PacketType
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class ClientHandler(var socket: Socket) : Runnable{
    @Volatile
    var connected: Boolean = true
    var reader: BufferedReader = BufferedReader(InputStreamReader(socket.getInputStream()))
    private var writer: PrintWriter = PrintWriter(socket.getOutputStream(), true)
    lateinit var name : String

    fun sendPacket(packet: Packet){
        writer.println(Packet.writePacket(packet))
    }

    override fun run() {
        while(connected){
            val received = reader.readLine()
            var packet = Packet.parseData(received)
            Main.server.onReceivePacket(packet, this)
        }
        Thread.currentThread().interrupt()
    }


}