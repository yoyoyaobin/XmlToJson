package com.hyb.dom4jparser

import java.io.File

/**
 * 类描述：xml解析器
 * 创建人：huangyaobin
 * 创建时间：2019/9/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class XmlParser private constructor(){

    /**
     * 双重校验锁单例
     */
    companion object{
        val instance:XmlParser by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            XmlParser()
        }
    }

    /**
     * xml转json字符串
     */
    fun xmlToJsonString(xmlFile:File):String{
        return ParserUtil.xmlToJson(xmlFile)
    }

    /**
     * xml转json字符串
     */
    fun xmlToJsonString(xml:String):String{
        return ParserUtil.xmlToJson(xml)
    }

//    /**
//     * json转xml字符串
//     */
//    fun jsonToXmlString(json:String):String{
//        return String()
//    }

}