package almostlover.com.viewcollection.socket

import almostlover.com.viewcollection.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_socket_test.*
import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.net.UnknownHostException


class SocketTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket_test)
        btn.setOnClickListener {
            doNext()
        }
    }

    private fun doNext() {

        object : Thread() {
            override fun run() {
                super.run()
                try { //1.创建监听指定服务器地址以及指定服务器监听的端口号
                    val socket =
                        Socket("118.186.13.146", 12306) //111.111.11.11为我这个本机的IP地址，端口号为12306.
                    //2.拿到客户端的socket对象的输出流发送给服务器数据
                    val os: OutputStream = socket.getOutputStream()
                    //写入要发送给服务器的数据
                    val toByte = et.text.toString().toByteArray()
                    os.write(toByte)
                    os.flush()
                    socket.shutdownOutput()
                    //拿到socket的输入流，这里存储的是服务器返回的数据
                    val `is`: InputStream = socket.getInputStream()
                    //解析服务器返回的数据
                    val reader = InputStreamReader(`is`)
                    val bufReader = BufferedReader(reader)
                    var s: String? = null
                    val sb = StringBuffer()
                    while (bufReader.readLine().also({ s = it }) != null) {
                        sb.append(s)
                    }
                    runOnUiThread { tv.setText(sb.toString()) }
                    //3、关闭IO资源（注：实际开发中需要放到finally中）
                    bufReader.close()
                    reader.close()
                    `is`.close()
                    os.close()
                    socket.close()
                } catch (e: UnknownHostException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
}
