# Tcupoon
JavaFX练手项目

由于某牛默认不支持https外链(需要对自定义域名备案)，故我将博客的图床从某牛搬到了腾讯云，
而官方的对象存储管理客户端不能自动复制外链到剪切板，所以就自己开发了这个工具，顺便学习一下JvaFX

## 软件截图
- 主界面    
![main](https://blogfiles-1254091060.cos.ap-shanghai.myqcloud.com/Github/Tcupoon/20180521/WHKERbwLqck5.png)    
- 关于界面    
![about](https://blogfiles-1254091060.cos.ap-shanghai.myqcloud.com/Github/Tcupoon/20180521/JniGA9yDTiO2.png)    
- 设置界面    
![config](https://blogfiles-1254091060.cos.ap-shanghai.myqcloud.com/Github/Tcupoon/20180521/jiw56PrwQDtL.png)    
- 上传成功    
![uploadSuccess](https://blogfiles-1254091060.cos.ap-shanghai.myqcloud.com/Github/Tcupoon/20180521/7BR8yEJGW85d.png)
## 功能
腾讯云对象存储-COS文件上传工具，可通过拖拽的形式快速上传文件到COS上指定目录，
并自动复制https外链或者MarkDown格式图片URL到剪切板。   
 
**注意**：
不支持拖拽文件夹上传    
不支持同时拖拽多个文件上传(选择多个拖拽只会上传最后一个文件)    
### 已开发功能
- 自定义上传位置
- 拖拽上传    
上传至"指定目录/当前年月日/"目录下并将文件重命名为长度12位的随机字符串
- 设置是否自动复制外链到剪切板
- 上传成功后可直接在软件界面点击访问外链（腾讯云就这点不太好，点击外链会弹出下载窗口，不能直接在浏览器中预览）

### 待开发
- 配置信息加密存储
- **帮助**窗口
- 软件图标制作