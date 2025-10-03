# 一、Debian 编译安装

1. git clone git@github.com:git/git.git
1. cd git
1. sudo apt install libcurl4-openssl-dev zlib1g-dev libssl-dev
1. make clean
1. make NO_TCLTK=1 NO_GETTEXT=1 NO_DOC=1 prefix=/home/ivfzhou/programs/git
1. make NO_TCLTK=1 NO_GETTEXT=1 NO_DOC=1 prefix=/home/ivfzhou/programs/git install

# 二、设置 Git 访问远程仓库账密

1. 开启账密存储：`git config --global credential.helper store`。
2. 用户家目录下文件 `.git-credentials` 添加 `https://$user:token@host.com`。`.netrc` 添加 `machine github.com login USERNAME password APIKEY`。

# 三、常用命令

## 1. 公钥密钥

- `ssh-keygen -t rsa -C <email>`：生成 rsa 密钥对。
- `ssh -T git@github.com`：测试与 github 链接。

## 2. 配置

- `git --version`：打印版本号。
- `git config --global user.name <user>`：全局配置在家目录下 .gitconfig，项目配置在 .git/config。
- `git config --global user.email <email>`
- `git config --global init.defaultBranch <name>`：设置初始化仓库默认分支名。
- `git config --global http.sslVerify false`
- `git config -l`：查看配置信息。
- `git config --global https.proxy https://proxyuser:password@proxyserver:port`
- `git config --global http.proxy http://proxyuser:password@proxyserver:port`：配置网络代理。

### 2.1 配置 ssh 网络代理

- sudo apt install connect-proxy
- vim .ssh/config
- 添加：
    ```config
    Host github.com
    User git
    ProxyCommand connect-proxy -S 127.0.0.1:1089 %h %p
    ```

## 3. 初始化仓库

- `git init <dir>`：初始化仓库。
- `git clone -o <origin> -b <branch> <dir>`：克隆远程仓库。

## 4. 添加与提交

- `git add <file>`：将一个文件添加到暂存区。
- `git add .`：将所有修改和新增文件添加到暂存区。
- `git add -f <file>`：将一个忽略的文件添加到暂存区。
- `git add -u .`：将已追踪文件的修改添加到暂存区。
- `git checkout <filename>`：暂存区覆盖工作区。
- `git checkout .`：暂存区覆盖工作区。
- `git rm --cached -rf <filename>`：删除暂存区文件。
- `git status -s`：显示文件是否新添加暂存区，是否有修改，是否未追踪。
- `git commit -m <注释>`：工作区提交到仓库。
- `git commit -am <注释>`：将工作区所有修改的追踪文件提交暂存区和本地仓库。
- `git commit --amend -m <注释>`：重新上次提交。
- `git reset <filename>`：本地仓库覆盖暂存区。
- `git reset --hard HEAD^`：回退上一个版本。`^^` 或者 `~2`。本地仓库覆盖暂存区和工作区。
- `git reset --hard <commit_id>`：回退到这个版本覆盖三区。
- `git reset --soft <commit_id>`：仓库版本重置。
- `git checkout <origin/branch> .`：工作区应用远程分支修改量。

## 5. 远程分支

- `git remote -v`：查看远程仓库。
- `git remote add <origin> <url>`：添加远程仓库地址。
- `git remote set-url <origin> <newurl>`：设置远程仓库地址。
- `git remote rm <origin>`：删除远程仓库。
- `git remote rename <origin> <newname>`：修改远程仓库名称。
- `git fetch --all`：获取远程仓库数据信息。

## 6. 标签

- `git tag --sort=-creatordate -l -n <pattern>`：列出 tag。
- `git tag -d <tag>`：删除 tag。

## 7. 分支

- `git checkout <branchname>`：切换分支。
- `git checkout -b <branchname>`：创建新分支并切换。
- `git branch -v`：查看分支。
- `git branch <branchname>`：创建分支。
- `git branch -d <branchname>`：删除分支。
- `git pull <origin> <branch>`：将远程仓库上的变动拉取到本地库并且合并。
- `git push -u <origin> <branchname>`：提交分支到远程仓库，并关联分支 
- `git push <origin> --tags`：将本地所有 tag 推送给远端仓库。
- `git push <origin> <tagname>`：将本地的 tag 推送给远程仓库。
- `git push <origin> --all`：所有分支推送到远程仓库。
- `git merge <branchname>`：当前分支合并选定分支保留分支信息。

## 8. 日志

- `git reflog`：查看操作记录。
- `git log --pretty=oneline`：查看提交记录。
- `git log --graph --pretty=oneline --abbrev=commit`：查看提交记录。
- `git log --oneline`：查看提交记录。

## 9. 暂存

- `git stash`：隐藏当前工作区的修改。
- `git stash pop`：将一个隐藏版本恢复并删除。
- `git stash apply`：将一个隐藏版本恢复。
- `git stash list`：查看所有隐藏的版本。

## 10. 比较

- `git diff <file>`：暂存区与工作区的不同。
- `git diff --cached <file>`：仓库与暂存区的不同。
- `git diff <commitId> <file>`：提交记录与工作区的不同。
- `git diff <a> <b> <file>`：提交记录 a 与提交记录 b 的不同。
- `git diff <branch> <branch> <file>`

## 11. 查看操作提交

- `git revert <commitId>`：回滚到这个提交记录。
- `git ls-tree <commitId>`：查看提交记录下的文件。
- `git cat-file <id>`：查看文件内容。
- `git cherry-pick <commitId>`： 应用某个提交记录的改动。
- `git rebase <branch>`： 当前分支与 branch 分支变基。
- `git blame <file>`： 显示最后修改人。
- `git ls-remote --heads <remote>`：查看远程分支。
- `git ls-remote --tags <remote>`：查看远程标签。
- `git rev-parse HEAD`：获取 HEAD 的提交 HASH。

## 12. 其他

- `git rev-list`
- `git archive`
- `git show-ref`
- `git show-index`
- `git fsck`
- `git prune`
- `git gc`
- `git describe`
- `git name-rev`
- `git check-ref-format`
- `git format-patch`
- `git am`
- `git commit-tree`
