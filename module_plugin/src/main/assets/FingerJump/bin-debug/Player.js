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
var Player = (function (_super) {
    __extends(Player, _super);
    function Player() {
        var _this = _super.call(this) || this;
        _this.getPlayerAnimation();
        return _this;
    }
    Player.prototype.getPlayerAnimation = function () {
        var mcDataFactory = new egret.MovieClipDataFactory(RES.getRes("robotJump_json"), RES.getRes("robotJump_png"));
        var mc = new egret.MovieClip(mcDataFactory.generateMovieClipData("robotJump"));
        mc.anchorOffsetX = mc.width / 2;
        mc.anchorOffsetY = mc.height;
        this.playerWalkAnimation = mc;
        this.addChild(this.playerWalkAnimation);
    };
    //0:往左跳，1：往右跳
    Player.prototype.playerJump = function (direction) {
        var playerJump = this.playerWalkAnimation;
        var lastDirection;
        if (lastDirection == direction) {
            playerJump.play(2);
        }
        else {
            if (direction == 0) {
                playerJump.scaleX = 1;
            }
            else {
                playerJump.scaleX = -1;
            }
            playerJump.play(2);
        }
        lastDirection = direction;
    };
    //0:面向左，1：面向右
    Player.prototype.playerDirection = function (direction) {
        if (direction == 0) {
            this.playerWalkAnimation.scaleX = 1;
        }
        if (direction == 1) {
            this.playerWalkAnimation.scaleX = -1;
        }
    };
    // 0:跳错 1:时间到
    Player.prototype.playerDied = function (diedNum) {
        if (diedNum == 0) {
            this.playerWalkAnimation.addEventListener(egret.Event.COMPLETE, this.playOver, this);
        }
        if (diedNum == 1) {
            this.playOver();
        }
    };
    Player.prototype.playOver = function () {
        this.playerWalkAnimation.removeEventListener(egret.Event.COMPLETE, this.playOver, this);
        egret.Tween.get(this).to({ y: this.y + 200 }, 350).call(function () {
            this.parent.removeChild(this);
        });
    };
    return Player;
}(egret.Sprite));
__reflect(Player.prototype, "Player");
//# sourceMappingURL=Player.js.map