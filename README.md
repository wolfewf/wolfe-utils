# wolfe-utils
Java Common Tools</br>
整理一些开发的常用工具类，目前有date,json,file工具类，后续将不断更新...</br>
注：</br>
1、GsonUtil和FastJsonUtil两个类对于转换json中的非String字段类型有不同的处理，如json：{"price":23}，使用GsonUtil中相应的方法转换会将"price"的value转换成23.0,使用FastJsonUtil中相应的方法转换会将会将"price"的value转换成23</br>
2、FileUtil使用的是apache的commons的FileUtils类，主要是对于FileUtils类的一些基本操作，详细的API请查看：http://commons.apache.org/proper/commons-io/javadocs/api-2.4/index.html


