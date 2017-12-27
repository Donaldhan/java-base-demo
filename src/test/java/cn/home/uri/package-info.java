/**
 * 看开源框架经常看到会用到URL或者URI之类的，为什么要熟悉这个呢?
 * 
 * 比如说你写一个网络程序，需要访问资源，资源位置信息最好封装起来，如果使用String表示，虽然可以使用，
 * 但是感觉挺low的。你用了URI或者URL，
 * 当你想获取这个资源位置信息的某个属性时怎么着也比String方便吧。
 * 
 * 
 * 1、URI与URL的定义 URI：统一资源标志符（Uniform Resource Identifier） URL：统一资源定位符（uniform
 * resource location）
 * 说白了，URI与URL都是定位资源位置的，就是表示这个资源的位置信息，就像经纬度一样可以表示你在世界的哪个角落。
 * URI是一种宽泛的含义更广的定义，
 * 而URL则是URI的一个子集，就是说URL是URI的一部分。
 * 换句话说，每个URL都是URI，但是不是每个URI都是URL的。他们之间最明显的不同就是在java.net.URI你只能看到他的一些属性，
 * 他只是表示一个位置，但是你没有办法通过URI获取到这个对象的流，但是URL就不同了。java.net.URL该类提供方法（openConnection（）
 * ），通过该方法我们可以通过IO流操作他。但是URI中我貌似没看到相关的方法。
 * 也就是说：URL是可以直接操作的，但是URI并不行。（如果你发现这句话不对，麻烦告诉我下）
 * 
 * 2、URL的语法 URL与URI很像，两者的格式几乎差不多，但是我们接触的还是URL比较多，就以URL为例说明
 * URL提供了一种访问定位因特网上任意资源的手段，但是这些资源可以通过不同的方法（例如HTTP、FTP、SMTP）来访问，不管怎样，
 * 他都基本上由9个部分构成： <scheme>://<user>:<password>@<host>:<port>/<path>;<params>?<query>#<fragment> 
 * scheme：获取资源使用的协议，例如http、ftp等，没有默认值
 * user:password：用户名与密码，这个是一个特殊的存在，一般访问ftp时会用到，他显示的表明了访问资源的用户名与密码。
 * 但是这个可以不写，
 * 不写的话可能会让你输入用户名密码 host：主机，访问那台主机，有时候可以是IP，有时候是主机名，例如www.baidu.com
 * port：端口，访问主机时的端口，如果http访问默认80，可以省略。
 * path：通过host:port我们能找到主机，但是主机上文件很多，通过path则可以定位具体文件。
 * 例如www.baidu.com/file/index.
 * html。则path是/file/index.html，表示我们访问/file/index.html这个文件，他很像linux上的路径。
 * params：这个很少见，主要作用就是像服务器提供额外的参数，用来表示本次请求的一些特性。
 * 例如ftp传输模式有两种，二进制和文本，
 * 你肯定不希望使用文本形式传输二进制图片，这样你的图片下载下来后可能没法看了。
 * 为了向应用程序提供更丰富的信息，URL中有个专门的部分来表示这种参数。
 * 例如ftp://file.donald.com/pub/guid.pdf;type=d其中的type=d就是params
 * query：通过get方式请求的参数，例如：www.donald.com/index.html?username=donald&passwd=123456
 * fragment：例如www.qiandu.com/index.html#1。当html页面比较长时，我们通常会将其分为好几段，#1就可以
 * 快速定位到某一段。
 * 
 * @author donald 2017年12月27日 下午9:04:11
 */
package cn.home.uri;