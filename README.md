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

![](http://www.apkbus.com/data/attachment/forum/201509/24/141035ugk917wf899f4gi8.gif)

![](http://www.apkbus.com/data/attachment/forum/201509/24/112741wmju2p3nenjnjg8d.png)

以上只是随意说说，看看就好....呵呵

用法
----------------------------------------
在文件xml中添加
```xml
<com.cjj.PerseiLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/persei"
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
 
 




