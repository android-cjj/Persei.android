#Persei
==========================


 说明
----------------
Yalantis公司出品的开源库，动画这块都做的非常漂亮，不信，你可以去看看[Yalantis](https://github.com/Yalantis),我看到了其中的一个库[Persei](https://github.com/Yalantis/Persei),可惜用Swift写的，并没有android的。你可以去dribble网站看看设计原图[Top Menu Animation](https://dribbble.com/shots/1706861-Top-Menu-Animation?list=users&offset=23)

![](https://github.com/Yalantis/Persei/raw/master/Assets/animation.gif)

没错，以上就是设计图，现在要开始编码了...
我们心中需要得到的是这个结果

![](http://img.55bbs.com/10_500x2000/974/li5c2kC78GdkQ.jpg)

事实是我越写越成了这个

![](http://www.ename.cn/data/article/201412/03/1417588595.jpg)

本来想放弃了，后来想想，既然做不到原设计图的效果，至少也要让它变成这样的

![](http://img4.duitang.com/uploads/item/201403/04/20140304122431_XMCuj.thumb.600_0.jpeg)

最后，它就是这个样子的

![](http://www.apkbus.com/data/attachment/forum/201509/25/144242qv69abhu6n9vav66.gif)

![](http://www.apkbus.com/data/attachment/forum/201509/24/141035ugk917wf899f4gi8.gif)


以上只是随意说说，看看就好....呵呵

用法
----------------------------------------
Add dependency.

```
dependencies {
  compile 'com.cjj.persei:library:1.0.0'
}
```


在文件xml中添加
```xml
<com.cjj.PerseiLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/persei"
    app:menu_height="200"
    >
   <ListView or ScrollView or RecyclerView or GridView ...>

</com.cjj.PerseiLayout>
```

在代码中实例化并设置头部View
```java
 PerseiLayout perseiLayout = (PerseiLayout) this.findViewById(R.id.persei);
 perseiLayout.setHeaderView(...);
 
 perseiLayout.closeMenu();//关闭菜单
 ```
 
 
Thanks
=======
 [Yalantis/Persei](https://github.com/Yalantis/Persei)
 
广告
-----------------------------
刚建了个[github小伙伴交流群](http://t.cn/RyiVjnv)(477826523),有兴趣的朋友可以加进来的...小小要求：github有至少7个关注者或者开源库有7颗星星的... 

 
License
=======

    The MIT License (MIT)

	Copyright (c) 2015 android-cjj

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.





