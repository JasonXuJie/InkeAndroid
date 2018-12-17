var __reflect = (this && this.__reflect) || function (p, c, t) {
    p.__class__ = c, t ? t.push(c) : t = [c], p.__types__ = p.__types__ ? t.concat(p.__types__) : t;
};
var __extends = this && this.__extends || function __extends(t, e) { 
 function r() { 
 this.constructor = t;
}
for (var i in e) e.hasOwnProperty(i) && (t[i] = e[i]);
r.prototype = e.prototype, t.prototype = new r();
};
var StoneMap = (function (_super) {
    __extends(StoneMap, _super);
    function StoneMap() {
        var _this = _super.call(this) || this;
        _this.canPassStoneDis = [];
        _this.notPassStoneDis = [];
        _this.canPassStone = [];
        _this.notPassStone = [];
        _this.moveStone = [];
        _this.addEventListener(egret.Event.ADDED_TO_STAGE, _this.onAddToStage, _this);
        return _this;
    }
    StoneMap.prototype.onAddToStage = function () {
        this.removeEventListener(egret.Event.ADDED_TO_STAGE, this.onAddToStage, this);
        this.stageW = this.stage.stageWidth;
        this.stageH = this.stage.stageHeight;
        this.createMap();
    };
    StoneMap.prototype.getCanPassStone = function () {
        return this.canPassStone;
    };
    StoneMap.prototype.getCanPassStoneDis = function () {
        return this.canPassStoneDis;
    };
    StoneMap.prototype.AddCanPassStoneDis = function () {
        var i = Math.round(Math.random() * 1);
        this.canPassStoneDis.push(i);
    };
    StoneMap.prototype.AddNotPassStoneDis = function () {
        var i = Math.round(Math.random() * 10);
        var j = 0;
        if (i <= 5) {
            j = 0;
        }
        if (i > 5 && i <= 7) {
            j = 1;
        }
        if (i > 7 && i <= 9) {
            j = 2;
        }
        if (i > 9 && i <= 10) {
            j = 3;
        }
        this.notPassStoneDis.push(j);
    };
    StoneMap.prototype.createMap = function () {
        var stoneNum = 10;
        for (var i = 0; i < stoneNum; i++) {
            this.addStoneAndDis();
        }
    };
    StoneMap.prototype.mapMove = function () {
        this.removeStoneAndDis();
        //this.addStoneAndDis();
        var stoneW = this.canPassStone[0].width;
        var stoneH = this.canPassStone[0].height;
        var dis = this.canPassStoneDis[0] ? -1 : 1;
        egret.Tween.get(this).to({ x: this.x + dis * (stoneW / 2), y: this.y + (stoneH - 26) }, 200).call(function () {
            //this.removeStoneAndDis();
            this.addStoneAndDis();
        });
    };
    StoneMap.prototype.addStoneAndDis = function () {
        var passStone = StonePool.getObjectPool(0);
        this.addChildAt(passStone, 0);
        if (this.canPassStone[0] == null) {
            passStone.x = this.stageW - passStone.width >> 1;
            passStone.y = this.stageH - (200 + passStone.height);
            this.canPassStoneDis.push(-1);
        }
        else {
            this.AddCanPassStoneDis();
            var dis = this.canPassStoneDis[this.canPassStoneDis.length - 1] ? 1 : -1;
            passStone.x = this.canPassStone[this.canPassStone.length - 1].x + dis * (passStone.width / 2);
            passStone.y = this.canPassStone[this.canPassStone.length - 1].y - (passStone.height - 26);
        }
        this.canPassStone.push(passStone);
        if (this.canPassStoneDis.length >= 2) {
            this.AddNotPassStoneDis();
            for (var j = 1; j <= this.notPassStoneDis[this.notPassStoneDis.length - 1]; j++) {
                var e = StonePool.getObjectPool(Math.floor(Math.random() * 4 + 1)); //new Stone(Math.floor(Math.random() * 4 + 1));
                this.addChildAt(e, 0);
                var dist = this.canPassStoneDis[this.canPassStoneDis.length - 1] ? -1 : 1;
                e.x = this.canPassStone[this.canPassStone.length - 2].x + dist * (passStone.width / 2) * (j);
                e.y = this.canPassStone[this.canPassStone.length - 2].y - (passStone.height - 26) * (j);
                this.notPassStone.push(e);
            }
        }
    };
    StoneMap.prototype.removeStoneAndDis = function () {
        var stone = this.canPassStone[0];
        var stoneY = stone.y;
        this.canPassStone.splice(0, 1);
        this.canPassStoneDis.splice(0, 1);
        this.notPassStoneDis.splice(0, 1);
        stone.stoneDown();
        for (var i = 0; i < this.notPassStone.length; i++) {
            if (this.notPassStone[i].y >= stoneY) {
                var s = this.notPassStone[i];
                this.notPassStone.splice(i, 1);
                s.stoneDown();
            }
        }
    };
    return StoneMap;
}(egret.DisplayObjectContainer));
__reflect(StoneMap.prototype, "StoneMap");
//# sourceMappingURL=StoneMap.js.map