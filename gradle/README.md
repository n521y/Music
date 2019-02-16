# 歌单详情页面
##实现思路 在viewholder中给组件设置监听器然后执行相应方法其他类不变
##涉及到的类
   -adapter-ListAdapter
   -app-ScrollShapeUIApplication  注：ScrollShapeUIApplication为自定义的application，要记得在manifest.xml文件中的application节点填加
   -ui-NeteasePlaylistActivity
   -util-CommonUtils
        -CustomChangeBounds
        -StatusBarUtil
        -StatusBarView
   -view-MyNestedScrollView

   -layout-activity_movie_detail.xml
          -header_slide_shape.xml
          -header_slide_shape.xml

   以上类可以直接使用不需要改变
 -
### 涉及到的点：
 - 1、Activity页面跳转共享元素曲线动画
 - 2、Glide加载固定图片大小，解决切换页面时图片闪烁问题；加载状态监听等
 - 3、Toolbar背景图显示布局高斯模糊图底部的一部分
 - 4、滑动渐变Toolbar背景图
 - 5、NestedScrollView在Api23下的滑动兼容
 
### 实现思路：
- 1、Activity设置自定义Shared Element切换动画
- 2、透明状态栏（透明Toolbar,使背景图上移）
- 3、Toolbar底部增加和背景一样的高斯模糊图，并上移图片（为了使背景图的底部作为Toolbar的背景）
- 4、上下滑动，通过NestedScrollView拿到移动的高度，同时调整Toolbar的背景图透明度

