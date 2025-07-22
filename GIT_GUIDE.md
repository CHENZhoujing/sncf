# Git 使用指南 - SNCF 项目

## 基本配置

### 全局配置（已配置）
```bash
git config --global user.name "chen_zhoujing"
git config --global user.email "zhoujingchen1@gmail.com"
```

## 方式一：克隆仓库

如果你要从远程仓库开始：

```bash
git clone git@github.com:CHENZhoujing/sncf.git
cd sncf
touch README.md
git add README.md
git commit -m "add README"
git push -u origin main
```

## 方式二：已有文件夹或仓库

如果你已经有了本地项目（当前情况）：

```bash
cd /Users/czj/projets/sncf
git init
git remote add origin git@github.com:CHENZhoujing/sncf.git
git add .
git commit -m "Initial commit"
git push -u origin main
```

## 日常开发流程

### 1. 查看状态
```bash
git status
```

### 2. 添加文件
```bash
# 添加单个文件
git add <文件名>
# 添加所有修改
git add .
# 添加所有Java文件
git add *.java
```

### 3. 提交更改
```bash
git commit -m "描述你的更改"
```

### 4. 推送到远程
```bash
git push
```

### 5. 拉取远程更新
```bash
git pull
```

## 分支管理

### 创建并切换到新分支
```bash
git checkout -b feature/新功能名称
```

### 查看所有分支
```bash
git branch -a
```

### 切换分支
```bash
git checkout main
git checkout feature/新功能名称
```

### 合并分支
```bash
git checkout main
git merge feature/新功能名称
```

### 删除分支
```bash
# 删除本地分支
git branch -d feature/新功能名称
# 删除远程分支
git push origin --delete feature/新功能名称
```

## 常用命令

### 查看提交历史
```bash
git log
git log --oneline
```

### 查看文件差异
```bash
git diff
git diff <文件名>
```

### 撤销更改
```bash
# 撤销工作区的更改
git checkout -- <文件名>
# 撤销暂存区的更改
git reset HEAD <文件名>
# 撤销最后一次提交
git reset --soft HEAD~1
```

### 暂存更改
```bash
# 暂存当前更改
git stash
# 查看暂存列表
git stash list
# 恢复暂存的更改
git stash pop
```

## 项目特定信息

- **项目名称**: SNCF
- **远程仓库**: git@github.com:CHENZhoujing/sncf.git
- **主分支**: main
- **项目类型**: Maven Spring Boot 项目
- **开发者**: 陈周京 (zhoujingchen1@gmail.com)

## 注意事项

1. 提交前请确保代码能够正常编译
2. 提交信息要清晰描述更改内容
3. 定期推送到远程仓库备份代码
4. 使用分支进行功能开发，避免直接在main分支上开发
5. 合并前请先拉取最新的远程更改

## 快速开始

当前项目状态：已初始化，文件已暂存，准备首次提交

执行以下命令完成初始设置：

```bash
git commit -m "Initial commit"
git remote add origin git@github.com:CHENZhoujing/sncf.git
git push -u origin main
```

之后就可以正常使用git管理你的SNCF项目了！