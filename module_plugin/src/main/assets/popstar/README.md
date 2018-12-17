# H5小游戏100例：消灭星星

「消灭星星」是一款很经典的「消除类游戏」，它的玩法很简单：消除相连通的同色砖块。

![demo](http://7xv39r.com1.z0.glb.clouddn.com/popstar.gif)

## 1. 游戏规则

「消灭星星」存在多个版本，不过它们的规则除了「关卡分值」有些出入外，其它的规则都是一样的。笔者介绍的版本的游戏规则整理如下：

**1. 色砖分布**

- 10 x 10 的表格
- 5种颜色 ------ 红、绿、蓝，黄，紫
- 每类色砖个数在指定区间内随机
- 5类色砖在 10 x 10 表格中随机分布

**2. 消除规则**

两个或两个以上同色砖块相连通即是可被消除的砖块。

**3. 分值规则**

- 消除总分值 = n * n * 5
- 奖励总分值 = 2000 - n * n * 20

「n」表示砖块数量。上面是「总」分值的规则，还有「单」个砖块的分值规则：

- 消除砖块得分值 = 10 * i + 5
- 剩余砖块扣分值 = 40 * i + 20 

「i」表示砖块的索引值（从 0 开始）。简单地说，单个砖块「得分值」和「扣分值」是一个等差数列。

**4. 关卡分值** 

关卡分值 = 1000 + (level - 1) * 2000；「level」即当前关卡数。

**5. 通关条件**

- 可消除色块不存在
- 累计分值 >= 当前关卡分值

上面两个条件同时成立游戏才可以通关。

## 2. MVC 设计模式

笔者这次又是使用了 MVC 模式来写「消灭星星」。星星「砖块」的数据结构与各种状态由 Model 实现，游戏的核心在 Model 中完成；View 映射 Model 的变化并做出对应的行为，它的任务主要是展示动画；用户与游戏的交互由 Control 完成。

从逻辑规划上看，Model 很重而View 与 Control 很轻，不过，从代码量上看，View 很重而 Model 与 Control 相对很轻。


## 3. Model 

10 x 10 的表格用长度为 100 的数组可完美映射游戏的星星「砖块」。

```javascript
[
	R, R, G, G, B, B, Y, Y, P, P, 
	R, R, G, G, B, B, Y, Y, P, P, 
	R, R, G, G, B, B, Y, Y, P, P, 
	R, R, G, G, B, B, Y, Y, P, P, 
	R, R, G, G, B, B, Y, Y, P, P, 
	R, R, G, G, B, B, Y, Y, P, P, 
	R, R, G, G, B, B, Y, Y, P, P, 
	R, R, G, G, B, B, Y, Y, P, P, 
	R, R, G, G, B, B, Y, Y, P, P, 
	R, R, G, G, B, B, Y, Y, P, P
]
```
R - 红色，G - 绿色，B - 蓝色，Y - 黄色，P - 紫色。Model 的核心任务是以下四个：

- 生成砖墙
- 消除砖块 （生成砖块分值）
- 夯实砖墙 
- 清除残砖 （生成奖励分值）

### 3.1 生成砖墙

砖墙分两步生成：
- 色砖数量分配
- 打散色砖

理论上，可以将 100 个格子可以均分到 5 类颜色，不过笔者玩过的「消灭星星」都不使用均分策略。通过分析几款「消灭星星」，其实可以发现一个规律 ------ 「**色砖之间的数量差在一个固定的区间内**」。

如果把传统意义上的均分称作「完全均分」，那么「消灭星星」的分配是一种在均分线上下波动的「不完全均分」。

![正余弦波线图](http://7xv39r.com1.z0.glb.clouddn.com/2017-12-06-waveAverage.gif)

笔者把上面的「不完全均分」称作「波动均分」，算法的具体实现可以参见「[波动均分算法](https://aotu.io/notes/2018/01/11/waveaverage/)」。

「打散色砖」其实就是将数组乱序的过程，笔者推荐使用「 [费雪耶兹乱序算法](https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle)」。

以下是伪代码的实现：
```javascript
// 波动均分色砖
waveaverage(5, 4, 4).forEach(
	// tiles 即色墙数组
	(count, clr) => tiles.concat(generateTiles(count, clr)); 
); 
// 打散色砖
shuffle(tiles); 
```

### 3.2 消除砖块 

「消除砖块」的规则很简单 ------ **相邻相连通相同色即可以消除**。

![连通图](http://7xv39r.com1.z0.glb.clouddn.com/20180111-connection.png)
前两个组合符合「相邻相连通相同色即可以消除」，所以它们可以被消除；第三个组合虽然「相邻相同色」但是不「相连通」所以它不能被消除。

「消除砖块」的同时有一个重要的任务：生成砖块对应的分值。在「游戏规则」中，笔者已经提供了对应的数学公式：「消除砖块得分值 = 10 * i + 5」。

**「消除砖块」算法实现如下：**
```javascript
function clean(tile) { 
	let count = 1; 
	let sameTiles = searchSameTiles(tile); 
	if(sameTiles.length > 0) { 
		deleteTile(tile); 
		while(true) {
			let nextSameTiles = []; 
			sameTiles.forEach(tile => { 
				nextSameTiles.push(...searchSameTiles(tile)); 
				makeScore(++count * 10 + 5); // 标记当前分值 
				deleteTile(tile); // 删除砖块
			}); 
			// 清除完成，跳出循环
			if(nextSameTiles.length === 0) break; 
			else {
				sameTiles = nextSameTiles; 
			}
		}
	}
}
```
清除的算法使用「递归」逻辑上会清晰一些，不过「递归」在浏览器上容易「栈溢出」，所以笔者没有使用「递归」实现。

### 3.3 夯实砖墙

砖墙在消除了部分砖块后，会出现空洞，此时需要对墙体进行夯实：

| ![向下夯实](http://7xv39r.com1.z0.glb.clouddn.com/20180112-down.gif) | ![向左夯实](http://7xv39r.com1.z0.glb.clouddn.com/20180112-left.gif) | ![先下再左夯实](http://7xv39r.com1.z0.glb.clouddn.com/20180112-down-left.gif) | 
| :----: | :----: | :----: |
| **向下夯实** | **向左夯实** | **向左下夯实**（先下后左） |

一种快速的实现方案是，每次「消除砖块」后直接遍历砖墙数组（10x10数组）再把空洞夯实，伪代码表示如下：

```javascript
for(let row = 0; row < 10; ++row) {
	for(let col = 0; col < 10; ++col) { 
		if(isEmpty(row, col)) {
			// 水平方向（向左）夯实
			if(isEmptyCol(col)) { 
				tampRow(col); 
			}
			// 垂直方向（向下）夯实
			else {
				tampCol(col); 
			}
			break; 
		}
	}
}
```

But... 为了夯实一个空洞对一张大数组进行全量遍历并不是一种高效的算法。在笔者看来影响「墙体夯实」效率的因素有：

1. 定位空洞
2. 砖块移动（夯实）

扫描墙体数组的主要目的是「定位空洞」，但能否不扫描墙体数组直接「定位空洞」？

墙体的「空洞」是由于「消除砖块」造成的，换种说法 ------ **被消除的砖块留下来的坑位就是墙体的空洞**。在「消除砖块」的同时标记空洞的位置，这样就无须全量扫描墙体数组，伪代码如下：

```javascript
function deleteTile(tile) { 
	// 标记空洞
	markHollow(tile.index); 
	// 删除砖块逻辑
	...
}
```

在上面的夯实动图，其实可以看到它的夯实过程如下：

1. 空洞上方的砖块向下移动
2. 空列右侧的砖块向左移动

墙体在「夯实」过程中，它的边界是实时在变化，如果「夯实」不按真实边界进行扫描，会产生多余的空白扫描：

![对比](http://7xv39r.com1.z0.glb.clouddn.com/20180116-clean.gif)

**如何记录墙体的边界？**  
把墙体拆分成一个个单独的列，那么列最顶部的空白格片段就是墙体的「空白」，而其余非顶部的空白格片段即墙体的「空洞」。

![列](http://7xv39r.com1.z0.glb.clouddn.com/20180117-col.gif)

笔者使用一组「列集合」来描述墙体的边界并记录墙体的空洞，它的模型如下：

```javascript
/*
	@ count - 列砖块数
	@ start - 顶部行索引
	@ end - 底部行索引
	@ pitCount - 坑数
	@ topPit - 最顶部的坑
	@ bottomPit - 最底部的坑
*/ 
let wall = [
	{count, start, end, pitCount, topPit, bottomPit}, 
	{count, start, end, pitCount, topPit, bottomPit},
	...
]; 
```
这个模型可以描述墙体的三个细节：

- 空列
- 列的连续空洞
- 列的非连续空洞

```javascript
// 空列
if(count === 0) { 
	...
}
// 连续空洞
else if(bottomPit - topPit + 1 === pitCount) { 
	...
}
// 非连续空洞
else {
	...
}
```

砖块在消除后，映射到单个列上的空洞会有两种分布形态 ------ 连续与非连续。  

![连续空格与非连续空格](http://7xv39r.com1.z0.glb.clouddn.com/20180117-col2.gif)

「连续空洞」与「非连续空洞」的夯实过程如下：

![空洞压缩](http://7xv39r.com1.z0.glb.clouddn.com/20180117-tamp.gif)

其实「空列」放大于墙体上，也会有「空洞」类似的分布形态 ------ 连续与非连续。
![空洞压缩](http://7xv39r.com1.z0.glb.clouddn.com/20180117-col3.gif)

它的夯实过程与空洞类似，这里就不赘述了。

### 4.4 消除残砖

上一小节提到了「描述墙体的边界并记录墙体的空洞」的「列集合」，笔者是直接使用这个「列集合」来消除残砖的，伪代码如下：

```javascript
function clearAll() {
	let count = 0; 
	for(let col = 0, len = this.wall.length;  col < len; ++col) { 
		let colInfo = this.wall[col]; 
		for(let row = colInfo.start; row <= colInfo.end; ++row) {
			let tile = this.grid[row * this.col + col]; 
			tile.score = -20 - 40 * count++; // 标记奖励分数
			tile.removed = true; 
		}
	}
}
```

## 4. View

View 主要的功能有两个：

- UI 管理
- 映射 Model 的变化（动画）

UI 管理主要是指「界面绘制」与「资源加载管理」，这两项功能比较常见本文就直接略过了。View 的重头戏是「映射 Model 的变化」并完成对应的动画。动画是复杂的，而映射的原理是简单的，如下伪代码：

```javascript
update({originIndex, index, clr, removed, score}) { 
	// 还没有 originIndex 或没有色值，直接不处理
	if(originIndex === undefined || clr === undefined) return ; 
	let tile = this.tiles[originIndex]; 
	// tile 存在，判断颜色是否一样
	if(tile.clr !== clr) {
		this.updateTileClr(tile, clr); 
	}
	// 当前索引变化 ----- 表示位置也有变化 
	if(tile.index !== index) { 
		this.updateTileIndex(tile, index); 
	}
	// 设置分数
	if(tile.score !== score) {
		tile.score = score; 
	}

	if(tile.removed !== removed) { 
		// 移除或添加当前节点
		true === removed ? this.bomb(tile) : this.area.addChild(tile.sprite); 
		tile.removed = removed; 
	}
}
```

Model 的砖块每次数据的更改都会通知到 View 的砖块，View 会根据对应的变化做对应的动作（动画）。

## 5. Control

Control 要处理的事务比较多，如下：

- 绑定 Model & View 
- 生成通关分值
- 判断通关条件
- 对外事件
- 用户交互


初始化时，Control 把 Model 的砖块单向绑定到 View 的砖块了。如下：

```javascript
Object.defineProperties(model.tile, 
	{
		originIndex: {
			get: () => {...}, 
			set: () => {
				...
				view.update({originIndex});
			}
		},  
		index: {
			get: () => {...}, 
			set: () => {
				...
				view.update({index});
			}
		}, 
		clr: {
			get: () => {...}, 
			set: () => {
				...
				view.update({clr});
			}
		}, 
		removed: {
			get: () => {...}, 
			set: () => {
				...
				view.update({removed});
			}
		},  
		score: {
			get: () => {...}, 
			set: () => {
				...
				view.update({score});
			}
		}, 
	}
); 
``` 

「通关分值」与「判断通关条件」这对逻辑在本文的「游戏规则」中有相关介绍，这里不再赘述。

对外事件规划如下：

| name | detail | 
| :-- | :-- |
| pass | 通关 |
| pause | 暂停 |
| resume | 恢复 |
| gameover | 游戏结束 |

用户交互 APIs 规划如下：

| name | type | deltail |
| :-- | :-- | :-- |
| init | method | 初始化游戏 |
| next | method | 进入下一关 |
| enter | method | 进入指定关卡 |
| pause | method | 暂停 |
| resume | method | 恢复 | 
| destroy | method | 销毁游戏 |

## 6. 问题

在知乎有一个关于「消灭星星」的话题：[popstar关卡是如何设计的？](https://www.zhihu.com/question/22530789)

这个话题在最后提出了一个问题 ------ **「无法消除和最大得分不满足过关条件的矩阵」**。

![两个问题](https://pic1.zhimg.com/50/98d59c105bc5331d0596e20ec2e2e554_hd.jpg)

「无法消除的矩阵」其实就是最大得分为0的矩阵，本质上是「最大得分不满足过关条件的矩阵」。

**最大得分不满足过关条件的矩阵**  
求「矩阵」的最大得分是一个 「[背包问题](https://baike.baidu.com/item/%E8%83%8C%E5%8C%85%E9%97%AE%E9%A2%98)」，求解的算法不难：对当前矩阵用「递归」的形式把所有的消灭分支都执行一次，并取最高分值。但是  javascript 的「递归」极易「栈溢出」导致算法无法执行。

其实在知乎的话题中提到一个解决方案：

> 网上查到有程序提出做个工具随机生成关卡，自动计算，把符合得分条件的关卡筛选出来

这个解决方案代价是昂贵的！笔者提供有源码并没有解决这个问题，而是用一个比较取巧的方法：**进入游戏前检查是事为「无法消除矩阵」，如果是重新生成关卡矩阵**。

注意：笔者使用的取巧方案并没有解决问题。


## 7. 结语

下面是本文介绍的「消灭星星」的线上 DEMO 的二维码：

![二维码](http://7xv39r.com1.z0.glb.clouddn.com/qr.jpg)

游戏的源码托管在：[https://github.com/leeenx/popstar](https://github.com/leeenx/popstar)

感谢耐心阅读完本文章的读者。本文仅代表笔者的个人观点，如有不妥之处请不吝赐教。
如果对「H5游戏开发」感兴趣，欢迎关注我们的[专栏](https://zhuanlan.zhihu.com/snsgame)。 

## 参考资料

- [Knapsack problem](https://en.wikipedia.org/wiki/Knapsack_problem)
- [NP-completeness](https://en.wikipedia.org/wiki/NP-completeness)
- [popstar关卡是如何设计的？](https://www.zhihu.com/question/22530789)
- [费雪耶兹乱序算法](https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle)
- [波动均分算法](https://aotu.io/notes/2018/01/11/waveaverage/)
