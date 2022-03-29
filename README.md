# AndroidOpenCV

### 前言 

android进行图像处理，需要导入OpenCV作为依赖。简单记录流程

### 导入整体流程

#### 下载opencv

https://opencv.org/releases/ 选择android系统平台下载

https://github.com/opencv/opencv/releases 最好选择在github下载

#### 解压导入sample 工程

解压打开有一个示例 sample 工程

直接导入AS打开

build工程出现错误

##### 第一个错误 NDK 无法找到

SDK管理里面下载NDK 

NDK版本选择 没有明说，我自己测试了21可以

local.properties 写入路径

```properties
sdk.dir=C\:\\Users\\huruwo\\AppData\\Local\\Android\\Sdk
ndk.dir = C:\\Users\\huruwo\\AppData\\Local\\Android\\Sdk\\ndk\\21.1.6352462
```
#### 第二个错误 Invalid revision: 3.18.1-g262b901-dirty

这是cmake的错误

进入sdk管理里面去掉这个3.18版本的cmake就行

也就是降低使用的cmake版本

#### 运行

这个没啥说的，运行即可 核心在于手机的摄像头权限

#### sample工程内容

核心的lib

- opencv 这个可以移植到自己的项目去


都基于摄像头的数据图像采集


- camera-calibration       相机校准
- color-blob-detection     颜色斑点检测
- face-detection           人脸检测
- image-manipulations      图像处理
- tutorial-1-camerapreview   相机预览视图
- tutorial-2-mixedprocessing 相机图像混合加工  
- tutorial-3-cameracontrol   相机控制

- 15-puzzle 摄像头然后15块拼图的游戏 

### 新建工程导入OpenCV

这个非常简单 新建AndoridOpenCV工程

点击 import moulde

选择sample里面的SDK模块

导入

#### 修复错误 Plugin with id 'kotlin-android' not found.

导入kotlin代码

```gradle
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath "com.android.tools.build:gradle:4.1.1"
        classpath 'org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
```
新增引用

```
implementation project(path: ':sdk')
```

这样就能运行起来了。

### 总结

无
