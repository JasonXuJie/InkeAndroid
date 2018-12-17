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
var Stone = (function (_super) {
    __extends(Stone, _super);
    function Stone(bmpNum) {
        var _this = _super.call(this) || this;
        _this.spSheet = new egret.SpriteSheet(RES.getRes("spritestairs_png"));
        _this.createBmp(bmpNum);
        return _this;
    }
    Stone.prototype.createBmp = function (bmpNum) {
        var stoneTex;
        var otherTex;
        stoneTex = this.spSheet.createTexture("stone", 0, 0, 150, 126);
        if (bmpNum == 1) {
            otherTex = this.spSheet.createTexture("wood", 0, 126, 170, 180);
        }
        if (bmpNum == 2) {
            otherTex = this.spSheet.createTexture("bomb", 170, 126, 170, 180);
        }
        if (bmpNum == 3) {
            otherTex = this.spSheet.createTexture("ice", 340, 126, 170, 180);
        }
        if (bmpNum == 4) {
            otherTex = this.spSheet.createTexture("mushroom", 510, 126, 170, 180);
        }
        this.stoneBmp = new egret.Bitmap(stoneTex);
        this.addChild(this.stoneBmp);
        this.name = "0";
        if (otherTex) {
            this.otherBmp = new egret.Bitmap(otherTex);
            this.addChild(this.otherBmp);
            this.anchorOffsetX = 0;
            this.anchorOffsetY = this.stoneBmp.y;
            this.otherBmp.y -= 60;
            this.name = bmpNum.toString();
        }
    };
    Stone.prototype.stoneDown = function () {
        egret.Tween.get(this).to({ y: this.y + 300 }, 300).call(function () {
            if (this.parent) {
                StonePool.addObjectPool(this);
                this.parent.removeChild(this);
            }
        }, this);
    };
    return Stone;
}(egret.Sprite));
__reflect(Stone.prototype, "Stone");
//# sourceMappingURL=Stone.js.map