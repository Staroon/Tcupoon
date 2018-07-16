# Tcupoon
JavaFX练手项目

由于某牛默认不支持https外链(需要对自定义域名备案)，故我将博客的图床从某牛搬到了腾讯云，
而官方的对象存储管理客户端不能自动复制外链到剪切板，所以就自己开发了这个工具，顺便学习一下JvaFX。

## 软件截图
- 主界面    
![main](https://raw.githubusercontent.com/Staroon/Tcupoon/master/imgs/main-page.png)    
- 关于界面    
![about](https://raw.githubusercontent.com/Staroon/Tcupoon/master/imgs/about-page.png)    
- 设置界面    
![config](https://raw.githubusercontent.com/Staroon/Tcupoon/master/imgs/config-page.png)    
- 上传成功    
![uploadSuccess](https://raw.githubusercontent.com/Staroon/Tcupoon/master/imgs/upload-success.png)
- 上传历史
![uploadHistory](https://raw.githubusercontent.com/Staroon/Tcupoon/master/imgs/history-page.png)    

## 功能
腾讯云对象存储-COS文件上传工具，可通过拖拽的形式快速上传文件到COS上指定目录，
并自动复制https外链或者MarkDown格式图片URL到剪切板。   
 
**注意**：    
- 不支持拖拽文件夹上传；    
- 不支持同时拖拽多个文件上传(选择多个拖拽只会上传最后一个文件)。    

### 已开发功能
- 自定义上传位置
- 拖拽上传    
上传至"指定目录/当前年月日/"目录下并将文件重命名为长度12位的随机字符串
- 设置是否自动复制外链到剪切板
- 上传成功后可直接在软件界面点击访问外链（点击外链会弹出下载窗口，不能直接在浏览器中预览；如果开启了防盗链设置的话下载窗口也不会有）
- 记录、查看上传历史
