#AndroidBase


 - 顶级父类BaseActivity，抽象的，基于FragmentActivity，新增了几个生命周期方法，抽象的，必须重载：
        init();
        findView();
        setView();
        setViewListener();
 - 在顶级父类BaseActivity中，规避了APP进入后台后重新返回时可能的奔溃问题
 - BaseActionBarActivity，继承自BaseActivity，也是抽象的，此类主要实现对ActionBar的统一定义，规定所有继承自此类的Activity的顶级布局为RelativeLayout，然后再一个RelativeLayout。
 - 顶级父类BaseFragment，抽象的，基于Fragment，新增了几个生命周期方法，抽象的，必须重载：
        init();
        findView();
        setView();
        setViewListener();
 顶级父类BaseFragment同时统一定义了获取顶级父类BaseActivity的方法：getBaseActivity()，此方法规避和处理在某些情况下所获取到的Activity可能为null的问题。
 - PromptUtils，实现对快速调用Toast的支持
 - LogUtils，实现对Log的统一调用和管理
 - 支持Layout动画
 - 快速调出Loading对话框
 - 带有粘性动画效果的弹窗
 - UIUtils，实现对px、dp转换、屏幕尺寸获取、Text宽高获取等的支持
