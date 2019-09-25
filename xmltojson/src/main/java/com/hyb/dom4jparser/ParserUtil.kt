package com.hyb.dom4jparser

import android.text.TextUtils
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import org.dom4j.Document
import org.dom4j.DocumentHelper
import org.dom4j.Element
import org.dom4j.io.SAXReader
import java.io.File


/**
 * 类描述：解析工具类
 * 创建人：huangyaobin
 * 创建时间：2019/9/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class ParserUtil {

    companion object {

        private val saxReader = SAXReader()

        /**
         * xml字符串转json
         */
        fun xmlToJson(xml: String): String {
            var document = DocumentHelper.parseText(xml)
            return parser(document)
        }

        /**
         * xml文件转json
         */
        fun xmlToJson(xmlFile: File): String {
            return parser(saxReader.read(xmlFile))
        }

        private fun parser(document: Document): String {
            var json = JSONObject()
            treeWalk(document.rootElement, json)
            var resultJson = JSONObject()
            resultJson.put(document.rootElement.name, json)
            return resultJson.toString()
        }

        /**
         * 递归遍历节点
         */
        private fun treeWalk(element: Element, json: JSONObject) {
            //遍历属性跟内容
//            var contentJson = getAttrJson(element)

            var elementList = element.elements()
            //递归
            if (elementList != null && elementList.size != 0) {//有子元素

                //先添加当前层父元素的属性内容等
                var attrJson = getAttrJson(element)
                json.putAll(attrJson)

                //遍历子元素
                for (elementChild in elementList) {
                    //传入一个变量去下层组装
                    var childJson = JSONObject()
                    treeWalk(elementChild, childJson)

                    var obj = json[elementChild.name]
                    when (obj) {
                        null -> //没有这个key的数据
                            json[elementChild.name] = childJson

                        is JSONObject -> {//已经有一个这个key的数据
                            var tempJson = obj as JSONObject
                            json.remove(elementChild.name)
                            var array = JSONArray()
                            array.add(tempJson)
                            array.add(childJson)
                            json[elementChild.name] = array

                        }

                        is JSONArray -> //一个以上这个key的数据
                            (json[elementChild.name] as JSONArray).add(childJson)
                    }

                }
            } else {//没子元素
                var childJson = getAttrJson(element)
                json.putAll(childJson)
            }


        }


        /**
         * 获取属性以及内容
         */
        private fun getAttrJson(element: Element): JSONObject {
            //添加节点属性
            var attributesList = element.attributes()
            var json = JSONObject()
            for (attribute in attributesList) {
                json[attribute.name] = attribute.value
            }

            //添加节点内容
            if (!TextUtils.isEmpty(element.textTrim)) {
                json["#text"] = element.text
            }
            return json
        }


    }

}
