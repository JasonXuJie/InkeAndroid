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
var bg = (function (_super) {
    __extends(bg, _super);
    function bg() {
        var _this = _super.call(this) || this;
        _this.addEventListener(egret.Event.ADDED_TO_STAGE, _this.onAddToStage, _this);
        return _this;
    }
    bg.prototype.onAddToStage = function () {
        this.removeEventListener(egret.Event.ADDED_TO_STAGE, this.onAddToStage, this);
        this.stageW = this.stage.stageWidth;
        this.stageH = this.stage.stageHeight;
        var bg = new egret.Shape();
        bg.graphics.beginFill(0x001605);
        bg.graphics.drawRect(0, 0, this.stageW, this.stageH);
        bg.graphics.endFill();
        this.addChild(bg);
    };
    return bg;
}(egret.DisplayObjectContainer));
__reflect(bg.prototype, "bg");
//# sourceMappingURL=bg.js.map