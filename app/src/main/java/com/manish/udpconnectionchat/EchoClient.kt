package com.manish.udpconnectionchat

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class EchoClient(val port : Int) {

    private val socket: DatagramSocket = DatagramSocket()
    private val address: InetAddress = InetAddress.getByName("localhost")

    private lateinit var buf: ByteArray

    fun sendEcho(msg: String): String {
        buf = msg.toByteArray()
        var packet = DatagramPacket(buf, buf.size, address, port)
        socket.send(packet)
        packet = DatagramPacket(buf, buf.size)
        socket.receive(packet)
        return String(packet.data, 0, packet.length)
    }

    fun close() {
        socket.close()
    }
}