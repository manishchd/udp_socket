package com.manish.udpconnectionchat

import android.util.Log
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class EchoServer(val port : Int) : Thread() {

    private val socket: DatagramSocket = DatagramSocket(port)

    private var running = false
    private val buf = ByteArray(256)

    override fun run() {
        running = true
        while (running) {
            var packet = DatagramPacket(buf, buf.size, InetAddress.getByName("localhost"),port)
            try {
                socket.receive(packet)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
            val address = packet.address
            val port = packet.port
            packet = DatagramPacket(buf, buf.size, address, port)
            val received = String(packet.data, 0, packet.length)
            if (received == "end") {
                running = false
                continue
            }
            Log.e("EchoServer", received)
            try {
                socket.send(packet)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
        socket.close()
    }
}