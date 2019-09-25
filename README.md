# XmlToJson
快速简便通用的xml转json工具模块，使用dom4j(sax解析)+fastjson处理数据

![image](https://jitpack.io/v/yoyoyaobin/XmlToJson.svg)

## 配置
1.在project的build.gradle里配置
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2.在app的build.gradle里配置
```
android {
  ...

  compileOptions {
      sourceCompatibility JavaVersion.VERSION_1_8
      targetCompatibility JavaVersion.VERSION_1_8
  }
}

dependencies {
  '''
  
  implementation 'com.github.yoyoyaobin:XmlToJson:1.0'
}

```

## 使用方式
```
kotlin:
var jsonStr = XmlParser.instance.xmlToJsonString(xmlString)

java:
String jsonStr = XmlParser.Companion.getInstance().xmlToJsonString(xmlString);
```

## 注意
1.因为为了通用性，所以需要分辨节点属性跟节点内容，属性的key为原本的Key值，而内容的key为#text,具体如下：
```
<a name="张三">节点内容</a>
```
转出来的json为
```
{
"a":{
   "name":"张三",
   "#text":"节点内容"
  }
}
```
2.如果节点名称有相同的，则会变成Array形式，只有单一一个则会变成object形式，具体如下：
```
<a name="张三"></a>
<a name="李四"></a>
<b name="王五"></b>
```
转出的json为
```
{
"a":[{
   "name":"张三"
  },{
   "name":"李四"
  }],
"b":{
   "name":"王五"
  }
}
```
