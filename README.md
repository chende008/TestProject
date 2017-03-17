# TestProject
SimpleProject

一个精简的 Android 项目基础框架 
### 功能模块
- BaseActivity 的封装
```
（1）findViewById的转换与简化：基类中定义 --> T view = (T) findViewById(id)
    可达到下面效果：
     tv_test = (TextView) findViewById(R.id.tv_test);
 --> tv_test = $(R.id.tv_test);
```
```
（2）标准 TitleBar 的展示与设置的事件：
    按需引入布局文件 titlebar 在子类的 Activity 中只面要通过调用 backWithTitle("标题") 
    设置标栏标题并添加默认的点击返回上一页面的事件。
```
```
（3）通过定义抽象类设置抽象方法方式，保证子类的 Activity 的数据初始化与 View 的 inflate 保持统一模式 
    public abstract void initViewAfterOnCreate();
    public abstract void initDataAfterOnCreate();
```
- BaseFragment 的封装
```
(1) 利用Fragment 的 onCreate 的调用在 onCreateView 的调用之前的特性，提交传入子 Fragment 的布局文件，
    并结合findViewById 的精简方法，实现在子Fragment 中分离 findViewById 与 onCreateView方法，
    最终实现子 Fragment 的类结构保持与 Activity 的结构近乎一致的模式。使代码可读性更强。
```
- BaseAdapter 的封装
```
(1) 在基类中实现 BaseAdapter 的 基础方法，只保留 getView 方法给子类实现，使用子类的结构更简单
(2) 重写 ViewHolder 精简 ViewHolder 的 findViewById
```
- BaseRecycleAdapter 的封装
```
(1) 在基类中实现 BaseRecycleAdapter 的 getHolder fillData 方法即可。
(2) 重写 BaseViewHolder 精简 ViewHolder 的 findViewById
```
- MultiRecycleAdapter 的封装 + SwipeRefreshLayout
```
(1) 在基类中实现 MultiRecycleAdapter 的 getHolder fillData 方法即可。
(2) 重写 BaseViewHolder 精简 ViewHolder 的 findViewById
(3) 实现下拉刷新，滚动到底部自动加载
(4) EmptyView 的实现，在不同网络或数据状态情分别显示不同的 View
(5) 在 RecyclerView 在GridLayoutManager布局结构上添加 HeaderView 的正常展示处理
![app](images/swipe_scroll.gif)
```
