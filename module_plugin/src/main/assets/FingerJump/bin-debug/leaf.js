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
var leaf = (function (_super) {
    __extends(leaf, _super);
    function leaf() {
        var _this = _super.call(this) || this;
        _this.addEventListener(egret.Event.ADDED_TO_STAGE, _this.onAddToStage, _this);
        return _this;
    }
    leaf.prototype.onAddToStage = function () {
        this.removeEventListener(egret.Event.ADDED_TO_STAGE, this.onAddToStage, this);
        this.stageW = this.stage.stageWidth;
        this.stageH = this.stage.stageHeight;
        this.leftLeaf1 = new egret.Bitmap(RES.getRes("leaf_left_png"));
        this.leftLeaf2 = new egret.Bitmap(RES.getRes("leaf_left_png"));
        this.rightLeaf1 = new egret.Bitmap(RES.getRes("leaf_right_png"));
        this.rightLeaf2 = new egret.Bitmap(RES.getRes("leaf_right_png"));
        this.addChild(this.leftLeaf1);
        this.leftLeaf1.x = 0;
        this.leftLeaf1.y = this.stageH - this.leftLeaf1.height;
        this.addChild(this.leftLeaf2);
        this.leftLeaf2.x = 0;
        this.leftLeaf2.y = this.leftLeaf1.y - this.leftLeaf2.height;
        this.addChild(this.rightLeaf1);
        this.rightLeaf1.x = this.stageW - this.rightLeaf1.width;
        this.rightLeaf1.y = this.stageH - this.rightLeaf1.height;
        this.addChild(this.rightLeaf2);
        this.rightLeaf2.x = this.stageW - this.rightLeaf2.width;
        this.rightLeaf2.y = this.rightLeaf1.y - this.rightLeaf2.height;
    };
    leaf.prototype.move = function () {
        this.leafMove(this.leftLeaf1);
        this.leafMove(this.leftLeaf2);
        this.leafMove(this.rightLeaf1);
        this.leafMove(this.rightLeaf2);
    };
    leaf.prototype.leafMove = function (leaf) {
        egret.Tween.get(leaf).to({ y: leaf.y + 50 }, 200).call(this.leafMoveOver, this, [leaf]);
    };
    leaf.prototype.leafMoveOver = function (leaf) {
        egret.Tween.removeTweens(leaf);
        if (leaf.y >= this.stageH) {
            leaf.y -= leaf.height * 2;
        }
    };
    return leaf;
}(egret.Sprite));
__reflect(leaf.prototype, "leaf");
//# sourceMappingURL=leaf.js.map