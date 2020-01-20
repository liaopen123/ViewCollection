package almostlover.com.viewcollection.socket

import almostlover.com.viewcollection.MainActivity
import almostlover.com.viewcollection.R
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_socket_server.*
import java.io.*
import java.net.ServerSocket
import java.net.Socket


class SocketServerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket_server)

        btn.setOnClickListener {
            startActivity(Intent(this@SocketServerActivity,SocketTestActivity::class.java))

        }

        try {
            val serverSocket = ServerSocket(12306)
            println("服务端监听开始了...")
            val socket: Socket = serverSocket.accept()
            val inputStream: InputStream = socket.getInputStream()
            //解析数据
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var s: String? = null
            val stringBuffer = StringBuffer()
            while (bufferedReader.readLine().also({ s = it }) != null) {
                stringBuffer.append(s)
            }
            println("服务器拿到数据:$stringBuffer")
            //关闭输入流
            socket.shutdownInput()
            val outputStream: OutputStream = socket.getOutputStream()
            outputStream.write("客服端发送数据:$stringBuffer".toByteArray())
            outputStream.flush()
            socket.shutdownOutput()
            outputStream.close()
            bufferedReader.close()
            inputStream.close()
            inputStreamReader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
