package com.hyb.xmltojson

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alibaba.fastjson.JSON
import com.hyb.dom4jparser.XmlParser
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var jsonStr = XmlParser.instance.xmlToJsonString("<?xml version=\"1.0\" encoding=\"UTF-8\"?><PARAM><DBID type=\"attr\" name=\"hey\">35</DBID><MAXEVALUE>10</MAXEVALUE><MAXNS>10</MAXNS><MINIDENTITIES>90</MINIDENTITIES><PASSWORD>111111</PASSWORD><RETURN_TYPE>2</RETURN_TYPE><SEQUENCE>atgtca</SEQUENCE><TYPE>P</TYPE><USERNAME>admin</USERNAME></PARAM>")
        try {
            var jsonObject = JSON.parseObject(jsonStr)
            Toast.makeText(this , "转换成功" , Toast.LENGTH_LONG).show()
        }catch (e:Exception){
            Toast.makeText(this , "转换失败" , Toast.LENGTH_LONG).show()
        }

    }
}
