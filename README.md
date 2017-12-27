# java-base-demo
some examples to show java base knowledge

# Guide  
* [Java Type Framework](#Java Type Framework)  
* [URI&URL](#uri&url)  


## Java Type Framework
***

Now,the project is for java type framework,include
ParameterizedType,GenericArrayType,TypeVariable,Class,WildcardType.

***

ParameterizedType:see#org.reflect.bootstrap.ParameterizedTypeTest;

GenericArrayType:see#org.reflect.bootstrap.GenericArrayTypeTest;

TypeVariable:see#org.reflect.bootstrap.TypeVariableTest,TypeVariableGetBoundsTest,
GenericDeclarationTest*;

Class:see#org.reflect.bootstrap.ClassTest;

WildcardType:see#org.reflect.bootstrap.WildcardTypeTest,ExtendsSuperTest.


## URI&URL   
the URI and URL Test case in the *src/test/java/cn.home.uri*.   
the difference between URI and URL, please refer to  [url-uri][] and [URL、URN、URI区别][] .

[url-uri]: https://danielmiessler.com/study/url-uri/ "url-uri"  
[URL、URN、URI区别]:https://www.cnblogs.com/52php/p/5677645.html "URL、URN、URI区别"

simplely as follows:  
URI(Uniform Resource Identifier) include URL(Uniform Resource Locators ) or URN(Uniform Resource Names) , or both;  
URL and URN is URI , conversely, not.
